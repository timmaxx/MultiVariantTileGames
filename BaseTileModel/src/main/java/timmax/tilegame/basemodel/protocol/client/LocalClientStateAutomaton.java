package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEventHashSet;
import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.server.ParamName_paramModelDescriptionMap;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.IFabricOfClientStates;
import timmax.tilegame.baseview.View;

import java.util.HashMap;
import java.util.Map;

//  Автомат состояний клиента, работающий на клиенте и учитывающий состояния локального клиента.
public class LocalClientStateAutomaton extends ClientStateAutomaton implements ObserverOnAbstractEvent {
    private final ObserverOnAbstractEventHashSet observerOnAbstractEventHashSet;
    //  ToDo:   Попробовать переместить это поле, и методы, связанные с ним, в класс GameClientPaneJfx.
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
