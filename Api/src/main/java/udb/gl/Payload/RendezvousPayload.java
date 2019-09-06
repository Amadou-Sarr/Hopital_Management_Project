package udb.gl.payload;

import udb.gl.Patient;
import udb.gl.Utilisateur;

import java.util.Date;

public class RendezvousPayload {

    private Date date;

    private String heure;

    private Patient patient;

    private Utilisateur utilisateur;

    public RendezvousPayload() {}

    public RendezvousPayload(Date date, String heure, Patient patient, Utilisateur utilisateur) {
        this.date = date;
        this.heure = heure;
        this.patient = patient;
        this.utilisateur = utilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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
}
