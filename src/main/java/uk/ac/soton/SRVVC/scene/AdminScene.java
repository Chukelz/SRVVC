package uk.ac.soton.SRVVC.scene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.ac.soton.SRVVC.DbConnect;
import uk.ac.soton.SRVVC.ui.Pane;
import uk.ac.soton.SRVVC.ui.Window;

public class AdminScene extends BaseScene {

    private static final Logger logger = LogManager.getLogger(MenuScene.class);
    public DbConnect db = new DbConnect();

    public AdminScene(Window gameWindow) {
        super(gameWindow);
        logger.info("Creating Menu Scene");
    }

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
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setPrefWidth(220);
        menuBox.setPadding(new Insets(100));
        menuBox.setSpacing(25);
        mainPane.setCenter(menuBox);
        BorderPane.setAlignment(menuBox, Pos.CENTER);

        BorderPane top = new BorderPane();
        top.getStyleClass().add("menu-buttonc");
        top.setPadding(new Insets(5,5,5,5));
        top.setPrefHeight(root.getHeight()*1/12);

        Button back = new Button("<- Back");
        back.setOnAction(x -> gameWindow.startMenu());
        back.setAlignment(Pos.CENTER_RIGHT);

        HBox leftHbox = new HBox();
        leftHbox.setSpacing(20);
        leftHbox.setAlignment(Pos.CENTER);
        leftHbox.setPadding(new Insets(5,5,5,5));
        leftHbox.getChildren().addAll(back);

        top.setLeft(leftHbox);
        top.setAlignment(leftHbox, Pos.CENTER);

        mainPane.setTop(top);

        //For now, let us just add a button that starts the game. I'm sure you'll do something way better.
        var buttona = new Button("Restart Election");
        buttona.getStyleClass().add("menu-buttond");

        menuBox.getChildren().addAll(buttona);

        buttona.setOnAction(actionEvent -> {
            try {
                ClearResults();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void ClearResults() throws ClassNotFoundException {
        db.ClearVotes();
    }

    @Override
    public void initialise() {

    }


}
