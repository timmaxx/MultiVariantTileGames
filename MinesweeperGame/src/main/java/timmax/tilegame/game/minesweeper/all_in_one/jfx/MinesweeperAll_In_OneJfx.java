package timmax.tilegame.game.minesweeper.all_in_one.jfx;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;

import timmax.tilegame.basemodel.BaseModel;

import timmax.tilegame.guiengine.jfx.GameAll_In_OneJfx;
import timmax.tilegame.guiengine.jfx.view.ViewJfx;
import timmax.tilegame.guiengine.jfx.view.ViewTextFieldsPersistentSettingsJfx;
import timmax.tilegame.guiengine.jfx.controller.GameSceneController;
import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;

import timmax.tilegame.game.minesweeper.model.MinesweeperModel;

import timmax.tilegame.game.minesweeper.jfx.view.MinesweeperMainFieldViewJfx;
import timmax.tilegame.game.minesweeper.jfx.view.MinesweeperPersistentSettings;
import timmax.tilegame.game.minesweeper.jfx.view.MinesweeperVariableSettingsFlag;
import timmax.tilegame.game.minesweeper.jfx.view.MinesweeperVariableSettingsOpenClose;
import timmax.tilegame.game.minesweeper.jfx.controller.MinesweeperGameStackPaneController;

public class MinesweeperAll_In_OneJfx extends GameAll_In_OneJfx {
    @Override
    public BaseModel initModel( ) {
        return new MinesweeperModel( );
    }

    @Override
    public ViewJfx initViewOfMainField( BaseModel baseModel, GameStackPaneController gameStackPaneController) {
        return new MinesweeperMainFieldViewJfx( baseModel, gameStackPaneController);
    }

    @Override
    public String initAppTitle( ) {
        return "Minesweeper";
    }

    @Override
    public GameSceneController initGameSceneController( BaseModel baseModel) {
        return null;
    }

    @Override
    public GameStackPaneController initGameStackPaneController( BaseModel basemodel) {
        return new MinesweeperGameStackPaneController( basemodel);
    }

    @Override
    protected List< Node> initNodeList( BaseModel baseModel) {
        List< Node> nodeList = new ArrayList< >( );

        nodeList.add( new ViewTextFieldsPersistentSettingsJfx( baseModel));
        nodeList.add( new MinesweeperPersistentSettings( baseModel));
        nodeList.add( new MinesweeperVariableSettingsOpenClose( baseModel));
        nodeList.add( new MinesweeperVariableSettingsFlag( baseModel));

        return nodeList;
    }
}