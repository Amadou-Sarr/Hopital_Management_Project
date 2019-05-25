package udb.gl.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import udb.gl.*;
import udb.gl.payload.ApiResponse;
import udb.gl.payload.ConsultationPayload;
import udb.gl.services.PostRequestsService;
import udb.gl.services.SaveHeavyAntecedantsPayloadRequest;
import udb.gl.services.SaveHeavyMedicamentsPayloadRequest;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/consultation")
@CrossOrigin
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SECRETAIRE') or hasRole('MEDECIN')")
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
        //Todo use a mapper() for this and
        Consultation consultation = new Consultation(consultationPayload.getDate(),
                consultationPayload.getCommentaire(),consultationPayload.getPrescription());
        consultation.setPatient(consultationPayload.getPatient());
        consultation.setUtilisateur(consultationPayload.getUtilisateur());
        //TODO there are certainly controls to implements here; Find out later
        consultationRepository.save(consultation);
        return ResponseEntity.ok(new ApiResponse(true, "Les données ont été enregistrer avec succes! "));
    }

    // TODO Do this the right way AND replace all the Domain calles and use a mepper()
}
