package timmax.tilegame.basemodel.protocol.server;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ParamOfModelDescription implements Externalizable {
    private int defaultValue;
    private int minValue;
    private int maxValue;

    public ParamOfModelDescription() {
        super();
    }

    public ParamOfModelDescription(int defaultValue, int minValue, int maxValue) {
        this();
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
        verifyParams();
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    private void verifyParams() {
        if (defaultValue < minValue || defaultValue > maxValue) {
            throw new RuntimeException("Wrong parameters. " + toString());
        }
    }

    @Override
    public String toString() {
        return
                ParamOfModelDescription.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                        "defaultValue=" + defaultValue +
                        ", minValue=" + minValue +
                        ", maxValue=" + maxValue +
                        '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(defaultValue);
        out.writeInt(minValue);
        out.writeInt(maxValue);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        defaultValue = in.readInt();
        minValue = in.readInt();
        maxValue = in.readInt();
        verifyParams();
    }
}
