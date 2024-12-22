package timmax.tilegame.basemodel.protocol.server_client;

public interface IClientState99 extends
        IClientState01NoConnect,
        IClientState02ConnectWithoutServerInfo,
        IClientState03ConnectWithServerInfo,
        IClientState04UserWasAuthorized,
        IClientState06GameTypeWasSet,
        IClientState07GameMatchWasSet,
        IClientState08GameMatchIsPlaying {
}
