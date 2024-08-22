package timmax.tilegame.basemodel.protocol.server_client;

public interface IGameMatchX extends IGameMatchXDto {
    int getWidth();
    int getHeight();

    GameMatchExtendedDto start(GameMatchExtendedDto gameMatchExtendedDto);
    // ToDo: Вероятно возвращаемый параметр должен быть GameMatchExtendedDto
    void start();
    // ToDo: Вероятно возвращаемый параметр должен быть GameMatchExtendedDto
    void resume();
}
