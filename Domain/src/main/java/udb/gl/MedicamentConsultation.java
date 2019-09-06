package udb.gl;

import javax.persistence.*;

@Entity
public class MedicamentConsultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String dosage;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="consultation")
    private Consultation consultation;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)//CascadeType.Persit & Merge
    @JoinColumn(name = "medicament")
    private Medicaments medicaments;

    public MedicamentConsultation() {}

    public MedicamentConsultation(String dosage, Consultation consultation, Medicaments medicaments) {
        this.dosage = dosage;
        this.consultation = consultation;
        this.medicaments = medicaments;
    }

    public MedicamentConsultation(String dosage) {
        this.dosage = dosage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public Medicaments getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Medicaments medicaments) {
        this.medicaments = medicaments;
    }
}
