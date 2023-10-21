package timmax.tilegame.transport;

import timmax.tilegame.basemodel.gamecommand.GameCommand;

public interface TransportOfController {
    void sendCommand( GameCommand gameCommand);
}