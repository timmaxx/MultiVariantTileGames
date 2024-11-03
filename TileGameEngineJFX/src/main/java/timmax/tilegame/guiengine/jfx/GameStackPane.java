package timmax.tilegame.guiengine.jfx;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;

public class GameStackPane extends StackPane {
    private static final double COORDINATE_TEXT_FONT_SIZE_COEFFICIENT = 0.15;
    private static final int MAX_GENERAL_TEXT_LENGTH_FOR_GENERAL_TEXT_FONT_SIZE_COEFFICIENT = 4;
    private static final double GENERAL_TEXT_FONT_SIZE_COEFFICIENT = 0.4;

    public GameStackPane(XYCoordinate xyCoordinate, int cellSize, boolean showGrid, boolean showCoordinates) {
        super(new Rectangle(), new Text(), new Text());

        if (showGrid) {
            getRectangle().setWidth(cellSize - 1);
            getRectangle().setHeight(cellSize - 1);
            getRectangle().setStroke(Color.BLACK);
        }
        if (showCoordinates) {
            getCoordinateText().setFont(Font.font(cellSize * COORDINATE_TEXT_FONT_SIZE_COEFFICIENT));
            StackPane.setAlignment(getCoordinateText(), Pos.TOP_LEFT);
            getCoordinateText().setText(xyCoordinate.toString());
        }
        getRectangle().setWidth(cellSize);
        getRectangle().setHeight(cellSize);

        setLayoutX(xyCoordinate.getX() * cellSize);
        setLayoutY(xyCoordinate.getY() * cellSize);

    }

    private Rectangle getRectangle() {
        return ((Rectangle) getChildren().get(0));
    }

    private Text getGeneralText() {
        return ((Text) getChildren().get(1));
    }

    private Text getCoordinateText() {
        return ((Text) getChildren().get(2));
    }

    public void setText(String textValue, int cellSize) {
        if (getGeneralText().getText().equals(textValue)) {
            return;
        }
        if (textValue.length() <= MAX_GENERAL_TEXT_LENGTH_FOR_GENERAL_TEXT_FONT_SIZE_COEFFICIENT) {
            double fontSize = cellSize * GENERAL_TEXT_FONT_SIZE_COEFFICIENT;
            getGeneralText().setFont(Font.font(fontSize));
        } else {
            int fontSize = cellSize / textValue.length();
            getGeneralText().setFont(Font.font(fontSize));
        }
        getGeneralText().setText(textValue);
    }

    public void setBackgroundColor(Color cellColor) {
        if (cellColor != null &&
                cellColor != Color.TRANSPARENT &&
                !cellColor.equals(getRectangle().getFill())
        ) {
            getRectangle().setFill(cellColor);
        }
    }

    public void setTextColor(Color textColor) {
        if (!textColor.equals(getGeneralText().getFill())) {
            getGeneralText().setFill(textColor);
        }
    }
}
