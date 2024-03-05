package uk.ac.soton.SRVVC.scene;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.ac.soton.SRVVC.ui.Pane;
import uk.ac.soton.SRVVC.ui.Window;

/**
 * The main menu of the game. Provides a gateway to the rest of the game.
 */
public class MenuScene extends BaseScene {

    private static final Logger logger = LogManager.getLogger(MenuScene.class);

    /**
     * Create a new menu scene
     * @param gameWindow the Game Window this will be displayed in
     */
    public MenuScene(Window gameWindow) {

        super(gameWindow);
        logger.info("Creating Menu Scene");
    }

    /**
     * Build the menu layout
     */
    @Override
    public void build() {
        logger.info("Building " + this.getClass().getName());

        root = new Pane(gameWindow.getWidth(),gameWindow.getHeight());

        var menuPane = new StackPane();
        menuPane.setMaxWidth(gameWindow.getWidth());
        menuPane.setMaxHeight(gameWindow.getHeight());
        menuPane.getStyleClass().add("menu-background");
        root.getChildren().add(menuPane);

        var mainPane = new BorderPane();
        menuPane.getChildren().add(mainPane);

        VBox menuBox = new VBox();
        menuBox.setAlignment(Pos.BOTTOM_RIGHT);
        menuBox.setPrefWidth(220);
        menuBox.setPadding(new Insets(100));
        menuBox.setSpacing(25);
        mainPane.setCenter(menuBox);
        BorderPane.setAlignment(menuBox, Pos.BOTTOM_RIGHT);

        //Awful title
        var title = new Text("SRVVC");
        title.getStyleClass().add("bigtitle");
        mainPane.setTop(title);

        //For now, let us just add a button that starts the game. I'm sure you'll do something way better.
        var buttona = new Button("View Results");
        buttona.getStyleClass().add("menu-buttond");

        var buttonb = new Button("Upload Results");
        buttonb.getStyleClass().add("menu-buttond");

        var buttonc = new Button("Admin Actions");
        buttonc.getStyleClass().add("menu-buttond");


        menuBox.getChildren().addAll(buttona,buttonb,buttonc);

        //Bind the button action to the startGame method in the menu
        buttona.setOnAction(this::startResults);
        buttonb.setOnAction(this::login);
        //buttonc.setOnAction(this::start);
    }

    private void startResults(ActionEvent actionEvent) {
        gameWindow.startResults();
    }

    /**
     * Initialise the menu
     */
    @Override
    public void initialise() {

    }

    /**
     * Handle when the Start Game button is pressed
     * @param event event
     */


    private void login(ActionEvent event) {
        gameWindow.startLogin();
    }

}
