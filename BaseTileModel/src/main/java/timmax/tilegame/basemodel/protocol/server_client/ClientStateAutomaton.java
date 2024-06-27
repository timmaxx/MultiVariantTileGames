package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

import java.util.Set;

public class ClientStateAutomaton<Model> implements
        IClientState01NoConnect,
        IClientState02ConnectNonIdent,
        IClientState03ConnectAuthorized,
        IClientState04GameTypeSetSelected,
        IClientState05GameTypeSelected<Model>,
        IClientState06GameMatchSetSelected<Model>,
        IClientState07GameMatchSelected<Model>,
        IClientState08GameIsPlaying {
    final ClientState01NoConnect<Model> clientState01NoConnect;
    final ClientState02ConnectNonIdent<Model> clientState02ConnectNonIdent;
    final ClientState03ConnectAuthorized<Model> clientState03ConnectAuthorized;
    final ClientState04GameTypeSetSelected<Model> clientState04GameTypeSetSelected;
    final ClientState05GameTypeSelected<Model> clientState05GameTypeSelected;
    final ClientState06GameMatchSetSelected<Model> clientState06GameMatchSetSelected;
    final ClientState07GameMatchSelected<Model> clientState07GameMatchSelected;
    final ClientState08GameIsPlaying<Model> clientState08GameIsPlaying;

    private IClientState00 currenState;

    public ClientStateAutomaton(
            IFabricOfClientStates<Model> iFabricOfClientStates) {
        clientState01NoConnect = iFabricOfClientStates.getClientState01NoConnect(this);
        clientState02ConnectNonIdent = iFabricOfClientStates.getClientState02ConnectNonIdent(this);
        clientState03ConnectAuthorized = iFabricOfClientStates.getClientState03ConnectAuthorized(this);
        clientState04GameTypeSetSelected = iFabricOfClientStates.getClientState04GameTypeSetSelected(this);
        clientState05GameTypeSelected = iFabricOfClientStates.getClientState05GameTypeSelected(this);
        clientState06GameMatchSetSelected = iFabricOfClientStates.getClientState06GameMatchSetSelected(this);
        clientState07GameMatchSelected = iFabricOfClientStates.getClientState07GameMatchSelected(this);
        clientState08GameIsPlaying = iFabricOfClientStates.getClientState08GameIsPlaying(this);

        currenState = clientState01NoConnect;
    }

    // 2 interface IClientState02ConnectNonIdent
    @Override
    public void setUserName(String userName) {
        clientState02ConnectNonIdent.setUserName(userName);
        currenState = clientState03ConnectAuthorized;
    }

    // 3 interface IClientState03ConnectAuthorized
    @Override
    public String getUserName() {
        return clientState03ConnectAuthorized.getUserName();
    }

    @Override
    public void forgetUserName() {
        clientState03ConnectAuthorized.forgetUserName();
        currenState = clientState02ConnectNonIdent;
    }

    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        clientState03ConnectAuthorized.setGameTypeSet(setOfModelOfServerDescriptor);
        currenState = clientState04GameTypeSetSelected;
    }

    // 4 interface IClientState04GameTypeSetSelected
    @Override
    public Set<ModelOfServerDescriptor> getGameTypeSet() {
        return clientState04GameTypeSetSelected.getGameTypeSet();
    }

    @Override
    public void forgetGameTypeSet() {
        clientState04GameTypeSetSelected.forgetGameTypeSet();
        currenState = clientState03ConnectAuthorized;
    }

    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        clientState04GameTypeSetSelected.setGameType(modelOfServerDescriptor);
        currenState = clientState05GameTypeSelected;
    }

    // 5 interface IClientState05GameTypeSelected
    @Override
    public ModelOfServerDescriptor getGameType() {
        return clientState05GameTypeSelected.getGameType();
    }

    @Override
    public void forgetGameType() {
        clientState05GameTypeSelected.forgetGameType();
        currenState = clientState03ConnectAuthorized;
    }

    @Override
    public void setGameMatchSet(Set<Model> setOfServerBaseModel) {
        clientState05GameTypeSelected.setGameMatchSet(setOfServerBaseModel);
        currenState = clientState06GameMatchSetSelected;
    }

    // 6 interface IClientState06GameMatchSetSelected
    @Override
    public Set<Model> getGameMatchSet() {
        return clientState06GameMatchSetSelected.getGameMatchSet();
    }

    @Override
    public void forgetGameMatchSet() {
        clientState06GameMatchSetSelected.forgetGameMatchSet();
        currenState = clientState05GameTypeSelected;
    }

    @Override
    public void setServerBaseModel(Model serverBaseModel) {
        clientState06GameMatchSetSelected.setServerBaseModel(serverBaseModel);
        currenState = clientState07GameMatchSelected;
    }

    // 7 interface IClientState07GameMatchSelected
    @Override
    public Model getServerBaseModel() {
        return clientState07GameMatchSelected.getServerBaseModel();
    }

    @Override
    public void forgetServerBaseModel() {
        clientState07GameMatchSelected.forgetServerBaseModel();
        currenState = clientState06GameMatchSetSelected;
    }

    @Override
    public void setGameIsPlaying(Boolean gameIsPlaying) {
        clientState07GameMatchSelected.setGameIsPlaying(gameIsPlaying);
        currenState = clientState08GameIsPlaying;
    }

    // 8 interface IClientState08GameIsPlaying
    @Override
    public Boolean getGameIsPlaying() {
        return clientState08GameIsPlaying.getGameIsPlaying();
    }

    @Override
    public void forgetGameIsPlaying() {
        clientState08GameIsPlaying.forgetGameIsPlaying();
        currenState = clientState07GameMatchSelected;
    }

    // class Object
    @Override
    public String toString() {
        return "ClientStateAutomaton{" +
                "currenState=" + currenState +
                '}';
    }
}
