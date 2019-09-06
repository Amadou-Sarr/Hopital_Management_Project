package udb.gl.payload;

import org.springframework.security.core.GrantedAuthority;
import udb.gl.Role;
import udb.gl.ServiceName;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UtilisateurSummary {

        private Long id;
        private String username;
        private String prenom;
        private Set<Role> role;
        private String matricule;
        private ServiceName service;

    public UtilisateurSummary(Long id, String username, String prenom,  Set<Role> role,String matricule, ServiceName service) {
        this.id = id;
        this.username = username;
        this.prenom = prenom;
        this.role = role;
        this.matricule = matricule;
        this.service = service;
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

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public ServiceName getService() {
        return service;
    }

    public void setService(ServiceName service) {
        this.service = service;
    }
}
