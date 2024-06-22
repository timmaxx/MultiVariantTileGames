package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.HashSetOfObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.IFabricOfClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.IFabricOfClientStates;
import timmax.tilegame.baseview.View;
// import timmax.tilegame.transport.TransportOfClient;

import java.util.HashMap;
import java.util.Map;

public class LocalClientStateAutomaton<Model> extends ClientStateAutomaton<Model> {
    // ToDo: По аналогии с RemoteClientStateAutomaton включу сюда TransportOfClient.
    // private final TransportOfClient transportOfClient;
    private final HashSetOfObserverOnAbstractEvent hashSetOfObserverOnAbstractEvent;
    private final Map<String, View> mapOfViewName_View;

    public LocalClientStateAutomaton(
            IFabricOfClientStates<Model> IFabricOfClientStates,
            IFabricOfClientStateAutomaton iFabricOfClientStateAutomaton/*,
            TransportOfClient transportOfClient*/) {
        super(IFabricOfClientStates, iFabricOfClientStateAutomaton);

        // this.transportOfClient = transportOfClient;
        hashSetOfObserverOnAbstractEvent = new HashSetOfObserverOnAbstractEvent();
        mapOfViewName_View = new HashMap<>();
    }
/*
    public TransportOfClient getTransportOfClient() {
        return transportOfClient;
    }
*/
    public HashSetOfObserverOnAbstractEvent getHashSetOfObserverOnAbstractEvent() {
        return hashSetOfObserverOnAbstractEvent;
    }

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
