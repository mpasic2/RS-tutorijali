package ba.unsa.etf.rpr.tutorijal_3;

public class MedunarodniBroj extends TelefonskiBroj{
    private String medjunarodni;
    MedunarodniBroj(String drzava, String broj){
        //odmah postavljen ispis tako da ga u metodu ispisi samo vracamo
        this.medjunarodni = drzava + "/" + broj;
    }


    @Override
    public String ispisi() {
        return medjunarodni;
    }

    @Override
    public int hashCode() {
        return medjunarodni.hashCode();
    }
}
