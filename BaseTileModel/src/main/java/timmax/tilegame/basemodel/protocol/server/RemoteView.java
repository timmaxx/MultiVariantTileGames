package timmax.tilegame.basemodel.protocol.server;

import java.util.Objects;

// В экземпляре этого класса будет храниться идентификатор клиента и строковый идентификатор выборки,
// которой нужно отправить то или иное сообщение об игровом событии.
public class RemoteView<ClientId> {
    private final ClientId clientId;
    private final String viewName;

    public RemoteView(ClientId clientId, String viewName) {
        this.clientId = clientId;
        this.viewName = viewName;
    }

    public ClientId getClientId() {
        return clientId;
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public String toString() {
        return
                RemoteView.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                        "clientId=" + clientId +
                        ", viewName='" + viewName + '\'' +
                        '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RemoteView<?> that = (RemoteView<?>) o;

        if (!Objects.equals(clientId, that.clientId)) return false;
        return viewName.equals(that.viewName);
    }

    @Override
    public int hashCode() {
        int result = clientId != null ? clientId.hashCode() : 0;
        result = 31 * result + viewName.hashCode();
        return result;
    }
}
