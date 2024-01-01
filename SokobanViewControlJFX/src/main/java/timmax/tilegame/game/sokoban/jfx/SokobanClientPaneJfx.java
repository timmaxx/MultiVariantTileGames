package timmax.tilegame.game.sokoban.jfx;

import javafx.stage.Stage;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.guiengine.jfx.GameClientPaneJfx;
import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;
import timmax.tilegame.guiengine.jfx.view.ViewJfx;
import timmax.tilegame.transport.TransportOfClient;

import timmax.tilegame.game.sokoban.jfx.view.SokobanMainFieldViewJfx;
import timmax.tilegame.game.sokoban.jfx.controller.SokobanGameStackPaneController;

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
    public GameSceneController initGameSceneController(TransportOfClient transportOfClient) {
        return new SokobanGameSceneController(transportOfClient);
    }
*/
    @Override
    public GameStackPaneController initGameStackPaneController(TransportOfClient transportOfClient) {
        return new SokobanGameStackPaneController(transportOfClient);
    }
/*
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
