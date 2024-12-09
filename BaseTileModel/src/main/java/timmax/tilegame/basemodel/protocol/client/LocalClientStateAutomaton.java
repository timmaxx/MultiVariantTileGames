package timmax.tilegame.basemodel.protocol.client;

import javafx.application.Platform;
import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEventHashSet;
import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.server.ParamName_paramModelDescriptionMap;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchExtendedDto;
import timmax.tilegame.basemodel.protocol.server_client.IFabricOfClientStates;
import timmax.tilegame.baseview.View;
import timmax.tilegame.baseview.ViewMainField;

import java.util.HashMap;
import java.util.Map;

//  Автомат состояний клиента, работающий на клиенте и учитывающий состояния локального клиента.
public class LocalClientStateAutomaton extends ClientStateAutomaton<GameMatchDto> implements ObserverOnAbstractEvent {
    private final ObserverOnAbstractEventHashSet observerOnAbstractEventHashSet;
    private final Map<String, View> viewName_ViewMap;

    public LocalClientStateAutomaton(
            IFabricOfClientStates<GameMatchDto> iFabricOfClientStates) {
        super(iFabricOfClientStates);

        observerOnAbstractEventHashSet = new ObserverOnAbstractEventHashSet();
        viewName_ViewMap = new HashMap<>();
    }

    public void addView(View view) {
        viewName_ViewMap.put(view.getViewName(), view);
    }

    public void clearViewName_ViewMap() {
        viewName_ViewMap.clear();
    }

    public View getView(String key) {
        return viewName_ViewMap.get(key);
    }

    public void addCallBackOnIncomingTransportPackageEvent(ObserverOnAbstractEvent observerOnAbstractEvent) {
        observerOnAbstractEventHashSet.add(observerOnAbstractEvent);
    }

    // ToDo: Вместо прямого доступа к getViewName_ViewClassMap(), лучше что-бы нужное действие
    //       (см. конструктор класса GameClientPaneJfx), выполнялось в мапе viewName_ViewClassMap.
    //       Поэтому пришлось сделать его public. Но это не хорошо!
    public Map<String, Class<? extends View>> getViewName_ViewClassMap() {
        //  Warning:(51, 16) Unchecked assignment: 'java.util.Map' to 'java.util.Map<java.lang.String,java.lang.Class<? extends timmax.tilegame.baseview.View>>'. Reason: 'getGameType_()' has raw type, so result of getViewName_ViewClassMap is erased
        return getGameType_().getViewName_ViewClassMap();
    }

    // ToDo: Вместо прямого доступа к getParamName_paramModelDescriptionMap(), лучше что-бы нужное действие
    //       (см. Pane07GameMatchSelected :: void updateOnSetGameMatch()), выполнялось в мапе paramName_paramModelDescriptionMap.
    //       Поэтому пришлось сделать его public. Но это не хорошо!
    public ParamName_paramModelDescriptionMap getParamName_paramModelDescriptionMap() {
        return getGameType_().getParamName_paramModelDescriptionMap();
    }

    @Override
    protected GameMatchExtendedDto startGameMatch_(GameMatchExtendedDto gameMatchExtendedDto) {
        // ToDo: Блок кода ниже попробовать переместить отсюда, что-бы сделать этот и родительский метод package-private.
        View view = getView(ViewMainField.class.getSimpleName());
        if (view instanceof ViewMainField viewMainField) {
            Platform.runLater(() -> {
                viewMainField.initMainField(gameMatchExtendedDto.getParamsOfModelValueMap());
                for (GameEventOneTile gameEventOneTile : gameMatchExtendedDto.getGameEventOneTileSet()) {
                    viewMainField.update(gameEventOneTile);
                }
            });
        }
        return gameMatchExtendedDto;
    }

    @Override
    public void updateOnClose() {
        observerOnAbstractEventHashSet.updateOnClose();
    }

    @Override
    public void updateOnOpen() {
        observerOnAbstractEventHashSet.updateOnOpen();
    }

    @Override
    public void updateOnAuthorizeUser() {
        observerOnAbstractEventHashSet.updateOnAuthorizeUser();
    }

    @Override
    public void updateOnSetGameType() {
        observerOnAbstractEventHashSet.updateOnSetGameType();
    }

    @Override
    public void updateOnSetGameMatch() {
        observerOnAbstractEventHashSet.updateOnSetGameMatch();
    }

    @Override
    public void updateOnSetGameMatchIsPlaying() {
        observerOnAbstractEventHashSet.updateOnSetGameMatchIsPlaying();
    }
}
