package udb.gl.payload;

public class DossierSearchPayload {

    private String numeroDossier ;


    public DossierSearchPayload() {}

    public DossierSearchPayload(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    public String getNumeroDossier() {
        return numeroDossier;
    }

    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }
}
