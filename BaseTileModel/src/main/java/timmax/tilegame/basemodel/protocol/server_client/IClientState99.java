package timmax.tilegame.basemodel.protocol.server_client;

public interface IClientState99<GameMatchX extends IGameMatchX> extends
        IClientState01NoConnect,
        IClientState02ConnectNonIdent,
        IClientState04UserWasAuthorized<GameMatchX>,
        IClientState06GameTypeWasSet<GameMatchX>,
        IClientState07GameMatchWasSet<GameMatchX>,
        IClientState08GameMatchIsPlaying {

    void doBeforeTurnOff();
    void doAfterTurnOn();
}
