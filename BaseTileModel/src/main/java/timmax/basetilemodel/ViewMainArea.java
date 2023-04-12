package timmax.basetilemodel;

public abstract class ViewMainArea implements View {
    protected BaseModel model;

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
        this.model = baseModel;
        baseModel.addViewListener( this);
    }
}