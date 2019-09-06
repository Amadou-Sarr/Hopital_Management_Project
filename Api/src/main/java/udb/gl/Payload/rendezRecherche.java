package udb.gl.payload;

public class rendezRecherche {

    private String service;

    public rendezRecherche() {}

    public rendezRecherche(String service) {
        this.service = service;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
