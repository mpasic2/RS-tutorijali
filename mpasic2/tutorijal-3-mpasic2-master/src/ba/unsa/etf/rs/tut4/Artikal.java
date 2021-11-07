package ba.unsa.etf.rs.tut4;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Objects;

public class Artikal {

    private String sifra;
    private String naziv;
    private double cijena;

    public Artikal(String artikli) {
        String[] atributi = artikli.split(",");
        this.sifra =atributi[0];
        this.naziv = atributi[1];
        this.cijena = Double.parseDouble(atributi[2]);
    }

    public static void izbaciDuplikate(ArrayList<Artikal> artikli) {
        int i,j;
        for(i=0;i<artikli.size();i++)
            for(j=i+1;j<artikli.size();j++)
                    if(artikli.get(i).equals(artikli.get(j))){
                        artikli.remove(j);
                        j--;
                    }
    }


    public Artikal(String sifra, String artikal, double cijena) {
        setSifra(sifra);
        setNaziv(artikal);
        setCijena(cijena);
    }


    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        if(sifra.isEmpty()){
            throw new IllegalArgumentException("Šifra je prazna");
        }
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        if(naziv.isEmpty()){
            throw new IllegalArgumentException("Naziv je prazan");
        }
        this.naziv = naziv;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        if (cijena < 0) {
            throw new IllegalArgumentException("Cijena je negativna");
        }
        this.cijena = cijena;
    }
    @Override
    public String toString() {
        return sifra+", "+naziv+", "+cijena;
    }

    @Override
    public boolean equals(Object o){
        Artikal akrt = (Artikal) o;
        if(this==o) return true;
        if(getClass()!=o.getClass()) return false;

        return Double.compare(akrt.cijena, cijena)==0 && Objects.equals(sifra, akrt.sifra) &&
                    Objects.equals(naziv,akrt.naziv);
    }




}
