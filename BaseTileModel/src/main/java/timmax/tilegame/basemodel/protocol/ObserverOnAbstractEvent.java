package timmax.tilegame.basemodel.protocol;

// ToDo: Перечень методов для интерфейсов ObserverOnAbstractEvent и TransportOfClient похож.
//       Может всё свести к одному интерфейсу?
// ToDo: Можно разбить на несколько интерфейсов.
public interface ObserverOnAbstractEvent {
    // 1
    void updateOnClose();

    void updateOnOpen();

    // 2
    void updateOnAuthorizeUser();

    // 4
    void updateOnSelectGameType();

    // 6
    void updateOnSelectGameMatch();

    // 7
    void updateOnSetGameMatchIsPlaying();
}
