package udb.gl.tokensecurity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import udb.gl.ServiceName;
import udb.gl.Utilisateur;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UtilisateurPrincipal implements UserDetails {

    private Long id;

    private String nom;

    private  String prenom;

    private String username;

    private String matricule;

    private String photo;

    private ServiceName service;

    private boolean changed;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UtilisateurPrincipal(Long id, String nom, String prenom, String username, String photo, String email, String password, String matricule,ServiceName service, boolean changed, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.photo = photo;
        this.email = email;
        this.password = password;
        this.matricule = matricule;
        this.service = service;
        this.changed = changed;
        this.authorities = authorities;
    }



    public static UtilisateurPrincipal create(Utilisateur utilisateur) {
        List<GrantedAuthority> authorities = utilisateur.getRole().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UtilisateurPrincipal(
                utilisateur.getId(),
                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getPhoto(),
                utilisateur.getUsername(),
                utilisateur.getEmail(),
                utilisateur.getPassword(),
                utilisateur.getMatricule(),
                utilisateur.getService(),
                utilisateur.isChanged(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPhoto() {
        return photo;
    }

    public String getEmail() {
        return email;
    }

    public  String getMatricule(){ return matricule;}

    public ServiceName getService() {
        return service;
    }

    public boolean isChanged() {
        return changed;
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtilisateurPrincipal that = (UtilisateurPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}



