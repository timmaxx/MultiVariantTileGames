package timmax.tilegame.model__views_controllers.transport.model;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.GameQueueForOneView;
import timmax.tilegame.basemodel.TransportModelFromModelToViews;
import timmax.tilegame.baseview.ViewInterface;
import timmax.tilegame.basemodel.gameevent.GameEvent;

public class TransportModelFromModelToViewsForModelViews implements TransportModelFromModelToViews {
    private BaseModel baseModel;

    public TransportModelFromModelToViewsForModelViews( ) {
    }

    public TransportModelFromModelToViewsForModelViews( BaseModel baseModel) {
        this.baseModel = baseModel;
    }

    public void setBaseModel( BaseModel baseModel) {
        this.baseModel = baseModel;
    }

    @Override
    public GameQueueForOneView addView( ViewInterface viewInterface) {
        return baseModel.addView( viewInterface);
    }

    @Override
    public void send( GameEvent gameEvent) {
        // ???
    }

    @Override
    public void receiveFeedback( GameQueueForOneView gameQueueForOneView) {
        // ???
    }
}