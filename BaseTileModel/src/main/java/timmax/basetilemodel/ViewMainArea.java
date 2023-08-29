package timmax.basetilemodel;

// Представление основного игрового поля
public abstract class ViewMainArea extends View {

    public ViewMainArea( BaseModel baseModel) {
        super( baseModel);
    }

    // Обновление всего такого представления делается через циклы по всем плиткам модели
    @Override
    public void update( ) {
        for ( int y = 0; y < baseModel.getHeight( ); y++) {
            for ( int x = 0; x < baseModel.getWidth( ); x++) {
                updateOneTile( x, y);
            }
        }
    }

    // Обновление одной плитки представления
    protected abstract void updateOneTile( int x, int y);
}