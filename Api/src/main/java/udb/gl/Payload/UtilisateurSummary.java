package udb.gl.payload;

import org.springframework.security.core.GrantedAuthority;
import udb.gl.Role;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UtilisateurSummary {

        private Long id;
        private String username;
        private String prenom;
        private Collection<? extends GrantedAuthority> authorities;

    public UtilisateurSummary(Long id, String username, String prenom,  Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.prenom = prenom;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
