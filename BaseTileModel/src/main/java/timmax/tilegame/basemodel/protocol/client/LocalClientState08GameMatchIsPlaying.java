package timmax.tilegame.basemodel.protocol.client;

import javafx.application.Platform;
import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.basemodel.protocol.server_client.ClientState08GameMatchIsPlaying;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.baseview.View;
import timmax.tilegame.baseview.ViewMainField;

public class LocalClientState08GameMatchIsPlaying extends ClientState08GameMatchIsPlaying {
    public LocalClientState08GameMatchIsPlaying(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    //  class State
    @Override
    public void doAfterTurnOn() {
        getBaseStateAutomaton().updateOnSetGameMatchIsPlaying();

        //  ToDo:   Попробовать перенести этот код в
        //          Pane07GameMatchSelected :: void updateOnSetGameMatchIsPlaying()
        //          после (или до) doOnNextState().
        View view = getBaseStateAutomaton().getView(ViewMainField.class.getSimpleName());
        if (view instanceof ViewMainField viewMainField) {
            Platform.runLater(() -> {
                viewMainField.initMainField(getBaseStateAutomaton().getGameMatchExtendedDto().getParamsOfModelValueMap());
                for (GameEventOneTile gameEventOneTile : getBaseStateAutomaton().getGameMatchExtendedDto()/*gameMatchExtendedDto*/.getGameEventOneTileSet()) {
                    viewMainField.update(gameEventOneTile);
                }
            });
        }
    }

    // class ClientState
    @Override
    public LocalClientStateAutomaton getBaseStateAutomaton() {
        return (LocalClientStateAutomaton) (super.getBaseStateAutomaton());
    }
}
