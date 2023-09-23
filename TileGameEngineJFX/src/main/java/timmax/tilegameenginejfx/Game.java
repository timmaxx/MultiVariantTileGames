package timmax.tilegameenginejfx;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.stage.*;
import timmax.basetilemodel.BaseModel;

public abstract class Game extends Application implements GameScreen {
    public final static int APP_WIDTH = 800; // 1600;
    public final static int APP_HEIGHT = 600; // 1200


    @Override
    public void start( Stage primaryStage) {
        BaseModel baseModel = initModel();

        Pane root = new VBox( );

        // viewGameOverMessage = new ViewGameOverMessage( baseModel);

        ViewJfx viewMainFieldJfx = initViewMainField(baseModel);
        root.getChildren( ).add( viewMainFieldJfx); // viewMainFieldJfx должен быть наследником Node

        GameScene scene = new GameScene( root);

        primaryStage.setTitle( initTitle( ));
        primaryStage.setResizable( false);
        primaryStage.setScene( scene);

        baseModel.createNewGame( );

        primaryStage.show( );
    }
}