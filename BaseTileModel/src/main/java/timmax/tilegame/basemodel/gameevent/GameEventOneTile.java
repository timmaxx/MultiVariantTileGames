package timmax.tilegame.basemodel.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.paint.Color;

import timmax.common.JFXColorWithExternalizable;

public abstract class GameEventOneTile extends GameEvent {
    private int x;
    private int y;

    // Поля ниже нужны для визуализации. И сейчас они добавлены сюда для работы универсального клиента.
    // Но:
    // ToDo: Было-бы лучше вынести из этого класса логику визуализации.
    // ToDo: Хотя-бы в GameType эти реквизиты можно было-бы переместить.
    protected Color cellBackgroundColor;
    protected Color cellTextColor;
    protected String cellText;

    public GameEventOneTile() {
    }

    public GameEventOneTile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
                "x=" + x +
                ", y=" + y +
                ", cellBackgroundColor=" + cellBackgroundColor +
                ", cellTextColor=" + cellTextColor +
                ", cellText='" + cellText + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(x);
        out.writeInt(y);

        // Тип Color не сереализуемый, поэтому сериализуем его через дополнительный класс:
        out.writeObject(new JFXColorWithExternalizable(cellBackgroundColor));
        out.writeObject(new JFXColorWithExternalizable(cellTextColor));

        out.writeObject(cellText);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        x = in.readInt();
        y = in.readInt();

        // Тип Color не сереализуемый, поэтому десериализуем его через дополнительный класс:
        cellBackgroundColor = ((JFXColorWithExternalizable) in.readObject()).getColor();
        cellTextColor = ((JFXColorWithExternalizable) in.readObject()).getColor();

        cellText = (String) in.readObject();
    }
}
