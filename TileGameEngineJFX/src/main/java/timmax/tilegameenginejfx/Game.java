package timmax.tilegameenginejfx;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.stage.*;
import timmax.basetilemodel.BaseModel;

public abstract class Game extends Application implements GameScreen {
    public final static int APP_WIDTH = 800; // 1600;
    public final static int APP_HEIGHT = 600; // 1200

    private BaseModel baseModel;


    @Override
    public void start( Stage primaryStage) {
        baseModel = initModel( );

        Pane root = new VBox( );
        // GameBorderImage gameBorderImage = new GameBorderImage( baseModel);

        // viewGameOverMessage = new ViewGameOverMessage( baseModel);

        ViewJfx viewMainFieldJfx = initViewMainField( baseModel, this);
        root.getChildren( ).add( viewMainFieldJfx); // viewMainFieldJfx должен быть наследником Node

        GameScene scene = new GameScene( root);

        primaryStage.setTitle( initTitle( ));
        primaryStage.setResizable( false);
        primaryStage.setScene( scene);
        initialize( );

        primaryStage.show( );
    }

    // ToDo: 1. game.initialize( ) перенести в модель и тогда см. п. 2.
    @Override
    public void initialize( ) {
        baseModel.createNewGame( );
/*
        // Done: 22.09.2023 работа с gameBorderImage приостановлена.
        // ToDo: gameBorderImage сделать наследником (или реализующим) ViewJfx. И тогда отсюда она уйдёт. И в принципе initialize( ) уйдёт из этого класса.
        cellSize = Math.min( APP_WIDTH / baseModel.getWidth( ), APP_HEIGHT / baseModel.getHeight( ));
        gameBorderImage.setWidthAndHeight( baseModel.getWidth( ), baseModel.getHeight( ), cellSize);
*/
        // viewGameOverMessage.initRootFromModel( root);
    }
}