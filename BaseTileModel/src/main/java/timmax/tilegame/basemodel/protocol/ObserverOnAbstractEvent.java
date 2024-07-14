package timmax.tilegame.basemodel.protocol;

// ToDo: Перечень методов для интерфейсов ObserverOnAbstractEvent и TransportOfClient похож.
//       Может всё свести к одному интерфейсу?
// ToDo: Можно разбить на несколько интерфейсов по два метода.
public interface ObserverOnAbstractEvent {
    // 1
    void updateOnClose();
    void updateOnOpen();

    // 2
    void updateOnForgetGameTypeSet();
    void updateOnSetUser();

    // 4
    void updateOnForgetGameType();
    void updateOnSetGameType();

    // 5
    void updateOnForgetGameMatchSet();
    void updateOnSetGameMatchSet();

    // 6
    void updateOnForgetGameMatch();
    void updateOnSetGameMatch();

    // 7
    void updateOnForgetGameMatchPlaying();
    void updateOnSetGameMatchPlaying();
}
