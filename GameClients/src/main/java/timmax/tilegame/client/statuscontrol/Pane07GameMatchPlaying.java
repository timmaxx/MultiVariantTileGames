package timmax.tilegame.client.statuscontrol;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.guiengine.jfx.GameClientPaneJfx;
import timmax.tilegame.transport.TransportOfClient;

public class Pane07GameMatchPlaying extends AbstractConnectStatePane {
    private final Pane paneGameViewsAndControls;

    public Pane07GameMatchPlaying(IModelOfClient iModelOfClient, TransportOfClient transportOfClient) {
        super(iModelOfClient, transportOfClient);

        // Контролы для продвижения состояния "вперёд":
        buttonNextState.setText("Start the game match");
        buttonNextState.setOnAction(event -> {
            disableAllControls();
            iModelOfClient.startGameMatchPlaying();
        });

        // Контролы для продвижения состояния "назад":
        paneGameViewsAndControls = new Pane();

        buttonPrevState.setText("Stop the game match");
        buttonPrevState.setFocusTraversable(false); // Это в любом случае д.б.
        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            iModelOfClient.stopGameMatchPlaying();
        });

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контроллов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(),
                List.of(paneGameViewsAndControls)
        );
    }

    // Implemented methods of interface ObserverOnAbstractEvent
    // 1
    @Override
    public void updateOnClose() {
        doOnPrevState();
    }

    @Override
    public void updateOnOpen() {
        doOnPrevState();
    }

    // 2
    @Override
    public void updateOnLogout() {
        doOnPrevState();
    }

    @Override
    public void updateOnLogin() {
        doOnPrevState();
    }

    // 3
    @Override
    public void updateOnForgetGameTypeSet() {
        doOnPrevState();
    }

    @Override
    public void updateOnGetGameTypeSet() {
        doOnPrevState();
    }

    // 4
    @Override
    public void updateOnForgetGameType() {
        doOnPrevState();
    }

    @Override
    public void updateOnSelectGameType() {
        doOnPrevState();
        paneNextState.getChildren().clear();
        List<Region> regionList = new ArrayList<>();
        int y = LAYOUT_Y_OF_FIRST_ROW;
        for (String paramName: iModelOfClient.getLocalClientState().getGameType().getMapOfParamsOfModel().keySet()) {
            Label label = new Label(paramName);
            TextField textField = new TextField();
            label.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN);
            label.setLayoutY(y);
            textField.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
            textField.setLayoutY(y);
            regionList.add(label);
            regionList.add(textField);
            y += DIFFERENCE_OF_LAYOUT_Y;
        }
        paneNextState.setPrefHeight(y);
        paneNextState.setMinHeight(y);

        setListsOfControlsAndAllDisable(
                regionList,
                List.of(paneGameViewsAndControls)
        );

        paneGameViewsAndControls.getChildren().clear();
        paneGameViewsAndControls.getChildren().add(new GameClientPaneJfx(iModelOfClient, transportOfClient));

        if (y > DIFFERENCE_OF_LAYOUT_Y) {
            y -= DIFFERENCE_OF_LAYOUT_Y;
        }
        getParent().getScene().getWindow().setHeight(0 * 0 + 34 + 12 * DIFFERENCE_OF_LAYOUT_Y + y);
    }

    // 5
    @Override
    public void updateOnForgetGameMatchSet() {
        //doOnPrevState();
    }

    @Override
    public void updateOnGetGameMatchSet() {
        //doOnPrevState();
    }

    // 6
    @Override
    public void updateOnForgetGameMatch() {
        //doOnPrevState();
    }

    @Override
    public void updateOnSelectGameMatch() {
        doOnThisState();
    }

    // 7
    @Override
    public void updateOnStopGameMatchPlaying() {
        doOnThisState();
    }

    @Override
    public void updateOnStartGameMatchPlaying() {
        doOnNextState();
    }

    // X
    @Override
    public void doOnPrevState() {
        super.doOnPrevState();
        paneNextState.setPrefHeight(DIFFERENCE_OF_LAYOUT_Y);
        paneNextState.setMinHeight(DIFFERENCE_OF_LAYOUT_Y);
        setListsOfControlsAndAllDisable(
                List.of(),
                List.of(paneGameViewsAndControls)
        );
        paneGameViewsAndControls.getChildren().clear();
        panePrevState.setPrefWidth(PANE_PREV_STATE_PREF_WIDTH);

        // ToDo: Похожий код см. в ViewMainFieldJfx::initMainField()

        //  16 - количество пикселей слева и справа, что-бы главное поле влезло во внутреннее окно - PrimaryStage
        getParent().getScene().getWindow().setWidth(0 * 0 + 16 + PANE_NEXT_STATE_PREF_WIDTH + BUTTON_NEXT_STATE_PREF_WIDTH + BUTTON_PREV_STATE_PREF_WIDTH);
        //  34 - количество пикселей сверху и снизу (высота заголовка окна приложения), что-бы главное поле влезло во внутреннее окно - PrimaryStage
        //  12 - количество "строк" элементов в панелях Pane0x... ("строк" от 1 до 7: 3 + 1 + 2 + 1 + 2 + 2 + 1)
        // // 180 - количество пикселей в высоту, нужное для достаточного отображения четырёх текстовых выборок
        getParent().getScene().getWindow().setHeight(0 * 0 + 34 + 12 * DIFFERENCE_OF_LAYOUT_Y);
    }
}
