package ba.unsa.etf.rs;

import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException{
        boolean pogodjenBroj=false;

        while(!pogodjenBroj){
            System.out.println("Unesi broj: ");
            Scanner uneseni = new Scanner(System.in);
            int br = uneseni.nextInt();
            String brPoslani = "{\"broj\": \"" + br + "\"}";

            String url = "https://web-tutorijal-10.herokuapp.com/pokusaj?username=mpasic2";
            URL link = new URL(url);
            HttpURLConnection konekcija = (HttpURLConnection) link.openConnection();
            konekcija.setRequestMethod("POST");
            konekcija.setRequestProperty("Content-Type","application/json; utf-8");
            konekcija.setRequestProperty("Accept","application/json");
            konekcija.setDoOutput(true);

            OutputStream out = konekcija.getOutputStream();
            byte[] ulaz = brPoslani.getBytes("utf-8");
            out.write(ulaz,0,ulaz.length);

            BufferedReader input = new BufferedReader(new InputStreamReader(konekcija.getInputStream(),"utf-8"));
            String js = "", line = null;
            while ((line = input.readLine())!=null){
                js=js+line;
            }

            JSONObject res = new JSONObject(js);


            System.out.println(res.get("message"));
            if(res.get("message").toString().contains("Pogodak"))
                    pogodjenBroj=true;


        }


    }



}
