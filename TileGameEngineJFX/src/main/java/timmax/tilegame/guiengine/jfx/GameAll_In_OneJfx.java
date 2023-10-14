package timmax.tilegame.guiengine.jfx;

import java.util.List;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.stage.*;

import timmax.tilegame.basemodel.BaseModel;

import timmax.tilegame.guiengine.jfx.view.ViewJfx;
import timmax.tilegame.guiengine.jfx.controller.GameSceneController;
import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;

public abstract class GameAll_In_OneJfx extends Application {
    @Override
    public void start( Stage primaryStage) {
        // Сервер
        // Для модели отделенной от клиента где-то здесь открывать серверный WebSocket
        BaseModel baseModel = initModel( );


        // Клиент
        {   // Для модели отделенной от клиента где-то здесь открывать клиентский WebSocket.
            // И создавать свой BaseModel (но уже не тот, что на сервере, а тот, который будет соединением к серверному).
            Pane root = new VBox( );

            GameStackPaneController gameStackPaneController = initGameStackPaneController( baseModel);
            ViewJfx viewMainFieldJfx = initViewOfMainField( baseModel, gameStackPaneController);
            root.getChildren( ).add( viewMainFieldJfx);

            List< Node> nodeList = initNodeList( baseModel);
            root.getChildren( ).addAll( nodeList);

            GameSceneController gameSceneController = initGameSceneController( baseModel);
            GameScene scene = new GameScene( root, gameSceneController);

            primaryStage.setTitle( initAppTitle( ));
            primaryStage.setResizable( false);
            primaryStage.setScene( scene);
        }

        // Сервер
        baseModel.createNewGame( );

        // Клиент
        primaryStage.show( );
    }

    abstract protected List< Node> initNodeList( BaseModel baseModel);

    abstract protected BaseModel initModel( );

    abstract protected ViewJfx initViewOfMainField( BaseModel baseModel, GameStackPaneController gameStackPaneController);

    abstract protected String initAppTitle( );

    abstract protected GameSceneController initGameSceneController( BaseModel baseModel);

    abstract protected GameStackPaneController initGameStackPaneController( BaseModel basemodel);
}