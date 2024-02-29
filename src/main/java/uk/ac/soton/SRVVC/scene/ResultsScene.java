package uk.ac.soton.SRVVC.scene;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.ac.soton.SRVVC.DbConnect;
import uk.ac.soton.SRVVC.ui.Pane;
import uk.ac.soton.SRVVC.ui.Window;

import java.util.ArrayList;

public class ResultsScene extends BaseScene{

    private static final Logger logger = LogManager.getLogger(ResultsScene.class);

    public DbConnect db = new DbConnect();

    public ResultsScene(Window gameWindow) {
        super(gameWindow);

        logger.info("Creating Results Scene");
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

        Button total = new Button("Current Total Results");
        total.setPrefWidth(150);
        //total.setPrefHeight(30);
        total.setAlignment(Pos.CENTER);
        total.setOnAction(x -> {
            try {
                displayTVotes();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        TextField ps = new TextField("Search Specific Polling Station");
        ps.setPrefWidth(200);
        Button submitps = new Button("Search");
        submitps.setOnAction(x -> {
            try {
                displayVotesp(ps.getText());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });


        HBox leftHbox = new HBox();
        leftHbox.setSpacing(20);
        leftHbox.setAlignment(Pos.CENTER);
        leftHbox.setPadding(new Insets(5,5,5,5));
        leftHbox.getChildren().addAll(back);

        HBox rightHbox = new HBox();
        rightHbox.setSpacing(20);
        rightHbox.setAlignment(Pos.CENTER);
        rightHbox.setPadding(new Insets(5,5,5,5));
        rightHbox.getChildren().addAll(total);

        HBox centerHbox = new HBox();
        centerHbox.setSpacing(20);
        centerHbox.setAlignment(Pos.CENTER);
        centerHbox.setPadding(new Insets(5,5,5,5));
        centerHbox.getChildren().addAll(ps,submitps);

        top.setLeft(leftHbox);
        top.setRight(rightHbox);
        top.setCenter(centerHbox);
        top.setAlignment(leftHbox, Pos.CENTER);
        top.setAlignment(rightHbox, Pos.CENTER);
        top.setAlignment(centerHbox, Pos.CENTER);

        mainPane.setTop(top);

        Button Alimosho = new Button("Alimosho");
        Alimosho.setOnAction(x -> {
            try {
                displayVotes("Alimosho");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        HBox box = new HBox();
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(25,5,5,50));
        box.getChildren().addAll(Alimosho);

        VBox menuBox = new VBox();
        menuBox.setAlignment(Pos.CENTER);
        //menuBox.setPadding(new Insets(100));
        menuBox.setSpacing(25);
        menuBox.getChildren().addAll(box);
        mainPane.setCenter(menuBox);
        BorderPane.setAlignment(menuBox, Pos.CENTER);

    }

    private void displayVotesp(String ps) throws ClassNotFoundException {
        if(db.getPs().contains(ps)){
            ArrayList<String> v = db.getVotesp(ps);
            String t = new String("LGA: " + v.get(8) + "\n" + "Officer: " + v.get(9) + "\n" + "APC: " + v.get(0) + "\n" + "PDP: " + v.get(1) + "\n" + "LP: " + v.get(2) + "\n" +
                    "APGA: " + v.get(3) + "\n" + "NNPP: " + v.get(4) + "\n" + "YPP: " + v.get(5) + "\n" + "SDP: " + v.get(6) + "\n" +
                    "ADC: " + v.get(7) + "\n");

            logger.info(v);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText(t);
            a.setTitle("Votes for Polling Station:  " + ps);
            a.setHeaderText("Votes for Polling Station:  " + ps);
            a.show();
        }
        else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Not a Polling Station");
            a.show();
        }

    }

    private void displayVotes(String lga) throws ClassNotFoundException {
        ArrayList<String> v = db.getVotesState(lga);
        String t = new String("APC: " + v.get(0) + "\n" + "PDP: " + v.get(1) + "\n" + "LP: " + v.get(2) + "\n" +
                "APGA: " + v.get(3) + "\n" + "NNPP: " + v.get(4) + "\n" + "YPP: " + v.get(5) + "\n" + "SDP: " + v.get(6) + "\n" +
                "ADC: " + v.get(7) + "\n");

        logger.info(v);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(t);
        a.setTitle("Votes for " + lga);
        a.setHeaderText("Votes for " + lga);
        a.show();

    }

    private void displayTVotes() throws ClassNotFoundException {
        ArrayList<String> v = db.getVotesTotal();
        String t = new String("APC: " + v.get(0) + "\n" + "PDP: " + v.get(1) + "\n" + "LP: " + v.get(2) + "\n" +
                "APGA: " + v.get(3) + "\n" + "NNPP: " + v.get(4) + "\n" + "YPP: " + v.get(5) + "\n" + "SDP: " + v.get(6) + "\n" +
                "ADC: " + v.get(7) + "\n");

        logger.info(v);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(t);
        a.setTitle("Current Total Count");
        a.setHeaderText("Current Total Count");
        a.show();

    }

    public void initialise() {

    }


}
