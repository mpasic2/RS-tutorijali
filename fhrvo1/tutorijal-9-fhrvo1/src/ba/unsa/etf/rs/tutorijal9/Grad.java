package ba.unsa.etf.rs.tutorijal9;

public class Grad {
    private String naziv = "";
    private double temperature[] = new double[1000];

    public Grad() {
    }

    public Grad(String naziv, double[] temperature) {
        this.naziv = naziv;
        this.temperature = temperature;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double[] getTemperature() {
        return temperature;
    }

    public void setTemperature(double[] temperature) {
        this.temperature = temperature;
    }
}
