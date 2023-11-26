package timmax.tilegame.basemodel.protocol.server;

// В экземпляре этого класса будет сохранятся идентификатор клиента и строковый идентификатор выборки,
// которой нужно отправить то или иное сообщение об игровом событии.
public class RemoteView {
    private final ClientId clientId;
    private final String viewId;


    public RemoteView(ClientId clientId, String viewId) {
        this.clientId = clientId;
        this.viewId = viewId;
    }
/*
    public ClientId getClientId() {
        return clientId;
    }

    public String getViewId() {
        return viewId;
    }
*/
}