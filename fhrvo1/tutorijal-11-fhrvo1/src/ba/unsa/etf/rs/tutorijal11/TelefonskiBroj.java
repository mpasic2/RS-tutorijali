package ba.unsa.etf.rs.tutorijal11;

public abstract class TelefonskiBroj implements Comparable<TelefonskiBroj>{
    public String ispisi() {
        return null;
    }

    public int hashCode() {
        return 0;
    }

    @Override
    public int compareTo(TelefonskiBroj drugi) {
        String broj1 = ispisi(), broj2 = drugi.ispisi();
        return broj1.compareTo(broj2);
    }
}
