package grafikus;

import javax.persistence.*;

@Entity
@Table(name = "telepules")
public class Telepules {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int id;
    @Column(name = "nev")
    public String nev;

    @Column(name = "npid")
    public int npid;

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

    public int getNpid() {
        return npid;
    }

    public void setNpid(int npid) {
        this.npid = npid;
    }

    public Telepules(String nev, int npid) {
        this.nev = nev;
        this.npid = npid;
    }

    public Telepules() {
    }
}