package ba.unsa.etf.rpr.tutorijal_3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Imenik {
    private Map<String, TelefonskiBroj> imenik;

    public Imenik() {
        imenik = new HashMap<>();
    }

    public void dodaj(String ime, TelefonskiBroj broj) {
        imenik.put(ime, broj);
    }

    public String dajBroj(String ime) {
        TelefonskiBroj telefonskiBroj = imenik.get(ime);
        if(telefonskiBroj==null)
            return null;
        else
            return telefonskiBroj.ispisi();
    }

    public String dajIme(TelefonskiBroj broj) {
        //prolazimo iteratorom kroz mapu
        for (Map.Entry<String, TelefonskiBroj> it : imenik.entrySet()) {
            if(it.getValue().ispisi().equals(broj.ispisi())) {   //poredimo da li su brojevi isti
                return it.getKey(); //vracamo osobu ciji je broj jednak onom poslamon parametru
            }
        }
        return null;
    }

    public String naSlovo(char prvoSlovo) {
        StringBuilder rezultat = new StringBuilder();

        int brojac = 1;
        //prolazimo iteratorom kroz mapu
        for (Map.Entry<String, TelefonskiBroj> it : imenik.entrySet()) {
            if (it.getKey().charAt(0) == prvoSlovo) {//poredimo da li su slova ista
                rezultat.append(String.format("%d. %s - %s\n", brojac, it.getKey(), it.getValue().ispisi()));
                brojac++;
            }
        }

        return rezultat.toString();
    }

    public Set<String> izGrada(FiksniBroj.Grad grad) {
        Set<String> rezultat = new TreeSet<>();
        //prolazimo iteratorom kroz mapu
        for (Map.Entry<String, TelefonskiBroj> it : imenik.entrySet()) {
            //gledamo pizivni broj da li odgovara gradu i da li je to uopste fiksni broj
            if (it.getValue() instanceof FiksniBroj && ((FiksniBroj) it.getValue()).getGrad().equals(grad)) {
                rezultat.add(it.getKey());
            }
        }

        return rezultat;
    }

    public Set<TelefonskiBroj> izGradaBrojevi(FiksniBroj.Grad grad) {
        Set<TelefonskiBroj> rezultat = new TreeSet<>();
        //ista kao prethodna samo umjesto imena gledmao brojeve
        for (Map.Entry<String, TelefonskiBroj> it : imenik.entrySet()) {
            if (it.getValue() instanceof FiksniBroj && ((FiksniBroj) it.getValue()).getGrad().equals(grad)) {
                rezultat.add(it.getValue());
            }
        }

        return rezultat;
    }
}