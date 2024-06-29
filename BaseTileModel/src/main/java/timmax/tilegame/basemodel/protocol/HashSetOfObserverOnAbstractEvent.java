package timmax.tilegame.basemodel.protocol;

import java.util.HashSet;

import javafx.application.Platform;

public class HashSetOfObserverOnAbstractEvent extends HashSet<ObserverOnAbstractEvent> implements ObserverOnAbstractEvent {
    //  Описанное было обнаружено при работе с Pane04SelectGameType
    //  Если ранее comboBoxGameTypeSet уже было заполнено (т.е. вызывался updateOnGetGameTypeSet)
    //  и не использовать здесь Platform.runLater(), то возникнет исключение:
    //  Not on FX application thread
    //  Например:
    //  Exception in thread "WebSocketConnectReadThread-25" java.lang.IllegalStateException: Not on FX application thread; currentThread = WebSocketConnectReadThread-25

    // 1
    @Override
    public void updateOnClose() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnClose();
            }
        });
    }

    @Override
    public void updateOnOpen() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnOpen();
            }
        });
    }

    // 2
    @Override
    public void updateOnForgetUser() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnForgetUser();
            }
        });
    }

    @Override
    public void updateOnSetUser() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnSetUser();
            }
        });
    }

    // 3
    @Override
    public void updateOnForgetGameTypeSet() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnForgetGameTypeSet();
            }
        });
    }

    @Override
    public void updateOnSetGameTypeSet() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnSetGameTypeSet();
            }
        });
    }

    // 4
    @Override
    public void updateOnForgetGameType() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnForgetGameType();
            }
        });
    }

    @Override
    public void updateOnSetGameType() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnSetGameType();
            }
        });
    }

    // 5
    @Override
    public void updateOnForgetGameMatchSet() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnForgetGameMatchSet();
            }
        });
    }

    @Override
    public void updateOnSetGameMatchSet() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnSetGameMatchSet();
            }
        });
    }

    // 6
    @Override
    public void updateOnForgetGameMatch() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnForgetGameMatch();
            }
        });
    }

    @Override
    public void updateOnSetGameMatch() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnSetGameMatch();
            }
        });
    }

    // 7
    @Override
    public void updateOnStartGameMatchPlaying() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnStartGameMatchPlaying();
            }
        });
    }

    @Override
    public void updateOnForgetGameIsPlaying() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnForgetGameIsPlaying();
            }
        });
    }
}
