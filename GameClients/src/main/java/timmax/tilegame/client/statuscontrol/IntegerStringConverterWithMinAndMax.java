package timmax.tilegame.client.statuscontrol;

import javafx.util.converter.IntegerStringConverter;

public class IntegerStringConverterWithMinAndMax extends IntegerStringConverter {
    private final int minValue;
    private final int maxValue;

    public IntegerStringConverterWithMinAndMax(int minValue, int maxValue) {
        if (minValue > maxValue) {
            throw new RuntimeException("minValue must be less or equal maxValue (minValue = " + minValue + ", maxValue = " + maxValue + ") ");
        }
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public Integer fromString(String value) {
        int result = super.fromString(value);
        if (result < minValue) {
            throw new RuntimeException("Result is less then minValue");
        }
        if (result > maxValue) {
            throw new RuntimeException("Result is more then maxValue");
        }
        return result;
    }
}
