package ba.unsa.etf.rs.tutorijal9;

import java.util.ArrayList;

public class UN {
    private ArrayList<Drzava> drzave = new ArrayList<>();

    public UN() {
    }

    public UN(ArrayList<Drzava> drzave) {
        this.drzave = drzave;
    }

    public ArrayList<Drzava> getDrzave() {
        return drzave;
    }

    public void setDrzave(ArrayList<Drzava> drzave) {
        this.drzave = drzave;
    }

    public void addDrzava(Drzava d){
        drzave.add(d);
    }
}
