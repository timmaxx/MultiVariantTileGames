package timmax.tilegameenginejfx;

import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.View;

public abstract class ViewMainArea< T> implements View {
    protected Game game;
    protected BaseModel< T> model;

    public ViewMainArea( Game game) {
        this.game = game;
    }

    @Override
    public void update( ) {
        for ( int y = 0; y < model.getHeight( ); y++) {
            for ( int x = 0; x < model.getWidth( ); x++) {
                updateOneTile( x, y);
            }
        }
    }

    protected abstract void updateOneTile( int x, int y);

    @Override
    public void setModel( BaseModel baseModel) {
        this.model = ( BaseModel< T>) baseModel;
        baseModel.addViewListener( this);
    }
}