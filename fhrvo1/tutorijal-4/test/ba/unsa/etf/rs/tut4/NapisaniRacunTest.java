package ba.unsa.etf.rs.tut4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NapisaniRacunTest {

    @Test
    void setKolicina() {
        ArrayList<Artikal> artikli_lista = new ArrayList<>();
        Artikal artikal = new Artikal("HLB001","Hljeb" ,2.5);
        artikli_lista.add(artikal);
        Racun.StavkaRacuna stavka = new Racun.StavkaRacuna(artikli_lista, 5);
        assertEquals(5, stavka.getKolicina());
    }

    @Test
    void getArtikli() {
        ArrayList<Artikal> artikli_lista = new ArrayList<>();
        Artikal artikal = new Artikal("HLB001","Hljeb" ,2.5);
        artikli_lista.add(artikal);
        Racun.StavkaRacuna stavka = new Racun.StavkaRacuna(artikli_lista, 5);
        assertEquals(artikli_lista, stavka.getArtikli());
    }

    @Test
    void StavkaRacuna() {
        ArrayList<Artikal> artikli_lista = new ArrayList<>();
        Artikal artikal = new Artikal("HLB001","Hljeb" ,2.5);
        artikli_lista.add(artikal);
        Racun.StavkaRacuna stavka = new Racun.StavkaRacuna();
        assertEquals(null, stavka.getArtikli());
    }

}