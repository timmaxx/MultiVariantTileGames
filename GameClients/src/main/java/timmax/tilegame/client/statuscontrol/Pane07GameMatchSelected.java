package timmax.tilegame.client.statuscontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Region;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;
import timmax.tilegame.baseview.View;
import timmax.tilegame.baseview.ViewMainField;
import timmax.tilegame.guiengine.jfx.GameClientPaneJfx;
import timmax.tilegame.transport.ISenderOfEventOfClient;

import static timmax.tilegame.basemodel.GameMatchStatus.GAME;
import static timmax.tilegame.basemodel.GameMatchStatus.NOT_STARTED;

public class Pane07GameMatchSelected extends AbstractConnectStatePane {
    private final GameClientPaneJfx gameClientPaneJfx;

    public Pane07GameMatchSelected(ISenderOfEventOfClient senderOfEventOfClient) {
        super(senderOfEventOfClient);

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

            if (senderOfEventOfClient.getLocalClientStateAutomaton().getGameMatchX().getStatus() == GAME) {
                throw new RuntimeException("Resume is not realised yet!");
            } else if (senderOfEventOfClient.getLocalClientStateAutomaton().getGameMatchX().getStatus() == NOT_STARTED) {
                senderOfEventOfClient.startGameMatch(paramsOfModelValueMap);
            } else {
                throw new RuntimeException("Pane07GameMatchSelected :: Pane07GameMatchSelected(TransportOfClient transportOfClient). nextStateButton.setOnAction. transportOfClient.getLocalClientStateAutomaton().getGameMatchX().getStatus() = " + senderOfEventOfClient.getLocalClientStateAutomaton().getGameMatchX().getStatus());
            }
            getScene().getWindow().sizeToScene();
        });

        // Контролы для продвижения состояния "назад":
        gameClientPaneJfx = new GameClientPaneJfx();

        prevStateButton.setText("Stop the game match");
        prevStateButton.setFocusTraversable(false); // Это в любом случае д.б.
        prevStateButton.setOnAction(event -> {
            disableAllControls();
            senderOfEventOfClient.resetGameMatch();
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
        for (String paramName : senderOfEventOfClient.getLocalClientStateAutomaton().getParamName_paramModelDescriptionMap().keySet()) {
            Label paramNameLabel = new Label(paramName);
            paramNameLabel.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN);
            paramNameLabel.setLayoutY(y);

            TextField paramNameTextField = new TextField();
            paramNameTextField.setId(paramName);
            paramNameTextField.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
            paramNameTextField.setLayoutY(y);
            ParamOfModelDescription paramOfModelDescription = senderOfEventOfClient
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
            if (senderOfEventOfClient.getLocalClientStateAutomaton().getGameMatchStatus() == GAME) {
                // Если матч уже был начат.
                // Достаём параметр из матча.
                paramValue = senderOfEventOfClient
                        .getLocalClientStateAutomaton()
                        .getGameMatchX()
                        .getParamsOfModelValueMap()
                        .get(paramName);
            } else if (senderOfEventOfClient.getLocalClientStateAutomaton().getGameMatchStatus() == NOT_STARTED) {
                // Если матч ещё не был начат.
                // Достаём параметр из описания типа игры.
                paramValue = paramOfModelDescription.getDefaultValue();
            } else {
                throw new RuntimeException("Pane07GameMatchSelected :: void updateOnSetGameMatch(). transportOfClient.getLocalClientStateAutomaton().getGameMatchStatus() = " + senderOfEventOfClient.getLocalClientStateAutomaton().getGameMatchStatus());
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
            region.setDisable(senderOfEventOfClient.getLocalClientStateAutomaton().getGameMatchStatus() != NOT_STARTED);
        }

        gameClientPaneJfx.createViews(senderOfEventOfClient);
    }

    // 7
    @Override
    public void updateOnSetGameMatchIsPlaying() {
        doOnNextState();

        //  ToDo:   Может этот код вообще перенести в отдельную Pane08 (не реализована).
        //          Код ниже делается на главной выборке, которая находится в gameClientPaneJfx при переходе
        //          в следующее состояние.
        //  ToDo:   Попробовать вызывать getView у класса GameClientPaneJfx.
        View view = senderOfEventOfClient.getLocalClientStateAutomaton().getView(ViewMainField.class.getSimpleName());
        if (view instanceof ViewMainField viewMainField) {
            viewMainField.initMainField(senderOfEventOfClient.getLocalClientStateAutomaton().getGameMatchExtendedDto().getParamsOfModelValueMap());
            for (GameEventOneTile gameEventOneTile : senderOfEventOfClient.getLocalClientStateAutomaton().getGameMatchExtendedDto().getGameEventOneTileSet()) {
                viewMainField.update(gameEventOneTile);
            }
        }
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
