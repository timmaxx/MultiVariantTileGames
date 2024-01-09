package timmax.tilegame.guiengine.jfx.view;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventNewGame;
import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

import timmax.tilegame.guiengine.jfx.Game;
import timmax.tilegame.guiengine.jfx.GameStackPane;

public class ViewMainFieldJfx extends ViewJfx {
    protected GameStackPane[][] cells;
    protected int cellSize;

    public ViewMainFieldJfx(IModelOfClient iModelOfClient, BaseController baseController) {
        super(iModelOfClient, baseController);

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
        ((Stage) (getParent().getScene().getWindow())).setResizable(true);

        int width = gameEventNewGame.getWidth();
        int height = gameEventNewGame.getHeight();
        cellSize = Math.min(Game.APP_WIDTH / width, Game.APP_HEIGHT / height);

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
        // this.setWidth( cellSize * width);
        // this.setHeight( cellSize * height);
        //  17 - количество пикселей слева и справа, что-бы главное поле влезло во внутреннее окно - PrimaryStage
        getParent().getScene().getWindow().setWidth(cellSize * width + 17 + 300);
        //  40 - количество пикселей сверху и снизу (высота заголовка окна приложения), что-бы главное поле влезло во внутреннее окно - PrimaryStage
        // 180 - количество пикселей в высоту, нужное для достаточного отображения четырёх текстовых выборок
        getParent().getScene().getWindow().setHeight(cellSize * height + 40 + 180);
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
