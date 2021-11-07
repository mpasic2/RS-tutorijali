package ba.unsa.etf.rs.tutorijal9;

import java.util.ArrayList;

public class UN {
    private ArrayList<Drzava> drzave = new ArrayList<>();


    public ArrayList<Drzava> getDrzave() {
        return drzave;
    }

    public void setDrzave(Drzava dr) {
        this.drzave.add(dr);
    }

}
