package ba.unsa.etf.rs.tut4;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class KasaController {
    public TextArea txtUlaz;
    public TextArea txtIzlaz;
    public void addToList(ActionEvent actionEvent) {
        ArrayList<Artikal> artikli = new ArrayList<>();
        String tekstTxt1 = txtUlaz.getText();
        String[] redoviArtikla;
        redoviArtikla=tekstTxt1.split("\n");
        int i;
        String izlaz ="";

        for(i=0;i<redoviArtikla.length;i++){
            String[] redoviPoZarezu = redoviArtikla[i].split(",");
            if(redoviPoZarezu.length==3){
                Artikal art = new Artikal(redoviPoZarezu[0],redoviPoZarezu[1],Double.parseDouble(redoviPoZarezu[2]));
                artikli.add(art);
            }
        }
        Artikal.izbaciDuplikate(artikli);
        for(Artikal artikal : artikli)
                izlaz+=artikal.toString() +"\n";
        txtIzlaz.setText(izlaz);
    }
}
