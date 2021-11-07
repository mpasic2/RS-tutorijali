package ba.unsa.etf.rs.tut5;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Arrays;

public class KorisnikController {

    private KorisniciModel model;

    public Button kraj;
    public Button dodaj;
    public ListView<Korisnik> listKorisnici;
    public TextField fldIme;
    public TextField fldPrezime;
    public TextField fldEmail;
    public TextField fldUsername;
    public PasswordField fldPassword;

    public KorisnikController (KorisniciModel m) {
        model = m;
    }

    @FXML
    public void initialize() {
        listKorisnici.setItems(model.getKorisnici());

        listKorisnici.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Korisnik>() {
            @Override
            public void changed(ObservableValue<? extends Korisnik> observableValue, Korisnik oldkorisnik, Korisnik newkorisnik) {
                fldIme.getStyleClass().add("poljeNijeIspravno");
                fldPrezime.getStyleClass().add("poljeNijeIspravno");
                fldEmail.getStyleClass().add("poljeNijeIspravno");
                fldUsername.getStyleClass().add("poljeNijeIspravno");
                fldPassword.getStyleClass().add("poljeNijeIspravno");

                if(oldkorisnik != null) {
                    fldIme.textProperty().unbindBidirectional(oldkorisnik.imeProperty());
                    fldPrezime.textProperty().unbindBidirectional(oldkorisnik.prezimeProperty());
                    fldEmail.textProperty().unbindBidirectional(oldkorisnik.emailProperty());
                    fldUsername.textProperty().unbindBidirectional(oldkorisnik.usernameProperty());
                    fldPassword.textProperty().unbindBidirectional(oldkorisnik.passwordProperty());
                }

                model.setTrenutniKorisnik(newkorisnik);
                fldIme.setText(model.getTrenutniKorisnik().getIme());
                fldPrezime.setText(model.getTrenutniKorisnik().getPrezime());
                fldEmail.setText(model.getTrenutniKorisnik().getEmail());
                fldUsername.setText(model.getTrenutniKorisnik().getUsername());
                fldPassword.setText(model.getTrenutniKorisnik().getPassword());

                if(newkorisnik != null) {
                    fldIme.getStyleClass().add("poljeIspravno");
                    fldPrezime.getStyleClass().add("poljeIspravno");
                    fldEmail.getStyleClass().add("poljeIspravno");
                    fldUsername.getStyleClass().add("poljeIspravno");
                    fldPassword.getStyleClass().add("poljeIspravno");

                    fldIme.textProperty().bindBidirectional(model.getTrenutniKorisnik().imeProperty());
                    fldPrezime.textProperty().bindBidirectional(model.getTrenutniKorisnik().prezimeProperty());
                    fldEmail.textProperty().bindBidirectional(model.getTrenutniKorisnik().emailProperty());
                    fldUsername.textProperty().bindBidirectional(model.getTrenutniKorisnik().usernameProperty());
                    fldPassword.textProperty().bindBidirectional(model.getTrenutniKorisnik().passwordProperty());
                }

            }
        });

        fldIme.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue!="") fldIme.getStyleClass().add("poljeIspravno");
                else if (newValue==" ") fldIme.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldPrezime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue!="") fldPrezime.getStyleClass().add("poljeIspravno");
                else if (newValue==" ") fldPrezime.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldEmail.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue!="") fldEmail.getStyleClass().add("poljeIspravno");
                else if (newValue==" ") fldEmail.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue!="") fldUsername.getStyleClass().add("poljeIspravno");
                else if (newValue==" ") fldUsername.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue!="") fldPassword.getStyleClass().add("poljeIspravno");
                else if (newValue==" ") fldPassword.getStyleClass().add("poljeNijeIspravno");
            }
        });


    }

    public void dodaj(ActionEvent actionEvent) {
        Korisnik novi = new Korisnik("","","","","");
        ObservableList<Korisnik> korisnici = model.getKorisnici();
        korisnici.add(novi);
        listKorisnici.getSelectionModel().select(korisnici.size()-1);


        fldIme.setText("");
        fldPrezime.setText("");
        fldEmail.setText("");
        fldUsername.setText("");
        fldPassword.setText("");
    }

    public void kraj(ActionEvent actionEvent) {
        Stage stage = (Stage) kraj.getScene().getWindow();
        stage.close();
    }

}

