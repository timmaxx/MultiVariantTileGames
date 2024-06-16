package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

import java.util.Set;

public interface IClientState05GameTypeSelected<Model> extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 5 (GameTypeSelected)
    ModelOfServerDescriptor getGameType();

    void forgetGameType();

    void setGameMatchSet(Set<Model> setOfServerBaseModel);
}
