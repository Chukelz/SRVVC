package uk.ac.soton.SRVVC.scene;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.ac.soton.SRVVC.ui.Pane;
import uk.ac.soton.SRVVC.ui.Window;

import java.util.Objects;

public class UsernameScene extends BaseScene{

    private static final Logger logger = LogManager.getLogger(UsernameScene.class);

    public UsernameScene(Window gameWindow) {
        super(gameWindow);
        logger.info("Creating Username Scene");
    }

    @Override
    public void build() {
        root = new Pane(gameWindow.getWidth(),gameWindow.getHeight());

        var userPane = new StackPane();
        userPane.setMaxWidth(gameWindow.getWidth());
        userPane.setMaxHeight(gameWindow.getHeight());
        userPane.getStyleClass().add("menu-background");
        root.getChildren().add(userPane);

        var mainPane = new BorderPane();
        userPane.getChildren().add(mainPane);

        BorderPane top = new BorderPane();
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


        TextField username = new TextField("Enter your Username");
        Label ulabel = new Label("Username:");
        ulabel.getStyleClass().add("label");

        HBox box = new HBox();
        box.setAlignment(Pos.BOTTOM_CENTER);
        box.setPadding(new Insets(25,5,5,50));
        Button submit = new Button("Submit");
        submit.getStyleClass().add("menu-buttonb");
        box.getChildren().addAll(ulabel,username,submit);
        BorderPane.setAlignment(box, Pos.CENTER);

        submit.setOnAction(x -> verify(username.getText()));



        mainPane.setCenter(box);

    }

    private void verify(String u) {
        if(Objects.equals(u, "cue1u21")){
            gameWindow.startLoginp();
        }
        else{
            gameWindow.showNotification("Invalid Username");
            gameWindow.startLogin();
        }
    }

    public void initialise() {
    }
}
