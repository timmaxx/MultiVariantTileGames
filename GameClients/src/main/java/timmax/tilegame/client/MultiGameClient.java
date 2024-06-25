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
        // ToDo: Warning:(38, 9) Raw use of parameterized class 'IModelOfClient'
        //       Warning:(38, 45) Raw use of parameterized class 'ModelOfClient<>'
        // ToDo: Устранить взаимозависимость классов, реализующих интерфейс IModelOfClient, с классом
        //       TransportOfClient.
        //       Из-за взаимозависимости приходится применять transportOfClient.setModelOfClient().
        IModelOfClient iModelOfClient = new ModelOfClient<>(transportOfClient, localClientStateJfx);
        // ToDo: Как-то не нравится мне, что iModelOfClient приходится внедрять в transportOfClient через сеттер.
        //       См. предыдущий комментарий.
        transportOfClient.setModelOfClient(iModelOfClient);

        root.getChildren().addAll(
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
