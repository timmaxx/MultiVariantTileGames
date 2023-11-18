package timmax.tilegame.websocket.client;

import timmax.tilegame.basemodel.protocol.TypeOfTransportPackage;

import java.util.HashSet;

public class SetOfObserverOnAbstractEvent extends HashSet<ObserverOnAbstractEvent> {
    public void updateConnectStatePane(TypeOfTransportPackage typeOfTransportPackage) {
        for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
            observerOnAbstractEvent.update(typeOfTransportPackage);
        }
    }
}