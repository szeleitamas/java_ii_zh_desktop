package grafikus;

import javax.persistence.*;

@Entity
@Table(name = "np")
public class Np {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int id;
    @Column(name = "nev")
    public String nev;
    @Column(name = "igazgato")
    public String igazgato;
    @Column(name = "kinevezes")
    public int kinevezes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getIgazgato() {
        return igazgato;
    }

    public void setIgazgato(String igazgato) {
        this.igazgato = igazgato;
    }

    public int getKinevezes() {
        return kinevezes;
    }

    public void setKinevezes(int kinevezes) {
        this.kinevezes = kinevezes;
    }

    public Np(String nev, String igazgato, int kinevezes) {
        this.nev = nev;
        this.igazgato = igazgato;
        this.kinevezes = kinevezes;
    }

    public Np() {
    }
}
