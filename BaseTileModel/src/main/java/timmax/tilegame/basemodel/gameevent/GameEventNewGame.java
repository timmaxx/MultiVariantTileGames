package timmax.tilegame.basemodel.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

import javafx.scene.paint.Color;

public class GameEventNewGame extends GameEvent {
    private int width;
    private int height;

    // Поля ниже нужны для визуализации. И сейчас они добавлены сюда для работы универсального клиента.
    // Но:
    // ToDo: Было-бы лучше вынести из этого класса логику визуализации.
    // ToDo: Хотя-бы в GameType эти реквизиты можно было-бы переместить.
    private boolean isThereCellSettingDefault;
    private Color defaultCellBackgroundColor;
    private Color defaultCellTextColor;
    private String defaultCellText;

    public GameEventNewGame() {
    }

    public GameEventNewGame(int width, int height) {
        this(width, height, Color.BLACK, Color.BLACK, "");
        this.isThereCellSettingDefault = false;
    }

    public GameEventNewGame(int width, int height, Color defaultCellBackgroundColor, Color defaultCellTextColor, String defaultCellText) {
        this.width = width;
        this.height = height;
        this.isThereCellSettingDefault = true;
        this.defaultCellBackgroundColor = defaultCellBackgroundColor;
        this.defaultCellTextColor = defaultCellTextColor;
        this.defaultCellText = defaultCellText;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isThereCellSettingDefault() {
        return isThereCellSettingDefault;
    }

    public Color getDefaultCellBackgroundColor() {
        return defaultCellBackgroundColor;
    }

    public Color getDefaultCellTextColor() {
        return defaultCellTextColor;
    }

    public String getDefaultCellText() {
        return defaultCellText;
    }

    @Override
    public String toString() {
        return "GameEventNewGame{" +
                "width=" + width +
                ", height=" + height +
                ", isThereCellSettingDefault=" + isThereCellSettingDefault +
                ", defaultCellBackgroundColor=" + defaultCellBackgroundColor +
                ", defaultCellTextColor=" + defaultCellTextColor +
                ", defaultCellText='" + defaultCellText + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(width);
        out.writeInt(height);
        out.writeBoolean(isThereCellSettingDefault);

        // Тип Color не сереализуемый, поэтому сериализуем четыре его составляющих:
        // ToDo: Лучше было-бы сделать надстройку над Color с сериализацией.
        // out.writeObject(defaultCellColor);
        out.writeDouble(defaultCellBackgroundColor.getRed());
        out.writeDouble(defaultCellBackgroundColor.getGreen());
        out.writeDouble(defaultCellBackgroundColor.getBlue());
        out.writeDouble(defaultCellBackgroundColor.getOpacity());

        // out.writeObject(defaultTextColor);
        out.writeDouble(defaultCellTextColor.getRed());
        out.writeDouble(defaultCellTextColor.getGreen());
        out.writeDouble(defaultCellTextColor.getBlue());
        out.writeDouble(defaultCellTextColor.getOpacity());

        out.writeObject(defaultCellText);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        width = in.readInt();
        height = in.readInt();
        isThereCellSettingDefault = in.readBoolean();

        // Тип Color не сереализуемый, поэтому десериализуем четыре его составляющих и вызовем конструктор:
        // defaultCellColor = (Color) in.readObject();
        defaultCellBackgroundColor = new Color(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble());
        // defaultTextColor = (Color) in.readObject();
        defaultCellTextColor = new Color(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble());

        defaultCellText = (String) in.readObject();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameEventNewGame that = (GameEventNewGame) o;

        if (width != that.width) return false;
        if (height != that.height) return false;
        if (isThereCellSettingDefault != that.isThereCellSettingDefault) return false;
        if (!Objects.equals(defaultCellBackgroundColor, that.defaultCellBackgroundColor))
            return false;
        if (!Objects.equals(defaultCellTextColor, that.defaultCellTextColor))
            return false;
        return Objects.equals(defaultCellText, that.defaultCellText);
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        result = 31 * result + (isThereCellSettingDefault ? 1 : 0);
        result = 31 * result + (defaultCellBackgroundColor != null ? defaultCellBackgroundColor.hashCode() : 0);
        result = 31 * result + (defaultCellTextColor != null ? defaultCellTextColor.hashCode() : 0);
        result = 31 * result + (defaultCellText != null ? defaultCellText.hashCode() : 0);
        return result;
    }
}
