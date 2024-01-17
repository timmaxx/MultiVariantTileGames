package timmax.tilegame.basemodel.protocol;

import java.util.HashSet;

import javafx.application.Platform;

public class HashSetOfObserverOnAbstractEvent extends HashSet<ObserverOnAbstractEvent> implements ObserverOnAbstractEvent {
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

    @Override
    public void updateOnCreateNewGame() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnCreateNewGame();
            }
        });
    }

    @Override
    public void updateOnCloseGame() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnCloseGame();
            }
        });
    }

    @Override
    public void updateOnAddView() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnAddView();
            }
        });
    }

    @Override
    public void updateOnGameEvent() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnGameEvent();
            }
        });
    }
}
