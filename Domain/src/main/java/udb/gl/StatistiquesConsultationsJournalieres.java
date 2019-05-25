package udb.gl;

import udb.gl.audit.DateAudit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StatistiquesConsultationsJournalieres extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int nombreDeRendezVousTotaux;

    private int nombreDeConsultationsTotaux;

    private int nombreDeConsultationHomme;

    private int nombreDeConsultationFemme;

    private int nombreDeConsultationEnfant;

    private int nombreDeConsultationAdulte;


    public StatistiquesConsultationsJournalieres() {}

    public StatistiquesConsultationsJournalieres(int nombreDeRendezVousTotaux, int nombreDeConsultationsTotaux, int nombreDeConsultationHomme, int nombreDeConsultationParFemme, int nombreDeConsultationEnfant, int nombreDeConsultationAdulte) {
        this.nombreDeRendezVousTotaux = nombreDeRendezVousTotaux;
        this.nombreDeConsultationsTotaux = nombreDeConsultationsTotaux;
        this.nombreDeConsultationHomme = nombreDeConsultationHomme;
        this.nombreDeConsultationFemme = nombreDeConsultationParFemme;
        this.nombreDeConsultationEnfant = nombreDeConsultationEnfant;
        this.nombreDeConsultationAdulte = nombreDeConsultationAdulte;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNombreDeRendezVousTotaux() {
        return nombreDeRendezVousTotaux;
    }

    public void setNombreDeRendezVousTotaux(int nombreDeRendezVousTotaux) {
        this.nombreDeRendezVousTotaux = nombreDeRendezVousTotaux;
    }

    public int getNombreDeConsultationsTotaux() {
        return nombreDeConsultationsTotaux;
    }

    public void setNombreDeConsultationsTotaux(int nombreDeConsultationsTotaux) {
        this.nombreDeConsultationsTotaux = nombreDeConsultationsTotaux;
    }

    public int getNombreDeConsultationHomme() {
        return nombreDeConsultationHomme;
    }

    public void setNombreDeConsultationHomme(int nombreDeConsultationHomme) {
        this.nombreDeConsultationHomme = nombreDeConsultationHomme;
    }

    public int getNombreDeConsultationEnfant() {
        return nombreDeConsultationEnfant;
    }

    public void setNombreDeConsultationEnfant(int nombreDeConsultationEnfant) {
        this.nombreDeConsultationEnfant = nombreDeConsultationEnfant;
    }

    public int getNombreDeConsultationFemme() {
        return nombreDeConsultationFemme;
    }

    public void setNombreDeConsultationFemme(int nombreDeConsultationFemme) {
        this.nombreDeConsultationFemme = nombreDeConsultationFemme;
    }

    public int getNombreDeConsultationAdulte() {
        return nombreDeConsultationAdulte;
    }

    public void setNombreDeConsultationAdulte(int nombreDeConsultationAdulte) {
        this.nombreDeConsultationAdulte = nombreDeConsultationAdulte;
    }
}

