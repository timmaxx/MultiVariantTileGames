package timmax.tilegame.basemodel.protocol.server;

import java.io.*;
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
    private static final String FILE_NAME_WITH_CLASS_NAMES_OF_MODELS = "models.txt";

    public static <ClientId> Set<GameType> getCollectionOfModelOfServerDescriptor(
            RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton,
            ClientId clientId
    ) {
        Path path = null;
        try {
            path = Paths.get(Objects.requireNonNull(ModelOfServerLoader.class.getResource(FILE_NAME_WITH_CLASS_NAMES_OF_MODELS)).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert path != null : "path to file '" + FILE_NAME_WITH_CLASS_NAMES_OF_MODELS + "' must be not null";

        Set<GameType> gameTypeSet = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            GameType gameType;
            while ((line = reader.readLine()) != null) {
                try {
                    // ToDo: Нужно минимизировать количество согласований в методах и между классами.
                    //       Второй и третий параметр - это группа параметров для конструктора модели.
                    //       И эту группу и можно было-бы обрабатывать как группу.
                    //       1. Во первых при вызове new GameType(...)
                    gameType = new GameType(line, remoteClientStateAutomaton, clientId);
                } catch (ClassNotFoundException e) {
                    logger.warn("Class '{}' is not found.", line, e);
                    continue;
                } catch (NoSuchMethodException e) {
                    // ToDo: - продолжение предыдущего ToDo.
                    //       2. Во вторых при логировании здесь.
                    logger.warn("Class '{}' does not contains constructor with " + "'parameter(s) " + remoteClientStateAutomaton.getClass() + "'" + " type and " + clientId.getClass() + " type.", line, e);
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
            throw new RuntimeException("No one class was found!");
            // System.exit(1);
        }
        return gameTypeSet;
    }
}
