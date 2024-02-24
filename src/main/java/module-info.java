module uk.ac.soton.comp1206 {
    requires java.scripting;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.apache.logging.log4j;
    requires nv.websocket.client;
    opens uk.ac.soton.SRVVC.ui to javafx.fxml;
    exports uk.ac.soton.SRVVC;
    exports uk.ac.soton.SRVVC.ui;
    exports uk.ac.soton.SRVVC.network;
    exports uk.ac.soton.SRVVC.scene;
    exports uk.ac.soton.SRVVC.event;
    exports uk.ac.soton.SRVVC.component;
    exports uk.ac.soton.SRVVC.game;
}