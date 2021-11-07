package ba.unsa.etf.rs.tutorijal10;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Controller {
    public TextField ulaz;
    public TextArea izlaz;

    public void posalji(ActionEvent actionEvent) {
        int broj = Integer.parseInt(ulaz.getText());
        try {
            URL url = new URL("http://c9.etf.unsa.ba/proba/postanskiBroj.php?postanskiBroj=" + broj);
            //URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            URLConnection conn = url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.addRequestProperty("Content-type", "application/json;");
/*
            JSONObject json = new JSONObject();
            json.put("broj", broj);
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(json.toString().getBytes());
            outputStream.close();*/

            InputStream inputStream = conn.getInputStream();
            String rezultat = "";
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()){

                rezultat += scanner.nextLine() + "\n";
            }

            scanner.close();
            inputStream.close();
            String finalRezultat = rezultat.trim();
            Platform.runLater(()->{
                izlaz.setText(finalRezultat);
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
