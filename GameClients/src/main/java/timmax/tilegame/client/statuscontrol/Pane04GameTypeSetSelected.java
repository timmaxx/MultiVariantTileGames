package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.transport.ISenderOfEventOfClient;

public class Pane04GameTypeSetSelected extends AbstractConnectStatePane {
    private final ComboBox<String> gameTypeSetComboBox;

    public Pane04GameTypeSetSelected(ISenderOfEventOfClient senderOfEventOfClient) {
        super(senderOfEventOfClient);

        // 1 (обязательные)
        // Контролы для продвижения состояния "вперёд":
        gameTypeSetComboBox = new ComboBox<>();

        nextStateButton.setText("Select the game type");
        nextStateButton.setOnAction(event -> {
            String gameTypeName = gameTypeSetComboBox.getValue();
            if(gameTypeName == null || gameTypeName.isEmpty()) {
                return;
            }
            //  Warning:(27, 13) Raw use of parameterized class 'GameType'
            GameType gameType =
                    senderOfEventOfClient
                            .getLocalClientStateAutomaton()
                            .getGameTypeSet()
                            .stream()
                            .filter(x -> x.getId().equals(gameTypeName))
                            .findAny()
                            .orElse(null);
            if (gameType == null) {
                return;
            }
            disableAllControls();
            senderOfEventOfClient.setGameType(gameType);
        });

        // Контролы для продвижения состояния "назад":
        prevStateButton.setText("Forget the game type");
        prevStateButton.setFocusTraversable(false);
        prevStateButton.setOnAction(event -> {
            disableAllControls();
            senderOfEventOfClient.reauthorizeUser();
        });


        // 1
        int y = LAYOUT_Y_OF_FIRST_ROW;
        gameTypeSetComboBox.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        gameTypeSetComboBox.setLayoutY(y);

        // Получилось 1 строка контролов:
        y += DIFFERENCE_OF_LAYOUT_Y;
        nextStatePane.setMinHeight(y);
        nextStatePane.setMaxHeight(y);

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контролов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(gameTypeSetComboBox),
                List.of()
        );
    }

    // interface ObserverOnAbstractEvent
    // 1
    @Override
    public void updateOnOpen() {
        super.updateOnOpen();
        doOnPrevState();
    }

    // 2
    @Override
    public void updateOnAuthorizeUser() {
        gameTypeSetComboBox.setItems(
                FXCollections.observableArrayList(
                        senderOfEventOfClient
                                .getLocalClientStateAutomaton()
                                .getGameTypeSet()
                                .stream()
                                .map(GameType::getId)
                                .toList()
                )
        );
        doOnThisState();
    }

    // 4
    @Override
    public void updateOnSetGameType() {
        doOnNextState();
    }

    // doOnХХХState()
    @Override
    protected void doOnPrevState() {
        super.doOnPrevState();
        gameTypeSetComboBox.setItems(FXCollections.observableArrayList());
    }
}
