package udb.gl.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import udb.gl.Antecedants;
import udb.gl.AntecedantsRepository;
import udb.gl.MedicamentRepository;

import java.util.List;

import static udb.gl.api.ConsultationController.antecedantsStaticList;

public class SaveHeavyAntecedantsPayloadRequest implements Runnable {

    @Autowired
    AntecedantsRepository antecedantsRepository;

    @Autowired
    MedicamentRepository medicamentRepository;

    Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void run() {
        if(antecedantsStaticList == null){
            logger.error("ERROR : antecedantsStaticList NE DOIT PAS ETRE NULL! Verifiez son instanciation au niveau du module API ");
        }else{
            asyncSaveAntecedant(antecedantsStaticList);
        }

    }



    public void asyncSaveAntecedant(List<Antecedants> antecedantsList){
        for (Antecedants antecedant : antecedantsList) {
           Antecedants antecedantInstanceToCompare = antecedantsRepository.findByAntecedant(antecedant.getAntecedant());
            if(antecedantInstanceToCompare == null){
                antecedantsRepository.save(antecedant);
            }

            if(!antecedantInstanceToCompare.getDescription().equals(antecedant.getDescription())){
                antecedantsRepository.save(antecedant);
            }
        }
    }

}
