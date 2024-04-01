package uk.ac.soton.SRVVC.scene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.ac.soton.SRVVC.DbConnect;
import uk.ac.soton.SRVVC.ui.Pane;
import uk.ac.soton.SRVVC.ui.Window;

import java.util.Objects;

public class OTPScene extends BaseScene {
    private static final Logger logger = LogManager.getLogger(PasswordScene.class);

    public DbConnect db = new DbConnect();

    public OTPScene(Window gameWindow) {
        super(gameWindow);
        logger.info("Creating Password Scene");
    }

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
        back.setOnAction(x -> gameWindow.startLogin());
        back.setAlignment(Pos.CENTER_RIGHT);

        HBox leftHbox = new HBox();
        leftHbox.setSpacing(20);
        leftHbox.setAlignment(Pos.CENTER);
        leftHbox.setPadding(new Insets(5,5,5,5));
        leftHbox.getChildren().addAll(back);

        top.setLeft(leftHbox);
        top.setAlignment(leftHbox, Pos.CENTER);

        mainPane.setTop(top);


        PasswordField username = new PasswordField();
        username.setPrefWidth(185);
        TextField pass = new TextField();
        ToggleButton toggle = new ToggleButton("Show/Hide");
        Label ulabel = new Label("OTP:");
        ulabel.getStyleClass().add("label");

        HBox box = new HBox();
        box.setAlignment(Pos.BOTTOM_CENTER);
        box.setPadding(new Insets(25,5,5,50));
        Button submit = new Button("Submit");
        submit.getStyleClass().add("menu-buttonb");
        box.getChildren().addAll(ulabel,username,pass,toggle,submit);

        username.textProperty().bindBidirectional(pass.textProperty());
        username.visibleProperty().bind(toggle.selectedProperty().not());
        pass.visibleProperty().bind(toggle.selectedProperty());

        BorderPane.setAlignment(box, Pos.CENTER);

        submit.setOnAction(x -> {
            try {
                verifyOTP(username.getText());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        mainPane.setCenter(box);

    }

    private void verifyOTP(String u) throws ClassNotFoundException {
        int number = 0;
        try {
            number = Integer.parseInt(u); //convert integer to number
            System.out.println("The integer value is: " + number);
        } catch (NumberFormatException e) {
            gameWindow.showNotification("Enter a number");//tell user to enter a number if he does not
            System.out.println("Error: The string cannot be converted to an integer.");
        }
        if (Objects.equals(gameWindow.getRand(), number)) {
            gameWindow.startInputVotes();//if the otp is correct, take the user to input votes screen
            logger.info("Going to input screen");
        } else {
            gameWindow.showNotification("Invalid OTP");//tell user if the otp is wrong
            gameWindow.startLogin();
        }
    }

    public void initialise() {
    }
}
