package timmax.tilegame.game.minesweeper.client.websocket.jfx;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;

import timmax.tilegame.basemodel.BaseModel;

import timmax.tilegame.guiengine.jfx.view.ViewJfx;
import timmax.tilegame.guiengine.jfx.view.ViewTextFieldsPersistentSettingsJfx;
import timmax.tilegame.guiengine.jfx.controller.GameSceneController;
import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;

import timmax.tilegame.guiengine.jfx.GameClientWebSocketJfx;

import timmax.tilegame.game.minesweeper.jfx.view.MinesweeperMainFieldViewJfx;
import timmax.tilegame.game.minesweeper.jfx.view.MinesweeperPersistentSettings;
import timmax.tilegame.game.minesweeper.jfx.view.MinesweeperVariableSettingsFlag;
import timmax.tilegame.game.minesweeper.jfx.view.MinesweeperVariableSettingsOpenClose;
import timmax.tilegame.game.minesweeper.jfx.controller.MinesweeperGameStackPaneController;
import timmax.tilegame.transport.TransportOfController;

public class MinesweeperClientWebSocketJfx extends GameClientWebSocketJfx {
    @Override
    public ViewJfx initViewOfMainField( BaseModel baseModel, GameStackPaneController gameStackPaneController) {
        return new MinesweeperMainFieldViewJfx( baseModel, gameStackPaneController);
    }

    @Override
    public String initAppTitle( ) {
        return "Minesweeper";
    }

    @Override
    public GameSceneController initGameSceneController( BaseModel baseModel, TransportOfController transportOfController) {
        return null;
    }

    @Override
    public GameStackPaneController initGameStackPaneController( BaseModel basemodel, TransportOfController transportOfController) {
        return new MinesweeperGameStackPaneController( basemodel, transportOfController);
    }

    @Override
    protected List< Node> initNodeList( BaseModel baseModel, TransportOfController transportOfController) {
        List< Node> nodeList = new ArrayList< >( );

        nodeList.add( new ViewTextFieldsPersistentSettingsJfx( baseModel));
        nodeList.add( new MinesweeperPersistentSettings( baseModel));
        nodeList.add( new MinesweeperVariableSettingsOpenClose( baseModel));
        nodeList.add( new MinesweeperVariableSettingsFlag( baseModel));

        return nodeList;
    }
}