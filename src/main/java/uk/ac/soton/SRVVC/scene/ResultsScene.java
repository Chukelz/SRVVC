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
        //stage.minHeightProperty().bind(this.heightProperty());
        //stage.minWidthProperty().bind(this.widthProperty());

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
        top.getStyleClass().add("menu-buttonc");
        top.setPadding(new Insets(5,5,5,5));
        top.setPrefHeight(root.getHeight()*1/12);

        Button back = new Button("<- Back");
        back.setOnAction(x -> gameWindow.startMenu());
        back.setAlignment(Pos.CENTER_RIGHT);

        Button total = new Button("Current Total Results");
        total.setPrefWidth(220);
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
        ps.setPrefWidth(300);
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

        BorderPane bottom = new BorderPane();
        bottom.getStyleClass().add("menu-buttonc");
        bottom.setPadding(new Insets(5,5,5,5));
        bottom.setPrefHeight(root.getHeight()*2/12);

        int progress = 0;

        try {
            progress = getprogress();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ProgressBar pb = new ProgressBar((double) progress /50);
        pb.setPrefWidth(250);
        ProgressIndicator pi = new ProgressIndicator((double) progress /50);

        Label res = new Label("Election Status: ");

        HBox bottomHbox = new HBox();
        bottomHbox.setSpacing(5);
        bottomHbox.setAlignment(Pos.CENTER);
        //bottomHbox.setPadding(new Insets(5,5,5,5));
        bottomHbox.getChildren().addAll(res,pb,pi);

        bottom.setCenter(bottomHbox);

        mainPane.setTop(top);
        mainPane.setBottom(bottom);

        Button Alimosho = new Button("Alimosho");
        Alimosho.setOnAction(x -> {
            try {
                displayVotes("Alimosho");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button Ajeromi_Ifelodun = new Button("Ajeromi-Ifelodun");
        Ajeromi_Ifelodun.setOnAction(x -> {
            try {
                displayVotes("Ajeromi-Ifelodun");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button Mushin = new Button("Mushin");
        Mushin.setOnAction(x -> {
            try {
                displayVotes("Mushin");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button Oshodi_Isolo = new Button("Oshodi-Isolo");
        Oshodi_Isolo.setOnAction(x -> {
            try {
                displayVotes("Oshodi-Isolo");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button Ojo = new Button("Ojo");
        Ojo.setOnAction(x -> {
            try {
                displayVotes("Ojo");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        HBox box = new HBox();
        box.getStyleClass().add("menu-buttond");
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        box.setPadding(new Insets(25,5,5,50));
        box.getChildren().addAll(Alimosho,Ajeromi_Ifelodun,Mushin,Oshodi_Isolo,Ojo);

        Button Ikorodu = new Button("Ikorodu");
        Ikorodu.setOnAction(x -> {
            try {
                displayVotes("Ikorodu");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button Surulere = new Button("Surulere");
        Surulere.setOnAction(x -> {
            try {
                displayVotes("Surulere");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button Agege = new Button("Agege");
        Agege.setOnAction(x -> {
            try {
                displayVotes("Agege");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button Ifako_Ijaiye = new Button("Ifako-Ijaiye");
        Ifako_Ijaiye.setOnAction(x -> {
            try {
                displayVotes("Ifako-Ijaiye");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button Somolu = new Button("Somolu");
        Somolu.setOnAction(x -> {
            try {
                displayVotes("Somolu");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        HBox boxb = new HBox();
        boxb.getStyleClass().add("menu-buttond");
        boxb.setAlignment(Pos.CENTER);
        boxb.setSpacing(10);
        boxb.setPadding(new Insets(25,5,5,50));
        boxb.getChildren().addAll(Ikorodu,Surulere,Agege,Ifako_Ijaiye,Somolu);

        Button Anuwo_Odofin = new Button("Anuwo-Odofin");
        Anuwo_Odofin.setOnAction(x -> {
            try {
                displayVotes("Anuwo-Odofin");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button Lagos_Mainland = new Button("Lagos Mainland");
        Lagos_Mainland.setOnAction(x -> {
            try {
                displayVotes("Lagos Mainland");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button Ikeja = new Button("Ikeja");
        Ikeja.setOnAction(x -> {
            try {
                displayVotes("Ikeja");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button Eti_Osa = new Button("Eti-Osa");
        Eti_Osa.setOnAction(x -> {
            try {
                displayVotes("Eti-Osa");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button Badagary = new Button("Badagary");
        Badagary.setOnAction(x -> {
            try {
                displayVotes("Badagary");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        HBox boxc = new HBox();
        boxc.getStyleClass().add("menu-buttond");
        boxc.setAlignment(Pos.CENTER);
        boxc.setSpacing(10);
        boxc.setPadding(new Insets(25,5,5,50));
        boxc.getChildren().addAll(Anuwo_Odofin,Lagos_Mainland,Ikeja,Eti_Osa,Badagary);

        Button Apapa = new Button("Apapa");
        Apapa.setOnAction(x -> {
            try {
                displayVotes("Apapa");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button Lagos_Island = new Button("Lagos Island");
        Lagos_Island.setOnAction(x -> {
            try {
                displayVotes("Lagos Island");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button Epe = new Button("Epe");
        Epe.setOnAction(x -> {
            try {
                displayVotes("Epe");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button Ibeju_Lekki = new Button("Ibeju-Lekki");
        Ibeju_Lekki.setOnAction(x -> {
            try {
                displayVotes("Ibeju-Lekki");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Button Kosofe = new Button("Kosofe");
        Kosofe.setOnAction(x -> {
            try {
                displayVotes("Kosofe");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        HBox boxd = new HBox();
        boxd.getStyleClass().add("menu-buttond");
        boxd.setAlignment(Pos.CENTER);
        boxd.setSpacing(10);
        boxd.setPadding(new Insets(25,5,5,50));
        boxd.getChildren().addAll(Apapa,Lagos_Island,Epe,Ibeju_Lekki,Kosofe);

        VBox menuBox = new VBox();
        menuBox.setAlignment(Pos.CENTER);
        //menuBox.setPadding(new Insets(100));
        menuBox.setSpacing(25);
        menuBox.getChildren().addAll(box,boxb,boxc,boxd);
        mainPane.setCenter(menuBox);
        BorderPane.setAlignment(menuBox, Pos.CENTER);

    }

    private int getprogress() throws ClassNotFoundException {
        return db.getTotalZ();
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
