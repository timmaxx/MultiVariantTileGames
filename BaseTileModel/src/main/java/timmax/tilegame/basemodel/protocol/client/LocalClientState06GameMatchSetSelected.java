package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState06GameMatchSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class LocalClientState06GameMatchSetSelected extends ClientState06GameMatchSetSelected {
    public LocalClientState06GameMatchSetSelected(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState99
    @Override
    public void doAfterTurnOn() {
        //  ToDo:   Может перенести во внутрь getClientStateAutomaton().updateOnSelectGameType()?...
        getClientStateAutomaton().clearViewName_ViewMap();
        getClientStateAutomaton().updateOnSelectGameType();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getClientStateAutomaton() {
        return (LocalClientStateAutomaton) (super.getClientStateAutomaton());
    }
}
