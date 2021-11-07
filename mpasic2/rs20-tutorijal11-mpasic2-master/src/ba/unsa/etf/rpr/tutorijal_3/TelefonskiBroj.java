package ba.unsa.etf.rpr.tutorijal_3;

public abstract class TelefonskiBroj implements Comparable<TelefonskiBroj> {

    public abstract String ispisi();
    public abstract int hashCode();

    @Override
    public int compareTo(TelefonskiBroj telefon) {
        return this.ispisi().compareTo(telefon.ispisi());
    }
}