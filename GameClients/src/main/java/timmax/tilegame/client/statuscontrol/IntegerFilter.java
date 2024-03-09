package timmax.tilegame.client.statuscontrol;

import java.util.function.UnaryOperator;

import javafx.scene.control.TextFormatter;

public class IntegerFilter implements UnaryOperator<TextFormatter.Change> {
    @Override
    public TextFormatter.Change apply(TextFormatter.Change change) {
        if (change.getControlNewText().matches("^-?[0-9]*")) {
            return change;
        }
        return null;
    }
}
