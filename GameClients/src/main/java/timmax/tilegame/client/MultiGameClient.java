package timmax.tilegame.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import timmax.tilegame.websocket.client.MultiGameWebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

public class MultiGameClient extends Application {
    public static void main( String[ ] args) {
        Application.launch( args);
    }

    @Override
    public void start( Stage primaryStage) {
        Pane root = new VBox( );

        // ------------------------------------------------------------------------------------------------------------
        Pane paneForServerAddress = new HBox( );
        final MultiGameWebSocketClient[ ] netModel = { null};
        Label labelProtocol = new Label( "ws");
        TextFieldOnOpenOnClose textFieldServerAddress = new TextFieldOnOpenOnClose( "localhost");
        TextFieldOnOpenOnClose textFieldServerPort = new TextFieldOnOpenOnClose( "8887");
        ButtonConnect buttonConnect = new ButtonConnect( "Connect");
        Label labelConnectString = new Label( );
        ButtonDisconnect buttonDisconnect = new ButtonDisconnect( "Disconnect");
        buttonDisconnect.setDisable( true);

        buttonConnect.setOnAction( new EventHandler< ActionEvent>( ) {
            @Override
            public void handle( ActionEvent event) {
                labelConnectString.setText( labelProtocol.getText( ) + "://" + textFieldServerAddress.getText( ) + ":" + textFieldServerPort.getText( ));
                try {
                    labelConnectString.setText( labelProtocol.getText( ) + "://" + textFieldServerAddress.getText( ) + ":" + textFieldServerPort.getText( ));
                    netModel[0] = new MultiGameWebSocketClient( new URI( labelConnectString.getText()));
                    netModel[0].addViewOnOpen( buttonConnect);
                    netModel[0].addViewOnOpen( buttonDisconnect);
                    netModel[0].addViewOnOpen( textFieldServerAddress);
                    netModel[0].addViewOnOpen( textFieldServerPort);
                    netModel[0].addViewOnClose( buttonConnect);
                    netModel[0].addViewOnClose( buttonDisconnect);
                    netModel[0].addViewOnClose( textFieldServerAddress);
                    netModel[0].addViewOnClose( textFieldServerPort);
                } catch ( URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                netModel[ 0].connect();
            }
        });
        buttonDisconnect.setOnAction( new EventHandler< ActionEvent>( ) {
            @Override
            public void handle( ActionEvent event) {
                netModel[ 0].close( );
            }
        });
        paneForServerAddress.getChildren( ).addAll( labelProtocol, textFieldServerAddress, textFieldServerPort, buttonConnect, labelConnectString, buttonDisconnect);
        // ------------------------------------------------------------------------------------------------------------

        Pane paneForUserAndPassword = new HBox( );
        Label labelUser = new Label( "User");
        TextField textFieldUser = new TextField( "");
        Label labelPassword = new Label( "Password");
        PasswordField passwordField = new PasswordField( );
        Button buttonLogin = new Button( "Login");
        Button buttonLogoff = new Button( "Logoff");
        buttonLogoff.setDisable( true);
        paneForUserAndPassword.getChildren( ).addAll( labelUser, textFieldUser, labelPassword, passwordField, buttonLogin, buttonLogoff);
        // ------------------------------------------------------------------------------------------------------------

        // LabelStatusBar

        root.getChildren( ).addAll( paneForServerAddress, paneForUserAndPassword);

        Scene scene = new Scene( root);
        primaryStage.setTitle( "Multi Game Client");
        primaryStage.setScene( scene);
        primaryStage.show( );
    }
}