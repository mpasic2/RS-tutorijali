package ba.unsa.etf.rs.tutorijal9;

public class Drzava {
    private String naziv, jedinica;
    private int broj;
    private double povrsina;
    private Grad glavniGrad;

    public Drzava() {
    }

    public Drzava(String naziv, String jedinica, int broj, double povrsina, Grad glavniGrad) {
        this.naziv = naziv;
        this.jedinica = jedinica;
        this.broj = broj;
        this.povrsina = povrsina;
        this.glavniGrad = glavniGrad;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getJedinica() {
        return jedinica;
    }

    public void setJedinica(String jedinica) {
        this.jedinica = jedinica;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
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
}
