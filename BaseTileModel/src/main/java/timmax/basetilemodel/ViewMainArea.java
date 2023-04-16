package timmax.basetilemodel;

public abstract class ViewMainArea extends View {

    public ViewMainArea( BaseModel baseModel) {
        super( baseModel);
    }

    @Override
    public void update( ) {
        for ( int y = 0; y < baseModel.getHeight( ); y++) {
            for ( int x = 0; x < baseModel.getWidth( ); x++) {
                updateOneTile( x, y);
            }
        }
    }

    protected abstract void updateOneTile( int x, int y);
}