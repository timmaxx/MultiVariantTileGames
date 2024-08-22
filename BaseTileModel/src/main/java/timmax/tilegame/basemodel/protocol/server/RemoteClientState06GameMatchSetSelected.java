package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState06GameMatchSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

public class RemoteClientState06GameMatchSetSelected<ClientId> extends ClientState06GameMatchSetSelected<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState06GameMatchSetSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    @Override
    public void selectGameType(GameType gameType, Set<IGameMatch> gameMatchXSet) {
        if (gameType == null) {
            getClientStateAutomaton().sendEventOfServer(
                    clientId,
                    new EventOfServer11OpenConnectWithoutUserIdentify()
            );
            return;
        }

        // Done:
        //       1.1. При создании перечня матчей на сервере,
        //            если список не содержит ни одного матча в состоянии "Не начат",
        //            то сервер сам создаёт такой матч.
        //            В этом случае на клиент всегда будет отправляться перечень как минимум с одним матчем.
        //            Т.е. и логика в этом методе уйдёт большей частью в более ранний класс.
        //       1.2. Клиент должен работать только с таким списком, который поступил от сервера
        //            (т.е. не создавать новую запись, а только выбирать).
        //            И только для не игранного матча должна быть доступна возможность редактировать параметры матча.
        //            А для игранного (матч на паузе):
        //            - на клиенте опционально: не давать возможность редактировать в принципе.
        //            - на сервере обязательно: проверять попытку изменить параметры матча и возвращать актуальные
        //                значения на клиент.
        // ToDo:
        //       1.2.1. Однако потом нужно будет вернуться к возможности удалять или как-то скидывать в архив
        //              - начатые, но не оконченные (на паузе) партии.
        //              - начатые и оконченные партии - для возможности ознакомления с ними.

        IGameMatch iGameMatch = null;
        Constructor<? extends IGameMatch> GameMatchConstructor = gameType.getGameMatchConstructor();

        try {
            // Создаём экземпляр модели, ранее выбранного типа.
            // ToDo: Нужно минимизировать количество согласований между классами.
            //       Параметры, которые передаются в newInstance():
            //       1. Перечень параметров согласовывается с перечнем в
            //          GameType :: GameType(...)
            //          в строке
            //          GameMatchConstructor = gameMatchClass.getConstructor(...);
            //          и там-же ниже в строке
            //          iGameMatch = GameMatchConstructor.newInstance(...);
            //       2. Ну в т.ч. это, те-же параметры, которые поступили в executeOnServer().
            // ToDo: Здесь создаётся экземпляр матча и, как видно, ему передаётся идентификатор клиента,
            //       Но это единственный класс в котором этот идентификатор используется.
            //       Нужно сделать так, что-бы идентификатор клиента не использовался-бы, и тогда
            //       для всей иерархии классов EventOfClientХХ... можно будет удалить параметр ClientId для метода
            //       executeOnServer(...). А может и для классов.
            iGameMatch = GameMatchConstructor.newInstance(getClientStateAutomaton(), clientId);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            logger.error("Server cannot create object of model for {} with GameMatchConstructor with specific parameters.", gameType, e);
            System.exit(1);
        }
        gameMatchXSet.add(iGameMatch);

        //  ToDo:   Требует переделки, т.к. второй параметр (gameMatchXSet):
        //          - в принципе не должен поступать в этот метод, т.к. это зависимое значение.
        //          - и соответственно определаять его нужно до вызова этого метода внутри gameType.
        //          Как пример (в части не вычисления внутри метода), видно, что в
        //          RemoteClientState04GameTypeSetSelected :: authorizeUser(String userName, Set<GameType> gameTypeSet)
        //          второй параметр не вычисляется внутри authorizeUser(...). Но он всё-же передаётся в него...
        super.selectGameType(gameType, gameMatchXSet);


        //  ToDo:   Ниже, использовать входящий параметр (здесь это gameMatchX) не рекомендуется, т.к.
        //          в методе super он может быть не принят полностью или в какой-то части, но в целевом экземпляре
        //          (здесь это GameMatchXSet) будет либо принят, либо сформирован свой (здесь это gameMatchX).
        //          Вот его и нужно упаковать в EventOfServer (здесь это EventOfServer61SelectGameMatch) и
        //          отправить клиенту.
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer41SelectGameType(
                        gameType.getGameTypeName(),
                        gameMatchXSet
                                .stream()
                                .map(x -> new GameMatchDto(x.getId(), x.getStatus(), x.getParamsOfModelValueMap()))
                                .collect(Collectors.toSet())
                )
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton getClientStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getClientStateAutomaton());
    }
}
