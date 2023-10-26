package timmax.tilegame.basemodel.protocol;

public class ParamName_ServerBaseModelClass {
    private final String paramName;
    private final Class clazz;

    public ParamName_ServerBaseModelClass( String paramName, Class clazz) {
        this.paramName = paramName;
        this.clazz = clazz;
    }

    public String getParamName( ) {
        return paramName;
    }

    public Class getClazz( ) {
        return clazz;
    }
}