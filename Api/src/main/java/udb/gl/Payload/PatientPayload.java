package udb.gl.payload;

import javax.validation.constraints.Null;

public class PatientPayload {


    private int numero_telephone;

    @Null
    private String numeroPatient;

    private String nom;

    private String prenom;

    private String sexe;

    private int age;

    private String adresse;


    public PatientPayload() {}

    public PatientPayload(int numero_telephone, @Null String numeroPatient, String nom, String prenom, String sexe, int age, String adresse) {
        this.numero_telephone = numero_telephone;
        this.numeroPatient = numeroPatient;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.age = age;
        this.adresse = adresse;
    }

    public int getNumero_telephone() {
        return numero_telephone;
    }

    public void setNumero_telephone(int numero_telephone) {
        this.numero_telephone = numero_telephone;
    }

    public String getNumeroPatient() {
        return numeroPatient;
    }

    public void setNumeroPatient(String numeroPatient) {
        this.numeroPatient = numeroPatient;
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
