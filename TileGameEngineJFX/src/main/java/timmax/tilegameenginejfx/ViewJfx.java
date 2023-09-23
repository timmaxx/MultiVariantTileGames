package timmax.tilegameenginejfx;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import timmax.basetilemodel.*;
import timmax.basetilemodel.gameevent.GameEvent;

public class ViewJfx extends Pane implements ViewJfxInterface {
    protected GameQueueForOneView gameQueueForOneView;
    // private ViewInterface view;

    protected BaseModel baseModel;


    public ViewJfx( BaseModel baseModel) {
        super( );
        this.baseModel = baseModel;
        // view = new View( baseModel);
        gameQueueForOneView = baseModel.addViewListener( this); // К модели привязать это представление
    }

    @Override
    public void update( ) {
        System.out.println( "ViewJfx.update( ) " + this.getClass( ) + " " + this);
    }

    @Override
    public Node getStyleableNode( ) {
        return super.getStyleableNode( );
    }

    @Override
    public GameEvent removeFromGameQueueForOneView() {
        return gameQueueForOneView.remove( );
    }
}