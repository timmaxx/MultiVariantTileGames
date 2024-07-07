package timmax.tilegame.basemodel.protocol.server;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelOfServerLoader {
    protected static final Logger logger = LoggerFactory.getLogger(ModelOfServerLoader.class);
    private static final String FILE_NAME_WITH_CLASS_NAMES_OF_GAME_TYPES = "gameTypes.txt";

    public static Set<GameType> getCollectionOfGameType() {
        Path path = null;
        try {
            path = Paths.get(Objects.requireNonNull(ModelOfServerLoader.class.getResource(FILE_NAME_WITH_CLASS_NAMES_OF_GAME_TYPES)).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert path != null : "path to file '" + FILE_NAME_WITH_CLASS_NAMES_OF_GAME_TYPES + "' must be not null";

        Set<GameType> gameTypeSet = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            GameType gameType;
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
}
