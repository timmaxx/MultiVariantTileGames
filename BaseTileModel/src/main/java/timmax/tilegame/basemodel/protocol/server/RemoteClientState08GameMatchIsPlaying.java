package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

import java.util.Map;

public class RemoteClientState08GameMatchIsPlaying<ClientId> extends ClientState08GameMatchIsPlaying<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState08GameMatchIsPlaying(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    // ToDo: Удалить после реализации void startGameMatch(...).
    @Override
    public void setParamsOfModelValueMapOfGameMatchAndStart(Map<String, Integer> paramsOfModelValueMap) {
        // ToDo: Внести изменения в событие, отправляемые при старте / возбоновлении игры. А именно:
        //       отправлять не только ширину и высоту, но и все плитки, отличающиеся от умолчательного значения.
        //       Тогда отпралять нужно будет не getGameMatchX().getParamsOfModelValueMap() (т.е. только параметры),
        //       весь getGameMatchX() (т.е. параметры, все плитки, отличающиеся от умолчательных, и для
        //       многопользовательских игр - признак хода определённого игрока).
        //       Сейчас EventOfServer71StartGameMatch отправляет только ширину и высоту.
        //
        //       Структура метода и имена вызываемых методов отличаются от других подобных в классах
        //       RemoteClientState0X..., а именно в тех методах такая структура:
        //       1. Что-то вычисляется для следующего шага.
        //       2. super.ЭтотМетод(ЭтиЖеПараметры).
        //       3. getClientStateAutomaton().sendEventOfServer(clientId, new EventOfServer0X...()).
        //       Здесь имя класса
        //       ...GameMatchIsPlaying
        //       не соответствует именам методов
        //       - setParamsOfModelValueMapOfGameMatchAndStart(...) и
        //       - start().
        //       И здесь ещё вызывается "getClientStateAutomaton().getGameMatchX().start()".
        //       Так здесь сделано потому, что EventOfServer71StartGameMatch несёт в себе width и height,
        //       а в start() будет отправлено множество игровых событий:
        //       - одниплиточных - для отрисовки в главной выборке,
        //       - текстовых для отрисовки в других выборках сопроводительную информацию (очки, и прочая промежуточная статистика)

        // В метод передаётся, поступившая от клиента мапа с параметрами матча, но внутри метода будет проверка на
        // соответствие: если мапа будет признана неподходящей, то внутри матча будет сформирована мапа с другим содержанием.
        super.setParamsOfModelValueMapOfGameMatchAndStart(paramsOfModelValueMap);

        // Именно из-за того, что мапа могла быть сформирована не такая, какая пришла, клиенту будет отправлена мапа,
        // которая была сформирована при вызове предыдущего метода.
        // Клиент, после получения этого события только строит главную выборку (пустую доску).
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer71StartGameMatch(getGameMatchX().getParamsOfModelValueMap())
        );

        // Метод вызывается для модели, для которой ранее ещё не был вызван start().
        // Сервер отправляет клиенту одноплиточные игровые события - расстановку игровых объектов.
        getClientStateAutomaton().getGameMatchX().start();
    }

    @Override
    public void startGameMatch(GameMatchExtendedDto gameMatchExtendedDto) {
        //  ToDo:   Внести изменения в событие, отправляемые при старте / возбоновлении игры. А именно:
        //          отправлять не только ширину и высоту, но и все плитки, отличающиеся от умолчательного значения.
        //          Тогда отпралять нужно будет не getGameMatchX().getParamsOfModelValueMap() (т.е. только параметры),
        //          а весь getGameMatchX() (т.е. параметры, все плитки, отличающиеся от умолчательных, и для
        //          многопользовательских игр - признак хода определённого игрока).
        //          Сейчас EventOfServer71StartGameMatch отправляет только ширину и высоту.

        // В метод передаётся, поступившая от клиента информация (параметры матча, текущее положения объектов),
        // но внутри метода будет проверка на соответствие: если информация будет признана неподходящей,
        // то внутри матча будет сформирована информация с другим содержанием.
        super.startGameMatch(gameMatchExtendedDto);

        //  ToDo:   Ниже, использовать входящий параметр (здесь это gameMatchExtendedDto) не рекомендуется, т.к.
        //          в методе super он может быть не принят полностью или в какой-то части, но в целевом экземпляре
        //          (здесь это GameMatch) будет либо принят, либо сформирован свой (здесь это gameMatchExtendedDto).
        //          Вот его и нужно упаковать в EventOfServer (здесь это EventOfServer74StartGameMatch) и
        //          отправить клиенту.
        //  Именно из-за того, что информация могла быть сформирована не такая, какая пришла,
        //  клиенту будет отправлена информация, которая была сформирована при вызове предыдущего метода.
        //  (Клиент, после получения этого события только строит главную выборку (пустую доску)).
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer74StartGameMatch(gameMatchExtendedDto) // GameMatchExtendedDto gameMatchExtendedDto
        );
    }

    @Override
    public void resumeGameMatch() {
        super.resumeGameMatch();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer73ResumeGameMatch()
                // new EventOfServer73ResumeGameMatch(getGameMatchX().getParamsOfModelValueMap())
        );

        // ToDo: Вызов этого метода для модели:
        //       - у которой был ранее вызов start(), потом было хотя-бы одно игровое событие, но потом она была поставлена на паузу.
        getClientStateAutomaton().getGameMatchX().resume();
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton getClientStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getClientStateAutomaton());
    }
}
