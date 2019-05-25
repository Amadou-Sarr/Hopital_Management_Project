package udb.gl.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import udb.gl.*;
import udb.gl.exception.RessourceNotFound;
import udb.gl.payload.*;
import udb.gl.tokensecurity.CurrentUser;
import udb.gl.tokensecurity.UtilisateurPrincipal;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("{username}")
    public ProfilUtilisateur getUserProfile(@PathVariable(value = "username") String username) {
        Utilisateur user = utilisateurRepository.findByUsername(username)
                .orElseThrow(() -> new RessourceNotFound("Utilisateur", "username", username));

        ProfilUtilisateur userProfile = new ProfilUtilisateur(user.getId(),user.getPrenom(), user.getNom(), user.getUsername(),user.getMatricule(), user.getCreatedAt());
        return userProfile;
    }


    @GetMapping("/me")
    public UtilisateurSummary showprofile(@CurrentUser UtilisateurPrincipal currentUser){
        UtilisateurSummary utilisateurSummary = new UtilisateurSummary(currentUser.getId(),currentUser.getUsername(),currentUser.getPrenom());
        return utilisateurSummary;
    }


    @GetMapping("admin/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Utilisateur> getAll(){
        return utilisateurRepository.findAll();
    }


    @PostMapping("/admin/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> createUtilisateur(@Valid @RequestBody CreateUtilisateur createUtilisateur){
        // Todo Refactor
        Utilisateur utilisateur = new Utilisateur(createUtilisateur.getNom(),createUtilisateur.getPrenom(),createUtilisateur.getPassword(),
                createUtilisateur.getUsername(),createUtilisateur.getEmail(),createUtilisateur.getPhoto(),createUtilisateur.getMatricule());
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));

        for(Role role : createUtilisateur.getRole()){
            Role roleValidator = roleRepository.findById(role.getId()).orElseThrow (
                    () -> new RessourceNotFound("Role", "roleId", role.getName())
            );
            utilisateur.setRole(Collections.singleton(roleValidator));
        }

        Utilisateur result = utilisateurRepository.save(utilisateur);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/utilisateur")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Utilisateur Ajouté avec Succes!!"));

    }

}
