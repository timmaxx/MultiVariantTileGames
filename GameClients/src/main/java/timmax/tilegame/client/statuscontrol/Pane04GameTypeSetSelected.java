package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx.*;

public class Pane04GameTypeSetSelected extends AbstractConnectStatePane {
    private final ComboBox<String> gameTypeSetComboBox;

    public Pane04GameTypeSetSelected(TransportOfClient transportOfClient) {
        super(transportOfClient);

        // 1 (обязательные)
        // Контролы для продвижения состояния "вперёд":
        gameTypeSetComboBox = new ComboBox<>();

        nextStateButton.setText("Select the game type");
        nextStateButton.setOnAction(event -> {
            String gameTypeName = gameTypeSetComboBox.getValue();
            if(gameTypeName == null || gameTypeName.isEmpty()) {
                return;
            }
            GameType gameType =
                    transportOfClient
                            .getLocalClientStateAutomaton()
                            .getGameTypeSet()
                            .stream()
                            .filter(x -> x.getGameTypeName().equals(gameTypeName))
                            .findAny()
                            .orElse(null);
            if (gameType == null) {
                return;
            }
            disableAllControls();
            transportOfClient.setGameType(gameType);
        });

        // Контролы для продвижения состояния "назад":
        prevStateButton.setText("Forget the game type");
        prevStateButton.setFocusTraversable(false);
        prevStateButton.setOnAction(event -> {
            disableAllControls();
            transportOfClient.reauthorizeUser();
        });

        // 1
        int y = LAYOUT_Y_OF_FIRST_ROW;
        gameTypeSetComboBox.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        gameTypeSetComboBox.setLayoutY(y);

        // Получилось 1 строка контролов:
        y += DIFFERENCE_OF_LAYOUT_Y;
        nextStatePane.setMinHeight(y);

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контролов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(gameTypeSetComboBox),
                List.of()
        );
    }

    // interface ObserverOnAbstractEvent
    // 2
    @Override
    public void updateOnAuthorizeUser() {
        gameTypeSetComboBox.setItems(
                FXCollections.observableArrayList(
                        transportOfClient
                                .getLocalClientStateAutomaton()
                                .getGameTypeSet()
                                .stream()
                                .map(GameType::getGameTypeName)
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
