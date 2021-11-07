package ba.unsa.etf.rs.tut5;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;

public class KorisniciModel {

    private ObservableList<Korisnik> korisnici = FXCollections.observableArrayList();
    private SimpleObjectProperty<Korisnik> trenutniKorisnik = new SimpleObjectProperty();

    public Korisnik getTrenutniKorisnik() {
        return trenutniKorisnik.get();
    }

    public SimpleObjectProperty<Korisnik> trenutniKorisnikProperty() {
        return trenutniKorisnik;
    }

    public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
        this.trenutniKorisnik.set(trenutniKorisnik);
    }

    public KorisniciModel() {
    }


    public KorisniciModel(ObservableList<Korisnik> korisnici, SimpleObjectProperty trenutniKorisnik) {
        this.korisnici = korisnici;
        this.trenutniKorisnik = trenutniKorisnik;
    }

    public ObservableList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(ObservableList<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }


    public void napuni() {
        korisnici.add(new Korisnik("Vedran", "Ljubović", "vljubovic@etf.unsa.ba",
                "vljubovic", "pass"));
        korisnici.add(new Korisnik("Amra", "Delić", "adelic@etf.unsa.ba",
                "adelic", "pass"));
        korisnici.add(new Korisnik("Tarik", "Sijerčić", "tsijercic@etf.unsa.ba",
                "tsijercic", "pass"));
        korisnici.add(new Korisnik("Rijad", "Fejzić", "rfejzic@etf.unsa.ba",
                "rfejzic", "pass"));
    }

}
