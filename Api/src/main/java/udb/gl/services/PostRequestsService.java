package udb.gl.services;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import udb.gl.payload.ApiResponse;

import java.util.List;

@Service
public class PostRequestsService {


    public ResponseEntity<?> saveRequestInDB(List<?> requestPayloadList, List<?> staticList,Thread asyncSaveMethod){
        if(requestPayloadList.isEmpty()){
           return new ResponseEntity(new ApiResponse(false, "Erreur Vous n'avez entrer aucune donnée à Enregistrer"), HttpStatus.BAD_REQUEST);
        }
        if(requestPayloadList.size() > 200){
            staticList = requestPayloadList;
            asyncSaveMethod.start();
            return ResponseEntity.ok(new ApiResponse(true,"Nous traitons présentement votre Requete, et vous reviendrons très prochainement! Vous pouvez Continuer à Utiliser l'applcation"));
        }

        return null;
    }

    // This is what i used for the request processing.
    // if(antecedants.isEmpty()){
    //            return new ResponseEntity(new ApiResponse(false, "Erreur Vous n'avez entrer aucune donnée à Enregistrer"), HttpStatus.BAD_REQUEST);
    //        }
    //        if(antecedants.size() > 200){
    //            antecedantsStaticList = antecedants;
    //            Thread asyncSaveAntecedantsThread = new Thread(new SaveHeavyAntecedantsPayloadRequest());
    //            asyncSaveAntecedantsThread.start();
    //            return ResponseEntity.ok(new ApiResponse(true,"Nous traitons présentement votre Requete, et vous reviendrons très prochainement! Vous pouvez Continuer à Utiliser l'applcation"));
    //        }
}
