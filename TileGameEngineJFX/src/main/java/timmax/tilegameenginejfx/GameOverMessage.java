package timmax.tilegameenginejfx;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class GameOverMessage extends TextFlow {
    private final Pane root;


    public GameOverMessage( Pane root) {
        this.root = root;
    }

    public void show( Color cellColor, String message, Color textColor, int textSize) {
        getChildren( ).clear( );
        Text messageText = new Text( );
        messageText.setFont( Font.font( "Verdana", FontWeight.BOLD, textSize));
        messageText.setText( message);
        double preferredWidth = messageText.getLayoutBounds( ).getWidth( );
        messageText.setFill( textColor);

        setLayoutX( ( root.getWidth( ) - preferredWidth) / 2.);
        setLayoutY( root.getHeight( ) / 2. - 30);
        setBackground( new Background( new BackgroundFill( cellColor, CornerRadii.EMPTY, Insets.EMPTY)));
        getChildren( ).add( messageText);

        setVisible( true);
    }
}