package timmax.tilegame.basemodel.protocol.client;

import javafx.application.Platform;
import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEventHashSet;
import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.server.ParamName_paramModelDescriptionMap;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchExtendedDto;
import timmax.tilegame.basemodel.protocol.server_client.IFabricOfClientStates;
import timmax.tilegame.baseview.View;
import timmax.tilegame.baseview.ViewMainField;

import java.util.HashMap;
import java.util.Map;

//  Автомат состояний клиента, работающий на клиенте и учитывающий состояния локального клиента.
public class LocalClientStateAutomaton extends ClientStateAutomaton implements ObserverOnAbstractEvent {
    private final ObserverOnAbstractEventHashSet observerOnAbstractEventHashSet;
    private final Map<String, View> viewName_ViewMap;

    public LocalClientStateAutomaton(IFabricOfClientStates iFabricOfClientStates) {
        super(iFabricOfClientStates);

        observerOnAbstractEventHashSet = new ObserverOnAbstractEventHashSet();
        viewName_ViewMap = new HashMap<>();
    }

    public void addView(View view) {
        viewName_ViewMap.put(view.getViewName(), view);
    }

    public View getView(String key) {
        return viewName_ViewMap.get(key);
    }

    public void addCallBackOnIncomingTransportPackageEvent(ObserverOnAbstractEvent observerOnAbstractEvent) {
        observerOnAbstractEventHashSet.add(observerOnAbstractEvent);
    }

    //  ToDo:   Вместо прямого доступа к getViewName_ViewClassMap(), лучше что-бы нужное действие
    //          (см. конструктор класса GameClientPaneJfx), выполнялось в мапе viewName_ViewClassMap.
    //          Поэтому пришлось сделать его public. Но это не хорошо!
    public Map<String, Class<? extends View>> getViewName_ViewClassMap() {
        return getGameType_().getViewName_ViewClassMap();
    }

    //  ToDo:   Вместо прямого доступа к getParamName_paramModelDescriptionMap(), лучше что-бы нужное действие
    //          (см. Pane07GameMatchSelected :: void updateOnSetGameMatch()), выполнялось в мапе paramName_paramModelDescriptionMap.
    //          Поэтому пришлось сделать его public. Но это не хорошо!
    public ParamName_paramModelDescriptionMap getParamName_paramModelDescriptionMap() {
        return getGameType_().getParamName_paramModelDescriptionMap();
    }

    //  Этот метод единственный из ..._(...) методов родительского класса, который здесь перегружается.
    //  Из-за этого в родительском классе он как минимум должен быть protected
    //  (а не package-private, как другие ..._(...).
    @Override
    protected void startGameMatch_(GameMatchExtendedDto gameMatchExtendedDto) {
        logger.info("__ Метод используется! __");
        //  ToDo:   Переделать наследование и(или) вызов startGameMatch_().
        //          В текущей реализации метод вызывает родительский. Но для DTO этого не должно быть.
        //          И вызываемый метод только в лог и пишет, что он был зачем-то вызван.
        super.startGameMatch_(gameMatchExtendedDto);

        //  ToDo:   Блок кода ниже попробовать переместить отсюда, что-бы сделать родительский метод package-private,
        //          а этот вообще удалить.
        View view = getView(ViewMainField.class.getSimpleName());
        if (view instanceof ViewMainField viewMainField) {
            Platform.runLater(() -> {
                viewMainField.initMainField(gameMatchExtendedDto.getParamsOfModelValueMap());
                for (GameEventOneTile gameEventOneTile : gameMatchExtendedDto.getGameEventOneTileSet()) {
                    viewMainField.update(gameEventOneTile);
                }
            });
        }
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
        viewName_ViewMap.clear();
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
