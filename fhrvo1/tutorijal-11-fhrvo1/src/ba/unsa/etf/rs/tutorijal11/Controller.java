package ba.unsa.etf.rs.tutorijal11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Iterator;
import java.util.Set;

import static ba.unsa.etf.rs.tutorijal11.FiksniBroj.Grad.SARAJEVO;

public class Controller {
    public ChoiceBox<String> cbTipBroja;
    public ChoiceBox<String> cbTipPretrage;

    public TextField tfIme;
    public TextField tfPozivni;
    public TextField tfOstatak;
    public TextField tfPretraga;

    public TextArea tAreaDodaj;
    public TextArea tAreaIspisi;
    public TextArea tAreaIzlaz;

    private Imenik imenik = new Imenik();

    @FXML
    public void initialize() {
        cbTipBroja.getItems().add("Međunarodni broj");
        cbTipBroja.getItems().add("Mobilni broj");
        cbTipBroja.getItems().add("Fiksni broj");
        tAreaDodaj.setText("Kao pozivni broj za dodavanje medunarodnog broja unesite format \"+broj\" ili \"00broj\"," +
                "\nza mobilni unesite \"6broj\", a za fiksni unesite naziv grada");

        cbTipPretrage.getItems().add("Po imenu i prezimenu");
        cbTipPretrage.getItems().add("Po broju");
        cbTipPretrage.getItems().add("Po gradu");
        cbTipPretrage.getItems().add("Na slovo");
        tAreaIspisi.setText("U polje unesite vrijednost unesite vrijednost.\n" +
                "Kada odaberete \"Po imenu i prezimenu\" unesite ime i prezime osobe čiji broj želite.\n" +
                "Kada odaberete \"Po broju\" unesite broj telefona. Kada odaberete \"Po gradu\" grad iz kojeg želite brojeve.\n" +
                "Kada odaberete \"Na slovo\" unesite početno slovo osobe / osoba želite da dobijete.");
    }

    public void dodaj(ActionEvent actionEvent) {
        int izabrano = cbTipBroja.getSelectionModel().getSelectedIndex();
        if (izabrano==0) addMedunarodni();
        else if (izabrano==1) addMobilni();
        else if (izabrano==2) addFiksni();
        tfIme.setText("");
        tfPozivni.setText("");
        tfOstatak.setText("");
    }

    public void addMedunarodni(){
        try {
            MedunarodniBroj broj = new MedunarodniBroj(tfPozivni.getText(), tfOstatak.getText());
            imenik.dodaj(tfIme.getText(), broj);
        } catch (Exception e){  pograsanFormat();  }
    }

    public void addMobilni(){
        try {
        MobilniBroj broj = new MobilniBroj(Integer.parseInt(tfPozivni.getText()), tfOstatak.getText());
        imenik.dodaj(tfIme.getText(), broj);
        } catch (Exception e){  pograsanFormat(); }
    }

    public void addFiksni(){
        try {
        FiksniBroj.Grad grad = FiksniBroj.Grad.valueOf(tfPozivni.getText());
        FiksniBroj broj = new FiksniBroj(grad, tfOstatak.getText());
        imenik.dodaj(tfIme.getText(), broj);
        } catch (Exception e){ pograsanFormat(); }
    }

    public void pograsanFormat(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Greška");
        alert.setHeaderText("Došlo je do greške!");
        alert.setContentText("Unijeli ste neispravne podatke.");
        alert.showAndWait();
    }

    public void ispisi(ActionEvent actionEvent) {
        int izabrano = cbTipPretrage.getSelectionModel().getSelectedIndex();
        if (izabrano==0) pretragaIme();
        else if (izabrano==1) pretragaBroj();
        else if (izabrano==2) pretragaGrad();
        else if (izabrano==3) pretragaSlovo();
        tfPretraga.setText("");
    }

    private void pretragaIme() {
        try{
            String rezultat = imenik.dajBroj(tfPretraga.getText().trim());
            if (rezultat.length()==0) tAreaIzlaz.setText("Nema rezultata");
            else tAreaIzlaz.setText(rezultat);
        } catch (Exception e){ pograsanFormat(); }
    }

    private void pretragaBroj() {
        try{
            FiksniBroj broj = new FiksniBroj(FiksniBroj.Grad.SARAJEVO, tfPretraga.getText().trim());
            String rezultat = imenik.dajIme(broj);
            if (rezultat.length()==0) tAreaIzlaz.setText("Nema rezultata");
            else tAreaIzlaz.setText(rezultat);
        } catch (Exception e){  pograsanFormat(); }
    }

    private void pretragaGrad() {
        try{
            FiksniBroj.Grad grad = FiksniBroj.Grad.valueOf(tfPretraga.getText().trim());
            Set<String> imena = imenik.izGrada(grad);
            Set<TelefonskiBroj> brojevi = imenik.izGradaBrojevi(grad);
            String rezultat = "";
            Iterator imenaIterator = imena.iterator();
            Iterator brojeviIterator = brojevi.iterator();
            for (TelefonskiBroj broj : brojevi) {
                rezultat+= imenaIterator.next() + " " +
                        broj.ispisi() + "\n";
            }
            if (rezultat.length()==0) tAreaIzlaz.setText("Nema rezultata");
            else tAreaIzlaz.setText(rezultat);
        } catch (Exception e){ pograsanFormat(); }
    }

    private void pretragaSlovo() {
        try{
            String unos = tfPretraga.getText().trim();
            if (unos.length()!=1) throw new IllegalArgumentException();
            String rezultat = imenik.naSlovo(unos.charAt(0));
            if (rezultat.length()==0) tAreaIzlaz.setText("Nema rezultata");
            else tAreaIzlaz.setText(rezultat);
        } catch (Exception e){ pograsanFormat(); }
    }
}
