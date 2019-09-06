package udb.gl.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import udb.gl.*;
import udb.gl.exception.AppException;
import udb.gl.exception.RessourceNotFound;
import udb.gl.payload.*;
import udb.gl.services.Utils;
import udb.gl.tokensecurity.CurrentUser;
import udb.gl.tokensecurity.UtilisateurPrincipal;

import javax.validation.Valid;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    Utils utils;


    @GetMapping("/role/all")
    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }

    @GetMapping("{username}")
    public ProfilUtilisateur getUserProfile(@PathVariable(value = "username") String username) {
        Utilisateur user = utilisateurRepository.findByUsername(username)
                .orElseThrow(() -> new RessourceNotFound("Utilisateur", "username", username));

       return  new ProfilUtilisateur(user.getId(),user.getPrenom(), user.getNom(), user.getUsername(),user.getMatricule(), user.getCreatedAt());
    }


    @GetMapping("/me")
    public Utilisateur showprofile(@CurrentUser UtilisateurPrincipal currentUser){
        return utilisateurRepository.findById(currentUser.getId()).orElseThrow(
                ()->new AppException(" utilisateur current User n'est pas définit")
        );
        //return new UtilisateurSummary(currentUser.getId(),currentUser.getUsername(),currentUser.getPrenom(), user.getRole(), currentUser.getMatricule(), currentUser.getService());
    }


    @GetMapping("admin/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Utilisateur> getAll(){
        return utilisateurRepository.findAll();
    }


    @PostMapping("/admin/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> createUtilisateur(@Valid @RequestBody CreateUtilisateur createUtilisateur){
        String matricule = createUtilisateur.getNom() + utils.sdf.format(new  Date()) +( (createUtilisateur.getPrenom() != null ) ? createUtilisateur.getPrenom().substring(0,createUtilisateur.getPrenom().length()-2) : "");
        Utilisateur utilisateur = new Utilisateur(createUtilisateur.getNom(),createUtilisateur.getPrenom(),createUtilisateur.getPassword(),
                createUtilisateur.getUsername(),createUtilisateur.getEmail(),createUtilisateur.getPhoto(),matricule,createUtilisateur.getService());
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        Set<Role> roleSet = new HashSet<>();

        for(Role role : createUtilisateur.getRole()){
            Role roleValidator = roleRepository.findById(role.getId()).orElseThrow (
                    () -> new RessourceNotFound("Role", "roleId", role.getName())
            );
            roleSet.add(roleValidator);
            //utilisateur.setRole(Collections.singleton(roleValidator));
        }

        utilisateur.setRole(roleSet);

        Utilisateur result = utilisateurRepository.save(utilisateur);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/utilisateur")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Utilisateur Ajouté avec Succes!!"));

    }

}
