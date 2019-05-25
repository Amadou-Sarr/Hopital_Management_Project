package udb.gl.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import udb.gl.Patient;
import udb.gl.PatientRepository;
import udb.gl.payload.ApiResponse;
import udb.gl.RendezVous;
import udb.gl.RendezVousRepository;
import udb.gl.payload.RendezvousPayload;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rendezvous")
@CrossOrigin
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SECRETAIRE') or hasRole('ROLE_USER')")
public class RendezVousController {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    RendezVousRepository rendezVousRepository;

    @GetMapping("/all")
    public List<RendezVous> getAllRendezVous(){
        return  rendezVousRepository.findAll();
    }

    @GetMapping("/patientsByDate/{date}")
    public List<Patient> getPatientByDate(@RequestParam Date date){
        List<RendezVous> rendezVousList = rendezVousRepository.findAllByDate(date);
        List<Patient> patientList = new ArrayList<>();
        for(RendezVous rendezVous : rendezVousList){
            patientList.add(rendezVous.getPatient());
        }
        return patientList;
    }

    // TODO check if i need to use PathVariable or RequestParam
    @GetMapping("/avalaibleHours")
    public List<String> getAvalaibleHoursByDate(@RequestParam Date date){
        List<RendezVous> rendezVousList = rendezVousRepository.findAllByDate(date);
        List<String> avalaibleHoursList = new ArrayList<>();
        for(RendezVous rendezVous : rendezVousList){
            avalaibleHoursList.add(rendezVous.getHeure());
        }
        return avalaibleHoursList;
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

        rendezVousRepository.save(rendezVous);
        return ResponseEntity.ok(new ApiResponse(true, "Les données ont été enregistrer avec succes!"));
    }
}
