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
public class InputVotesb extends BaseScene {

    private static final Logger logger = LogManager.getLogger(InputVotesb.class);

    public DbConnect db = new DbConnect();
    private final int apc;
    private final int pdp;
    private final int lp;
    private final int apga;
    private final int nnpp;
    private final int ypp;
    private final int sdp;
    private final int adc;

    public InputVotesb(Window gameWindow,int apc, int pdp, int lp, int apga, int nnpp,int ypp, int sdp ,int adc) {
        super(gameWindow);
        this.apc = apc;
        this.pdp = pdp;
        this.lp = lp;
        this.apga = apga;
        this.nnpp = nnpp;
        this.ypp = ypp;
        this.sdp = sdp;
        this.adc = adc;


        logger.info("Creating Second Input Scene");
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
        back.setOnAction(x -> gameWindow.startInputVotes());
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

        Text t = new Text("Confirm Votes Please");
        t.getStyleClass().add("heading");

        Text co = null;
        Text t2 = null;
        try {
            co = new Text("Polling Station: " + db.getPolling(gameWindow.getUser()));
            t2 = new Text("LGA: " + db.getLGA(gameWindow.getUser()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        co.getStyleClass().add("heading");
        t2.getStyleClass().add("heading");

        VBox menuBox = new VBox();
        menuBox.setAlignment(Pos.CENTER);
        //menuBox.setPadding(new Insets(100));
        menuBox.setSpacing(25);
        menuBox.getChildren().addAll(t2,co,t,box,boxb,boxc);
        mainPane.setCenter(menuBox);
        BorderPane.setAlignment(menuBox, Pos.CENTER);

        submit.setOnAction(x -> {
            if(isPNumeric(apc.getText()) && isPNumeric(pdp.getText()) && isPNumeric(lp.getText())
                    && isPNumeric(apga.getText()) && isPNumeric(nnpp.getText())&& isPNumeric(ypp.getText()) &&
                    isPNumeric(sdp.getText()) && isPNumeric(adc.getText())){
                int a = Integer.parseInt(apc.getText());
                int b = Integer.parseInt(pdp.getText());
                int c = Integer.parseInt(lp.getText());
                int d = Integer.parseInt(apga.getText());
                int e = Integer.parseInt(nnpp.getText());
                int f = Integer.parseInt(ypp.getText());
                int g = Integer.parseInt(sdp.getText());
                int h = Integer.parseInt(adc.getText());
                if (this.apc == a && this.pdp == b && this.lp == c && this.apga == d && this.nnpp == e &&  this.ypp == f
                        && this.sdp == g && this.adc == h){
                    try {
                        db.UpdateVotes(gameWindow.getUser(), a,b,c,d,e,f,g,h);
                        //gameWindow.showNotification("Succesfull Submitted Votes. Thank you");
                        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                        al.setContentText(" You have succesfully submitted the results for your LGA. Head to the live results portal for further confirmation");
                        al.show();
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    gameWindow.startMenu();
                }
                else{
                    gameWindow.showNotification("Votes count do not match try again");
                    gameWindow.startInputVotes();
                }
            }


        });

    }

    public void initialise() {

    }

    public boolean isPNumeric(String strNum) {
        if (strNum == null) {
            logger.info("Null");
            gameWindow.showNotification("Some field are empty. Enter 0 if no votes");
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            logger.info("Wrong format");
            gameWindow.showNotification("Invalid Values. Please input Numbers");
            return false;
        }

        if(Integer.parseInt(strNum) < 0){
            logger.info("Negative Number");
            gameWindow.showNotification("Invalid Values. Please input Positive Numbers");
            return false;
        }

        return true;
    }
}
