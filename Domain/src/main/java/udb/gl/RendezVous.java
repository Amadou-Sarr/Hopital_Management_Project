package udb.gl;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date date;

    private String heure;

    private boolean estConsulter;

    @OneToOne(fetch = FetchType.LAZY)//CascadeType.Persit & Merge
    @JoinColumn(unique = false,  nullable = false)
    //@JoinTable(name="utilisateur_role",joinColumns = @JoinColumn(name = "utilisateur_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    // @OnDelete(action = OnDeleteAction.CASCADE)
    private Utilisateur  utilisateur;


    public RendezVous() {}

    public RendezVous(Date date, String heure, boolean estConsulter, Patient patient, Utilisateur utilisateur) {
        this.date = date;
        this.heure = heure;
        this.estConsulter = estConsulter;
        this.patient = patient;
        this.utilisateur = utilisateur;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public boolean isEstConsulter() {
        return estConsulter;
    }

    public void setEstConsulter(boolean estConsulter) {
        this.estConsulter = estConsulter;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
