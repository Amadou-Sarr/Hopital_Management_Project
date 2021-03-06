package udb.gl.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import udb.gl.*;
import udb.gl.exception.AppException;
import udb.gl.payload.*;
import udb.gl.tokensecurity.CurrentUser;
import udb.gl.tokensecurity.JwtTokenProvider;
import udb.gl.tokensecurity.UtilisateurPrincipal;
import udb.gl.exception.RessourceNotFound;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthentificationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUtilisateur(@Valid @RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateTokens(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUtilisateur(@Valid @RequestBody SignupRequest signupRequest){
        if(utilisateurRepository.existsByUsername(signupRequest.getUsername())){
            return new ResponseEntity( new ApiResponse(false, "Ce Nom d'utilisateur est déjà pris!"),HttpStatus.BAD_REQUEST);
        }

        if(utilisateurRepository.existsByEmail(signupRequest.getEmail())){
            return new ResponseEntity(new ApiResponse(false, "Cet Email est déjà en cours d'utilisation!"),HttpStatus.BAD_REQUEST);
        }

        //FINALLY Creating the USER
        Utilisateur utilisateur = new Utilisateur(signupRequest.getNom(),signupRequest.getPrenom(),signupRequest.getPassword(),signupRequest.getUsername(),signupRequest.getPhoto(),signupRequest.getEmail(),signupRequest.getMatricule(),signupRequest.getService());
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        Role roleUtilisateur = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(
                        ()->new AppException("Le Role de l'utilisateur n'est pas définit")
                );

        utilisateur.setRole(Collections.singleton(roleUtilisateur));

        Utilisateur result = utilisateurRepository.save(utilisateur);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/utilisateur/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Utilisateur Enregistrer avec Succes!! Veuillez Changer votre Mot de passe!! "));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/api/auth/changerpass/")
    public ResponseEntity<ApiResponse> setChangePassword(@Valid @RequestBody ChangePassword changePassword, @CurrentUser UtilisateurPrincipal currentUser){
        //TODO REFACTOR
        //Changing the password
        Utilisateur utilisateur = utilisateurRepository.findById(currentUser.getId()).orElseThrow(
                () -> new RessourceNotFound("Utilisateur", "Id", currentUser.getUsername())
        );

        utilisateur.setPassword(passwordEncoder.encode(changePassword.getPassword()));
        Utilisateur utilisateurWithNewPassword = utilisateurRepository.save(utilisateur);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/utilisateur/{username}")
                .buildAndExpand(utilisateurWithNewPassword.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Changement de mot de passe avec Succes!! Veuillez Changer votre Mot de passe!! "));


    }


    @GetMapping("/checkUsernameAvailability/{username}")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable =  utilisateurRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);

    }

    @GetMapping("/checkEmailAvailability/{email}")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = utilisateurRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);

    }

}
