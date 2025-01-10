package timmax.tilegame.guiengine.jfx.view;

import javafx.application.Platform;
import javafx.scene.layout.Pane;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server_client.GuiDefaultConstants;
import timmax.tilegame.guiengine.jfx.GameClientPaneJfx;
import timmax.tilegame.transport.ISenderOfEventOfClient;

import timmax.tilegame.baseview.ViewMainField;
import timmax.tilegame.guiengine.jfx.Game;
import timmax.tilegame.guiengine.jfx.GameStackPane;

import java.util.Map;

import static timmax.tilegame.basemodel.protocol.server_client.IGameMatchX.PARAM_NAME_HEIGHT;
import static timmax.tilegame.basemodel.protocol.server_client.IGameMatchX.PARAM_NAME_WIDTH;

public class ViewMainFieldJfx extends ViewJfx implements ViewMainField {
    protected GameStackPane[][] cells;
    protected int cellSize;

    public ViewMainFieldJfx(
            ISenderOfEventOfClient senderOfEventOfClient,
            BaseController baseController,
            String viewName,
            GameType gameType) {
        super(senderOfEventOfClient, baseController, viewName, gameType);

        setOnMouseClicked(event ->
                baseController.onMouseClick(
                        event.getButton(),
                        new XYCoordinate((int) (event.getX() / cellSize), (int) (event.getY() / cellSize))
                )
        );

        setOnKeyPressed(event ->
                baseController.onKeyPressed(event.getCode())
        );
    }

    @Override
    public void update(GameEvent gameEvent) {
        Platform.runLater(() -> {
            if (gameEvent instanceof GameEventOneTile gameEventOneTile) {
                drawCellDuringGame(gameEventOneTile);
            }
        });
    }

    @Override
    public void initMainField(Map<String, Integer> paramsOfModelValueMap) {
        getChildren().removeAll(getChildren());

        int width = paramsOfModelValueMap.get(PARAM_NAME_WIDTH);
        int height = paramsOfModelValueMap.get(PARAM_NAME_HEIGHT);

        cellSize = Math.min(Game.APP_WIDTH / width, Game.APP_HEIGHT / height) * 2 / 3;

        GuiDefaultConstants guiDefaultConstants = new GuiDefaultConstants(
                gameType.getDefaultCellBackgroundColor(),
                gameType.getDefaultCellTextColor(),
                gameType.getDefaultCellTextValue()
        );

        cells = new GameStackPane[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boolean showGrid = true;
                boolean showCoordinates = false;
                GameStackPane cell = new GameStackPane(new XYCoordinate(x, y), cellSize, showGrid, showCoordinates);
                cells[y][x] = cell;
                // ToDo: Возможно как-то по другому сделать.
                //       Ранее здесь drawCellDuringInitMainField(...) вызывался только если для типа игры были
                //       определены значения по умолчанию. Сейчас-же вызов будет сделан в любом случае.
                //       Но вроде для всех игр это и не нужно.
                //       Например:
                //       - для Сапёра значения по умолчанию нужны.
                //       - для Сокобана значения по умолчанию НЕ нужны.
                drawCellDuringInitMainField(cell, guiDefaultConstants);
                getChildren().add(cell);
            }
        }

        setFocusTraversable(true);

        GameClientPaneJfx gameClientPaneJfx = (GameClientPaneJfx) (getParent());
        Pane prevStatePane = (Pane) (gameClientPaneJfx.getParent());
        // ToDo: Удалить вызов prevStatePane.setPrefWidth().
        //       Сейчас без этого вызова не будет автоматической ширины для prevStatePane.
        //       Но я надеялся, что она должна быть, т.к.:
        //       - внутри ViewMainField создаётся массив GameStackPane, каждый элемент которого имеет свою ширину и высоту,
        //         соответственно, ViewMainField должен быть тоже определённой высоты и ширины.
        //       - ViewMainField (как и другие выборки, реализующие View) находятся в одной GameClientPaneJfx,
        //         и соответственно, GameClientPaneJfx тоже должна быть:
        //         - в высоту, как сумма высот всех View,
        //         - в ширину, как максимум из ширин всех View.
        prevStatePane.setMinWidth(cellSize * width);
        prevStatePane.setMaxWidth(cellSize * width);

        getScene().getWindow().sizeToScene();
    }

    private void drawCellDuringInitMainField(GameStackPane cell, GuiDefaultConstants guiDefaultConstants) {
        cell.setBackgroundColor(guiDefaultConstants.getDefaultCellBackgroundColor());
        cell.setTextColor(guiDefaultConstants.getDefaultCellTextColor());
        cell.setText(guiDefaultConstants.getDefaultCellTextValue(), cellSize);
    }

    private void drawCellDuringGame(GameEventOneTile gameEventOneTile) {
        GameStackPane cell = getCellByGameEventOneTile(gameEventOneTile);

        cell.setBackgroundColor(gameEventOneTile.getCellBackgroundColor());
        cell.setTextColor(gameEventOneTile.getCellTextColor());
        cell.setText(gameEventOneTile.getCellText(), cellSize);
    }

    private GameStackPane getCellByGameEventOneTile(GameEventOneTile gameEventOneTile) {
        XYCoordinate xyCoordinate = gameEventOneTile.getXyCoordinate();
        //  ToDo:   Что-то сделать с тем, что приходится вытаскивать из XYCoordinate отдельно x и y.
        //          Из-за этого пришлось сделать getX() и getY() публичными. Нехорошо!
        return cells[xyCoordinate.getY()][xyCoordinate.getX()];
    }
}
