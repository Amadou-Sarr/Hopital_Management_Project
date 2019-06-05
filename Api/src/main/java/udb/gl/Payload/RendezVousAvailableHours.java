package udb.gl.payload;

import udb.gl.Utilisateur;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class RendezVousAvailableHours {

    @NotNull
    private Utilisateur utilisateur;

    @NotNull
    private Date date;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
