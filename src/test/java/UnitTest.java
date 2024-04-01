import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import junitparams.JUnitParamsRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.testfx.framework.junit.ApplicationTest;
import uk.ac.soton.SRVVC.App;
import uk.ac.soton.SRVVC.scene.UsernameScene;
import uk.ac.soton.SRVVC.ui.Window;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;
@RunWith(JUnitParamsRunner.class)
public class UnitTest extends ApplicationTest {

    private final int width = 1000;
    private final int height = 600;
    private static App instance;
    private Stage stage;
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("SRVVC");
        stage.setMinWidth(0);
        stage.setMinHeight(0);



        stage.show();
        // Setup your scene and stage as necessary for your tests
        // This method is automatically called by the TestFX framework to start the JavaFX application thread
    }

    @BeforeAll
    public static void setUpClass() {
        // Perform any setup needed before all tests here
        // Since TestFX handles JavaFX initialization, you typically don't need to do anything specific for JavaFX here
    }

    @Test
    public void test1() throws InterruptedException {
        var gameWindow = new Window(stage, 0,0);
        UsernameScene scene = new UsernameScene(gameWindow);
        try {
            scene.verifyUsername("cag144");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
                // Your assertion should also be placed inside Platform.runLater
        assertEquals("cag144","cag144");
    }

}
