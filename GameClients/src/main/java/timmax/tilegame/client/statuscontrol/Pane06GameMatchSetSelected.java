package timmax.tilegame.client.statuscontrol;

import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import timmax.tilegame.basemodel.GameMatchStatus;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;
import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.basemodel.GameMatchStatus.NOT_STARTED;
import static timmax.tilegame.basemodel.GameMatchStatus.PAUSE;

public class Pane06GameMatchSetSelected extends AbstractConnectStatePane {
    private final ComboBox<String> gameMatchSetComboBox;

    public Pane06GameMatchSetSelected(TransportOfClient transportOfClient) {
        super(transportOfClient);

        // 1 (обязательные)
        // Контролы для продвижения состояния "вперёд":
        gameMatchSetComboBox = new ComboBox<>();

        nextStateButton.setText("Select the game match");
        nextStateButton.setOnAction(event -> {
            if (gameMatchSetComboBox.getValue() == null || gameMatchSetComboBox.getValue().isEmpty()) {
                return;
            }
            disableAllControls();

            //  ToDo:   Упростить код ниже по определению gameMatchId, gameMatchIsPlaying, paramsOfModelValueMap.
            String gameMatchId = gameMatchSetComboBox.getValue();
            //  ToDo:   Избавиться от "Warning:(33, 42) Unboxing of 'transportOfClient .getLocalClientStateAutomaton() .getGameMa...' may produce 'NullPointerException'"
            GameMatchStatus gameMatchStatus = transportOfClient
                    .getLocalClientStateAutomaton()
                    .getGameMatchXSet()
                    .stream()
                    .filter(x -> x.getId().equals(gameMatchId))
                    .findAny()
                    .map(GameMatchDto::getStatus)
                    .orElse(null);

            Map<String, Integer> paramsOfModelValueMap;
            if (gameMatchStatus == PAUSE) {
                // Если матч уже был начат.
                // Достаём параметры из матча.
                paramsOfModelValueMap = transportOfClient
                        .getLocalClientStateAutomaton()
                        .getGameMatchXSet()
                        .stream()
                        .filter(x -> x.getId().equals(gameMatchId))
                        .findAny()
                        .map(GameMatchDto::getParamsOfModelValueMap)
                        .orElse(null);
            } else if (gameMatchStatus == NOT_STARTED) {
                // Если матч ещё не был начат.
                // Достаём параметры из описания типа игры.
                paramsOfModelValueMap = transportOfClient
                        .getLocalClientStateAutomaton()
                        .getGameType()
                        .getParamName_paramModelDescriptionMap()
                        .getParamsOfModelValueMap();
            } else {
                throw new RuntimeException(
                        "Pane06GameMatchSetSelected :: Pane06GameMatchSetSelected(TransportOfClient transportOfClient). nextStateButton.setOnAction. gameMatchStatus = " + gameMatchStatus
                );
            }
            transportOfClient.selectGameMatch(new GameMatchDto(gameMatchId, gameMatchStatus, paramsOfModelValueMap));
        });

        // Контролы для продвижения состояния "назад":
        prevStateButton.setText("Forget the game match");
        prevStateButton.setFocusTraversable(false);
        prevStateButton.setOnAction(event -> {
            disableAllControls();
            transportOfClient.reselectGameType();
        });

        // 1
        int y = LAYOUT_Y_OF_FIRST_ROW;
        gameMatchSetComboBox.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        gameMatchSetComboBox.setLayoutY(y);

        // Получилась 1 строка контролов:
        y += DIFFERENCE_OF_LAYOUT_Y;
        nextStatePane.setMinHeight(y);
        nextStatePane.setMaxHeight(y);

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контролов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(gameMatchSetComboBox),
                List.of()
        );
    }

    // interface ObserverOnAbstractEvent
    // 2
    @Override
    public void updateOnAuthorizeUser() {
        super.updateOnAuthorizeUser();
        doOnPrevState();
    }

    // 4
    @Override
    public void updateOnSelectGameType() {
        ObservableList<GameMatchDto> observableList = FXCollections.observableArrayList();
        observableList.addAll(transportOfClient.getLocalClientStateAutomaton().getGameMatchXSet());
        gameMatchSetComboBox.setItems(
                FXCollections.observableArrayList(
                        observableList
                                .stream()
                                .map(GameMatchDto::getId)
                                .toList()
                )
        );
        doOnThisState();
    }

    // 6
    @Override
    public void updateOnSelectGameMatch() {
        doOnNextState();
    }

    // doOnХХХState()
    @Override
    protected void doOnPrevState() {
        super.doOnPrevState();
        gameMatchSetComboBox.setItems(FXCollections.observableArrayList());
    }
}
