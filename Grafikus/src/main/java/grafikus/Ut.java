package grafikus;

import javax.persistence.*;

@Entity
@Table(name = "ut")
public class Ut {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int id;
    @Column(name = "nev")
    public String nev;

    @Column(name = "hossz")
    public int hossz;

    @Column(name = "allomas")
    public int allomas;

    @Column(name = "ido")
    public int ido;

    @Column(name = "vezetes")
    public boolean vezetes;

    @Column(name = "telepulesid")
    public int telepulesid;

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

    public int getHossz() {
        return hossz;
    }

    public void setHossz(int hossz) {
        this.hossz = hossz;
    }

    public int getAllomas() {
        return allomas;
    }

    public void setAllomas(int allomas) {
        this.allomas = allomas;
    }

    public int getIdo() {
        return ido;
    }

    public void setIdo(int ido) {
        this.ido = ido;
    }

    public boolean isVezetes() {
        return vezetes;
    }

    public void setVezetes(boolean vezetes) {
        this.vezetes = vezetes;
    }

    public int getTelepulesid() {
        return telepulesid;
    }

    public void setTelepulesid(int telepulesid) {
        this.telepulesid = telepulesid;
    }

    public Ut(String nev, int hossz, int allomas, int ido, boolean vezetes, int telepulesid) {
        this.nev = nev;
        this.hossz = hossz;
        this.allomas = allomas;
        this.ido = ido;
        this.vezetes = vezetes;
        this.telepulesid = telepulesid;
    }

    public Ut() {
    }
}