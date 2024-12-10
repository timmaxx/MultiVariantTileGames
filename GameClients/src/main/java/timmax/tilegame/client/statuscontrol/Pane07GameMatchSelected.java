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

import static timmax.tilegame.basemodel.GameMatchStatus.GAME;
import static timmax.tilegame.basemodel.GameMatchStatus.NOT_STARTED;

public class Pane07GameMatchSelected extends AbstractConnectStatePane {
    private final GameClientPaneJfx gameClientPaneJfx;

    public Pane07GameMatchSelected(TransportOfClient transportOfClient) {
        super(transportOfClient);

        // 1 (обязательные)
        // Контролы для продвижения состояния "вперёд":
        nextStateButton.setText("Start / resume the game match");
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

            if (transportOfClient.getLocalClientStateAutomaton().getGameMatchX().getStatus() == GAME) {
                throw new RuntimeException("Resume is not realised yet!");
            } else if (transportOfClient.getLocalClientStateAutomaton().getGameMatchX().getStatus() == NOT_STARTED) {
                //  Не используется возвращаемое значение (но его там и нет)!
                transportOfClient.startGameMatch(paramsOfModelValueMap);
            } else {
                throw new RuntimeException("Pane07GameMatchSelected :: Pane07GameMatchSelected(TransportOfClient transportOfClient). nextStateButton.setOnAction. transportOfClient.getLocalClientStateAutomaton().getGameMatchX().getStatus() = " + transportOfClient.getLocalClientStateAutomaton().getGameMatchX().getStatus());
            }
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
            // !!! transportOfClient.getLocalClientStateAutomaton().getGameMatchIsPlaying() == true
            // ToDo: Если
            //       transportOfClient.getLocalClientStateAutomaton().getGameMatchIsPlaying() == true
            //       то вместо
            //       paramOfModelDescription.getDefaultValue()
            //       нужно взять значение из текущего матча.
            int paramValue;
            if (transportOfClient.getLocalClientStateAutomaton().getGameMatchStatus() == GAME) {
                // Если матч уже был начат.
                // Достаём параметр из матча.
                paramValue = transportOfClient
                        .getLocalClientStateAutomaton()
                        .getGameMatchX()
                        .getParamsOfModelValueMap()
                        .get(paramName);
            } else if (transportOfClient.getLocalClientStateAutomaton().getGameMatchStatus() == NOT_STARTED) {
                // Если матч ещё не был начат.
                // Достаём параметр из описания типа игры.
                paramValue = paramOfModelDescription.getDefaultValue();
            } else {
                throw new RuntimeException("Pane07GameMatchSelected :: void updateOnSetGameMatch(). transportOfClient.getLocalClientStateAutomaton().getGameMatchStatus() = " + transportOfClient.getLocalClientStateAutomaton().getGameMatchStatus());
            }
            paramNameTextField.setTextFormatter(
                    new TextFormatter<>(
                            new IntegerStringConverterWithMinAndMax(
                                    paramOfModelDescription.getMinValue(),
                                    paramOfModelDescription.getMaxValue()
                            ),
                            paramValue,
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

        // !!! transportOfClient.getLocalClientStateAutomaton().getGameMatchIsPlaying() == true
        // Запретим для редактирования параметры матча, если матч уже был начат.
        // Этот цикл нужно сделать после вызова 'doOnThisState()', т.к. там
        for (Region region : regionList) {
            region.setDisable(transportOfClient.getLocalClientStateAutomaton().getGameMatchStatus() != NOT_STARTED);
        }

        gameClientPaneJfx.createViews(transportOfClient);
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
