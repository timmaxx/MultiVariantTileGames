package timmax.common;

import javafx.scene.paint.Color;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

// Класс сделан для сериализации / десериализации класса javafx.scene.paint.Color.
// Сам класс javafx.scene.paint.Color не сериализуемый и более того он финальный (т.е. наследника от него не сделаешь).
// Пример применения для сериализации:
//  {   Color someColor = ...;
//      out.writeObject(new JFXColorWithExternalizable(defaultCellBackgroundColor));
//  }
// Пример применения для десериализации:
//  {   Color someColor = ((JFXColorWithExternalizable) in.readObject()).getColor();
//  }
public class JFXColorWithExternalizable implements Externalizable {
    private Color color;

    public JFXColorWithExternalizable() {
    }

    public JFXColorWithExternalizable(Color color) {
        this();
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeDouble(color.getRed());
        out.writeDouble(color.getGreen());
        out.writeDouble(color.getBlue());
        out.writeDouble(color.getOpacity());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        color = new Color(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JFXColorWithExternalizable that = (JFXColorWithExternalizable) o;

        return color.equals(that.color);
    }

    @Override
    public int hashCode() {
        return color.hashCode();
    }

    @Override
    public String toString() {
        return
                JFXColorWithExternalizable.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                "color=" + color +
                '}';
    }
}
