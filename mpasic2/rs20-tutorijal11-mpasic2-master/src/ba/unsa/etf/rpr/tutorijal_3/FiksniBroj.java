package ba.unsa.etf.rpr.tutorijal_3;

public class FiksniBroj extends TelefonskiBroj{

    enum Grad {SARAJEVO, TRAVNIK, ORASJE, ZENICA, TUZLA, BIHAC, LIVNO, SIROKIBRIJEG, GORAZDE, BRCKO, MOSTAR};
    String prviDioPozivnog, drugiDioPozivnog;
    private Grad grad;
    private String broj,glavniDio,pozivniBroj;
    FiksniBroj(Grad grad, String broj){
        this.grad=grad;
        this.broj=broj;
        glavniDio="";
        pozivniBroj="";

        //provjeravacemo koji je grad u pitanju
        //te dodati odgovarajuci dio pozivnog broja
        if(grad==Grad.SARAJEVO) glavniDio += 33;
        else if(grad==Grad.TRAVNIK) glavniDio += 30;
        else if(grad==Grad.ORASJE) glavniDio += 31;
        else if(grad==Grad.ZENICA) glavniDio += 32;
        else if(grad==Grad.TUZLA) glavniDio += 35;
        else if(grad==Grad.BIHAC) glavniDio += 37;
        else if(grad==Grad.LIVNO) glavniDio += 34;
        else if(grad==Grad.SIROKIBRIJEG) glavniDio += 39;
        else if(grad==Grad.GORAZDE) glavniDio += 38;
        else if(grad==Grad.BRCKO) glavniDio += 49;
        //(grad==Grad.MOSTAR)
        else glavniDio += 36;

        pozivniBroj = "0" + glavniDio + "/" + broj;


    }
    Grad getGrad(){
        return grad;
    }

    @Override
    public String ispisi() {
        return pozivniBroj;
    }

    @Override
    public int hashCode() {
        return pozivniBroj.hashCode();
    }

}
