package timmax.basetilemodel;

public abstract class View {
    protected BaseModel baseModel;

    public View( BaseModel baseModel) {
        this.baseModel = baseModel;
        baseModel.addViewListener( this);
    }

    protected abstract void update( );
}