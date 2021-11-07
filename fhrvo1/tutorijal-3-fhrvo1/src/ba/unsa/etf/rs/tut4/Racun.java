package ba.unsa.etf.rs.tut4;

import java.util.ArrayList;

public class Racun {
  public static class StavkaRacuna {
    private ArrayList<Artikal> artikli;
    private Integer kolicina;

    public StavkaRacuna(Artikal artikal, int i) {
      this.artikli = artikli;
      this.kolicina = kolicina;
    }

    public ArrayList<Artikal> getArtikli() {
      return artikli;
    }

    public void setArtikli(ArrayList<Artikal> artikli) {
      this.artikli = artikli;
    }

    public Integer getKolicina() {
      return kolicina;
    }

    public void setKolicina(Integer kolicina) {
      this.kolicina = kolicina;
    }
  }

  public ArrayList<StavkaRacuna> stavke_racuna;

  public Racun(){
    stavke_racuna= new ArrayList<>();
  }

  public void dodajStavku(Artikal artikal, int i) {
      stavke_racuna.add(new StavkaRacuna(artikal, i));
  }


  public double ukupanIznos() {
    double suma=0;
    for (StavkaRacuna stavkaRacuna : stavke_racuna) {
      suma += stavkaRacuna.getKolicina();
    }
    return suma;
  }
}
