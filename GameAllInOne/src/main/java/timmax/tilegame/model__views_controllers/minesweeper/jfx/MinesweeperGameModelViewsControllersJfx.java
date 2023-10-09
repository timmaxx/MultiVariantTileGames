package timmax.tilegame.model__views_controllers.minesweeper.jfx;

import javafx.scene.Node;

import timmax.tilegame.basemodel.ConnectionToBaseModel;
import timmax.tilegame.model__views_controllers.transport.model.TransportModelFromModelToViewsForModelViews;
import timmax.tilegame.model__views_controllers.transport.view.ConnectionToBaseModelServerPlusClient;

import timmax.tilegameenginejfx.*;

import timmax.minesweeper.model.MinesweeperModel;

import timmax.minesweeper.jfx.view.MinesweeperMainFieldViewJfx;
import timmax.minesweeper.jfx.view.MinesweeperPersistentSettings;
import timmax.minesweeper.jfx.view.MinesweeperVariableSettingsFlag;
import timmax.minesweeper.jfx.view.MinesweeperVariableSettingsOpenClose;

import timmax.minesweeper.jfx.controller.MinesweeperGameStackPaneController;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGameModelViewsControllersJfx extends GameModelViewsControllersJfx {
    @Override
    public ConnectionToBaseModel initConnectionToBaseModel( ) {
        TransportModelFromModelToViewsForModelViews transportModelFromModelToViewsForModelViews = new TransportModelFromModelToViewsForModelViews( );
        MinesweeperModel minesweeperModel = new MinesweeperModel( transportModelFromModelToViewsForModelViews);
        transportModelFromModelToViewsForModelViews.setBaseModel( minesweeperModel);
        return new ConnectionToBaseModelServerPlusClient( minesweeperModel);
    }

    @Override
    protected ViewJfx initViewOfMainField( ConnectionToBaseModel connectionToBaseModel, GameStackPaneController gameStackPaneController) {
        return new MinesweeperMainFieldViewJfx( connectionToBaseModel, gameStackPaneController);
    }

    @Override
    public String initAppTitle( ) {
        return "Minesweeper";
    }

    @Override
    protected GameSceneController initGameSceneController( ConnectionToBaseModel connectionToBaseModel) {
        // Ok
        return null;
    }

    @Override
    public GameStackPaneController initGameStackPaneController( ConnectionToBaseModel connectionToBaseModel) {
        return new MinesweeperGameStackPaneController( connectionToBaseModel);
    }

    @Override
    protected List< Node> initNodeList( ConnectionToBaseModel connectionToBaseModel) {
        List< Node> nodeList = new ArrayList< >( );

        nodeList.add( new ViewTextFieldsPersistentSettingsJfx( connectionToBaseModel));
        nodeList.add( new MinesweeperPersistentSettings( connectionToBaseModel));
        nodeList.add( new MinesweeperVariableSettingsOpenClose( connectionToBaseModel));
        nodeList.add( new MinesweeperVariableSettingsFlag( connectionToBaseModel));

        return nodeList;
    }
}