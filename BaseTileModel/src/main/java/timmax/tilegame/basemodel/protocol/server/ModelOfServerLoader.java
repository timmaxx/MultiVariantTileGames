package timmax.tilegame.basemodel.protocol.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelOfServerLoader {
    protected static final Logger logger = LoggerFactory.getLogger(ModelOfServerLoader.class);

    private final Path path;

    public ModelOfServerLoader(Path path) {
        this.path = path;
    }

    public <ClientId> Set<ModelOfServerDescriptor> getCollectionOfModelOfServerDescriptor(RemoteClientState<ClientId> remoteClientState) {
        Set<ModelOfServerDescriptor> result = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            ModelOfServerDescriptor modelOfServerDescriptor;
            while ((line = reader.readLine()) != null) {
                try {
                    modelOfServerDescriptor = new ModelOfServerDescriptor(line, remoteClientState);
                } catch (ClassNotFoundException e) {
                    logger.warn("Class '{}' is not found.", line, e);
                    continue;
                } catch (NoSuchMethodException e) {
                    logger.warn("Class '{}' does not contains constructor with one parameter TransportOfServer type.", line, e);
                    continue;
                }
                result.add(modelOfServerDescriptor);
            }
        } catch (FileNotFoundException fnfe) {
            logger.error("File with list of model class was not found.", fnfe);
            System.exit(1);
        } catch (IOException ioe) {
            logger.error("There is a problem with reading file with list of model class.", ioe);
            System.exit(1);
        }

        if (result.size() == 0) {
            throw new RuntimeException("No one class was found!");
            // System.exit(1);
        }
        return result;
    }
}
