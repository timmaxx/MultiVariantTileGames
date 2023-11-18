package timmax.tilegame.websocket.client;

import timmax.tilegame.basemodel.protocol.TypeOfTransportPackage;

public interface ObserverOnAbstractEvent {
    void update(TypeOfTransportPackage typeOfTransportPackage);
}