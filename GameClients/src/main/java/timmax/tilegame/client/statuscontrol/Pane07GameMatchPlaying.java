package timmax.tilegame.client.statuscontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;
import timmax.tilegame.guiengine.jfx.GameClientPaneJfx;
import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx.*;

public class Pane07GameMatchPlaying extends AbstractConnectStatePane {
    private final Pane paneGameViewsAndControls;

    public Pane07GameMatchPlaying(TransportOfClient transportOfClient) {
        super(transportOfClient);

        // 1 (обязательные)
        // Контролы для продвижения состояния "вперёд":
        buttonNextState.setText("Start the game match");
        buttonNextState.setOnAction(event -> {
            disableAllControls();
            Map<String, Integer> mapOfParamsOfModelValue = new HashMap<>();
            for (Region region : getListOfControlsNextState()) {
                if (region instanceof TextField textField) {
                    mapOfParamsOfModelValue.put(
                            textField.getId(),
                            Integer.valueOf(textField.getText()));
                }
            }
            transportOfClient.setGameMatchPlaying(mapOfParamsOfModelValue);
        });

        // Контролы для продвижения состояния "назад":
        paneGameViewsAndControls = new Pane();

        buttonPrevState.setText("Stop the game match");
        buttonPrevState.setFocusTraversable(false); // Это в любом случае д.б.
        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            transportOfClient.forgetGameMatchPlaying();
        });

        // По сравнению с предыдущими Pane0X (1 - 6)
        // Здесь нет строки "Получилось N строк контролов:"

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контролов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(),
                List.of(paneGameViewsAndControls)
        );
    }

    // interface ObserverOnAbstractEvent
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
    public void updateOnForgetGameTypeSet() {
        doOnPrevState();
    }

    @Override
    public void updateOnSetUser() {
        doOnPrevState();
    }

    // 4
    @Override
    public void updateOnForgetGameType() {
        doOnPrevState();
    }

    @Override
    public void updateOnSetGameType() {
        doOnPrevState();
        paneNextState.getChildren().clear();
        List<Region> regionList = new ArrayList<>();
        int y = LAYOUT_Y_OF_FIRST_ROW;
        for (String paramName : transportOfClient.getLocalClientStateAutomaton().getGameType().getMapOfParamsOfModelDescription().keySet()) {
            Label label = new Label(paramName);
            label.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN);
            label.setLayoutY(y);

            TextField textField = new TextField();
            textField.setId(paramName);
            textField.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
            textField.setLayoutY(y);
            ParamOfModelDescription paramOfModelDescription = transportOfClient
                    .getLocalClientStateAutomaton()
                    .getGameType()
                    .getMapOfParamsOfModelDescription()
                    .get(paramName);
            textField.setTextFormatter(
                    new TextFormatter<>(
                            new IntegerStringConverterWithMinAndMax(
                                    paramOfModelDescription.getMinValue(),
                                    paramOfModelDescription.getMaxValue()
                            ),
                            paramOfModelDescription.getDefaultValue(),
                            new IntegerFilter()
                    )
            );

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
        paneGameViewsAndControls.getChildren().add(new GameClientPaneJfx(transportOfClient));

        if (y > DIFFERENCE_OF_LAYOUT_Y) {
            y -= DIFFERENCE_OF_LAYOUT_Y;
        }
        getParent().getScene().getWindow().setHeight(
                LAYOUT_Y_OF_FIRST_ROW
                        + (2 * PIXELS_ON_LEFT_N_RIGHT_FOR_MAIN_FIELD_FITS_INTO_PRIMARY_STAGE)
                        + (ROWS_OF_CONTROLS_IN_PANE0X_EXCEPT_LAST + 1) * DIFFERENCE_OF_LAYOUT_Y
                        + y
        );
    }

    // 5
    @Override
    public void updateOnForgetGameMatchSet() {
        // ToDo: Почему закомментировано?
        // doOnPrevState();
    }

    @Override
    public void updateOnSetGameMatchSet() {
        // ToDo: Почему закомментировано?
        // doOnPrevState();
    }

    // 6
    @Override
    public void updateOnForgetGameMatch() {
        // ToDo: Почему закомментировано?
        // doOnPrevState();
    }

    @Override
    public void updateOnSetGameMatch() {
        doOnThisState();
    }

    // 7
    @Override
    public void updateOnForgetGameMatchPlaying() {
        doOnThisState();
    }

    @Override
    public void updateOnSetGameMatchPlaying() {
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
        getParent().getScene().getWindow().setWidth(
                LAYOUT_X_OF_FIRST_COLUMN
                        + PIXELS_ON_LEFT_N_RIGHT_FOR_MAIN_FIELD_FITS_INTO_PRIMARY_STAGE
                        + PANE_NEXT_STATE_PREF_WIDTH
                        + BUTTON_NEXT_STATE_PREF_WIDTH
                        + BUTTON_PREV_STATE_PREF_WIDTH
        );
        // ToDo: Вернуться к информационным представлениям (про 180).
        // // 180 - количество пикселей в высоту, нужное для достаточного отображения четырёх текстовых выборок
        getParent().getScene().getWindow().setHeight(
                LAYOUT_Y_OF_FIRST_ROW
                        + 2 * PIXELS_ON_TOP_N_BOTTOM_FOR_MAIN_FIELD_FITS_INTO_PRIMARY_STAGE
                        + (ROWS_OF_CONTROLS_IN_PANE0X_EXCEPT_LAST + 1) * DIFFERENCE_OF_LAYOUT_Y
        );
    }
}
