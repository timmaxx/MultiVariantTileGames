package timmax.tilegame.game.sokoban.all_in_one.jfx;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;

import timmax.tilegame.basemodel.BaseModel;

import timmax.tilegame.guiengine.jfx.Game;
import timmax.tilegame.guiengine.jfx.view.ViewJfx;
import timmax.tilegame.guiengine.jfx.view.ViewTextFieldsPersistentSettingsJfx;
import timmax.tilegame.guiengine.jfx.controller.GameSceneController;
import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;

import timmax.tilegame.game.sokoban.model.SokobanModel;

import timmax.tilegame.game.sokoban.jfx.view.SokobanMainFieldViewJfx;
import timmax.tilegame.game.sokoban.jfx.view.SokobanPersistentSettings;
import timmax.tilegame.game.sokoban.jfx.view.SokobanVariableSettingsCountOfBoxesInHouses;
import timmax.tilegame.game.sokoban.jfx.view.SokobanVariableSettingsCountOfSteps;
import timmax.tilegame.game.sokoban.jfx.controller.SokobanGameSceneController;

public class SokobanAll_In_OneJfx extends Game {
    @Override
    public BaseModel initModel( ) {
        return new SokobanModel( );
    }

    @Override
    public ViewJfx initViewOfMainField( BaseModel baseModel, GameStackPaneController gameStackPaneController) {
        return new SokobanMainFieldViewJfx( baseModel, gameStackPaneController);
    }

    @Override
    public String initAppTitle( ) {
        return "Sokoban";
    }

    @Override
    public GameSceneController initGameSceneController( BaseModel baseModel) {
        return new SokobanGameSceneController( baseModel);
    }

    @Override
    public GameStackPaneController initGameStackPaneController( BaseModel basemodel) {
        return null;
    }

    @Override
    protected List< Node> initNodeList( BaseModel baseModel) {
        List< Node> nodeList = new ArrayList< >( );

        nodeList.add( new ViewTextFieldsPersistentSettingsJfx( baseModel));
        nodeList.add( new SokobanPersistentSettings( baseModel));
        nodeList.add( new SokobanVariableSettingsCountOfSteps( baseModel));
        nodeList.add( new SokobanVariableSettingsCountOfBoxesInHouses( baseModel));

        return nodeList;
    }
}