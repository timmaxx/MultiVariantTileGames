package timmax.tilegame.basemodel.protocol.server_client;

public interface IClientState99 extends
        IClientState01NoConnect,
        IClientState02ConnectNonIdent,
        IClientState04GameTypeSetSelected,
        IClientState06GameMatchSetSelected,
        IClientState07GameMatchSelected,
        IClientState08GameMatchIsPlaying {

    void doBeforeTurnOff();
    void doAfterTurnOn();
}
