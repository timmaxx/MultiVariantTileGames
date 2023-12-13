package timmax.tilegame.game.sokoban.jfx;

import javafx.stage.Stage;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.guiengine.jfx.GameClientPaneJfx;
import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;
import timmax.tilegame.guiengine.jfx.view.ViewJfx;
import timmax.tilegame.transport.TransportOfClient;

import timmax.tilegame.game.sokoban.jfx.view.SokobanMainFieldViewJfx;

public class SokobanClientPaneJfx extends GameClientPaneJfx {
    public SokobanClientPaneJfx(Stage primaryStage, BaseModel baseModel, TransportOfClient transportOfClient) {
        super(primaryStage, baseModel, transportOfClient);
    }

    @Override
    public ViewJfx initViewOfMainField(BaseModel baseModel, GameStackPaneController gameStackPaneController) {
        return new SokobanMainFieldViewJfx(baseModel, gameStackPaneController);
    }

    @Override
    public String initAppTitle() {
        return "Sokoban";
    }
/*
    @Override
    public GameSceneController initGameSceneController(TransportOfController transportOfController) {
        return new SokobanGameSceneController(transportOfController);
    }

    @Override
    public GameStackPaneController initGameStackPaneController(TransportOfController transportOfController) {
        return null;
    }

    @Override
    protected List<Node> initNodeList(BaseModel baseModel, TransportOfController transportOfController) {
        List<Node> nodeList = new ArrayList<>();
*//*
        nodeList.add( new ViewTextFieldsPersistentSettingsJfx( baseModel));
        nodeList.add( new SokobanPersistentSettings( baseModel));
        nodeList.add( new SokobanVariableSettingsCountOfSteps( baseModel));
        nodeList.add( new SokobanVariableSettingsCountOfBoxesInHouses( baseModel));
*//*
        return nodeList;
    }*/
}