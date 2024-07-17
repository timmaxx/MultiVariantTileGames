package timmax.tilegame.guiengine.jfx.view;

import javafx.application.Platform;
import javafx.scene.paint.Color;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventNewGame;
import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.transport.TransportOfClient;

import timmax.tilegame.baseview.ViewMainField;
import timmax.tilegame.guiengine.jfx.Game;
import timmax.tilegame.guiengine.jfx.GameStackPane;

public class ViewMainFieldJfx extends ViewJfx implements ViewMainField {
    // X - составляющие
    public final static int LAYOUT_X_OF_FIRST_COLUMN = 0;
    public final static int LAYOUT_X_OF_SECOND_COLUMN = 100;

    // Количество пикселей слева и справа, что-бы главное поле влезло во внутреннее окно - PrimaryStage
    public final static int PIXELS_ON_LEFT_N_RIGHT_FOR_MAIN_FIELD_FITS_INTO_PRIMARY_STAGE = 17;

    public final static int PANE_NEXT_STATE_PREF_WIDTH = 300;
    public final static int PANE_PREV_STATE_PREF_WIDTH = 0;

    public final static int BUTTON_NEXT_STATE_PREF_WIDTH = 160;
    public final static int BUTTON_PREV_STATE_PREF_WIDTH = 160;
    private final static int PIXELS_ON_RIGHT_FROM_MAIN_FIELD = 2;

    // Y - составляющие
    public final static int LAYOUT_Y_OF_FIRST_ROW = 0;
    public final static int DIFFERENCE_OF_LAYOUT_Y = 30;
    // Количество пикселей сверху и снизу, что-бы главное поле влезло во внутреннее окно - PrimaryStage
    public final static int PIXELS_ON_TOP_N_BOTTOM_FOR_MAIN_FIELD_FITS_INTO_PRIMARY_STAGE = 17;
    // Количество "строк" контролов в панелях Pane0x..., кроме последней ("строк" от 1 до 6: 3 + 2 + 1 + 1)
    // В Pane0X (с первого по предпоследний) можно найти:
    //   paneNextState.setPrefHeight(DIFFERENCE_OF_LAYOUT_Y * ?);
    //   paneNextState.setMinHeight(DIFFERENCE_OF_LAYOUT_Y * ?);
    public final static int ROWS_OF_CONTROLS_IN_PANE0X_EXCEPT_LAST = 7;
    private final static int PIXELS_ON_BOTTOM_FROM_MAIN_FIELD = 2;

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

        // Не сильно красивое решение, нужное для того, чтобы установить ширину окна приложения
        // исходя из ширины главного игрового поля.
        // ToDo: Правильным было-бы:
        //  после того, как все Node привяжутся к корню, взять ширину и высоту скомпонованных в дерево узлов и сделать
        //  соответствующую ширину и высоту окна приложения.
        //  Т.е. делать это нужно не в этом классе, а, например, в Game в start() после
        //  root.getChildren( ).addAll( nodeList);
        //  А может ещё проще - через компоновку.

        // ToDo: Похожий код см. в Pane07GameMatchPlaying::doOnPrevState()
        getParent().getScene().getWindow().setWidth(
                LAYOUT_X_OF_FIRST_COLUMN
                        + cellSize * width
                        + PIXELS_ON_LEFT_N_RIGHT_FOR_MAIN_FIELD_FITS_INTO_PRIMARY_STAGE
                        + PANE_NEXT_STATE_PREF_WIDTH
                        + BUTTON_NEXT_STATE_PREF_WIDTH
                        + BUTTON_PREV_STATE_PREF_WIDTH
                        + PIXELS_ON_RIGHT_FROM_MAIN_FIELD
        );

        // ToDo: Вернуться к информационным представлениям (про 180).
        // // 180 - количество пикселей в высоту, нужное для достаточного отображения четырёх текстовых выборок
        getParent().getScene().getWindow().setHeight(
                LAYOUT_Y_OF_FIRST_ROW
                        + cellSize * height
                        // ToDo: 6 непонятная мне константа. Лучше разбираться с ней после того,
                        //       как компоновку принципиально по другому сделать.
                        + 6 + 2 * PIXELS_ON_TOP_N_BOTTOM_FOR_MAIN_FIELD_FITS_INTO_PRIMARY_STAGE // 40
                        + ROWS_OF_CONTROLS_IN_PANE0X_EXCEPT_LAST * DIFFERENCE_OF_LAYOUT_Y
                        + PIXELS_ON_BOTTOM_FROM_MAIN_FIELD
        );
    }

    protected void drawCellDuringInitMainField(GameStackPane cell, Color defaultCellBackgroundColor, Color defaultCellTextColor, String defaultCellText) {
        cell.setBackgroundColor(defaultCellBackgroundColor);
        cell.setTextColor(defaultCellTextColor);
        cell.setText(defaultCellText, cellSize);
    }

    protected void drawCellDuringGame(GameEventOneTile gameEventOneTile) {
        GameStackPane cell = getCellByGameEventOneTile(gameEventOneTile);

        cell.setBackgroundColor(gameEventOneTile.getCellBackgroundColor());
        cell.setTextColor(gameEventOneTile.getCellTextColor());
        cell.setText(gameEventOneTile.getCellText(), cellSize);
    }

    protected GameStackPane getCellByGameEventOneTile(GameEventOneTile gameEventOneTile) {
        int x = gameEventOneTile.getX();
        int y = gameEventOneTile.getY();
        return cells[y][x];
    }
}
