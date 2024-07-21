package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEventHashSet;
import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;
import timmax.tilegame.basemodel.protocol.server_client.IFabricOfClientStates;
import timmax.tilegame.baseview.View;

import java.util.HashMap;
import java.util.Map;

public class LocalClientStateAutomaton extends ClientStateAutomaton<GameMatchId> implements ObserverOnAbstractEvent {
    private final ObserverOnAbstractEventHashSet observerOnAbstractEventHashSet;
    private final Map<String, View> viewName_ViewMap;

    public LocalClientStateAutomaton(
            IFabricOfClientStates<GameMatchId> IFabricOfClientStates) {
        super(IFabricOfClientStates);

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

    @Override
    public void updateOnClose() {
        observerOnAbstractEventHashSet.updateOnClose();
    }

    @Override
    public void updateOnOpen() {
        observerOnAbstractEventHashSet.updateOnOpen();
    }

    @Override
    public void updateOnSetUser() {
        observerOnAbstractEventHashSet.updateOnSetUser();
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
    public void updateOnSetGameMatchPlaying() {
        observerOnAbstractEventHashSet.updateOnSetGameMatchPlaying();
    }
}
