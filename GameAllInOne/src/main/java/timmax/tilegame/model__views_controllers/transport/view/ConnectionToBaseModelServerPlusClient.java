package timmax.tilegame.model__views_controllers.transport.view;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.ConnectionToBaseModel;
import timmax.tilegame.basemodel.GameQueueForOneView;
import timmax.tilegame.basemodel.TransportModelFromModelToViews;
import timmax.tilegame.model__views_controllers.transport.model.TransportModelFromModelToViewsForModelViews;
import timmax.tilegame.baseview.ViewInterface;

public class ConnectionToBaseModelServerPlusClient implements ConnectionToBaseModel {
    TransportModelFromModelToViews transportModelFromModelToViews;

    public ConnectionToBaseModelServerPlusClient( BaseModel baseModel) {
        transportModelFromModelToViews = new TransportModelFromModelToViewsForModelViews( baseModel);
    }

    @Override
    public GameQueueForOneView addView( ViewInterface viewInterface) {
        return transportModelFromModelToViews.addView( viewInterface);
    }

    @Override
    public ConnectionToBaseModel createNewGame( ) {
        // ???
        return null;
    }
}