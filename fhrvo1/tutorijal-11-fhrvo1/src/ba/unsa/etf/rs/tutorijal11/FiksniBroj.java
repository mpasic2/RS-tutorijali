package ba.unsa.etf.rs.tutorijal11;

public class FiksniBroj extends TelefonskiBroj{
    enum Grad{
        TRAVNIK("030"),
        ORASJE("031"),
        ZENICA("032"),
        SARAJEVO("033"),
        LIVNO("034"),
        TUZLA("035"),
        MOSTAR("036"),
        BIHAC(" 037"),
        GORAZDE("038"),
        SIROKIBRIJEG("039"),
        BRCKO("049");

        private String broj;

        public String getBroj() {
            return broj;
        }

        Grad(String Broj){
            broj=Broj;
        }
    }

    private Grad grad;
    private String broj;

    public FiksniBroj(Grad grad, String Broj) {
        this.grad = grad;
        broj = Broj;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

    @Override
    public String ispisi() {
        return grad.getBroj() + "/" + broj;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (grad != null ? grad.hashCode() : 0);
        result = 31 * result + (broj != null ? broj.hashCode() : 0);
        return result;
    }
}
