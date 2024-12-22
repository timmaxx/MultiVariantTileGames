package timmax.tilegame.basemodel.protocol.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timmax.tilegame.basemodel.protocol.server_client.IGameMatchX;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class GameTypeFabric {
    protected static final Logger logger = LoggerFactory.getLogger(GameTypeFabric.class);

    //  ToDo:   Переместить файл gameTypes.txt из этого модуля (BaseTileModel) в другой.
    //          Файл gameTypes.txt сейчас находится в модуле BaseTileModel.
    //          Это, вероятно, не правильно, т.к. в модуле BaseTileModel не должны быть известны реализации.
    private static final String FILE_NAME_WITH_CLASS_NAMES_OF_GAME_TYPES = "gameTypes.txt";

    public static final Set<GameType<IGameMatchX>> GAME_TYPE_SET = getGameTypeSet();

    private GameTypeFabric() {
    }

    private static Set<GameType<IGameMatchX>> getGameTypeSet() {
        Path path = null;
        try {
            path = Paths.get(Objects.requireNonNull(GameTypeFabric.class.getResource(FILE_NAME_WITH_CLASS_NAMES_OF_GAME_TYPES)).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert path != null : "path to file '" + FILE_NAME_WITH_CLASS_NAMES_OF_GAME_TYPES + "' must be not null";

        Set<GameType<IGameMatchX>> gameTypeSet = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            GameType<IGameMatchX> gameType;
            while ((line = reader.readLine()) != null) {
                try {
                    // ToDo: Нужно минимизировать количество согласований в методах и между классами.
                    gameType = GameTypeFabric.create(line);
                } catch (ClassNotFoundException cnfe) {
                    logger.warn("Class '{}' is not found.", line, cnfe);
                    continue;
                } catch (NoSuchMethodException nsme) {
                    // ToDo: - продолжение предыдущего ToDo.
                    //       2. Во вторых при логировании здесь.
                    logger.warn("Class '{}' does not contains constructor.", line, nsme);
                    continue;
                } catch (InvocationTargetException ite) {
                    logger.warn("Class '{}'. InvocationTargetException.", line, ite);
                    continue;
                } catch (InstantiationException ie) {
                    logger.warn("Class '{}'. InstantiationException.", line, ie);
                    continue;
                } catch (IllegalAccessException ia) {
                    logger.warn("Class '{}'. IllegalAccessException.", line, ia);
                    continue;
                }
                gameTypeSet.add(gameType);
            }
        } catch (FileNotFoundException fnfe) {
            logger.error("File with list of model class was not found.", fnfe);
            System.exit(1);
        } catch (IOException ioe) {
            logger.error("There is a problem with reading file with list of model class.", ioe);
            System.exit(1);
        }

        if (gameTypeSet.size() == 0) {
            logger.error("No one class was found!");
            throw new RuntimeException("No one class was found!");
            // System.exit(1);
        }
        return gameTypeSet;
    }

    private static GameType<IGameMatchX> create(String gameTypeFullClassName)
            throws
            ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        //  Warning:(91, 64) Unchecked cast: 'java.lang.Class<capture<?>>' to 'java.lang.Class<? extends timmax.tilegame.basemodel.protocol.server.GameType<timmax.tilegame.basemodel.protocol.server_client.IGameMatchX>>'
        Class<? extends GameType<IGameMatchX>> gameTypeClass = (Class<? extends GameType<IGameMatchX>>) Class.forName(gameTypeFullClassName);
        Constructor<? extends GameType<IGameMatchX>> constructorOfGameTypeClass = gameTypeClass.getConstructor();

        return constructorOfGameTypeClass.newInstance();
    }
}
