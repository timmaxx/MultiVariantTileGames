package timmax.tilegame.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.client.ModelOfClient;
import timmax.tilegame.basemodel.protocol.client.jfx.FabricOfClientStateAutomatonJfx;
import timmax.tilegame.basemodel.protocol.client.jfx.FabricOfClientStatesJfx;
import timmax.tilegame.client.statuscontrol.*;
import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;
import timmax.tilegame.transport.TransportOfClient;

public class MultiGameClient extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    // ToDo: Удалить комментарий после решения проблеммы, указанной в нём.
    //       В классе MultiGameWebSocketServer используется только
    //       RemoteClientStateAutomaton<WebSocket>> mapOfRemoteClientState
    //       и это хорошо!
    //       Но здесь (в MultiGameClient :: void start(Stage primaryStage))
    //       есть одновременно и
    //       LocalClientStateAutomaton localClientStateJfx
    //       и
    //       IModelOfClient iModelOfClient
    //       что не есть хорошо, т.к. не единообразно!
    @Override
    public void start(Stage primaryStage) {
        Pane root = new VBox();

        // ToDo: Warning:(28, 27) Raw use of parameterized class 'IModelOfClient'
        TransportOfClient<?> transportOfClient = new MultiGameWebSocketClientManyTimesUse<>();
        // ToDo: Вместо использования класса ClientStateAutomaton стоит создать класс LocalClientStateAutomaton,
        //       в котором и использовать функционал FabricOfClientStateAutomatonJfx и отказаться от FabricOfClientStatesJfx.
        // ToDo: Warning:(32, 9) Raw use of parameterized class 'ClientStateAutomaton'
        // ToDo: Устранить взаимозависимость классов, реализующих интерфейс IFabricOfClientStates, с классом
        //       ClientStateAutomaton.
        //       См. комментарий к IFabricOfClientStates.
        LocalClientStateAutomaton localClientStateJfx = new LocalClientStateAutomaton(
                new FabricOfClientStatesJfx<>(),
                new FabricOfClientStateAutomatonJfx()
        );
        // ToDo: Удалить интерфейс IModelOfClient и класс, его реализующий (ModelOfClient), т.к.
        //       IModelOfClient - это надстройка над TransportOfClient. Вот и перенести методы
        //       IModelOfClient (ModelOfClient) в TransportOfClient (MultiGameWebSocketClientManyTimesUse).
        IModelOfClient iModelOfClient = new ModelOfClient<>(transportOfClient, localClientStateJfx);
        // ToDo: Удалить сеттер, т.к. и сеттеру нечего будет сетить
        transportOfClient.setModelOfClient(iModelOfClient);

        root.getChildren().addAll(
                // ToDo: Удалить параметр iModelOfClient после реализации предыдущего ToDo.
                new Pane01ServerConnect(iModelOfClient, transportOfClient),
                new Pane02UserLogin(iModelOfClient, transportOfClient),
                new Pane03GetGameTypeSet(iModelOfClient, transportOfClient),
                new Pane04SelectGameType(iModelOfClient, transportOfClient),
                new Pane05GetGameMatchSet(iModelOfClient, transportOfClient),
                new Pane06SelectGameMatch(iModelOfClient, transportOfClient),
                new Pane07GameMatchPlaying(iModelOfClient, transportOfClient)
        );

        Scene scene = new Scene(root);
        primaryStage.setTitle("Multi tile game client");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.exit(0);
    }
}
