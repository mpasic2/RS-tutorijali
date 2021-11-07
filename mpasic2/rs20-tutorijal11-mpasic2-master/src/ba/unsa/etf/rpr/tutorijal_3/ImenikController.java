package ba.unsa.etf.rpr.tutorijal_3;

import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javax.swing.text.TabableView;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ImenikController {

    public Imenik imenik = new Imenik();
    public TextField imeKorisnika;
    public TextField brojKorisnika;
    public TableView TBtabela;
    public HashMap<String, ArrayList<Integer>> mapa = new HashMap<String,ArrayList<Integer>>();

    public void dodavanjeNovog(ActionEvent actionEvent) {
        String ispitBroja = "";
        int duzinaBroja = brojKorisnika.getText().length();
        ispitBroja = brojKorisnika.getText();
        //provjeravamo da li broj koji korisnik aplikacije zeli dodati pocinje sa + ukoliko pocinje znamo da se radi o medjunarodnom broju
        if(ispitBroja.startsWith("+"))
            imenik.dodaj(imeKorisnika.getText(),new MedunarodniBroj(brojKorisnika.getText().substring(0,3),brojKorisnika.getText().substring(3,duzinaBroja-1)));
        else if(Integer.parseInt(ispitBroja.substring(0,2)) < 050 && Integer.parseInt(ispitBroja.substring(0,2)) > 030) {
            //ukoliko broj ne pocinje sa + onda cemo uzeti prve dvije cifre broja te njih provjeracvati
            int pozivni = Integer.parseInt(ispitBroja.substring(0,2));
            if(pozivni==032) imenik.dodaj(imeKorisnika.getText(), new FiksniBroj(FiksniBroj.Grad.ZENICA,brojKorisnika.getText().substring(3,duzinaBroja-1)));
            else if(pozivni==033) imenik.dodaj(imeKorisnika.getText(), new FiksniBroj(FiksniBroj.Grad.SARAJEVO,brojKorisnika.getText().substring(3,duzinaBroja-1)));
            else if(pozivni==030) imenik.dodaj(imeKorisnika.getText(), new FiksniBroj(FiksniBroj.Grad.TRAVNIK,brojKorisnika.getText().substring(3,duzinaBroja-1)));
            else if(pozivni==031) imenik.dodaj(imeKorisnika.getText(), new FiksniBroj(FiksniBroj.Grad.ORASJE,brojKorisnika.getText().substring(3,duzinaBroja-1)));
            else if(pozivni==035) imenik.dodaj(imeKorisnika.getText(), new FiksniBroj(FiksniBroj.Grad.TUZLA,brojKorisnika.getText().substring(3,duzinaBroja-1)));
            else if(pozivni==037) imenik.dodaj(imeKorisnika.getText(), new FiksniBroj(FiksniBroj.Grad.BIHAC,brojKorisnika.getText().substring(3,duzinaBroja-1)));
            else if(pozivni==034) imenik.dodaj(imeKorisnika.getText(), new FiksniBroj(FiksniBroj.Grad.LIVNO,brojKorisnika.getText().substring(3,duzinaBroja-1)));
            else if(pozivni==39) imenik.dodaj(imeKorisnika.getText(), new FiksniBroj(FiksniBroj.Grad.SIROKIBRIJEG,brojKorisnika.getText().substring(3,duzinaBroja-1)));
            else if(pozivni==38) imenik.dodaj(imeKorisnika.getText(), new FiksniBroj(FiksniBroj.Grad.GORAZDE,brojKorisnika.getText().substring(3,duzinaBroja-1)));
            else if(pozivni==49) imenik.dodaj(imeKorisnika.getText(), new FiksniBroj(FiksniBroj.Grad.BRCKO,brojKorisnika.getText().substring(3,duzinaBroja-1)));
            else imenik.dodaj(imeKorisnika.getText(), new FiksniBroj(FiksniBroj.Grad.MOSTAR,brojKorisnika.getText().substring(3,duzinaBroja-1)));
        }
        else{
            imenik.dodaj(imeKorisnika.getText(), new MobilniBroj(Integer.parseInt(brojKorisnika.getText().substring(0,2)),brojKorisnika.getText().substring(2,duzinaBroja-1)));
        }


    }
}
