package udb.gl.jobs;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import udb.gl.services.StatistiquesConsultationsService;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Component
public class StatistiquesConsulattonByServiceJob implements Job {

    @Autowired
    StatistiquesConsultationsService statistiquesConsultationsService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.debug("Execution du Job pour les Statistiques des Consultations par Jour.(Tous les Jours a 18h)");

        statistiquesConsultationsService.getNumberofConsultationsToday();
    }



}
