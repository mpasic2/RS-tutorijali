package ba.unsa.etf.rs.tutorijal9;


import com.github.tsijercic1.xml.common.InvalidXMLException;
import com.github.tsijercic1.xml.common.Node;
import com.github.tsijercic1.xml.parser.XMLParser;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

public class Tutorijal {

    public static void main(String[] args) {
    }


    public static ArrayList<Grad> ucitajGradove() {
        ArrayList<Grad> sviGradovi = new ArrayList<>();

        try {
            File file = new File("mjerenja.txt");
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String sadrzaj = "";
            while((sadrzaj = bf.readLine())!=null){
                String[] gradoviDat = sadrzaj.split(",");

                if(gradoviDat.length>1000) throw new UnsupportedEncodingException("Prevelik broj temperatura!");
                else {
                    String nazivGrada = gradoviDat[0];
                    //double[] temperature = new double[gradoviDat.length-1];
                    double[] temperature = new double[gradoviDat.length];
                    for (int i = 1; i < gradoviDat.length; i++)
                        temperature[i-1] = Double.parseDouble(gradoviDat[i]);

                    Grad grd = new Grad(nazivGrada, temperature);
                    sviGradovi.add(grd);
                }


            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        //sviGradovi.add(new Grad());
        return sviGradovi;
    }

    public static UN ucitajXml(ArrayList<Grad> gradovi) {
        UN un = new UN();


        try{
            XMLParser xml = new XMLParser("drzave.xml");
            Node rootNode = xml.getDocumentRootNode();
            ArrayList<Node> nodovi = rootNode.getChildNodes("drzava");
            for(Node nd : nodovi){
                String nazivDrz = "", nazivGrd = "", jedinica="";
                double povrsina = 0;
                int brStan;


                Node nazivNd = nd.getChildNode("naziv");
                Node povrsinaNd = nd.getChildNode("povrsina");
                Node gradNd = nd.getChildNode("glavnigrad");
                Node nazivGrdNd = gradNd.getChildNode("naziv");

                nazivDrz=nazivNd.getContent();
                jedinica=povrsinaNd.getAttributes().get("jedinica");
                povrsina= Double.parseDouble(povrsinaNd.getContent());
                brStan= Integer.parseInt(gradNd.getAttributes().get("stanovnika"));
                nazivGrd=nazivGrdNd.getContent();

                Grad gl = new Grad(nazivGrd);
                Drzava drz = new Drzava(nazivDrz,jedinica,brStan,povrsina,gl);
                un.setDrzave(drz);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidXMLException e) {
            e.printStackTrace();
        }

        return un;
    }


    private static void zapisiXML(UN un){
        try {
            FileOutputStream fos = new FileOutputStream("un.xml");
            XMLEncoder out = new XMLEncoder(fos);
            out.writeObject(un);
            out.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }



}
