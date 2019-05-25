package udb.gl;

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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST )//CascadeType.Persit & Merge
    @JoinColumn(unique = true)
    //@JoinTable(name="utilisateur_role",joinColumns = @JoinColumn(name = "utilisateur_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Patient patient;

    public RendezVous() {}

    public RendezVous(Date date, String heure, boolean estConsulter, Patient patient) {
        this.date = date;
        this.heure = heure;
        this.estConsulter = estConsulter;
        this.patient = patient;
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
}
