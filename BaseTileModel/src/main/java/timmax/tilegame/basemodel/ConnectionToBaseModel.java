package timmax.tilegame.basemodel;

import timmax.tilegame.baseview.ViewInterface;

public interface ConnectionToBaseModel {
    GameQueueForOneView addView( ViewInterface viewInterface);

    ConnectionToBaseModel createNewGame( );
}