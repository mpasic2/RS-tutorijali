package ba.unsa.etf.rs.tutorijal11;

import org.junit.jupiter.api.Test;

import static ba.unsa.etf.rs.tutorijal11.FiksniBroj.Grad.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoviImenikTest {
    @Test
    void setGetGrad() {
        FiksniBroj broj = new FiksniBroj(SARAJEVO, "123-456");
        broj.setGrad(ZENICA);
        assertEquals(ZENICA, broj.getGrad());
    }

    @Test
    void ispisiMedunarodni() {
        MedunarodniBroj broj = new MedunarodniBroj("+1", "123-456");
        assertEquals("+1/123-456", broj.ispisi());
    }

    @Test
    void dajIme() {
        Imenik imenik = new Imenik();
        MedunarodniBroj broj = new MedunarodniBroj("+1", "123-456");
        assertEquals(null, imenik.dajIme(broj));
    }
}
