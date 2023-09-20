package timmax.tilegameenginejfx;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class GameOverMessage extends TextFlow {
    private final Text messageText;

    public GameOverMessage( ) {
        messageText = new Text( );
        getChildren( ).add( messageText);
    }

    public void show( Color cellColor, String message, Color textColor, int textSize, int rootWidth, int rootHeight) {
        messageText.setFont( Font.font( "Verdana", FontWeight.BOLD, textSize));
        messageText.setText( message);

        // ToDo: разобраться с preferredWidth.
        double preferredWidth = messageText.getLayoutBounds( ).getWidth( ); // Почему-то получается 0?
        messageText.setFill( textColor);

        // ToDo: После разборок с preferredWidth "- 60" убрать.
        setLayoutX( ( rootWidth - preferredWidth) / 2. - 60); // Здесь поставил 60, т.к. preferredWidth = 0.
                                                              // (но это значение, которое только подходит для сообщений
                                                              // "Defeat" и "Victory").
        setLayoutY( rootHeight / 2. - 30);

        setBackground( new Background( new BackgroundFill( cellColor, CornerRadii.EMPTY, Insets.EMPTY)));

        setVisible( true);
    }
}