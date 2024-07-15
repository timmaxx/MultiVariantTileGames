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
    // ToDo: Переименовать.
    private final Map<String, View> mapOfViewName_View;

    public LocalClientStateAutomaton(
            IFabricOfClientStates<GameMatchId> IFabricOfClientStates) {
        super(IFabricOfClientStates);

        hashSetOfObserverOnAbstractEvent = new HashSetOfObserverOnAbstractEvent();
        mapOfViewName_View = new HashMap<>();
    }


    // ToDo: Переименовать.
    public HashSetOfObserverOnAbstractEvent getHashSetOfObserverOnAbstractEvent() {
        return hashSetOfObserverOnAbstractEvent;
    }

    // ToDo: Переименовать.
    public Map<String, View> getMapOfViewName_View() {
        return mapOfViewName_View;
    }

    public void addView(View view) {
        mapOfViewName_View.put(view.getViewName(), view);
    }

    public void addCallBackOnIncomingTransportPackageEvent(ObserverOnAbstractEvent observerOnAbstractEvent) {
        hashSetOfObserverOnAbstractEvent.add(observerOnAbstractEvent);
    }
}
