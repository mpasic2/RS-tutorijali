package ba.unsa.etf.rs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/glavna.fxml"));
        GlavnaController ctrl = new GlavnaController();
        loader.setController(ctrl);
        Parent root = loader.load();
        primaryStage.setTitle("Gradovi svijeta");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
/*

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

*/