package com.rpr.t5;

public class Korisnik extends Osoba {
    private Racun racun;

    public Korisnik(String ime, String prezime) {
        super(ime, prezime);
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public Racun getRacun() {
        return racun;
    }
}
