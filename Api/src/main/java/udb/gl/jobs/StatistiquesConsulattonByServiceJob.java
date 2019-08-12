package udb.gl.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import udb.gl.StatistiquesConsultationsJournalieres;
import udb.gl.StatistiquesConsultationsRepository;
import udb.gl.services.StatistiquesConsultationsService;


@Component
public class StatistiquesConsulattonByServiceJob implements Job {

    @Autowired
    StatistiquesConsultationsService statistiquesConsultationsService;

    @Autowired
    StatistiquesConsultationsRepository statistiquesConsultationsRepository;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.debug("Execution du Job pour les Statistiques des Consultations par Jour.(Tous les Jours a 18h)");
        StatistiquesConsultationsJournalieres statistiquesConsultationsJournalieres = new StatistiquesConsultationsJournalieres();
        statistiquesConsultationsJournalieres.setNombreDeConsultationsTotaux(statistiquesConsultationsService.getNumberofConsultationsToday());
        statistiquesConsultationsJournalieres.setNombreDeConsultationAdulte(statistiquesConsultationsService.getNumberOfConsultationByAgeToday().get("adulte"));
        statistiquesConsultationsJournalieres.setNombreDeConsultationEnfant(statistiquesConsultationsService.getNumberOfConsultationByAgeToday().get("enfant"));
        statistiquesConsultationsJournalieres.setNombreDeConsultationFemme(statistiquesConsultationsService.getNumberOfConsultationByAgeToday().get("feminin"));
        statistiquesConsultationsJournalieres.setNombreDeConsultationHomme(statistiquesConsultationsService.getNumberOfConsultationsByGenderToday().get("masculin"));
    }



}
