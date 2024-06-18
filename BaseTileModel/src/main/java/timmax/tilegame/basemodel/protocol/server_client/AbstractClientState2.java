package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.baseview.View;

import java.lang.reflect.Constructor;

// ToDo: Переименовать этот класс в AbstractClientState после того, как предыдущий AbstractClientState будет удалён.
public abstract class AbstractClientState2<Model, ClientId> {
    private final ClientStateAutomaton<Model, ClientId> clientStateAutomaton;

    public AbstractClientState2(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        this.clientStateAutomaton = clientStateAutomaton;
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
}
