package ba.unsa.etf.rpr.tutorijal_3;

public class MobilniBroj extends TelefonskiBroj{
private String mobliniNumber;
    MobilniBroj(int mobilnaMreza, String broj){
        this.mobliniNumber = "0" + mobilnaMreza + "/" + broj;
    }

    @Override
    public String ispisi() {
        return mobliniNumber;
    }

    @Override
    public int hashCode() {
        return mobliniNumber.hashCode();
    }
}
