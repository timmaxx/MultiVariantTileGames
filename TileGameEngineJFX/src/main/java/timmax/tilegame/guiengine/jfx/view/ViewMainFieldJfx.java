package timmax.tilegame.guiengine.jfx.view;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventNewGame;
import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

import timmax.tilegame.guiengine.jfx.Game;
import timmax.tilegame.guiengine.jfx.GameStackPane;
import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;

abstract public class ViewMainFieldJfx extends ViewJfx {
    private final LocalEventHandler localEventHandler;
    protected GameStackPane[][] cells;
    protected int cellSize;

    public ViewMainFieldJfx(BaseModel baseModel, GameStackPaneController gameStackPaneController) {
        super(baseModel, gameStackPaneController);
        localEventHandler = new LocalEventHandler();
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

        cells = new GameStackPane[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boolean showGrid = true;
                boolean showCoordinates = false;
                GameStackPane cell = new GameStackPane(x, y, cellSize, showGrid, showCoordinates);
                cells[y][x] = cell;
                initOnMouseClickEventHandlerOnCell(cell);
                drawCellDuringInitMainField(cell);
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

    protected void drawCellDuringInitMainField(GameStackPane cell) {
    }

    protected void drawCellDuringGame(GameEventOneTile gameEventOneTile) {
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
        cell.setOnMouseClicked(localEventHandler);
    }

    private class LocalEventHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            int x = ((GameStackPane) event.getSource()).getX();
            int y = ((GameStackPane) event.getSource()).getY();
            switch (event.getButton()) {
                case PRIMARY -> gameStackPaneController.onMousePrimaryClick(x, y);
                case SECONDARY -> gameStackPaneController.onMouseSecondaryClick(x, y);
            }
        }
    }
}
