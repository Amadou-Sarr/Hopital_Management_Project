package udb.gl.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import udb.gl.MedicamentRepository;
import udb.gl.Medicaments;

import java.util.List;

import static udb.gl.api.ConsultationController.medicamentsStaticList;

public class SaveHeavyMedicamentsPayloadRequest implements Runnable {

    @Autowired
    MedicamentRepository medicamentRepository;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run() {
        if(medicamentsStaticList == null){
            logger.error("Erreur : medicamentsStaticList est null! Verifier son Initialisation et son appel dans Consultatoion et SaveHeavyPayloadMedicamentsRequest");
        }else{
            asyncSaveMedicaments(medicamentsStaticList);
        }
    }

    public void asyncSaveMedicaments(List<Medicaments> medicamentsList){
        for(Medicaments medicament : medicamentsList){
            Medicaments medicamentInstanceToCompare = medicamentRepository.findByLibelle(medicament.getLibelle());
            if(medicamentInstanceToCompare == null){
                medicamentRepository.save(medicament);
            }
        }
    }

}

