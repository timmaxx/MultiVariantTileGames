package timmax.tilegame.basemodel.protocol.server_client;

import javafx.scene.paint.Color;
import timmax.common.JFXColorWithExternalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

//  ToDo:   Переименовать.
public class GuiDefaultConstants implements Externalizable {
    private Color defaultCellBackgroundColor;
    private Color defaultCellTextColor;
    private String defaultCellTextValue;

    public GuiDefaultConstants() {}

    public GuiDefaultConstants(Color defaultCellBackgroundColor, Color defaultCellTextColor, String defaultCellTextValue) {
        this.defaultCellBackgroundColor = defaultCellBackgroundColor;
        this.defaultCellTextColor = defaultCellTextColor;
        this.defaultCellTextValue = defaultCellTextValue;
    }

    public Color getDefaultCellBackgroundColor() {
        return defaultCellBackgroundColor;
    }

    public Color getDefaultCellTextColor() {
        return defaultCellTextColor;
    }

    public String getDefaultCellTextValue() {
        return defaultCellTextValue;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        // Тип Color не сереализуемый, поэтому сериализуем его через дополнительный класс:
        out.writeObject(new JFXColorWithExternalizable(defaultCellBackgroundColor));
        out.writeObject(new JFXColorWithExternalizable(defaultCellTextColor));

        out.writeObject(defaultCellTextValue);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // Тип Color не сереализуемый, поэтому десериализуем его через дополнительный класс:
        defaultCellBackgroundColor = ((JFXColorWithExternalizable) in.readObject()).getColor();
        defaultCellTextColor = ((JFXColorWithExternalizable) in.readObject()).getColor();

        defaultCellTextValue = (String) in.readObject();
    }

    @Override
    public String toString() {
        return
                GuiDefaultConstants.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                        "defaultCellBackgroundColor=" + defaultCellBackgroundColor +
                        ", defaultCellTextColor=" + defaultCellTextColor +
                        ", defaultCellTextValue='" + defaultCellTextValue + '\'' +
                        '}';
    }
}
