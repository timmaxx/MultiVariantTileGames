package timmax.tilegame.guiengine.jfx.view;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventNewGame;
import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.guiengine.jfx.GameClientPaneJfx;
import timmax.tilegame.transport.TransportOfClient;

import timmax.tilegame.baseview.ViewMainField;
import timmax.tilegame.guiengine.jfx.Game;
import timmax.tilegame.guiengine.jfx.GameStackPane;

public class ViewMainFieldJfx extends ViewJfx implements ViewMainField {
    protected GameStackPane[][] cells;
    protected int cellSize;

    public ViewMainFieldJfx(
            TransportOfClient transportOfClient,
            BaseController baseController,
            String viewName,
            GameType gameType) {
        super(transportOfClient, baseController, viewName, gameType);

        setOnMouseClicked(event ->
                baseController.onMouseClick(event.getButton(), (int) (event.getX() / cellSize), (int) (event.getY() / cellSize))
        );

        setOnKeyPressed(event ->
                baseController.onKeyPressed(event.getCode())
        );
    }

    @Override
    // ToDo: Возможно объявить этот метод как "public void update(GameEventOneTile gameEvent)"
    public void update(GameEvent gameEvent) {
        Platform.runLater(() -> {
            if (gameEvent instanceof GameEventOneTile gameEventOneTile) {
                drawCellDuringGame(gameEventOneTile);
            }
        });
    }

    @Override
    public void initMainField(int width, int height) {
        getChildren().removeAll(getChildren());

        cellSize = Math.min(Game.APP_WIDTH / width, Game.APP_HEIGHT / height) * 2 / 3;

        Color defaultCellBackgroundColor = gameType.getDefaultCellBackgroundColor();
        Color defaultCellTextColor = gameType.getDefaultCellTextColor();
        String defaultCellText = gameType.getDefaultCellTextValue();

        cells = new GameStackPane[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boolean showGrid = true;
                boolean showCoordinates = false;
                GameStackPane cell = new GameStackPane(x, y, cellSize, showGrid, showCoordinates);
                cells[y][x] = cell;
                // ToDo: Возможно как-то по другому сделать.
                //       Ранее здесь drawCellDuringInitMainField(...) вызывался только если для типа игры были
                //       определены значения по умолчанию. Сейчас-же вызов будет сделан в любом случае.
                //       Но вроде для всех игр это и не нужно.
                //       Например:
                //       - для Сапёра значения по умолчанию нужны.
                //       - для Сокобана значения по умолчанию НЕ нужны.
                drawCellDuringInitMainField(cell, defaultCellBackgroundColor, defaultCellTextColor, defaultCellText);
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

    private void drawCellDuringInitMainField(GameStackPane cell, Color defaultCellBackgroundColor, Color defaultCellTextColor, String defaultCellText) {
        cell.setBackgroundColor(defaultCellBackgroundColor);
        cell.setTextColor(defaultCellTextColor);
        cell.setText(defaultCellText, cellSize);
    }

    private void drawCellDuringGame(GameEventOneTile gameEventOneTile) {
        GameStackPane cell = getCellByGameEventOneTile(gameEventOneTile);

        cell.setBackgroundColor(gameEventOneTile.getCellBackgroundColor());
        cell.setTextColor(gameEventOneTile.getCellTextColor());
        cell.setText(gameEventOneTile.getCellText(), cellSize);
    }

    private GameStackPane getCellByGameEventOneTile(GameEventOneTile gameEventOneTile) {
        int x = gameEventOneTile.getX();
        int y = gameEventOneTile.getY();
        return cells[y][x];
    }
}
