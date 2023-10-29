package timmax.tilegame.client;

import java.net.URI;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import timmax.tilegame.websocket.client.MultiGameWebSocketClient;

public class MultiGameClient extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new VBox();

        // ------------------------------------------------------------------------------------------------------------
        Pane paneForServerAddress = new HBox();
        final MultiGameWebSocketClient[] netModel = {null};
        Label labelProtocol = new Label("ws");
        TextFieldOnOpenOnClose textFieldServerAddress = new TextFieldOnOpenOnClose("localhost");
        TextFieldOnOpenOnClose textFieldServerPort = new TextFieldOnOpenOnClose("8887");
        ButtonConnect buttonConnect = new ButtonConnect("Connect");
        Label labelConnectString = new Label();
        ButtonDisconnect buttonDisconnect = new ButtonDisconnect("Disconnect");
        buttonDisconnect.setDisable(true);

        // Эти свойства определены ниже, т.к. WebSocketClient нельзя использовать повторно!
        // buttonConnect.setOnAction
        // buttonDisconnect.setOnAction

        paneForServerAddress.getChildren().addAll(labelProtocol, textFieldServerAddress, textFieldServerPort, buttonConnect, labelConnectString, buttonDisconnect);
        // ------------------------------------------------------------------------------------------------------------

        Pane paneForUserAndPassword = new HBox();
        Label labelUser = new Label("User");
        TextFieldOnLoginOnLogout textFieldUser = new TextFieldOnLoginOnLogout("");
        textFieldUser.setDisable(true);
        Label labelPassword = new Label("Password");
        PasswordFieldOnLoginOnLogout passwordField = new PasswordFieldOnLoginOnLogout();
        passwordField.setDisable(true);
        Button buttonLogin = new Button("Login");
        Button buttonLogout = new Button("Logoff");
        buttonLogout.setDisable(true);
        paneForUserAndPassword.getChildren().addAll(labelUser, textFieldUser, labelPassword, passwordField, buttonLogin, buttonLogout);
        // ------------------------------------------------------------------------------------------------------------

        buttonConnect.setOnAction(event -> {
            labelConnectString.setText(labelProtocol.getText() + "://" + textFieldServerAddress.getText() + ":" + textFieldServerPort.getText());
            try {
                netModel[0] = new MultiGameWebSocketClient(new URI(labelConnectString.getText()));

                netModel[0].addViewOnOpen(buttonConnect);
                netModel[0].addViewOnOpen(buttonDisconnect);
                netModel[0].addViewOnOpen(textFieldServerAddress);
                netModel[0].addViewOnOpen(textFieldServerPort);

                netModel[0].addViewOnClose(buttonConnect);
                netModel[0].addViewOnClose(buttonDisconnect);
                netModel[0].addViewOnClose(textFieldServerAddress);
                netModel[0].addViewOnClose(textFieldServerPort);

                netModel[0].addViewOnLogin(textFieldUser);
                netModel[0].addViewOnLogin(passwordField);

                // netModel[ 0].addViewOnLogout( textFieldUser);
                // netModel[ 0].addViewOnLogout( passwordField);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            netModel[0].connect();
        });

        /*
        buttonDisconnect.setOnAction( new EventHandler< ActionEvent>( ) {
            @Override
            public void handle( ActionEvent event) {
                netModel[ 0].close( );
            }
        });
        */
        buttonDisconnect.setOnAction(event -> netModel[0].close());

        buttonLogin.setOnAction(event -> {
            netModel[0].login("u1", "1");
            // netModel[ 0].login( textFieldUser.getText( ), passwordField.getText( ));
        });

        // buttonLogout.setOnAction( event -> netModel[ 0].logout( ));
        // ------------------------------------------------------------------------------------------------------------

        // LabelStatusBar

        root.getChildren().addAll(paneForServerAddress, paneForUserAndPassword);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Multi Game Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}