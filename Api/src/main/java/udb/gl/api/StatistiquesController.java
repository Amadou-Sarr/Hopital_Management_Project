package udb.gl.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import udb.gl.StatistiquesConsultationsRepository;
import udb.gl.services.StatistiquesConsultationsService;


import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/statistiques")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CHEF_SERVICE')")
public class StatistiquesController {

    @Autowired
    StatistiquesConsultationsRepository statistiquesConsultationsRepository;

    @Autowired
    StatistiquesConsultationsService statistiquesConsultationsService;

    @GetMapping("/byage")
    public Map<String,Integer> getStatistiquesByAge(){
        Map<String, Integer> statistiquesByAgeMap = statistiquesConsultationsService.getNumberOfConsultationByAgeToday();
        return statistiquesByAgeMap;
    }

    @GetMapping("/bygender")
    public  Map<String, Integer> getStatistiquesByGender() {
        Map<String, Integer> statistiquesByGenderMap = statistiquesConsultationsService.getNumberOfConsultationsByGenderToday();
        return statistiquesByGenderMap;
    }

    @GetMapping("/byconsultation")
    public  int getStatistiquesByConsultation() {
        int statistiquesByConsultationMap = statistiquesConsultationsService.getNumberofConsultationsToday();
        return statistiquesByConsultationMap;
    }
}
