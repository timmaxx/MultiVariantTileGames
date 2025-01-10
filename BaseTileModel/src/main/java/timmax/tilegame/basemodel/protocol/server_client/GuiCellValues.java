package timmax.tilegame.basemodel.protocol.server_client;

import javafx.scene.paint.Color;
import timmax.common.JFXColorWithExternalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class GuiCellValues implements Externalizable {
    private Color backgroundColor;
    private Color textColor;
    private String textValue;

    public GuiCellValues() {}

    public GuiCellValues(Color backgroundColor, Color textColor, String textValue) {
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.textValue = textValue;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public String getTextValue() {
        return textValue;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        // Тип Color не сереализуемый, поэтому сериализуем его через дополнительный класс:
        out.writeObject(new JFXColorWithExternalizable(backgroundColor));
        out.writeObject(new JFXColorWithExternalizable(textColor));

        out.writeObject(textValue);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // Тип Color не сереализуемый, поэтому десериализуем его через дополнительный класс:
        backgroundColor = ((JFXColorWithExternalizable) in.readObject()).getColor();
        textColor = ((JFXColorWithExternalizable) in.readObject()).getColor();

        textValue = (String) in.readObject();
    }

    @Override
    public String toString() {
        return
                GuiCellValues.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                        "backgroundColor=" + backgroundColor +
                        ", textColor=" + textColor +
                        ", textValue='" + textValue + '\'' +
                        '}';
    }
}
