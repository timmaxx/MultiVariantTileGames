package timmax.tilegame.game.minesweeper.jfx;

import javafx.stage.Stage;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.transport.TransportOfClient;

import timmax.tilegame.guiengine.jfx.GameClientPaneJfx;
import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;
import timmax.tilegame.guiengine.jfx.view.ViewJfx;

import timmax.tilegame.game.minesweeper.jfx.view.MinesweeperMainFieldViewJfx;

// ToDo: Удалить класс. Использовать только GameClientPaneJfx.
public class MinesweeperClientPaneJfx extends GameClientPaneJfx {
    public MinesweeperClientPaneJfx(Stage primaryStage, BaseModel netModel, TransportOfClient transportOfClient) {
        super(primaryStage, netModel, transportOfClient);
    }

    @Override
    protected ViewJfx initViewOfMainField(BaseModel baseModel, GameStackPaneController gameStackPaneController) {
        return new MinesweeperMainFieldViewJfx(baseModel, gameStackPaneController);
    }

    @Override
    protected String initAppTitle() {
        return "Minesweeper";
    }

    @Override
    protected GameStackPaneController initGameStackPaneController(TransportOfClient transportOfClient) {
        return new GameStackPaneController(transportOfClient);
    }
}
