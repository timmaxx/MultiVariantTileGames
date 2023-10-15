package timmax.tilegame.basemodel;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.GameEventQueue;

public interface BaseModel {
    void createNewGame( );
    void addCommandIntoQueue( GameCommand gameCommand);
    GameEventQueue addViewListener( View view);

    void restart( );
    void nextLevel( );
    void prevLevel( );
    void win( );
}