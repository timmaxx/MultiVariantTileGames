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

    // For local clientState:
    // Эти переменные используются только в классах-наследниках LocalClientState0X.
    // ToDo: Пересмотреть архитектуру расположения этих переменных. Возможно:
    //       - удалить их отсюда
    //       - из них собрать класс и использовать в классах-наследниках LocalClientState0X
    private final HashSetOfObserverOnAbstractEvent hashSetOfObserverOnAbstractEvent;
    private final Map<String, View> mapOfViewName_View;

    public ClientStateAutomaton(IFabricOfClientStates<Model, ClientId> IFabricOfClientStates) {
        clientState01NoConect = IFabricOfClientStates.getClientState01NoConect(this);
        clientState02ConnectNonIdent = IFabricOfClientStates.getClientState02ConnectNonIdent(this);
        clientState03ConnectAuthorized = IFabricOfClientStates.getClientState03ConnectAuthorized(this);
        clientState04GameTypeSetSelected = IFabricOfClientStates.getClientState04GameTypeSetSelected(this);
        clientState05GameTypeSelected = IFabricOfClientStates.getClientState05GameTypeSelected(this);
        clientState06GameMatchSetSelected = IFabricOfClientStates.getClientState06GameMatchSetSelected(this);
        clientState07GameMatchSelected = IFabricOfClientStates.getClientState07GameMatchSelected(this);
        clientState08GameIsPlaying = IFabricOfClientStates.getClientState08GameIsPlaying(this);

        // For local clientState:
        this.hashSetOfObserverOnAbstractEvent = new HashSetOfObserverOnAbstractEvent();
        this.mapOfViewName_View = new HashMap<>();

        currenState = clientState01NoConect;
    }

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
