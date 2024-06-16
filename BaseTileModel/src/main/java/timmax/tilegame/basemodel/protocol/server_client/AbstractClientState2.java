package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfServer;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

// ToDo: Переименовать этот класс в AbstractClientState после того, как предыдущий AbstractClientState будет удалён.
public abstract class AbstractClientState2<Model, ClientId> {
    private final ClientStateAutomaton<Model, ClientId> clientStateAutomaton;
    // private Map<String, Integer> mapOfParamsOfModelValue;

    // ToDo: Вероятно вынести эти три переменные в ClientStateAutomaton
    // For remote clientState:
    private final ClientId clientId;
    private final TransportOfServer<ClientId> transportOfServer;
    private final Set<String> setOfViewName;

/*
    public void setMapOfParamsOfModelValue(Map<String, Integer> mapOfParamsOfModelValue) {
        this.mapOfParamsOfModelValue = mapOfParamsOfModelValue;
    }

    public Map<String, Integer> getMapOfParamsOfModelValue() {
        return mapOfParamsOfModelValue;
    }
*/
    public AbstractClientState2(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        this.clientStateAutomaton = clientStateAutomaton;

        // For remote clientState:
        this.clientId = null;
        this.transportOfServer = null;
        setOfViewName = null;
    }

    public AbstractClientState2(ClientStateAutomaton<Model, ClientId> clientStateAutomaton, TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        this.clientStateAutomaton = clientStateAutomaton;

        // For remote clientState:
        this.clientId = clientId;
        this.transportOfServer = transportOfServer;
        this.setOfViewName = new HashSet<>();
    }

    public ClientStateAutomaton<Model, ClientId> getClientStateAutomaton() {
        return clientStateAutomaton;
    }
/*
    // ---- X
    public abstract MainGameClientStatus getMainGameClientStatus();/-* {
        if (gameIsPlaying != null && gameIsPlaying) {
            return MainGameClientStatus.GAME_IS_PLAYING;
        } else if (serverBaseModel != null) {
            return MainGameClientStatus.GAME_MATCH_SELECTED;
        } // Проверка на длину набора закоментирована, т.к. предполагается, что пользователь может иметь возможность
          // создавать партию даже если он не получил набор готовых партий.
          else if (setOfServerBaseModel != null *//*&& setOfServerBaseModel.size() > 0*//*) {
            return MainGameClientStatus.GAME_MATCH_SET_SELECTED;
        } else if (modelOfServerDescriptor != null) {
            return MainGameClientStatus.GAME_TYPE_SELECTED;
        } // Проверка на длину списка не закоментирована, т.к. предполагается, что пользователь не может сам создать
          // новый тип игры. Что-бы это стало возможным, нужно что-бы сервер смог загрузить ещё один класс наследник
          // ModelOfServer. При развитии фреймворка возможно, но не сейчас.
          else if (setOfModelOfServerDescriptor != null && setOfModelOfServerDescriptor.size() > 0) {
            return MainGameClientStatus.GAME_TYPE_SET_SELECTED;
        } else if (userName != null && !userName.isEmpty()) {
            return MainGameClientStatus.CONNECT_AUTHORIZED;
        }
        return MainGameClientStatus.CONNECT_NON_IDENT;
    }*-/
*/

    // ToDo: Вынести этот метод в ClientStateAutomaton
    // For local clientState:
    public abstract Constructor<? extends View> getViewConstructor(
            Class<? extends View> classOfView
    );
    // End of local clientState


    // ToDo: Вынести эти методы в ClientStateAutomaton
    // For remote clientState:
    protected Set<String> getSetOfViewName() {
        return setOfViewName;
    }

    protected TransportOfServer<ClientId> getTransportOfServer() {
        return transportOfServer;
    }

    protected ClientId getClientId() {
        return clientId;
    }
    // End of remote clientState
}
