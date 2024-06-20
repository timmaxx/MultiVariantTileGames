package timmax.tilegame.basemodel.protocol.client;

import java.util.Map;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;

// ToDo: Этот интерфейс является по сути комбинацией интерфейсов IClientState0Х...
//       Нужно так и сделать!
// ToDo: Model здесь не должен быть параметром (см. коммент к getLocalClientState()).
// ToDo: Перечень методов для интерфейсов ObserverOnAbstractEvent и IModelOfClient похож.
//       Может всё свести к одному интерфесу?
public interface IModelOfClient<Model, ClientId> {
    // ---- 2
    void logout();
    void login(String userName, String password);

    // ---- 3
    void forgetGameTypeSet();
    void getGameTypeSet();

    // ---- 4
    void forgetGameType();
    void gameTypeSelect(ModelOfServerDescriptor modelOfServerDescriptor);

    // ---- 5
    void forgetGameMatchSet();
    void getGameMatchSet();

    // ---- 6
    void forgetGameMatch();
    void gameMatchSelect(InstanceIdOfModel model);

    // ---- 7
    void stopGameMatchPlaying();
    void startGameMatchPlaying(Map<String, Integer> mapOfParamsOfModelValue);

    // ---- X
    // ToDo: Здесь не от Model должен параметризироваться, а от конкретного класса/интерфейса
    ClientStateAutomaton<Model, ClientId> getLocalClientState();
}
