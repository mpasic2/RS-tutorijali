package ba.unsa.etf.rs.tut4;

import java.util.ArrayList;
import java.util.Objects;

public class Artikal {
        private String sifra;
        private String naziv;
        private double cijena;


    public Artikal(String abc, String proizvod, double i) {
        setSifra(abc);
        setNaziv(proizvod);
        setCijena(i);
    }

    public static void izbaciDuplikate(ArrayList<Artikal> lista) {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i+1; j < lista.size(); j++) {
                if (lista.get(i).equals(lista.get(j))) {
                    lista.remove(j);
                    j--;
                }
            }
        }
    }

    public String getSifra(){
        return sifra;
    }

    public void setSifra(String sifra) {
        if (sifra.isEmpty()) throw new IllegalArgumentException("Sifra je prazna");
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        if (naziv.isEmpty()) throw new IllegalArgumentException("Naziv je prazan");
        this.naziv = naziv;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        if (cijena<0) throw new IllegalArgumentException("Cijena je negativna");
        this.cijena = cijena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artikal artikal = (Artikal) o;
        return Double.compare(artikal.cijena, cijena) == 0 &&
                Objects.equals(sifra, artikal.sifra) &&
                Objects.equals(naziv, artikal.naziv);
    }

    @Override
    public String toString() {
        return sifra + "," + naziv + "," + cijena +  "\n";
    }
}
