package timmax.tilegame.basemodel.protocol;

import java.util.HashSet;

import javafx.application.Platform;

public class ObserverOnAbstractEventHashSet extends HashSet<ObserverOnAbstractEvent> implements ObserverOnAbstractEvent {
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
    public void updateOnSetUser() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnSetUser();
            }
        });
    }

    // 4
    @Override
    public void updateOnSetGameType() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnSetGameType();
            }
        });
    }

    // 6
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
    public void updateOnSetGameMatchPlaying() {
        Platform.runLater(() -> {
            for (ObserverOnAbstractEvent observerOnAbstractEvent : this) {
                observerOnAbstractEvent.updateOnSetGameMatchPlaying();
            }
        });
    }
}