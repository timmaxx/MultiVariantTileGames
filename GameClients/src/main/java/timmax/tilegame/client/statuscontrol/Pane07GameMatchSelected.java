package timmax.tilegame.client.statuscontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Region;

import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;
import timmax.tilegame.guiengine.jfx.GameClientPaneJfx;
import timmax.tilegame.transport.TransportOfClient;

public class Pane07GameMatchSelected extends AbstractConnectStatePane {
    private GameClientPaneJfx gameClientPaneJfx;

    public Pane07GameMatchSelected(TransportOfClient transportOfClient) {
        super(transportOfClient);

        // 1 (обязательные)
        // Контролы для продвижения состояния "вперёд":
        nextStateButton.setText("Start the game match");
        nextStateButton.setOnAction(event -> {
            disableAllControls();
            Map<String, Integer> paramsOfModelValueMap = new HashMap<>();
            for (Region region : getNextStateControlsList()) {
                if (region instanceof TextField textField) {
                    paramsOfModelValueMap.put(
                            textField.getId(),
                            Integer.valueOf(textField.getText()));
                }
            }
            transportOfClient.setGameMatchIsPlaying(paramsOfModelValueMap);
            gameClientPaneJfx.createViews(transportOfClient);
            getScene().getWindow().sizeToScene();
        });

        // Контролы для продвижения состояния "назад":
        gameClientPaneJfx = new GameClientPaneJfx();

        prevStateButton.setText("Stop the game match");
        prevStateButton.setFocusTraversable(false); // Это в любом случае д.б.
        prevStateButton.setOnAction(event -> {
            disableAllControls();
            transportOfClient.resetGameMatch();
            gameClientPaneJfx.clearChildren();
            prevStatePane.setMinWidth(0);
            prevStatePane.setMaxWidth(0);
            getScene().getWindow().sizeToScene();
        });

        // По сравнению с предыдущими Pane0X (1 - 6)
        // Здесь нет строки "Получилось N строк контролов:"

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контролов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(),
                List.of(gameClientPaneJfx)
        );
    }

    // interface ObserverOnAbstractEvent
    // 4
    @Override
    public void updateOnSetGameType() {
        super.updateOnSetGameType();
        doOnPrevState();
    }

    // 6
    @Override
    public void updateOnSetGameMatch() {
        nextStatePane.getChildren().clear();
        List<Region> regionList = new ArrayList<>();

        int y = LAYOUT_Y_OF_FIRST_ROW;
        // ToDo: Отказаться от прямого доступа к getParamName_paramModelDescriptionMap().
        //       Здесь используется
        //       LocalClientStateAutomaton :: Map<String, ParamOfModelDescription> getParamName_paramModelDescriptionMap()
        for (String paramName : transportOfClient.getLocalClientStateAutomaton().getParamName_paramModelDescriptionMap().keySet()) {
            Label paramNameLabel = new Label(paramName);
            paramNameLabel.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN);
            paramNameLabel.setLayoutY(y);

            TextField paramNameTextField = new TextField();
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

            regionList.add(paramNameLabel);
            regionList.add(paramNameTextField);
            y += DIFFERENCE_OF_LAYOUT_Y;
        }

        nextStatePane.setMinHeight(y);
        nextStatePane.setMaxHeight(y);
        gameClientPaneJfx.clearChildren();
        setListsOfControlsAndAllDisable(
                regionList,
                List.of(gameClientPaneJfx)
        );
        getScene().getWindow().sizeToScene();
        doOnThisState();
    }

    // 7
    @Override
    public void updateOnSetGameMatchIsPlaying() {
        doOnNextState();
    }

    // X
    @Override
    public void doOnPrevState() {
        super.doOnPrevState();

        nextStatePane.setMinHeight(DIFFERENCE_OF_LAYOUT_Y);
        nextStatePane.setMaxHeight(DIFFERENCE_OF_LAYOUT_Y);
        setListsOfControlsAndAllDisable(
                List.of(),
                List.of(gameClientPaneJfx)
        );
        getScene().getWindow().sizeToScene();
    }
}
