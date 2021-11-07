package ba.unsa.etf.rs.tutorijal11;

public class MedunarodniBroj extends TelefonskiBroj{
    private String drzava, broj;

    public MedunarodniBroj(String Drzava, String Broj) {
        drzava=Drzava;
        broj=Broj;
    }

    @Override
    public String ispisi() {
        return drzava + "/" + broj;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (drzava != null ? drzava.hashCode() : 0);
        result = 31 * result + (broj != null ? broj.hashCode() : 0);
        return result;
    }
}
