package udb.gl.payload;

public class MedicamentConsultationPayload {

    private long medicament;

    private String dosage;

    private String presciption;

    public MedicamentConsultationPayload() {}

    public MedicamentConsultationPayload(long medicament, String dosage, String presciption) {
        this.medicament = medicament;
        this.dosage = dosage;
        this.presciption = presciption;
    }

    public long getMedicament() {
        return medicament;
    }

    public void setMedicament(long medicament) {
        this.medicament = medicament;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getPresciption() {
        return presciption;
    }

    public void setPresciption(String presciption) {
        this.presciption = presciption;
    }
}
