package uk.ac.soton.SRVVC.ui;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.ac.soton.SRVVC.App;
import uk.ac.soton.SRVVC.scene.*;

/**
 * The GameWindow is the single window for the game where everything takes place. To move between screens in the game,
 * we simply change the scene.
 *
 * The GameWindow has methods to launch each of the different parts of the game by switching scenes. You can add more
 * methods here to add more screens to the game.
 */
public class Window {

    private static final Logger logger = LogManager.getLogger(Window.class);

    private final int width;
    private final int height;

    private final Stage stage;

    private BaseScene currentScene;
    private Scene scene;
    public String user;



    /**
     * Create a new GameWindow attached to the given stage with the specified width and height
     * @param stage stage
     * @param width width
     * @param height height
     */
    public Window(Stage stage, int width, int height) {
        this.width = width;
        this.height = height;

        this.stage = stage;
        this.user = user;

        //Setup window
        setupStage();

        //Setup resources
        setupResources();

        //Setup default scene
        setupDefaultScene();

        //Go to menu
        startMenu();
    }

    /**
     * Setup the font and any other resources we need
     */
    private void setupResources() {
        logger.info("Loading resources");

        //We need to load fonts here due to the Font loader bug with spaces in URLs in the CSS files
        Font.loadFont(getClass().getResourceAsStream("/style/Orbitron-Regular.ttf"),32);
        Font.loadFont(getClass().getResourceAsStream("/style/Orbitron-Bold.ttf"),32);
        Font.loadFont(getClass().getResourceAsStream("/style/Orbitron-ExtraBold.ttf"),32);
    }

    /**
     * Display the main menu
     */
    public void startMenu() {
        loadScene(new MenuScene(this));
    }

    public void startLogin() {
        loadScene(new UsernameScene(this));
    }

    public void startLoginp() {
        loadScene(new PasswordScene(this));
    }

    /**
     * Display the single player challenge
     */
    public void startChallenge() { loadScene(new ChallengeScene(this)); }

    /**
     * Setup the default settings for the stage itself (the window), such as the title and minimum width and height.
     */
    public void setupStage() {
        stage.setTitle("SRVVC");
        stage.setMinWidth(width);
        stage.setMinHeight(height + 20);
        stage.setOnCloseRequest(ev -> App.getInstance().shutdown());
    }

    /**
     * Load a given scene which extends BaseScene and switch over.
     * @param newScene new scene to load
     */
    public void loadScene(BaseScene newScene) {
        //Cleanup remains of the previous scene
        cleanup();

        //Create the new scene and set it up
        newScene.build();
        currentScene = newScene;
        scene = newScene.setScene();
        stage.setScene(scene);

        //Initialise the scene when ready
        Platform.runLater(() -> currentScene.initialise());
    }

    /**
     * Setup the default scene (an empty black scene) when no scene is loaded
     */
    public void setupDefaultScene() {
        this.scene = new Scene(new Pane(),width,height, Color.BLACK);
        stage.setScene(this.scene);
    }

    /**
     * When switching scenes, perform any cleanup needed, such as removing previous listeners
     */
    public void cleanup() {
        logger.info("Clearing up previous scene");
    }

    /**
     * Get the current scene being displayed
     * @return scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Get the width of the Game Window
     * @return width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Get the height of the Game Window
     * @return height
     */
    public int getHeight() {
        return this.height;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user){
        this.user = user;
    }

    public void showNotification(String message) {
        logger.info("Shown Notifications: " + message);
        Label label = new Label(message);
        label.setStyle("-fx-background-color: #333333; -fx-text-fill: white; -fx-padding: 10px;");
        label.setOpacity(0);

        javafx.stage.Popup popup = new Popup();
        popup.getContent().add(label);

        popup.setAutoHide(true);
        popup.setHideOnEscape(true);

        popup.show(stage);
        //popup.setX(stage.getX() + stage.getWidth() - label.getWidth());
        popup.setY(stage.getY() + stage.getHeight() - label.getHeight() - 40);
        // updateLabelPosition(label, stage); // Set initial label position

        FadeTransition ft = new FadeTransition(Duration.millis(800), label);
        ft.setToValue(1);
        ft.setOnFinished(evt -> {
            new Thread(() -> {
                try {
                    Thread.sleep(1800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    FadeTransition ft2 = new FadeTransition(Duration.millis(2000), label);
                    ft2.setToValue(0);
                    ft2.setOnFinished(evt2 -> popup.hide());
                    ft2.play();
                });
            }).start();
        });
        ft.play();
    }
}
