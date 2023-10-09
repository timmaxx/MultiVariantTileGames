package timmax.tilegame.model__views_controllers.transport.view;

import timmax.tilegame.basemodel.GameQueueForOneView;
import timmax.tilegame.baseview.TransportViewFromModelToView;
import timmax.tilegame.basemodel.gameevent.GameEvent;

public class TransportViewFromModelToViewServerPlusClient implements TransportViewFromModelToView {
    @Override
    public void receive( GameEvent gameEvent) {
        // ???
    }

    @Override
    public void sendFeedback( GameQueueForOneView gameQueueForOneView) {
        // ???
    }
}