package ba.unsa.etf.rs.tutorijal11;

import java.util.*;

public class Imenik extends TelefonskiBroj {
    private Map<TelefonskiBroj, String> brojevi;

    public Imenik() {
        brojevi = new HashMap<>();
    }

    public void dodaj(String ime, TelefonskiBroj broj) {
        brojevi.put(broj, ime);
    }

    public String dajBroj(String ime) {
        Set< Map.Entry<TelefonskiBroj, String> > st = brojevi.entrySet();
        for (Map.Entry<TelefonskiBroj, String> map:st)
        {
            if (map.getValue().equals(ime)) return map.getKey().ispisi();
        }
        return "";
    }

    public String dajIme(TelefonskiBroj broj) {
        return brojevi.get(broj);
    }

    public String naSlovo(char slovo) {
        String rezultat = "";
        int brojac=1;
        Set< Map.Entry<TelefonskiBroj, String> > st = brojevi.entrySet();
        for (Map.Entry<TelefonskiBroj, String> map:st)
        {
            if (map.getValue().charAt(0) == slovo){
                rezultat += brojac + ". " + map.getValue() + " - " + map.getKey().ispisi() + "\n";
                brojac++;
            }
        }
        return rezultat;
    }

    public Set<String> izGrada(FiksniBroj.Grad grad) {
        Set<String> setImena = new java.util.HashSet<>();
        Set< Map.Entry<TelefonskiBroj, String> > st = brojevi.entrySet();
        for (Map.Entry<TelefonskiBroj, String> map:st)
        {
           if (map.getKey() instanceof FiksniBroj) {
               if (((FiksniBroj) map.getKey()).getGrad().equals(grad)) {
                   setImena.add(map.getValue());
               }
           }
        }
        TreeSet<String> setImenaPoslozen = new TreeSet<String>(setImena);
        return setImenaPoslozen;
    }

    public Set<TelefonskiBroj> izGradaBrojevi(FiksniBroj.Grad grad) {
        Set<TelefonskiBroj> setBrojeva = new java.util.HashSet<>();
        Set< Map.Entry<TelefonskiBroj, String> > st = brojevi.entrySet();
        for (Map.Entry<TelefonskiBroj, String> map:st)
        {
            if (map.getKey() instanceof FiksniBroj) {
                if (((FiksniBroj) map.getKey()).getGrad().equals(grad)) {
                    setBrojeva.add(map.getKey());
                }
            }
        }
        TreeSet<TelefonskiBroj> setBrojevaPoslozeno = new TreeSet<>(setBrojeva);
        return setBrojevaPoslozeno;
    }
}
