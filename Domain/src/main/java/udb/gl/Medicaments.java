package udb.gl;

import javax.persistence.*;

@Entity
public class Medicaments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String libelle;

    @OneToOne(fetch=FetchType.LAZY, mappedBy="medicaments")
    private MedicamentConsultation medicamentConsultation;

    public Medicaments() {}

    public Medicaments(String libelle) {
        this.libelle = libelle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
