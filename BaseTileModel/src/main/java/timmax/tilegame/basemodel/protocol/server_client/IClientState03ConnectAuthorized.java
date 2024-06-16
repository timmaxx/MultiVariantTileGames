package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

import java.util.Set;

public interface IClientState03ConnectAuthorized extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 3 ConnectAuthorized
    String getUserName();

    void forgetUserName(); // logOff

    void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor);
}
