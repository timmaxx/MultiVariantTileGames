package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

import java.util.Set;

public interface IClientState04GameTypeSetSelected extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 4 (GameTypeSetSelected)
    Set<ModelOfServerDescriptor> getGameTypeSet();

    void forgetGameTypeSet();

    void setGameType(ModelOfServerDescriptor modelOfServerDescriptor);
}
