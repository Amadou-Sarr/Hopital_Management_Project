package udb.gl;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int numero;

    private String commentaire;

    @OneToOne(mappedBy = "dossier")
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

    public Dossier(int numero, String commentaire) {
        this.numero = numero;
        this.commentaire = commentaire;
        //this.patient = patient;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
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
