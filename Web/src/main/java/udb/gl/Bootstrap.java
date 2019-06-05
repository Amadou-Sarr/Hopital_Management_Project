package udb.gl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import udb.gl.exception.AppException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    MedicamentRepository medicamentRepository;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AntecedantsRepository antecedantsRepository;


    Logger logger;


    @Override
    public void run(String... args) throws Exception {
      //  bootstrapRoles();
       // String passwordUtilisateur =  passwordEncoder.encode("password");
        // bootstrapUtilisteurs("Sarr","Amadou",passwordUtilisateur,"username","photoStringLink","amadou.sarr@udb.sn","123ABC","Chirurgie");

    }


    void bootstrapUtilisteurs(String nom, String prenom, String password, String username, String photo, String email, String matricule,String service){

        Utilisateur utilisateur = new Utilisateur(nom,prenom,password,username,photo,email,matricule,service);
        Role utilisateurRole = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(
                ()->new AppException("Le Role de l'utilisateur n'est pas définit")
        );

        utilisateur.setRole(Collections.singleton(utilisateurRole));
        utilisateur.setEnabled(true);
        utilisateur.setChanged(true);
        utilisateurRepository.save(utilisateur);
    }

    void bootstrapRoles(){
        Role roleadmin = new Role(RoleName.ROLE_ADMIN);
        Role roleuser = new Role(RoleName.ROLE_USER);
        Role rolesecretaire = new Role(RoleName.ROLE_SECRETAIRE);
        Role roleMedecin = new Role(RoleName.ROLE_MEDECIN);
        Role roleProspect = new Role(RoleName.ROLE_PROSPECT);
        Role roleChefService = new Role(RoleName.ROLE_CHEF_SERVICE);
        roleRepository.save(roleadmin);
        roleRepository.save(roleuser);
        roleRepository.save(rolesecretaire);
        roleRepository.save(roleMedecin);
        roleRepository.save(roleProspect);
        roleRepository.save(roleChefService);
    }
}
