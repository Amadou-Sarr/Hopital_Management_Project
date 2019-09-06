package udb.gl.payload;

import udb.gl.Antecedants;
import udb.gl.Medicaments;
import udb.gl.Patient;
import udb.gl.Utilisateur;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class ConsultationPayload {

    private Date date;

    private String commentaire;

    private String prescription;

    private String numeroDossier;

    private Patient patient;

    private Utilisateur utilisateur;

    private List<MedicamentConsultationPayload> listMedicament;

    private Set<Antecedants> listAntecedants;

    public ConsultationPayload() {}

    public ConsultationPayload(Date date, String commentaire, String prescription, String numeroDossier, Patient patient, Utilisateur utilisateur, List<MedicamentConsultationPayload> listMedicament, Set<Antecedants> listAntecedants) {
        this.date = date;
        this.commentaire = commentaire;
        this.prescription = prescription;
        this.numeroDossier = numeroDossier;
        this.patient = patient;
        this.utilisateur = utilisateur;
        this.listMedicament = listMedicament;
        this.listAntecedants = listAntecedants;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getNumeroDossier() {
        return numeroDossier;
    }

    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<MedicamentConsultationPayload> getListMedicament() {
        return listMedicament;
    }

    public void setListMedicament(List<MedicamentConsultationPayload> listMedicament) {
        this.listMedicament = listMedicament;
    }

    public Set<Antecedants> getListAntecedants() {
        return listAntecedants;
    }

    public void setListAntecedants(Set<Antecedants> listAntecedants) {
        this.listAntecedants = listAntecedants;
    }
}
