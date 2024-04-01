package uk.ac.soton.SRVVC.scene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.ac.soton.SRVVC.DbConnect;
import uk.ac.soton.SRVVC.EmailUtil;
import uk.ac.soton.SRVVC.ui.Pane;
import uk.ac.soton.SRVVC.ui.Window;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Objects;
import java.util.Properties;

public class PasswordScene extends BaseScene {

    private static final Logger logger = LogManager.getLogger(PasswordScene.class);

    public DbConnect db = new DbConnect();

    public PasswordScene(Window gameWindow) {
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
        Label ulabel = new Label("Password:");
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
                verifyPassword(username.getText());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        mainPane.setCenter(box);

    }

    private void verifyPassword(String u) throws ClassNotFoundException {
        //checks if the password entered is equal to the unhashed password of the user in the database
        if(Objects.equals(unhash(db.getPasswordGivenUser(gameWindow.getUser())), u)){
            gameWindow.setRand(); //assign a random number to be the One-Time Password
            sendEmail(db.getEmail(gameWindow.getUser())); //Get the users email from database then send email
            gameWindow.startOTP(); // load one time password screen
            logger.info("Going to OTP screen");
        }
        else{
            //Inform user password is wrong
            gameWindow.showNotification("Invalid Password");
            gameWindow.startLogin();
        }
    }

    public void initialise() {
    }

    public String unhash(String s){
        String unhashed = "14onmyBalmain";
        return unhashed;
    }

    public void sendEmail(String toEmail){
        logger.info("SimpleEmail Start" + gameWindow.getRand());
        final String fromEmail = "noreplysrvvc@gmail.com"; //requires valid gmail id
        final String password = "ykgjonjpnrsqskll"; // correct password for gmail id
        //final String toEmail = "myemail@yahoo.com"; // can be any email id

        logger.info("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        //send the email
        EmailUtil.sendEmail(session, toEmail,"Verification code for SRVVC", "Code: " + gameWindow.getRand());
    }
}
