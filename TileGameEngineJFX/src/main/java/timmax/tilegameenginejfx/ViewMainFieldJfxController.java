package timmax.tilegameenginejfx;

import timmax.basetilemodel.BaseModel;

public class ViewMainFieldJfxController extends ViewMainFieldJfx {
    public ViewMainFieldJfxController( BaseModel baseModel, Game game) {
        super( baseModel, game);
    }
/*
    @Override
    protected void initOnMouseClickEventHandlerOnCell( GameStackPane cell) {
        cell.setOnMouseClicked( event ->
                System.out.println(
                        " x = " + ( ( GameStackPane)event.getSource( )).getX( ) + ", " +
                        "y = " + ( ( GameStackPane)event.getSource( )).getY( ) + ". " +
                        "src = " + event.getSource( ) + ". " +
                        // "event.getEventType( ) = " + event.getEventType( ) + ". " +
                        "event.getButton( ) = " + event.getButton( )));
    }
*/
}