package ba.unsa.etf.rs.tutorijal11;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class TestFXML {

    @Start
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/tutorijal11.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
    }

    @Test
    public void test1 (FxRobot robot) {
        //test dodavanja Medunardnog broja i pretraga broja po imenu
        robot.clickOn("#cbTipBroja");
        robot.clickOn("Međunarodni broj");
        robot.clickOn("#tfIme");
        robot.write("ime");
        robot.clickOn("#tfPozivni");
        robot.write("+1");
        robot.clickOn("#tfOstatak");
        robot.write("123-456");
        robot.clickOn("#dodaj");

        robot.clickOn("#tabIspisi");
        robot.clickOn("#cbTipPretrage");
        robot.clickOn("Po imenu i prezimenu");
        robot.clickOn("#tfPretraga");
        robot.write("ime");
        robot.clickOn("#ispisi");
        TextArea textArea = (TextArea)robot.lookup("#tAreaIzlaz").query();
        assertEquals("+1/123-456",textArea.getText());
    }

    @Test
    public void test2 (FxRobot robot) {
        //traženje nepostećeg broja, očekuje se Alert za Error
        robot.clickOn("#tabIspisi");
        robot.clickOn("#cbTipPretrage");
        robot.clickOn("Po broju");
        robot.clickOn("#tfPretraga");
        robot.write("061/789-456");
        robot.clickOn("#ispisi");
        TextArea textArea = (TextArea)robot.lookup("#tAreaIzlaz").query();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.lookup(".dialog-pane").tryQuery().isPresent();
        DialogPane dp = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        Button okButton = (Button) dp.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);
        assertEquals("",textArea.getText());
    }

    @Test
    public void test3 (FxRobot robot) {
        //kreiranje Mobilnog broja i pretraga na slovo
        robot.clickOn("#cbTipBroja");
        robot.clickOn("Mobilni broj");
        robot.clickOn("#tfIme");
        robot.write("ime");
        robot.clickOn("#tfPozivni");
        robot.write("61");
        robot.clickOn("#tfOstatak");
        robot.write("123-456");
        robot.clickOn("#dodaj");

        robot.clickOn("#tabIspisi");
        robot.clickOn("#cbTipPretrage");
        robot.clickOn("Na slovo");
        robot.clickOn("#tfPretraga");
        robot.write("i");
        robot.clickOn("#ispisi");
        TextArea textArea = (TextArea)robot.lookup("#tAreaIzlaz").query();
        assertEquals("1. ime - 061/123-456",textArea.getText().trim());
    }

    @Test
    public void test4 (FxRobot robot) {
        //kreiranje Fiksnog broja i pretraga po gradu
        robot.clickOn("#cbTipBroja");
        robot.clickOn("Fiksni broj");
        robot.clickOn("#tfIme");
        robot.write("ime");
        robot.clickOn("#tfPozivni");
        robot.write("SARAJEVO");
        robot.clickOn("#tfOstatak");
        robot.write("123-456");
        robot.clickOn("#dodaj");

        robot.clickOn("#tfIme");
        robot.write("Neko");
        robot.clickOn("#tfPozivni");
        robot.write("SARAJEVO");
        robot.clickOn("#tfOstatak");
        robot.write("456-789");
        robot.clickOn("#dodaj");

        robot.clickOn("#tfIme");
        robot.write("Mujo");
        robot.clickOn("#tfPozivni");
        robot.write("ZENICA");
        robot.clickOn("#tfOstatak");
        robot.write("789-123");
        robot.clickOn("#dodaj");

        robot.clickOn("#tabIspisi");
        robot.clickOn("#cbTipPretrage");
        robot.clickOn("Po gradu");
        robot.clickOn("#tfPretraga");
        robot.write("SARAJEVO");
        robot.clickOn("#ispisi");
        TextArea textArea = (TextArea)robot.lookup("#tAreaIzlaz").query();
        assertEquals("Neko 033/123-456\n" +
                                "ime 033/456-789",
                                textArea.getText().trim());
    }

}
