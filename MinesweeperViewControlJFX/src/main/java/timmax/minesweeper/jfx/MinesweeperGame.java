package timmax.minesweeper.jfx;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import timmax.basetilemodel.*;
import timmax.tilegameenginejfx.*;
import timmax.minesweeper.model.*;
import timmax.minesweeper.jfx.view.*;
import timmax.minesweeper.jfx.controller.MinesweeperGameStackPaneController;

public class MinesweeperGame extends Game {
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
    protected List<Node> initNodeList( BaseModel baseModel) {
        List< Node> nodeList = new ArrayList< >( );

        nodeList.add( new MinesweeperPersistentSettings( baseModel, null));
        nodeList.add( new MinesweeperVariableSettings( baseModel, null));

        return nodeList;
    }
}