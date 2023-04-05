package timmax.basetilemodel;

public interface View {

    void updateAllTiles( );

    void updateOneTile( int x, int y);

    void setModel( BaseModel baseModel);
}