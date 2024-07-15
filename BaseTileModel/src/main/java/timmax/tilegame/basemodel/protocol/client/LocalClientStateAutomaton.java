package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.HashSetOfObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;
import timmax.tilegame.basemodel.protocol.server_client.IFabricOfClientStates;
import timmax.tilegame.baseview.View;

import java.util.HashMap;
import java.util.Map;

public class LocalClientStateAutomaton extends ClientStateAutomaton<GameMatchId> {
    // ToDo: Переименовать.
    private final HashSetOfObserverOnAbstractEvent hashSetOfObserverOnAbstractEvent;
    private final Map<String, View> viewName_ViewMap;

    public LocalClientStateAutomaton(
            IFabricOfClientStates<GameMatchId> IFabricOfClientStates) {
        super(IFabricOfClientStates);

        hashSetOfObserverOnAbstractEvent = new HashSetOfObserverOnAbstractEvent();
        viewName_ViewMap = new HashMap<>();
    }


    // ToDo: Переименовать.
    public HashSetOfObserverOnAbstractEvent getHashSetOfObserverOnAbstractEvent() {
        return hashSetOfObserverOnAbstractEvent;
    }

    // ToDo: Удалить этот метод и вместо него ввести методы, которые что-то делают с viewName_ViewMap.
    public Map<String, View> getViewName_ViewMap() {
        return viewName_ViewMap;
    }

    public void addView(View view) {
        viewName_ViewMap.put(view.getViewName(), view);
    }

    public void addCallBackOnIncomingTransportPackageEvent(ObserverOnAbstractEvent observerOnAbstractEvent) {
        hashSetOfObserverOnAbstractEvent.add(observerOnAbstractEvent);
    }
}
