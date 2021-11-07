package ba.unsa.etf.rs.tut4;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(ApplicationExtension.class)
class MainTest {

    @Start
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    public void DaLiSuDodaneStvariUTabu1(FxRobot robot) {
        robot.lookup("#dodaj").queryButton();
        robot.lookup("#ulaz").queryTextInputControl();
        robot.lookup("#izlaz").queryTextInputControl();
    }

    @Test
    public void DaLiSuDodaneStvariUTabu2(FxRobot robot) {
        robot.lookup("#racun").queryButton();
        robot.lookup("#racun_izlaz").queryTextInputControl();
        robot.lookup("#choice").query();
        robot.lookup("#spinner").query();
    }

    @Test
    public void UnosJednogArtikla(FxRobot robot) {
        robot.clickOn("#ulaz");
        robot.write("HLB001,Crni hljeb,1");
        robot.clickOn("#dodaj");
        TextArea textArea = (TextArea)robot.lookup("#izlaz").query();
        assertEquals("HLB001,Crni hljeb,1.0\n", textArea.getText());
    }

    @Test
    public void UnosTriArtikla(FxRobot robot) {
        robot.clickOn("#ulaz");
        robot.write("HLB001,Crni hljeb,1\n"+
                "HLB002,Bijeli hljeb,1.20\n" +
                "MLK001,Mlijeko Meggle,1.10");
        robot.clickOn("#dodaj");
        TextArea textArea = (TextArea)robot.lookup("#izlaz").query();
        assertEquals("HLB001,Crni hljeb,1.0\n" + "HLB002,Bijeli hljeb,1.2\n"
                + "MLK001,Mlijeko Meggle,1.1\n", textArea.getText());
    }

    @Test
    public void UnosKolicina1(FxRobot robot) {
        robot.clickOn("#tab_racun");
        robot.clickOn("#racun");
        TextArea textArea = (TextArea)robot.lookup("#racun_izlaz").query();
        assertEquals("HLB001,Crni hljeb,1.0 1\n", textArea.getText());
    }

    @Test
    public void UnosSelektovana2StavkaKolicina5(FxRobot robot) {
        robot.clickOn("#tab_racun");
        robot.clickOn("#choice");
        robot.clickOn("HLB002,Bijeli hljeb,1.2");
        robot.clickOn("#spinner");
        robot.clickOn("#spinner").clickOn(".increment-arrow-button");
        robot.clickOn("#racun");
        TextArea textArea = (TextArea)robot.lookup("#racun_izlaz").query();
        assertEquals("HLB002,Bijeli hljeb,1.2 2\n", textArea.getText());
    }
}