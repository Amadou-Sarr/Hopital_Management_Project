package udb.gl.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import udb.gl.*;
import udb.gl.exception.AppException;
import udb.gl.payload.*;
import udb.gl.services.Utils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static jdk.nashorn.internal.objects.Global.print;


@RestController
@RequestMapping("/api/rendezvous")
@CrossOrigin
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SECRETAIRE') or hasRole('ROLE_MEDECIN')")
public class RendezVousController {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    RendezVousRepository rendezVousRepository;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DossierRepository dossierRepository;

    Utils utils = new Utils();

    @GetMapping("/all")
    public List<RendezVous> getAllRendezVous(){
        return  rendezVousRepository.findAll();
    }

    @PostMapping("/find/patient")
    public Patient findPatientByNumeroPatient(@RequestBody PatientSearchPayload patient){
        return patientRepository.findByNumeroPatient(patient.getNumeroPatient());
    }

    @PostMapping("/patientsByDate")
    public List<Patient> getPatientByDate(@RequestBody RendezVousAvailableHours rendezVousAvailableHours){
        List<RendezVous> rendezVousList = rendezVousRepository.findAllByDateAndAndUtilisateur(rendezVousAvailableHours.getDate(),rendezVousAvailableHours.getUtilisateur());
        List<Patient> patientList = new ArrayList<>();
        for(RendezVous rendezVous : rendezVousList){
            patientList.add(rendezVous.getPatient());
        }
        return patientList;
    }

    @GetMapping("/getMedecins")
    public List<Utilisateur> getAllMedecins(){
        Role role = roleRepository.findByName(RoleName.ROLE_MEDECIN).orElseThrow(
                () -> new AppException("Le role RoleMedecin n'existe pas")
        );
        return utilisateurRepository.findAllByRole(role);
    }

    @PostMapping("/searchByDate")
    public List<RendezVous> getAllByDateIntervalle(@RequestBody DateSearchPayload dateSearchPayload){

       //return rendezVousRepository.findAllByDateBetween()

        return null;
    }

    @PostMapping("/medecin/service")
    public List<Utilisateur> getMedecinByService(@RequestBody rendezRecherche rendezRecherche){
        Role role = roleRepository.findByName(RoleName.ROLE_MEDECIN).orElseThrow(
                () -> new AppException("Le role RoleMedecin n'existe pas")
        );
        List<Utilisateur> utilisateurList = utilisateurRepository.findAllByRole(role);
        List<Utilisateur> returnList = new ArrayList<>();
        for (Utilisateur  utilisateur :utilisateurList) {
            if(utilisateur.getService().name().equals(rendezRecherche.getService())){
                returnList.add(utilisateur);
            }
        }
        return returnList;
    }

    @PostMapping("/avalaibleHours")
    public List<String> getAvalaibleHoursByDate(@RequestBody RendezVousAvailableHours rendezVousAvailableHours){
        List<RendezVous> rendezVousList = rendezVousRepository.findAllByDateAndAndUtilisateur(rendezVousAvailableHours.getDate(),rendezVousAvailableHours.getUtilisateur());
        List<String> avalaibleHoursList = new ArrayList<>();
        for(RendezVous rendezVous : rendezVousList){
            avalaibleHoursList.add(rendezVous.getHeure());
        }
        return avalaibleHoursList;
    }


    @PostMapping("/patient/save")
    public ResponseEntity<ApiResponse> setPatientFromAngular(@RequestBody PatientPayload patientPayload){
        if(patientPayload == null){
            return new ResponseEntity(new ApiResponse(false, "Vous n'avez envoyé aucune donnée a enregistrer"), HttpStatus.BAD_REQUEST);
        }

        String numeroPatient = UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
        numeroPatient = numeroPatient.substring(0,6) + utils.sdf.format(new Date());

        patientPayload.setNumeroPatient(numeroPatient);

        Patient patient = new Patient();
        patient.setNumeroPatient(patientPayload.getNumeroPatient());
        patient.setNom(patientPayload.getNom());
        patient.setSexe(patientPayload.getSexe());
        patient.setAge(patientPayload.getAge());
        patient.setPrenom(patientPayload.getPrenom());
        patient.setAdresse(patientPayload.getAdresse());
        patient.setNumero_telephone(patientPayload.getNumero_telephone());

        Dossier dossier = new Dossier();
        dossier.setCommentaire("Dossier creer le : "+new Date().toString());

        patient.setDossier(dossier);
        dossier.setPatient(patient);

        try {
            patientRepository.save(patient);
            dossierRepository.save(dossier);
        }
        catch (Exception ex){
           print (ex.toString());
        }
        return  new ResponseEntity(new ApiResponse(true, "patient enregistrer avec succes"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveRendezVous(@Valid @RequestBody RendezvousPayload rendezvousPayload){
        if(rendezvousPayload == null ){
            return new ResponseEntity(new ApiResponse(false, "Vous n'avez envoyé aucune donnée aà enregistrer"), HttpStatus.BAD_REQUEST);
        }
        // Todo Create a ModelMapper for this below
        RendezVous rendezVous = new RendezVous();
        rendezVous.setDate(rendezvousPayload.getDate());
        rendezVous.setHeure(rendezvousPayload.getHeure());
        rendezVous.setEstConsulter(false);
        rendezVous.setPatient(rendezvousPayload.getPatient());
        rendezVous.setUtilisateur(rendezvousPayload.getUtilisateur());

        rendezVousRepository.save(rendezVous);
        return ResponseEntity.ok(new ApiResponse(true, "Les données ont été enregistrer avec succes!"));
    }
}
