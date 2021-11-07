package ba.unsa.etf.rs.tutorijal11;

public class MobilniBroj extends TelefonskiBroj{
    private int mobilnaMreza;
    private String broj;

    public MobilniBroj(int MobilnaMreza, String Broj) {
        mobilnaMreza = MobilnaMreza;
        broj = Broj;
    }

    @Override
    public String ispisi() {
        return "0" + mobilnaMreza + "/" + broj;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + mobilnaMreza;
        result = 31 * result + (broj != null ? broj.hashCode() : 0);
        return result;
    }
}
