package timmax.tilegameenginejfx;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import timmax.tilegame.basemodel.ConnectionToBaseModel;

import java.util.List;

public abstract class GameModelViewsControllersJfx extends Application {
    public final static int APP_WIDTH = 800; // 1600
    public final static int APP_HEIGHT = 600; // 1200


    @Override
    public void start( Stage primaryStage) {
        ConnectionToBaseModel connectionToBaseModel = initConnectionToBaseModel( );

        Pane root = new VBox( );

        // viewGameOverMessage = new ViewGameOverMessage( baseModel);

        GameStackPaneController gameStackPaneController = initGameStackPaneController( connectionToBaseModel);

        ViewJfx viewMainFieldJfx = initViewOfMainField( connectionToBaseModel, gameStackPaneController);

        root.getChildren( ).add( viewMainFieldJfx); // viewMainFieldJfx - Наследник ViewJfx, а ViewJfx должен быть наследником Node

        /*
        // Вариант с ещё одним главным полем, но без GameStackPaneController
        // - т.е. этот вариант от мыши не будет принимать сигналы.
        ViewJfx viewMainFieldJfx2 = initViewOfMainField( baseModel, null);
        root.getChildren( ).add( viewMainFieldJfx2);
        */

        List< Node> nodeList = initNodeList( connectionToBaseModel);

        root.getChildren( ).addAll( nodeList);

        GameSceneController gameSceneController = initGameSceneController( connectionToBaseModel);
        GameScene scene = new GameScene( root, gameSceneController);

        primaryStage.setTitle( initAppTitle( ));
        primaryStage.setResizable( false);
        primaryStage.setScene( scene);

        connectionToBaseModel.createNewGame( );

        primaryStage.show( );
    }

    abstract protected List< Node> initNodeList( ConnectionToBaseModel connectionToBaseModel);

    abstract protected ConnectionToBaseModel initConnectionToBaseModel( );

    abstract protected ViewJfx initViewOfMainField( ConnectionToBaseModel connectionToBaseModel, GameStackPaneController gameStackPaneController);

    abstract protected String initAppTitle( );

    abstract protected GameSceneController initGameSceneController( ConnectionToBaseModel connectionToBaseModel);

    abstract protected GameStackPaneController initGameStackPaneController( ConnectionToBaseModel connectionToBaseModel);
}