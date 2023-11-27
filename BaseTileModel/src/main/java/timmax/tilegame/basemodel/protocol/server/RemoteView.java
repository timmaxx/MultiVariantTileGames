package timmax.tilegame.basemodel.protocol.server;

// В экземпляре этого класса будет сохранятся идентификатор клиента и строковый идентификатор выборки,
// которой нужно отправить то или иное сообщение об игровом событии.
public class RemoteView<T> {
    private final T clientId;
    private final String viewId;


    public RemoteView(T clientId, String viewId) {
        this.clientId = clientId;
        this.viewId = viewId;
    }

    public T getClientId() {
        return clientId;
    }

    public String getViewId() {
        return viewId;
    }
}