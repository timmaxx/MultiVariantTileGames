package timmax.tilegame.server.websocket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

public class ModelOfServerLoader {
    private final Path path;

    public ModelOfServerLoader(Path path) {
        this.path = path;
    }

    public Collection<ModelOfServerDescriptor> getCollectionOfModelOfServerDescriptor() {
        Collection<ModelOfServerDescriptor> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            ModelOfServerDescriptor modelOfServerDescriptor;
            while ((line = reader.readLine()) !=null) {
                try {
                    modelOfServerDescriptor = new ModelOfServerDescriptor(line);
                } catch (ClassNotFoundException e) {
                    System.err.println("Class '" + line + "' is not found.");
                    e.printStackTrace();
                    continue;
                } catch (NoSuchMethodException e) {
                    System.err.println("Class '" + line + "' does not contains constructor with one parameter TransportOfServer type.");
                    e.printStackTrace();
                    continue;
                }
                result.add(modelOfServerDescriptor);
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("File with list of model class was not found.");
            fnfe.printStackTrace();
            System.exit(1);
        } catch (IOException ioe) {
            System.err.println("There is a problem with reading file with list of model class.");
            ioe.printStackTrace();
            System.exit(1);
        }

        if (result.size() == 0) {
            throw new RuntimeException("No one class was found!");
            // System.exit(1);
        }
        return result;
    }
}
