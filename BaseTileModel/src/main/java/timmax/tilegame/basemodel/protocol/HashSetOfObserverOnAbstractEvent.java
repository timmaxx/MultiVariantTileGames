package timmax.tilegame.basemodel.protocol;

import java.util.HashSet;

import javafx.application.Platform;

public class HashSetOfObserverOnAbstractEvent extends HashSet<ObserverOnAbstractEvent> implements ObserverOnAbstractEvent {
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
    public void updateOnLogout() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnLogout();
            }
        });
    }

    @Override
    public void updateOnLogin() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnLogin();
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
    public void updateOnGetGameTypeSet() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnGetGameTypeSet();
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
    public void updateOnSelectGameType() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnSelectGameType();
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
    public void updateOnGetGameMatchSet() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnGetGameMatchSet();
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
    public void updateOnSelectGameMatch() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnSelectGameMatch();
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
    public void updateOnStopGameMatchPlaying() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnStopGameMatchPlaying();
            }
        });
    }
}
