package com.rpr.t5;

public class Racun {
    private final Long brojRacuna;
    private final Osoba korisnikRacuna;
    private Double stanjeRacuna;
    private boolean odobrenjePrekoracenja;

    public boolean isOdobrenjePrekoracenja() {
        return odobrenjePrekoracenja;
    }

    public void setOdobrenjePrekoracenja(boolean odobrenjePrekoracenja) {
        this.odobrenjePrekoracenja = odobrenjePrekoracenja;
    }

    public void odobriPrekoracenje() {
        setOdobrenjePrekoracenja(true);
    }

    public Racun(Long brojRacuna, Osoba korisnikRacuna) {
        this.brojRacuna = brojRacuna;
        this.korisnikRacuna = korisnikRacuna;
        this.stanjeRacuna = 0d;
        this.odobrenjePrekoracenja = false;
    }

    public void izvrsiUplatu(Double v) {
        stanjeRacuna = stanjeRacuna + v;
    }

    public Double getStanjeRacuna() {
        return stanjeRacuna;
    }

    public String getBrojRacuna() {
        return brojRacuna.toString();
    }
}
