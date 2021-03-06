package udb.gl;

import udb.gl.audit.DateAudit;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Utilisateur extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length =100)
    private String nom;

    @Column(length =100)
    private String prenom;

    @Column(length =100)
    private String password;

    @Column(length =100)
    private String username;

    @Column(length =100)
    private String photo;

    @Column(length =100)
    private String matricule;

    @Column(length =100)
    private String email;

    @Column(length =100)
    @Enumerated(EnumType.STRING)
    private ServiceName service;

    private boolean enabled;

    private boolean changed;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE} )//CascadeType.Persit & Merge
    @JoinTable(name="utilisateur_role",joinColumns = @JoinColumn(name = "utilisateur_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> role = new HashSet<>();

    Utilisateur(){}

    public Utilisateur (String nom, String prenom, String password, String username, String photo, String email, String matricule, ServiceName service) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.username = username;
        this.photo = photo;
        this.email = email;
        this.matricule = matricule;
        this.service = service;

        // this.role = role;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getEmail() {
        return email;
    }

    public ServiceName getService() {
        return service;
    }

    public void setService(ServiceName service) {
        this.service = service;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}
