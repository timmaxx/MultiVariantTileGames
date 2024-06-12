package timmax.tilegame.basemodel.protocol.server_client;

import timmax.commons.state.IStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.state.AStateOfMVTGClient;

public interface IStateAutomatonOfMVTGClient<Model> extends IStateAutomaton, IStateOfMVTGClient<Model> {
    @Override
    AStateOfMVTGClient<Model> getCurrentState();
}
