package timmax.tilegame.basemodel.protocol.cs;

import java.util.Set;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

public interface IClientState<Model> {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 1 NoConnect
    // void open();

    // ---- 2 ConnectNonIdent
    void setUserName(String userName); // logIn
    // void close();

    // ---- 3 ConnectAuthorized
    String getUserName();
    void forgetUserName(); // logOff
    void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor);

    // ---- 4 (GameTypeSetSelected)
    Set<ModelOfServerDescriptor> getGameTypeSet();
    void forgetGameTypeSet();
    void setGameType(ModelOfServerDescriptor modelOfServerDescriptor);

    // ---- 5 (GameTypeSelected)
    ModelOfServerDescriptor getGameType();
    void forgetGameType();
    void setGameMatchSet(Set<Model> setOfServerBaseModel);

    // ---- 6 (MatchSetSelected)
    Set<Model> getGameMatchSet();
    void forgetGameMatchSet();
    void setServerBaseModel(Model serverBaseModel);

    // ---- 7 (MatchSelected)
    Model getServerBaseModel();
    void forgetServerBaseModel();
    void setGameIsPlaying(Boolean gameIsPlaying);

    // ---- 8 (GameIsPlaying)
    Boolean getGameIsPlaying();
    void forgetGameIsPlaying();

    // ---- X
    MainGameClientStatus getMainGameClientStatus();
}
