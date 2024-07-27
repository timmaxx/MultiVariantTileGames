package timmax.tilegame.client.statuscontrol;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;
import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx.LAYOUT_X_OF_FIRST_COLUMN;
import static timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx.LAYOUT_X_OF_SECOND_COLUMN;

// См. комменты к Pane07GameMatchSelected :: void updateOnSetGameMatch()
public class ControlJfx {
    private final Label paramNameLabel;
    private final TextField paramNameTextField;

    public ControlJfx(String paramName, int y, TransportOfClient transportOfClient) {
        paramNameLabel = new Label(paramName);
        paramNameLabel.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN);
        paramNameLabel.setLayoutY(y);

        paramNameTextField = new TextField();
        paramNameTextField.setId(paramName);
        paramNameTextField.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        paramNameTextField.setLayoutY(y);
        ParamOfModelDescription paramOfModelDescription = transportOfClient
                .getLocalClientStateAutomaton()
                .getParamName_paramModelDescriptionMap()
                .get(paramName);
        paramNameTextField.setTextFormatter(
                new TextFormatter<>(
                        new IntegerStringConverterWithMinAndMax(
                                paramOfModelDescription.getMinValue(),
                                paramOfModelDescription.getMaxValue()
                        ),
                        paramOfModelDescription.getDefaultValue(),
                        new IntegerFilter()
                )
        );
    }

    public Label getParamNameLabel() {
        return paramNameLabel;
    }

    public TextField getParamNameTextField() {
        return paramNameTextField;
    }
}
