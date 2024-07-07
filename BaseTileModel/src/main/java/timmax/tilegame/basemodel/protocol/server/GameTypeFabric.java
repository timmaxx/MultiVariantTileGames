package timmax.tilegame.basemodel.protocol.server;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class GameTypeFabric {
    public static GameType create(String gameTypeFullClassName)
            throws
            ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        // ToDo: Избавиться от "Warning:(13, 51) Unchecked cast: 'java.lang.Class<capture<?>>' to 'java.lang.Class<? extends timmax.tilegame.basemodel.protocol.server.GameType>'"
        Class<? extends GameType> gameTypeClass = (Class<? extends GameType>) Class.forName(gameTypeFullClassName);
        Constructor<? extends GameType> constructorOfGameTypeClass = gameTypeClass.getConstructor();

        return constructorOfGameTypeClass.newInstance();
    }
}
