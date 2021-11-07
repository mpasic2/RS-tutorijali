package ba.unsa.etf.rs;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Ukoliko zelite ispis gradove odaberite 1 ukoliko zelite da unesete drzavu odaberite 2");
        Scanner ulaz = new Scanner(System.in);
        if(ulaz.nextLine().equals("1")){
            String str = ispisiGradove();
            System.out.println(str);
        }
        else glavniGrad();

    }

    public static String ispisiGradove(){
        ArrayList<Grad> gradovi = GeografijaDAO.getInstance().gradovi();
        return gradovi.toString();

    }

    public static void glavniGrad(){
        Scanner ulaz = new Scanner(System.in);
        String drzava = ulaz.nextLine();
        Drzava drz = GeografijaDAO.getInstance().nadjiDrzavu(drzava);
        System.out.println("Glavni grad države " + drz.getNaziv() + " je " + drz.getGlavniGrad().getNaziv());
    }

}
