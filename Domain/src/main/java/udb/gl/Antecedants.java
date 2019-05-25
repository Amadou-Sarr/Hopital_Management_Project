package udb.gl;

import javax.persistence.*;
import java.util.List;

@Entity
public class Antecedants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    private String antecedant;

    @Column(length = 250)
    private String Description;

    public Antecedants() {}

    public Antecedants(String antecedant, String description) {
        this.antecedant = antecedant;
        Description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAntecedant() {
        return antecedant;
    }

    public void setAntecedant(String antecedant) {
        this.antecedant = antecedant;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

}
