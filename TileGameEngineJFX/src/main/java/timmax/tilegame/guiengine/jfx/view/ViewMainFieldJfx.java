package timmax.tilegame.guiengine.jfx.view;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventNewGame;
import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.guiengine.jfx.GameClientPaneJfx;
import timmax.tilegame.transport.TransportOfClient;

import timmax.tilegame.baseview.ViewMainField;
import timmax.tilegame.guiengine.jfx.Game;
import timmax.tilegame.guiengine.jfx.GameStackPane;

public class ViewMainFieldJfx extends ViewJfx implements ViewMainField {
    // X - составляющие
    public final static int LAYOUT_X_OF_FIRST_COLUMN = 0;
    public final static int LAYOUT_X_OF_SECOND_COLUMN = 100;

    public final static int PANE_NEXT_STATE_PREF_WIDTH = 300;

    public final static int BUTTON_NEXT_STATE_PREF_WIDTH = 160;
    public final static int BUTTON_PREV_STATE_PREF_WIDTH = 160;

    // Y - составляющие
    public final static int LAYOUT_Y_OF_FIRST_ROW = 0;
    public final static int DIFFERENCE_OF_LAYOUT_Y = 30;

    protected GameStackPane[][] cells;
    protected int cellSize;

    public ViewMainFieldJfx(
            TransportOfClient transportOfClient,
            BaseController baseController,
            String viewName) {
        super(transportOfClient, baseController, viewName);

        setOnMouseClicked(event ->
                baseController.onMouseClick(event.getButton(), (int) (event.getX() / cellSize), (int) (event.getY() / cellSize))
        );

        setOnKeyPressed(event ->
                baseController.onKeyPressed(event.getCode())
        );
    }

    @Override
    public void update(GameEvent gameEvent) {
        Platform.runLater(() -> {
            if (gameEvent instanceof GameEventNewGame gameEventNewGame) {
                initMainField(gameEventNewGame);
            } else if (gameEvent instanceof GameEventOneTile gameEventOneTile) {
                drawCellDuringGame(gameEventOneTile);
            }
        });
    }

    private void initMainField(GameEventNewGame gameEventNewGame) {
        getChildren().removeAll(getChildren());

        int width = gameEventNewGame.getWidth();
        int height = gameEventNewGame.getHeight();
        cellSize = Math.min(Game.APP_WIDTH / width, Game.APP_HEIGHT / height) * 2 / 3;

        Color defaultCellBackgroundColor = gameEventNewGame.getDefaultCellBackgroundColor();
        Color defaultCellTextColor = gameEventNewGame.getDefaultCellTextColor();
        String defaultCellText = gameEventNewGame.getDefaultCellText();

        cells = new GameStackPane[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boolean showGrid = true;
                boolean showCoordinates = false;
                GameStackPane cell = new GameStackPane(x, y, cellSize, showGrid, showCoordinates);
                cells[y][x] = cell;
                if (gameEventNewGame.isThereCellSettingDefault()) {
                    drawCellDuringInitMainField(cell, defaultCellBackgroundColor, defaultCellTextColor, defaultCellText);
                }
                getChildren().add(cell);
            }
        }

        setFocusTraversable(true);

        GameClientPaneJfx gameClientPaneJfx = (GameClientPaneJfx)(getParent());
        Pane prevStatePane = (Pane)(gameClientPaneJfx.getParent());
        // ToDo: Удалить вызов prevStatePane.setPrefWidth().
        //       Сейчас без этого вызова не будет автоматической ширины для prevStatePane.
        //       Но я надеялся, что она должна быть, т.к.:
        //       - внутри ViewMainField создаётся массив GameStackPane, каждый элемент которого имеет свою ширину и высоту,
        //         соответственно, ViewMainField должен быть тоже определённой высоты и ширины.
        //       - ViewMainField (как и другие выборки, реализующие View) находятся в одной GameClientPaneJfx,
        //         и соответственно, GameClientPaneJfx тоже должна быть:
        //         - в высоту, как сумма высот всех View,
        //         - в ширину, как максимум из ширин всех View.
        prevStatePane.setPrefWidth(cellSize * width);

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
