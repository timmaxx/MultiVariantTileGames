package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.HashSetOfObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.baseview.View;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ClientStateAutomaton<Model, ClientId> implements
        IClientState01NoConect,
        IClientState02ConnectNonIdent,
        IClientState03ConnectAuthorized,
        IClientState04GameTypeSetSelected,
        IClientState05GameTypeSelected<Model>,
        IClientState06GameMatchSetSelected<Model>,
        IClientState07GameMatchSelected<Model>,
        IClientState08GameIsPlaying {
    ClientState01NoConect<Model, ClientId> clientState01NoConect;
    ClientState02ConnectNonIdent<Model, ClientId> clientState02ConnectNonIdent;
    ClientState03ConnectAuthorized<Model, ClientId> clientState03ConnectAuthorized;
    ClientState04GameTypeSetSelected<Model, ClientId> clientState04GameTypeSetSelected;
    ClientState05GameTypeSelected<Model, ClientId> clientState05GameTypeSelected;
    ClientState06GameMatchSetSelected<Model, ClientId> clientState06GameMatchSetSelected;
    ClientState07GameMatchSelected<Model, ClientId> clientState07GameMatchSelected;
    ClientState08GameIsPlaying<Model, ClientId> clientState08GameIsPlaying;

    private IClientState00 currenState;
    //private Map<String, Integer> mapOfParamsOfModelValue;

    // For local clientState:
    // Эти переменные используются только в классах-наследниках LocalClientState0X.
    // ToDo: Пересмотреть архитектуру расположения этих переменных. Возможно:
    //       - удалить их отсюда
    //       - из них собрать класс и использовать в классах-наследниках LocalClientState0X
    private final HashSetOfObserverOnAbstractEvent hashSetOfObserverOnAbstractEvent;
    private final Map<String, View> mapOfViewName_View;


    public ClientStateAutomaton(FabricOfClientStates<Model, ClientId> fabricOfClientStates) {
        clientState01NoConect = fabricOfClientStates.getClientState01NoConect(this);
        clientState02ConnectNonIdent = fabricOfClientStates.getClientState02ConnectNonIdent(this);
        clientState03ConnectAuthorized = fabricOfClientStates.getClientState03ConnectAuthorized(this);
        clientState04GameTypeSetSelected = fabricOfClientStates.getClientState04GameTypeSetSelected(this);
        clientState05GameTypeSelected = fabricOfClientStates.getClientState05GameTypeSelected(this);
        clientState06GameMatchSetSelected = fabricOfClientStates.getClientState06GameMatchSetSelected(this);
        clientState07GameMatchSelected = fabricOfClientStates.getClientState07GameMatchSelected(this);
        clientState08GameIsPlaying = fabricOfClientStates.getClientState08GameIsPlaying(this);

        // For local clientState:
        this.hashSetOfObserverOnAbstractEvent = new HashSetOfObserverOnAbstractEvent();
        this.mapOfViewName_View = new HashMap<>();

        currenState = clientState01NoConect;
    }
/*
    protected IClientState00 getCurrenState() {
        return currenState;
    }

    protected void setMapOfParamsOfModelValue(Map<String, Integer> mapOfParamsOfModelValue) {
        this.mapOfParamsOfModelValue = mapOfParamsOfModelValue;
    }

    protected Map<String, Integer> getMapOfParamsOfModelValue() {
        return mapOfParamsOfModelValue;
    }
*/
    public Map<String, View> getMapOfViewName_View() {
        return mapOfViewName_View;
    }

    public HashSetOfObserverOnAbstractEvent getHashSetOfObserverOnAbstractEvent() {
        return hashSetOfObserverOnAbstractEvent;
    }

    public void addView(View view) {
        mapOfViewName_View.put(view.getViewName(), view);
    }

    // ToDo: Этот метод нужно не для состояния реализовывать, а для автомата!!!
    public Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView) {
        return currenState.getViewConstructor(classOfView);
    }

    public void addCallBackOnIncomingTransportPackageEvent(ObserverOnAbstractEvent observerOnAbstractEvent) {
        getHashSetOfObserverOnAbstractEvent().add(observerOnAbstractEvent);
    }

    // ---- 2 (Пользователь)
    protected void setUserName_(String userName) {
        setGameTypeSet_(null);
        // this.userName = userName;
        clientState03ConnectAuthorized.setUserName_(userName);
    }

    // ---- 3 (Список типов игр)
    protected void setGameTypeSet_(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        setGameType_(null);
        // this.setOfModelOfServerDescriptor = setOfModelOfServerDescriptor;
        clientState04GameTypeSetSelected.setGameTypeSet_(setOfModelOfServerDescriptor);
    }

    // ---- 4 (Конкретный тип игры)
    protected void setGameType_(ModelOfServerDescriptor modelOfServerDescriptor) {
        setGameMatchSet_(null);
        // this.modelOfServerDescriptor = modelOfServerDescriptor;
        clientState05GameTypeSelected.setGameType_(modelOfServerDescriptor);
    }

    // ---- 5 (Набор моделей игр)
    protected void setGameMatchSet_(Set<Model> setOfServerBaseModel) {
        setServerBaseModel_(null);
        // this.setOfServerBaseModel = setOfServerBaseModel;
        clientState06GameMatchSetSelected.setGameMatchSet_(setOfServerBaseModel);
    }

    // ---- 6 (Конкретная модель игры)
    protected void setServerBaseModel_(Model serverBaseModel) {
        setGameIsPlaying_(null);
        // this.serverBaseModel = serverBaseModel;
        clientState07GameMatchSelected.setServerBaseModel_(serverBaseModel);
    }

    // ---- 7
    protected void setGameIsPlaying_(Boolean gameIsPlaying) {
        // this.gameIsPlaying = gameIsPlaying;
        clientState08GameIsPlaying.setGameIsPlaying_(gameIsPlaying);
    }

    //
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return currenState.getMainGameClientStatus();
    }

    // 2
    @Override
    public void setUserName(String userName) {
        clientState02ConnectNonIdent.setUserName(userName);
        currenState = clientState03ConnectAuthorized;
    }

    // 3
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

    // 4
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

    // 5
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

    // 6
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

    // 7
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

    // 8
    @Override
    public Boolean getGameIsPlaying() {
        return clientState08GameIsPlaying.getGameIsPlaying();
    }

    @Override
    public void forgetGameIsPlaying() {
        clientState08GameIsPlaying.forgetGameIsPlaying();
        currenState = clientState07GameMatchSelected;
    }
}
