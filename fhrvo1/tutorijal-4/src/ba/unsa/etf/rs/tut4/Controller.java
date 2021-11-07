package ba.unsa.etf.rs.tut4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    public TextArea ulaz;
    public TextArea izlaz;
    public ChoiceBox<Artikal> choice;
    public Spinner spinner;
    public TextArea racun_izlaz;

    public void dodaj(ActionEvent actionEvent) {
        String ulazni = ulaz.getText();
        String[] red = ulazni.split("\n");
        ArrayList<Artikal> artikli = new ArrayList<>();
        for (int i=0; i<red.length; i++){
            String[] razvojeno = red[i].split (",");
            if (razvojeno.length == 3) {
                Artikal artikal = new Artikal(razvojeno[0],razvojeno[1],Double.parseDouble(razvojeno[2]));
                artikli.add(artikal);
            }
            else {
                throw new IllegalArgumentException("Niste unijeli 3 elementa");
            }
        }
        Artikal.izbaciDuplikate(artikli);
        String izlazni = "";
        for (Artikal artikal: artikli){
            izlazni = izlazni + artikal.toString();
        }
        izlaz.setText(izlazni);

        ObservableList<Artikal> stavke = FXCollections.observableArrayList(artikli);
        choice.setItems(stavke);


    }

    public void racun(ActionEvent actionEvent) {
        String artikal = choice.getSelectionModel().getSelectedItem().toString();
        int kolicina = (int) spinner.getValue();
        String izlazni = artikal.trim() + " " + kolicina + "\n";
        racun_izlaz.setText(racun_izlaz.getText() + izlazni);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Artikal> items = FXCollections.observableArrayList(
                new ArrayList<>(Arrays.asList(
                        new Artikal("HLB001","Crni hljeb", 1.0),
                        new Artikal("HLB002","Bijeli hljeb",1.20),
                        new Artikal("MLK001","Mlijeko Meggle",1.10)
                ))
        );
        choice.setItems(items);
        choice.getSelectionModel().selectFirst();
    }
}
