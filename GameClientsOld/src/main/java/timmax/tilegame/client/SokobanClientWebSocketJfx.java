package timmax.tilegame.client;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.transport.TransportOfController;

import timmax.tilegame.guiengine.jfx.GameClientWebSocketJfx;
import timmax.tilegame.guiengine.jfx.controller.GameSceneController;
import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;
import timmax.tilegame.guiengine.jfx.view.ViewJfx;
import timmax.tilegame.guiengine.jfx.view.ViewTextFieldsPersistentSettingsJfx;

import timmax.tilegame.game.sokoban.jfx.view.SokobanMainFieldViewJfx;
import timmax.tilegame.game.sokoban.jfx.view.SokobanPersistentSettings;
import timmax.tilegame.game.sokoban.jfx.view.SokobanVariableSettingsCountOfBoxesInHouses;
import timmax.tilegame.game.sokoban.jfx.view.SokobanVariableSettingsCountOfSteps;
import timmax.tilegame.game.sokoban.jfx.controller.SokobanGameSceneController;

public class SokobanClientWebSocketJfx extends GameClientWebSocketJfx {
    @Override
    public ViewJfx initViewOfMainField(BaseModel baseModel, GameStackPaneController gameStackPaneController) {
        return new SokobanMainFieldViewJfx(baseModel, gameStackPaneController);
    }

    @Override
    public String initAppTitle() {
        return "Sokoban";
    }

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

        nodeList.add(new ViewTextFieldsPersistentSettingsJfx(baseModel));
        nodeList.add(new SokobanPersistentSettings(baseModel));
        nodeList.add(new SokobanVariableSettingsCountOfSteps(baseModel));
        nodeList.add(new SokobanVariableSettingsCountOfBoxesInHouses(baseModel));

        return nodeList;
    }
}