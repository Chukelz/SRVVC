package uk.ac.soton.SRVVC.scene;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.ac.soton.SRVVC.DbConnect;
import uk.ac.soton.SRVVC.ui.Pane;
import uk.ac.soton.SRVVC.ui.Window;

import java.util.Objects;

public class InputVotesa extends BaseScene {

    private static final Logger logger = LogManager.getLogger(PasswordScene.class);

    public InputVotesa(Window gameWindow) {
        super(gameWindow);
        logger.info("Creating Password Scene");
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

        TextField apc = new TextField();
        apc.setPrefWidth(120);
        Label apcLabel = new Label("  APC: ");
        apcLabel.getStyleClass().add("labelb");

        TextField pdp = new TextField();
        pdp.setPrefWidth(120);
        Label pdpLabel = new Label("  PDP: ");
        pdpLabel.getStyleClass().add("labelb");

        TextField lp = new TextField();
        lp.setPrefWidth(120);
        Label lpLabel = new Label("  LP: ");
        lpLabel.getStyleClass().add("labelb");

        TextField apga = new TextField();
        apga.setPrefWidth(120);
        Label apgaLabel = new Label("  APGA: ");
        apgaLabel.getStyleClass().add("labelb");

        TextField nnpp = new TextField();
        nnpp.setPrefWidth(120);
        Label nnppLabel = new Label("  NNPP:");
        nnppLabel.getStyleClass().add("labelb");

        TextField ypp = new TextField();
        ypp.setPrefWidth(120);
        Label yppLabel = new Label("  YPP: ");
        yppLabel.getStyleClass().add("labelb");

        TextField sdp = new TextField();
        sdp.setPrefWidth(120);
        Label sdpLabel = new Label("  SDP: ");
        sdpLabel.getStyleClass().add("labelb");

        TextField adc = new TextField();
        adc.setPrefWidth(120);
        Label adcLabel = new Label("  ADC: ");
        adcLabel.getStyleClass().add("labelb");

        HBox box = new HBox();
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(25,5,5,50));
        box.getChildren().addAll(apcLabel,apc, pdpLabel,pdp, apgaLabel, apga, lpLabel,lp);

        HBox boxb = new HBox();
        boxb.setAlignment(Pos.CENTER);
        boxb.setPadding(new Insets(25,5,5,50));
        boxb.getChildren().addAll(nnppLabel,nnpp,yppLabel,ypp, sdpLabel, sdp,adcLabel,adc);


        HBox boxc = new HBox();
        boxc.setAlignment(Pos.CENTER);
        boxc.setPadding(new Insets(25,5,5,50));
        Button submit = new Button("Submit");
        submit.getStyleClass().add("menu-buttonb");
        boxc.getChildren().addAll(submit);

        VBox menuBox = new VBox();
        menuBox.setAlignment(Pos.CENTER);
        //menuBox.setPadding(new Insets(100));
        menuBox.setSpacing(25);
        menuBox.getChildren().addAll(box,boxb,boxc);
        mainPane.setCenter(menuBox);
        BorderPane.setAlignment(menuBox, Pos.CENTER);

        submit.setOnAction(x -> {
            gameWindow.startMenu();
        });

    }

    public void initialise() {

    }
}
