package timmax.tilegame.basemodel.protocol;

// ToDo: Перечень методов для интерфейсов ObserverOnAbstractEvent и TransportOfClient похож.
//       Может всё свести к одному интерфесу?
public interface ObserverOnAbstractEvent {
    // 1
    void updateOnClose();
    void updateOnOpen();

    // 2
    void updateOnForgetUser();
    void updateOnSetUser();

    // 3
    void updateOnForgetGameTypeSet();
    void updateOnSetGameTypeSet();

    // 4
    void updateOnForgetGameType();
    void updateOnSetGameType();

    // 5
    void updateOnForgetGameMatchSet();
    void updateOnGetGameMatchSet();

    // 6
    void updateOnForgetGameMatch();
    void updateOnSetGameMatch();

    // 7
    void updateOnStartGameMatchPlaying();
    void updateOnStopGameMatchPlaying();
}
