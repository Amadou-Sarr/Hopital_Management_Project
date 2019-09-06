package udb.gl;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Dossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String numero = UUID.randomUUID().toString().replaceAll("-","").toUpperCase();

    private String commentaire;


    @OneToOne(mappedBy = "dossier", optional=false, cascade = CascadeType.ALL)
    private Patient patient;

    @ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable
            (
                    name="Dossier_Antecedants",
                    joinColumns= @JoinColumn(name="dossier_id"),
                    inverseJoinColumns= @JoinColumn(name="antecedants_id")
            )
    private Set<Antecedants> antecedants = new HashSet<>();

    public Dossier() {}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Set<Antecedants> getAntecedants() {
        return antecedants;
    }

    public void setAntecedants(Set<Antecedants> antecedants) {
        this.antecedants = antecedants;
    }
}
