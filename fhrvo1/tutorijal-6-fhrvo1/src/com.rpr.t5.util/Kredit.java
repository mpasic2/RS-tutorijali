package com.rpr.t5.util;

import com.rpr.t5.Korisnik;
import com.rpr.t5.Racun;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Kredit {

    public static Double proracunKreditneSposobnosti(KreditnaSposobnost funkcija, Korisnik k1) {
        Racun r = k1.getRacun();
        double suma = funkcija.provjeri(r);
        return suma;
    }

    public static void ispisiSveKorisnikeBezPrekoracenja(List<Korisnik> korisnici) {
        korisnici.stream().filter(value -> value.getRacun().getStanjeRacuna() > 0.0).forEach(value -> System.out.println(value));
    }

    public static void odobriPrekoracenje(ArrayList<Korisnik> korisnici){
        korisnici.stream()
                .filter(korisnik -> korisnik.getRacun().getStanjeRacuna() > 10000)
                .collect(Collectors.toCollection(ArrayList::new)).forEach(korisnik -> korisnik.getRacun().odobriPrekoracenje());
    }
}
