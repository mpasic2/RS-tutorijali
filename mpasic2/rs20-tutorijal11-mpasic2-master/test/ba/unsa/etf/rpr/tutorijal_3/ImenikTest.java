package ba.unsa.etf.rpr.tutorijal_3;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static ba.unsa.etf.rpr.tutorijal_3.FiksniBroj.Grad.*;
import static org.junit.jupiter.api.Assertions.*;

public class ImenikTest {

    @Test
    void dodaj() {
        Imenik imenik = new Imenik();
        imenik.dodaj("John Smith", new MedunarodniBroj("+1", "23 45-67-89"));
        imenik.dodaj("Hana Hanic", new FiksniBroj(SARAJEVO, "123-156"));
        imenik.dodaj("Meho Mehic", new FiksniBroj(SARAJEVO, "123-456"));
        assertEquals("033/123-456", imenik.dajBroj("Meho Mehic"));
    }
    @Test
    void dajBrojPrazno() {
        Imenik imenik = new Imenik();
        assertNull(null, imenik.dajBroj("Meho Mehic"));

    }

    @Test
    void dajIme() {
        Imenik imenik = new Imenik();
        imenik.dodaj("Ramo Ramic", new FiksniBroj(BRCKO, "123-4567"));
        imenik.dodaj("Feriz Ferizovic", new MedunarodniBroj("+387", "123-156"));
        imenik.dodaj("Meho Mehic", new FiksniBroj(SARAJEVO, "123-456"));
        assertEquals("Ramo Ramic", imenik.dajIme(new FiksniBroj(BRCKO, "123-4567")));
        assertEquals("Meho Mehic", imenik.dajIme(new FiksniBroj(SARAJEVO, "123-456")));
    }

    @Test
    void dajImePrazno() {
        Imenik imenik = new Imenik();
        assertNull(null, imenik.dajIme(new FiksniBroj(BRCKO, "123-4555")));

    }

    @Test
    void dajImeNoviGradovi() {
        Imenik imenik = new Imenik();
        imenik.dodaj("Ramo Ramic", new FiksniBroj(ZENICA, "123-4567"));
        imenik.dodaj("Feriz Ferizovic", new MedunarodniBroj("+387", "123-156"));
        imenik.dodaj("Meho Mehic", new FiksniBroj(MOSTAR, "123-456"));
        assertEquals("Ramo Ramic", imenik.dajIme(new FiksniBroj(ZENICA, "123-4567")));
        assertEquals("Meho Mehic", imenik.dajIme(new FiksniBroj(MOSTAR, "123-456")));
    }

    @Test
    void ispisMedunarodni() {
        Imenik imenik = new Imenik();
        imenik.dodaj("Ramo Ramic", new FiksniBroj(ZENICA, "123-4567"));
        imenik.dodaj("Feriz Ferizovic", new MedunarodniBroj("+387", "123-156"));
        imenik.dodaj("Meho Mehic", new FiksniBroj(MOSTAR, "123-456"));
        assertEquals("Feriz Ferizovic", imenik.dajIme(new MedunarodniBroj("+387", "123-156")));
    }
    @Test
    void ispisMoblini() {
        Imenik imenik = new Imenik();
        imenik.dodaj("Ramo Ramic", new MobilniBroj(62, "123-4567"));
        imenik.dodaj("Feriz Ferizovic", new MedunarodniBroj("+387", "123-156"));
        imenik.dodaj("Meho Mehic", new FiksniBroj(MOSTAR, "123-456"));
        assertEquals("Ramo Ramic", imenik.dajIme(new MobilniBroj(62, "123-4567")));
    }

    @Test
    void hashCodeTest() {
        /*Imenik imenik = new Imenik();
        imenik.dodaj("Ramo Ramic", new MobilniBroj(62, "123-4567"));
        assertEquals("Ramo Ramic", imenik.dajIme(new MobilniBroj.has(62, "123-4567")));*/
        FiksniBroj x = new FiksniBroj(SARAJEVO,"123-456");  // equals and hashCode check name field value
        FiksniBroj y = new FiksniBroj(ZENICA,"123-456");
        assertFalse(x.hashCode() == y.hashCode());

    }

    @Test
    void hashCodeTest2() {
        /*Imenik imenik = new Imenik();
        imenik.dodaj("Ramo Ramic", new MobilniBroj(62, "123-4567"));
        assertEquals("Ramo Ramic", imenik.dajIme(new MobilniBroj.has(62, "123-4567")));*/
        MedunarodniBroj x = new MedunarodniBroj("+358","123-456");  // equals and hashCode check name field value
        MedunarodniBroj y = new MedunarodniBroj("+358","123-456");
        assertTrue(x.hashCode() == y.hashCode());

    }

    @Test
    void hashCodeTest3() {
        /*Imenik imenik = new Imenik();
        imenik.dodaj("Ramo Ramic", new MobilniBroj(62, "123-4567"));
        assertEquals("Ramo Ramic", imenik.dajIme(new MobilniBroj.has(62, "123-4567")));*/
        MobilniBroj x = new MobilniBroj(62,"123-456");  // equals and hashCode check name field value
        MobilniBroj y = new MobilniBroj(52,"123-456");
        assertFalse(x.hashCode() == y.hashCode());

    }

    @Test
    void naSlovo() {
        Imenik imenik = new Imenik();
        imenik.dodaj("Sara Sarac", new FiksniBroj(SARAJEVO, "123-156"));
        imenik.dodaj("Pero Peric", new FiksniBroj(SARAJEVO, "123-656"));
        imenik.dodaj("Ivo Ivic", new MobilniBroj(61, "321-645"));
        imenik.dodaj("Jozo Jozic", new MobilniBroj(64, "987-654"));
        imenik.dodaj("John Smith", new MedunarodniBroj("+1", "23 45-67-89"));
        assertEquals("1. Ivo Ivic - 061/321-645", imenik.naSlovo('I').trim());
    }

    @Test
    void izGrada() {
        Imenik imenik = new Imenik();
        imenik.dodaj("Ivo Ivic", new FiksniBroj(SARAJEVO, "123-456"));
        imenik.dodaj("Sara Sarac", new FiksniBroj(SARAJEVO, "123-156"));
        imenik.dodaj("Meho Mehic", new FiksniBroj(SARAJEVO, "123-656"));
        imenik.dodaj("Pero Peric", new MobilniBroj(64, "987-654"));
        imenik.dodaj("John Smith", new MedunarodniBroj("+1", "23 45-67-89"));
        Set<String> set = imenik.izGrada(SARAJEVO);
        String result = "";
        for (String ime : set) {
            result += ime + ",";
        }
        assertEquals("Ivo Ivic,Meho Mehic,Sara Sarac,", result);
    }

    @Test
    void izGradaBrojevi() {
        Imenik imenik = new Imenik();
        imenik.dodaj("Ivo Ivic", new FiksniBroj(SARAJEVO, "123-456"));
        imenik.dodaj("Sara Sarac", new FiksniBroj(SARAJEVO, "123-156"));
        imenik.dodaj("Meho Mehic", new FiksniBroj(SARAJEVO, "123-656"));
        imenik.dodaj("Pero Peric", new MobilniBroj(64, "987-654"));
        imenik.dodaj("John Smith", new MedunarodniBroj("+1", "23 45-67-89"));
        Set<TelefonskiBroj> set = imenik.izGradaBrojevi(SARAJEVO);
        String result = "";
        for (TelefonskiBroj broj : set) {
            result += broj.ispisi() + ",";
        }
        assertEquals("033/123-156,033/123-456,033/123-656,", result);
    }

}