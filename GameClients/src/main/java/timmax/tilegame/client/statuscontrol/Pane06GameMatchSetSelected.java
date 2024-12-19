package timmax.tilegame.client.statuscontrol;

import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;
import timmax.tilegame.transport.ISenderOfEventOfClient;

import static timmax.tilegame.basemodel.GameMatchStatus.NOT_STARTED;
import static timmax.tilegame.basemodel.GameMatchStatus.PAUSE;

public class Pane06GameMatchSetSelected extends AbstractConnectStatePane {
    private final ComboBox<String> gameMatchSetComboBox;

    public Pane06GameMatchSetSelected(ISenderOfEventOfClient senderOfEventOfClient) {
        super(senderOfEventOfClient);

        // 1 (обязательные)
        // Контролы для продвижения состояния "вперёд":
        gameMatchSetComboBox = new ComboBox<>();

        nextStateButton.setText("Select the game match");
        nextStateButton.setOnAction(event -> {
            if (gameMatchSetComboBox.getValue() == null || gameMatchSetComboBox.getValue().isEmpty()) {
                return;
            }
            disableAllControls();

            String gameMatchId = gameMatchSetComboBox.getValue();

            //  ToDo:   Избавиться от приведение типа.
            //  Warning:(36, 56) Unchecked call to 'filter(Predicate<? super T>)' as a member of raw type 'java.util.stream.Stream'
            //  Warning:(36, 56) Unchecked call to 'orElse(T)' as a member of raw type 'java.util.Optional'
            //  Warning:(107, 31) Unchecked assignment: 'java.util.Set' to 'java.util.Collection<? extends timmax.tilegame.basemodel.protocol.server_client.GameMatchDto>'. Reason: 'transportOfClient.getLocalClientStateAutomaton().getGameType()' has raw type, so result of getGameMatchDtoSet is erased
            GameMatchDto gameMatchDto = (GameMatchDto) senderOfEventOfClient
                    .getLocalClientStateAutomaton()
                    .getGameType()
                    .getGameMatchDtoSet()
                    .stream()
                    //  ToDo:   Избавиться от приведение типа.
                    .filter(x -> ((GameMatchDto) x).getId().equals(gameMatchId))
                    .findAny()
                    .orElse(null);

            Map<String, Integer> paramsOfModelValueMap;
            if (gameMatchDto.getStatus() == PAUSE) {
                // Если матч уже был начат.
                // Достаём параметры из матча.
                paramsOfModelValueMap = gameMatchDto.getParamsOfModelValueMap();

            } else if (gameMatchDto.getStatus() == NOT_STARTED) {
                // Если матч ещё не был начат.
                // Достаём параметры из описания типа игры.
                paramsOfModelValueMap = senderOfEventOfClient
                        .getLocalClientStateAutomaton()
                        .getGameType()
                        .getParamName_paramModelDescriptionMap()
                        .getParamsOfModelValueMap();
            } else {
                throw new RuntimeException(
                        "Pane06GameMatchSetSelected :: Pane06GameMatchSetSelected(TransportOfClient transportOfClient). nextStateButton.setOnAction. gameMatchStatus = " + gameMatchDto.getStatus()
                );
            }
            gameMatchDto.setParamsOfModelValueMap(paramsOfModelValueMap);
            senderOfEventOfClient.setGameMatch(gameMatchDto);
        });

        // Контролы для продвижения состояния "назад":
        prevStateButton.setText("Forget the game match");
        prevStateButton.setFocusTraversable(false);
        prevStateButton.setOnAction(event -> {
            disableAllControls();
            senderOfEventOfClient.resetGameType();
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
    public void updateOnSetGameType() {
        ObservableList<GameMatchDto> observableList = FXCollections.observableArrayList();
        //  ToDo:   Надо делать, как в закомментированном блоке.
        // observableList.addAll(senderOfEventOfClient.getLocalClientStateAutomaton().getGameMatchDtoSet());
        observableList.addAll(senderOfEventOfClient.getLocalClientStateAutomaton().getGameType().getGameMatchDtoSet());
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
    public void updateOnSetGameMatch() {
        doOnNextState();
    }

    // doOnХХХState()
    @Override
    protected void doOnPrevState() {
        super.doOnPrevState();
        gameMatchSetComboBox.setItems(FXCollections.observableArrayList());
    }
}
