package ba.unsa.etf.rs.tutorijal9;

import com.github.tsijercic1.InvalidXMLException;
import com.github.tsijercic1.Node;
import com.github.tsijercic1.XMLParser;

import java.beans.XMLEncoder;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Tutorijal {

    public static void main(String[] args) {
        ArrayList<Grad> gradovi = ucitajGradove();
        UN un = ucitajXml(gradovi);
        zapisiXML(un);
    }



    public static ArrayList<Grad> ucitajGradove() {
        ArrayList<Grad> gradovi = new ArrayList<>();
        File file = new File("mjerenja.txt");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = bufferedReader.readLine())!=null) {
                String[] data = s.split(",");
                if (data.length>2 && data.length<1001) {
                    String grad = data[0];
                    double temp[] = new double[data.length-1];
                    for (int i=1; i<data.length; i++) {
                        temp[i-1]=Double.parseDouble(data[i]);
                    }
                    gradovi.add(new Grad(grad, temp));
                } else {
                    throw new UnsupportedEncodingException("Broj temperatura veći od 1000!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gradovi;
    }

    public static UN ucitajXml(ArrayList<Grad> gradovi) {
        UN un = new UN();
        try {
            XMLParser xmlParser = new XMLParser("drzave.xml");
            Node root = xmlParser.getDocumentRootNode();
            ArrayList<Node> nodes = root.getChildNodes("drzava");
            for (Node node : nodes) {
                Node nazivNode = node.getChildNode("naziv");
                String naziv = nazivNode.getContent();
                Node povrsinaNode = node.getChildNode("povrsina");
                String jedinica= povrsinaNode.getAttributes().get("jedinica");
                double povrsina = Double.parseDouble(povrsinaNode.getContent());
                Node gradNode = node.getChildNode("glavnigrad");
                int stanovnika = Integer.parseInt(gradNode.getAttributes().get("stanovnika"));
                Node nazivGradaNode = gradNode.getChildNode("naziv");
                String ime_grada = nazivGradaNode.getContent();
                Grad glavni = new Grad ();
                glavni.setNaziv(ime_grada);

                Drzava drzava = new Drzava(naziv, jedinica, stanovnika, povrsina, glavni);
                un.addDrzava(drzava);
            }
        } catch (IOException | InvalidXMLException e) {

        }
        return un;
    }

    public static void zapisiXML (UN un) {
        try {
            XMLEncoder izlaz = new XMLEncoder(new FileOutputStream("un.xml"));
            izlaz.writeObject(un);
            izlaz.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
