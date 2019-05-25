package udb.gl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udb.gl.*;
import udb.gl.exception.AppException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class StatistiquesConsultationsService {

    @Autowired
    StatistiquesConsultationsRepository statistiquesConsultationsRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    ConsultationRepository consultationRepository;

    @Autowired
    RendezVousRepository rendezVousRepository;

    private List<Patient> patientList;

    private List<Consultation> consultationList;

    private final Date todayDate = new Date();


    public int getNumberOfRendezVouToday(){
        return rendezVousRepository.countAllByDate(todayDate);
   }

   public int getNumberofConsultationsToday() {
       return consultationRepository.countAllByDate(todayDate);
   }

   public Map<String, Integer> getNumberOfConsultationsByGenderToday() {
        Map<String,Integer> numberOfConsultationByGender = new HashMap<>();
       consultationList = consultationRepository.findAllByDate(todayDate)
               .orElseThrow( () -> new AppException("Aucune Consultation n'existe pour cette date")
       );

       int masculinCount = 0;
       int femininCount = 0;

       for (Consultation consultation : consultationList){
           Patient patient = consultation.getPatient();
           if(patient.getSexe().equals("masculin")){
               masculinCount += 1;
           }else {
               femininCount += 1;
           }
       }

       numberOfConsultationByGender.put("masculin",masculinCount);
       numberOfConsultationByGender.put("feminin",femininCount);

       return numberOfConsultationByGender;
   }

   public Map<String,Integer> getNumberOfConsultationByAgeToday() {
        Map<String,Integer> numberOfConsultationByAge = new HashMap<>();
       int enfantCount = 0;
       int adulteCount = 0;
       consultationList = consultationRepository.findAllByDate(todayDate)
               .orElseThrow( () -> new AppException("Aucune Consultation n'existe pour cette date")
               );

       for (Consultation consultation : consultationList){
           Patient patient = consultation.getPatient();
           if(patient.getAge() <= 18){
               enfantCount += 1;
           }else {
               adulteCount += 1;
           }
       }

       numberOfConsultationByAge.put("enfant",enfantCount);
       numberOfConsultationByAge.put("adulte",adulteCount);

       return numberOfConsultationByAge;
   }

}
