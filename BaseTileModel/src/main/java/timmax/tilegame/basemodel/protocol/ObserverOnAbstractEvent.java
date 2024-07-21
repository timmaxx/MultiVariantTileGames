package timmax.tilegame.basemodel.protocol;

// ToDo: Перечень методов для интерфейсов ObserverOnAbstractEvent и TransportOfClient похож.
//       Может всё свести к одному интерфейсу?
// ToDo: Можно разбить на несколько интерфейсов.
public interface ObserverOnAbstractEvent {
    // 1
    void updateOnClose();

    void updateOnOpen();

    // 2
    void updateOnSetUser();

    // 4
    void updateOnSetGameType();

    // 6
    void updateOnSetGameMatch();

    // 7
    void updateOnSetGameMatchPlaying();
}
