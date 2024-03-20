package timmax.tilegame.basemodel.protocol.cs.inners;

import java.util.Set;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

public abstract class AClientStateProtected<Model> implements IClientStateProtected<Model> {
    protected ClientState<Model> clientState;

    public AClientStateProtected(ClientState<Model> clientState) {
        this.clientState = clientState;
    }

    public final void setAsCurrent() {
        clientState.setICSPCurrent(this);
    }

    // ---- 2 ConnectNonIdent
    protected final void setUserName_(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new NullPointerException("UserName is null or empty. It must be not null for this method.");
        }
        forgetGameTypeSet_();
        clientState.userName = userName;
    }

    // ---- 3 ConnectAuthorized
    protected final void forgetUserName_() {
        forgetGameTypeSet_();
        clientState.userName = null;
    }

    protected final void setGameTypeSet_(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        if (setOfModelOfServerDescriptor == null || setOfModelOfServerDescriptor.size() == 0) {
            throw new NullPointerException("setOfModelOfServerDescriptor is null or empty. It must be not null for this method.");
        }
        forgetGameType_();
        clientState.setOfModelOfServerDescriptor = setOfModelOfServerDescriptor;
    }

    // ---- 4 (GameTypeSetSelected)
    protected final void forgetGameTypeSet_() {
        forgetGameType_();
        clientState.setOfModelOfServerDescriptor = null;
    }

    protected final void setGameType_(ModelOfServerDescriptor modelOfServerDescriptor) {
        if (modelOfServerDescriptor == null) {
            throw new NullPointerException("modelOfServerDescriptor is null. It must be not null for this method.");
        }
        setGameMatchSet_(null);
        clientState.modelOfServerDescriptor = modelOfServerDescriptor;
    }

    // ---- 5 (GameTypeSelected)
    protected final void forgetGameType_() {
        forgetGameMatchSet_();
        clientState.setOfModelOfServerDescriptor = null;
    }

    protected final void setGameMatchSet_(Set<Model> setOfServerBaseModel) {
        forgetServerBaseModel_();
        clientState.setOfServerBaseModel = setOfServerBaseModel;
    }

    // ---- 6 (MatchSetSelected)
    protected final void forgetGameMatchSet_() {
        forgetServerBaseModel_();
        clientState.setOfServerBaseModel = null;
    }

    protected final void setServerBaseModel_(Model serverBaseModel) {
        forgetGameIsPlaying_();
        clientState.serverBaseModel = serverBaseModel;
    }

    // ---- 7 (MatchSelected)
    protected final void forgetServerBaseModel_() {
        setGameIsPlaying_(null);
        clientState.serverBaseModel = null;
    }

    protected final void setGameIsPlaying_(Boolean gameIsPlaying) {
        clientState.gameIsPlaying = gameIsPlaying;
    }

    protected final void forgetGameIsPlaying_() {
        clientState.gameIsPlaying = null;
    }

    // ---- 3 ConnectAuthorized
    @Override
    public String getUserName() {
        throw new RuntimeException("You cannot call this method in this state!");
        // return clientState.getCSP03ConnectAuthorized().getUserName();
    }

    @Override
    public void forgetUserName() {
        throw new RuntimeException("You cannot call this method in this state!");
        // clientState.getCSP03ConnectAuthorized().forgetUserName();
    }

    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        throw new RuntimeException("You cannot call this method in this state!");
        // clientState.getCSP03ConnectAuthorized().setGameTypeSet(setOfModelOfServerDescriptor);
    }

    // ---- 4 (GameTypeSetSelected)
    @Override
    public Set<ModelOfServerDescriptor> getGameTypeSet() {
        throw new RuntimeException("You cannot call this method in this state!");
        // return clientState.getCSP04GameTypeSetSelected().getGameTypeSet();
    }

    @Override
    public void forgetGameTypeSet() {
        throw new RuntimeException("You cannot call this method in this state!");
        // clientState.getCSP04GameTypeSetSelected().forgetGameTypeSet();
    }

    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        throw new RuntimeException("You cannot call this method in this state!");
        // clientState.getCSP04GameTypeSetSelected().setGameType(modelOfServerDescriptor);
    }

    // ---- 5 (GameTypeSelected)
    @Override
    public ModelOfServerDescriptor getGameType() {
        throw new RuntimeException("You cannot call this method in this state!");
        // return clientState.getCSP05GameTypeSelected().getGameType();
    }

    @Override
    public void forgetGameType() {
        throw new RuntimeException("You cannot call this method in this state!");
        // clientState.getCSP05GameTypeSelected().forgetGameType();
    }

    @Override
    public void setGameMatchSet(Set<Model> setOfServerBaseModel) {
        throw new RuntimeException("You cannot call this method in this state!");
        // clientState.getCSP05GameTypeSelected().setGameMatchSet(setOfServerBaseModel);
    }

    // ---- 6 (MatchSetSelected)
    @Override
    public Set<Model> getGameMatchSet() {
        throw new RuntimeException("You cannot call this method in this state!");
        // return clientState.getCSP06MatchSetSelected().getGameMatchSet();
    }

    @Override
    public void forgetGameMatchSet() {
        throw new RuntimeException("You cannot call this method in this state!");
        // clientState.getCSP06MatchSetSelected().forgetGameMatchSet();
    }

    @Override
    public void setServerBaseModel(Model serverBaseModel) {
        throw new RuntimeException("You cannot call this method in this state!");
        // clientState.getCSP06MatchSetSelected().setServerBaseModel(serverBaseModel);
    }

    // ---- 7 (MatchSelected)
    @Override
    public Model getServerBaseModel() {
        throw new RuntimeException("You cannot call this method in this state!");
        // return (Model) clientState.getCsp07MatchSelected().getServerBaseModel();
    }

    @Override
    public void forgetServerBaseModel() {
        throw new RuntimeException("You cannot call this method in this state!");
        // clientState.getCsp07MatchSelected().forgetServerBaseModel();
    }

    @Override
    public void setGameIsPlaying(Boolean gameIsPlaying) {
        throw new RuntimeException("You cannot call this method in this state!");
        // clientState.getCsp07MatchSelected().setGameIsPlaying(gameIsPlaying);
    }

    // ---- 8 (GameIsPlaying)
    @Override
    public Boolean getGameIsPlaying() {
        throw new RuntimeException("You cannot call this method in this state!");
    }

    @Override
    public void forgetGameIsPlaying() {
        throw new RuntimeException("You cannot call this method in this state!");
    }

}
