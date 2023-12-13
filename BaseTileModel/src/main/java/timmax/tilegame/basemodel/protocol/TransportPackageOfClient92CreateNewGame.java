package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfModel;

public class TransportPackageOfClient92CreateNewGame<T> extends TransportPackageOfClient <T> {
    @Override
    public void execute(TransportOfModel<T> transportOfModel, T clientId) {
        System.out.println("  onCreateNewGame");

        transportOfModel.getModelOfServer().createNewGame();
    }
}