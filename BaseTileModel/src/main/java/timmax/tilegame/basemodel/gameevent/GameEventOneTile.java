package timmax.tilegame.basemodel.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.paint.Color;

import timmax.common.JFXColorWithExternalizable;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;

public abstract class GameEventOneTile extends GameEvent {
    private XYCoordinate xyCoordinate;

    // Поля ниже нужны для визуализации. И сейчас они добавлены сюда для работы универсального клиента.
    // Но:
    // ToDo: Было-бы лучше вынести из этого класса логику визуализации.
    // ToDo: Хотя-бы в GameType эти реквизиты можно было-бы переместить.
    protected Color cellBackgroundColor;
    protected Color cellTextColor;
    protected String cellText;

    public GameEventOneTile() {
    }

    public GameEventOneTile(XYCoordinate xyCoordinate) {
        this.xyCoordinate = xyCoordinate;
    }

    public XYCoordinate getXyCoordinate() {
        return xyCoordinate;
    }

    public Color getCellBackgroundColor() {
        return cellBackgroundColor;
    }

    public Color getCellTextColor() {
        return cellTextColor;
    }

    public String getCellText() {
        return cellText;
    }

    @Override
    public String toString() {
        return "GameEventOneTile{" +
                "xyCoordinate=" + xyCoordinate +
                ", cellBackgroundColor=" + cellBackgroundColor +
                ", cellTextColor=" + cellTextColor +
                ", cellText='" + cellText + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(xyCoordinate);

        // Тип Color не сереализуемый, поэтому сериализуем его через дополнительный класс:
        out.writeObject(new JFXColorWithExternalizable(cellBackgroundColor));
        out.writeObject(new JFXColorWithExternalizable(cellTextColor));

        out.writeObject(cellText);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        xyCoordinate = (XYCoordinate) in.readObject();

        // Тип Color не сереализуемый, поэтому десериализуем его через дополнительный класс:
        cellBackgroundColor = ((JFXColorWithExternalizable) in.readObject()).getColor();
        cellTextColor = ((JFXColorWithExternalizable) in.readObject()).getColor();

        cellText = (String) in.readObject();
    }
}
