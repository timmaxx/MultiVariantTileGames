package timmax.tilegame.guiengine.jfx.view;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventNewGame;
import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

import timmax.tilegame.guiengine.jfx.Game;
import timmax.tilegame.guiengine.jfx.GameStackPane;
import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;

public class ViewMainFieldJfx extends ViewJfx {
    private final LocalMouseEventHandler localMouseEventHandler;
//    private final LocalKeyEventHandler localKeyEventHandler;
    protected GameStackPane[][] cells;
    protected int cellSize;

    public ViewMainFieldJfx(BaseModel baseModel, GameStackPaneController gameStackPaneController) {
        super(baseModel, gameStackPaneController);
        localMouseEventHandler = new LocalMouseEventHandler();
/*
        localKeyEventHandler = new LocalKeyEventHandler();
        setOnKeyPressed(localKeyEventHandler);
*/
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
                initOnMouseClickEventHandlerOnCell(cell);
                if (gameEventNewGame.isThereCellSettingDefault()) {
                    drawCellDuringInitMainField(cell, defaultCellBackgroundColor, defaultCellTextColor, defaultCellText);
                }
                getChildren().add(cell);
            }
        }
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

        //( ( Stage)( getParent( ).getScene( ).getWindow( ))).setResizable( false);
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

    public void initOnMouseClickEventHandlerOnCell(GameStackPane cell) {
        if (gameStackPaneController == null) {
            return;
        }
        cell.setOnMouseClicked(localMouseEventHandler);
    }

    private class LocalMouseEventHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("class LocalMouseEventHandler. method handle");
            int x = ((GameStackPane) event.getSource()).getX();
            int y = ((GameStackPane) event.getSource()).getY();
            gameStackPaneController.onMouseClick(event.getButton(), x, y);
        }
    }
/*
    private class LocalKeyEventHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            System.out.println("ViewMainFieldJfx. class LocalKeyEventHandler. method handle");
            System.out.println("  event.getCode() = " + event.getCode());
        }
    }
*/
}
