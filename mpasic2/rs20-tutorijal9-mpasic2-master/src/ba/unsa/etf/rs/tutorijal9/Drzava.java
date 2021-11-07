package ba.unsa.etf.rs.tutorijal9;

public class Drzava {

    private String naziv, jedinica;
    private int brojStanovnika;
    private double povrsina;
    private Grad glavniGrad;

    public Drzava() {
    }

    public Drzava(String naziv, String jedinica, int brojStanovnika, double povrsina, Grad glavniGrad) {
        this.naziv = naziv;
        this.jedinica = jedinica;
        this.brojStanovnika = brojStanovnika;
        this.povrsina = povrsina;
        this.glavniGrad = glavniGrad;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public double getPovrsina() {
        return povrsina;
    }

    public void setPovrsina(double povrsina) {
        this.povrsina = povrsina;
    }

    public Grad getGlavniGrad() {
        return glavniGrad;
    }

    public void setGlavniGrad(Grad glavniGrad) {
        this.glavniGrad = glavniGrad;
    }

    public String getJedinica() {
        return jedinica;
    }

    public void setJedinica(String jedinica) {
        this.jedinica = jedinica;
    }
}
