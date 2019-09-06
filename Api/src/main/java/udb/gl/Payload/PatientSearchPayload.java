package udb.gl.payload;

public class PatientSearchPayload {

    private String numeroPatient;

    public PatientSearchPayload() {}

    public PatientSearchPayload(String numeroPatient) {
        this.numeroPatient = numeroPatient;
    }

    public String getNumeroPatient() {
        return numeroPatient;
    }

    public void setNumeroPatient(String numeroPatient) {
        this.numeroPatient = numeroPatient;
    }
}
