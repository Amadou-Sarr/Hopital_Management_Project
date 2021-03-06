package udb.gl;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int numero_telephone;

    private String numeroPatient;

    private String nom;

    private String prenom;

    private String sexe;

    private int age;

    private String adresse;


    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, optional = false)
    @JsonIgnore
    private RendezVous rendezVous;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)//CascadeType.Persit & Merge
    @JoinColumn(unique = true)
    //@JoinTable(name="utilisateur_role",joinColumns = @JoinColumn(name = "utilisateur_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Dossier dossier;

    public Patient() {}

    public Patient(int numero_telephone, String prenom, String nom, String sexe, int age, String adresse, String numeroPatient) {
        this.numero_telephone = numero_telephone;
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        this.sexe = sexe;
        this.adresse = adresse;
        this.numeroPatient = numeroPatient;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumero_telephone() {
        return numero_telephone;
    }

    public void setNumero_telephone(int numero_telephone) {
        this.numero_telephone = numero_telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
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

    public String getNumeroPatient() {
        return numeroPatient;
    }

    public void setNumeroPatient(String numeroPatient) {
        this.numeroPatient = numeroPatient;
    }

    public RendezVous getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(RendezVous rendezVous) {
        this.rendezVous = rendezVous;
    }
}
