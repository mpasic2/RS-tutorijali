package com.rpr.t5.util;

import com.rpr.t5.Banka;
import com.rpr.t5.Korisnik;
import com.rpr.t5.Racun;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KreditTest {
    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void proracunKreditneSposobnosti1() {
        Banka banka = new Banka();
        Korisnik k1 = banka.kreirajNovogKorisnika("Maja", "Majić");
        banka.kreirajRacun(k1);
        KreditnaSposobnost funkcija = (Racun racun) -> {
            double stanje = racun.getStanjeRacuna().doubleValue();
            if (stanje > 1000) {
                return stanje / 2;
            } else {
                return 0d;
            }
        };
        Double value = Kredit.proracunKreditneSposobnosti(funkcija, k1);
        boolean vece = false;
        if (value>0) vece= true;
        assertEquals(false, vece);
    }

    @Test
    void proracunKreditneSposobnosti2() {
        Banka banka = new Banka();
        Korisnik k1 = banka.kreirajNovogKorisnika("Maja", "Majić");
        banka.kreirajRacun(k1);
        k1.getRacun().izvrsiUplatu(2345.45d);
        KreditnaSposobnost funkcija = (Racun racun) -> {
            double stanje = racun.getStanjeRacuna().doubleValue();
            if (stanje > 1000) {
                return stanje / 2;
            } else {
                return 0d;
            }
        };
        Double value = Kredit.proracunKreditneSposobnosti(funkcija, k1);
        boolean vece = false;
        if (value>0) vece= true;
        assertEquals(true, vece);
    }


    @Test
    void ispisiSveKorisnikeBezPrekoracenja1(){
        Banka banka = new Banka();
        Korisnik k1 = banka.kreirajNovogKorisnika("Maja", "Majić");
        banka.kreirajRacun(k1);
        Korisnik k2 = banka.kreirajNovogKorisnika("Veseli", "Veseljkovic");
        banka.kreirajRacun(k2);
        Kredit.ispisiSveKorisnikeBezPrekoracenja(banka.getKorisnici());
        assertEquals("", outContent.toString().trim());
    }

    @Test
    void ispisiSveKorisnikeBezPrekoracenja2(){
        Banka banka = new Banka();
        Korisnik k1 = banka.kreirajNovogKorisnika("Maja", "Majić");
        banka.kreirajRacun(k1);
        k1.getRacun().izvrsiUplatu(2345.45d);
        Korisnik k2 = banka.kreirajNovogKorisnika("Veseli", "Veseljkovic");
        banka.kreirajRacun(k2);
        Kredit.ispisiSveKorisnikeBezPrekoracenja(banka.getKorisnici());
        assertEquals("{ime='Maja', prezime='Majić'}", outContent.toString().trim());
    }

    @Test
    void odobriPrekoracenje1() {
        Banka banka = new Banka();
        Korisnik k1 = banka.kreirajNovogKorisnika("Maja", "Majić");
        banka.kreirajRacun(k1);
        Kredit.odobriPrekoracenje((ArrayList<Korisnik>) banka.getKorisnici());
        assertEquals(false, k1.getRacun().isOdobrenjePrekoracenja());
    }

    @Test
    void odobriPrekoracenje2() {
        Banka banka = new Banka();
        Korisnik k1 = banka.kreirajNovogKorisnika("Maja", "Majić");
        banka.kreirajRacun(k1);
        k1.getRacun().izvrsiUplatu(122345.45d);
        Kredit.odobriPrekoracenje((ArrayList<Korisnik>) banka.getKorisnici());
        assertEquals(true, k1.getRacun().isOdobrenjePrekoracenja());
    }

}
