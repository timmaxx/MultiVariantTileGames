package timmax.tilegame.basemodel.protocol;

import java.util.HashSet;

public class HashSetOfObserverOnAbstractEvent extends HashSet<ObserverOnAbstractEvent> {
    public void updateConnectStatePane(TypeOfTransportPackage typeOfTransportPackage) {
        for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
            observerOnAbstractEvent.update(typeOfTransportPackage);
        }
    }
}