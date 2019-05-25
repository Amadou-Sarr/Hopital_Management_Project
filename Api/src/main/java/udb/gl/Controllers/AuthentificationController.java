package udb.gl.Controllers;



import com.sun.org.apache.xpath.internal.operations.String;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udb.gl.*;
import udb.gl.Payload.ApiResponse;
import udb.gl.Payload.JwtAuthenticationResponse;
import udb.gl.Payload.LoginModel;
import udb.gl.TokenSecurity.JwtTokenProvider;

import javax.validation.Valid;

import java.util.List;

import static udb.gl.ControlFunctions.isEqual;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthentificationController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public static Utilisateur utilisateurPrincipal;

    public static List<Role> autorisationsRole;

    public static boolean isAuthenticated;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginModel loginModel){


        java.lang.String usernameOrEmail = loginModel.getUsrenameOrEmail();

        utilisateurPrincipal = utilisateurRepository.findByUsernameOrEmail(usernameOrEmail , usernameOrEmail);

        if(utilisateurPrincipal == null){

            return new ResponseEntity("Login Incorrect !! Veuillez verifier votre Email ou Username", HttpStatus.BAD_REQUEST);
        }

       boolean verifPassword = isEqual(loginModel.getPassword(),utilisateurPrincipal.getPassword());

        if(verifPassword == false){

            return new ResponseEntity("Password Incorrect !! Veuillez verifier votre Mot de Passe", HttpStatus.BAD_REQUEST);
        }

        java.lang.String jwt = jwtTokenProvider.generateTokens();
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));


    }


}