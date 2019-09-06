package udb.gl.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import udb.gl.*;
import udb.gl.payload.ApiResponse;
import udb.gl.payload.ConsultationPayload;
import udb.gl.payload.DossierSearchPayload;
import udb.gl.payload.MedicamentConsultationPayload;
import udb.gl.services.PostRequestsService;
import udb.gl.services.SaveHeavyAntecedantsPayloadRequest;
import udb.gl.services.SaveHeavyMedicamentsPayloadRequest;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/consultation")
@CrossOrigin
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('MEDECIN')")
public class ConsultationController {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    ConsultationRepository consultationRepository;

    @Autowired
    DossierRepository dossierRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    AntecedantsRepository antecedantsRepository;

    @Autowired
    MedicamentRepository medicamentRepository;

    @Autowired
    MedicamentsConsultationRepository medicamentsConsultationRepository;

    @Autowired
    PostRequestsService postRequestsService;

    Logger logger = LoggerFactory.getLogger(getClass());



    public static List<Antecedants> antecedantsStaticList;

    public static List<Medicaments> medicamentsStaticList;



    @GetMapping("/antecedants/all")
    public List<Antecedants> getAllAntecedants(){
        return antecedantsRepository.findAll();
    }


    @GetMapping("/medicaments/all")
    public List<Medicaments> getAllMedicaments(){
        return medicamentRepository.findAll();
    }


    @GetMapping("/Patient/all")
    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }


    @GetMapping("/dossier/all")
    public List<Dossier> getAllDossiers() {
        return dossierRepository.findAll();
    }

    @GetMapping("/all")
    public List<Consultation> getAllConsultations(){
        return consultationRepository.findAll();
    }


    @PostMapping("/dossier/find")
    public Dossier getDosssier(@RequestBody DossierSearchPayload dossierSearchPayload){
        return dossierRepository.findByNumero(dossierSearchPayload.getNumeroDossier());
    }

    @PostMapping("/antecedants/save")
    public ResponseEntity<ApiResponse> saveAntecedants(@Valid @RequestBody List<Antecedants> antecedants){
       Thread asyncSaveAntecedantsThread = new Thread(new SaveHeavyAntecedantsPayloadRequest());
       postRequestsService.saveRequestInDB(antecedants,antecedantsStaticList,asyncSaveAntecedantsThread);
        for (Antecedants antecedant : antecedants) {
            antecedantsRepository.save(antecedant);
        }
        return ResponseEntity.ok(new ApiResponse(true, "Les données Antecedants ont été enregistrées avec succes"));
    }


    @PostMapping("/medicaments/save")
    public ResponseEntity<ApiResponse> saveMedicaments(@Valid @RequestBody List<Medicaments> medicaments){
       Thread asyncSaveMedicamentsThread = new Thread(new SaveHeavyMedicamentsPayloadRequest());
       postRequestsService.saveRequestInDB(medicaments,medicamentsStaticList,asyncSaveMedicamentsThread);
       for(Medicaments medicament : medicaments){
           medicamentRepository.save(medicament);
       }
        return ResponseEntity.ok(new ApiResponse(true, "Les données ont été enregistrées avec success!"));
    }

    @PostMapping("/patient/save")
    public ResponseEntity<ApiResponse> savePatient(@Valid @RequestBody List<Patient> patients){
        if(patients.isEmpty()){
            return new ResponseEntity(new ApiResponse(false,"Erreur Vous n'avez entrer aucune donnée à Enregistrer"),HttpStatus.BAD_REQUEST);
        }
        for(Patient patient : patients){
            patientRepository.save(patient);
        }
        return ResponseEntity.ok(new ApiResponse(true, "Les données ont été enregistrées avec success!"));
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveConsultation(@Valid @RequestBody ConsultationPayload consultationPayload){
        if(consultationPayload == null){
            return new ResponseEntity(new ApiResponse(false, "Vous n'avez enyoyé aucune donnée à enregistrer"), HttpStatus.BAD_REQUEST);
        }
        //Todo  complete

        Consultation consultation = new Consultation(consultationPayload.getDate(),
                consultationPayload.getCommentaire(),consultationPayload.getPrescription());
        consultation.setPatient(consultationPayload.getPatient());
        consultation.setUtilisateur(consultationPayload.getUtilisateur());
        
        consultation = consultationRepository.save(consultation);

        List<MedicamentConsultationPayload> medicamentConsultationPayloadList = consultationPayload.getListMedicament();

        if(medicamentConsultationPayloadList != null){
            for(MedicamentConsultationPayload medicamentConsultationPayload : medicamentConsultationPayloadList){
                MedicamentConsultation medicamentConsultation = new MedicamentConsultation();
                medicamentConsultation.setConsultation(consultation);
                medicamentConsultation.setDosage(medicamentConsultationPayload.getDosage());
                Medicaments medicament = medicamentRepository.findById(medicamentConsultationPayload.getMedicament());
                medicamentConsultation.setMedicaments(medicament);

                medicamentsConsultationRepository.save(medicamentConsultation);
            }
        }




        Dossier dossier = dossierRepository.findByNumero(consultationPayload.getNumeroDossier());
        dossier.setAntecedants(consultationPayload.getListAntecedants());
        dossierRepository.save(dossier);

        return ResponseEntity.ok(new ApiResponse(true, "Les données ont été enregistrer avec succes! "));
    }

    // TODO -- Do this the right way AND replace all the Domain calls by using a mapper() A.Sarr

    @PostMapping("/medicament/instance/save")
    public ResponseEntity<ApiResponse> saveUniqueMedicament(@RequestBody Medicaments medicament){

        if(medicament == null){
            return new ResponseEntity(new ApiResponse(false,"aucune donnée n'a été envoyé!!"), HttpStatus.BAD_REQUEST);
        }

        Medicaments medicamentPresent = medicamentRepository.findByLibelle(medicament.getLibelle());

        if(medicamentPresent == null){
            medicamentRepository.save(medicament);
            return ResponseEntity.ok(new ApiResponse(true,"données enregistrés avec succes!"));
        }

        return new ResponseEntity(new ApiResponse(false,"Cet Anecedant est deja present dans la base de données!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/antecedant/instance/save")
    public ResponseEntity<ApiResponse> saveUniqueAntecedant(@RequestBody Antecedants antecedant){

        if(antecedant == null){
            return new ResponseEntity(new ApiResponse(false,"aucune donnée n'a été envoyé!!"), HttpStatus.BAD_REQUEST);
        }

        Antecedants antecedantPresent = antecedantsRepository.findByAntecedant(antecedant.getAntecedant());

        if(antecedantPresent == null){
            antecedantsRepository.save(antecedant);
            return  ResponseEntity.ok(new ApiResponse(true, "Données enregistrés avec succes !"));
        }

        return new ResponseEntity(new ApiResponse(false,"Cet Anecedant est deja present dans la base de données!"), HttpStatus.BAD_REQUEST);
        }
}
