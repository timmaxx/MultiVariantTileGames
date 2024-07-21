package timmax.tilegame.basemodel.protocol.server_client;

public interface IClientState99<GameMatchX extends IGameMatchX> extends
        IClientState01NoConnect,
        IClientState02ConnectNonIdent,
        IClientState04GameTypeSetSelected<GameMatchX>,
        IClientState06GameMatchSetSelected<GameMatchX>,
        IClientState07GameMatchSelected<GameMatchX>,
        IClientState08GameMatchPlaying {

    void doBeforeTurnOff();
    void doAfterTurnOn();
}
