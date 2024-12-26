package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.baseview.View;

//  Событие сервера с игровым событием для определённого типа выборки.
public class EventOfServer92GameEvent extends EventOfServer {
    private String viewName;
    private GameEvent gameEvent;

    public EventOfServer92GameEvent() {
        super();
    }

    public EventOfServer92GameEvent(String viewName, GameEvent gameEvent) {
        this();
        this.viewName = viewName;
        this.gameEvent = gameEvent;
    }

    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        //  ToDo:   Все поступающие события от сервера, желательно записывать сначала в какую-то очередь,
        //          а извлекать из этой очереди нужно тогда, когда все предыдущие события уже будут обработаны.
        //          Для клиента можно делать разные очереди для выборок.
        //          Для сервера нужно делать разные очереди для отдельных клиентов.
        //          И вероятно нужно быть уверенным, что события поступили в правильной последовательности.
        //          Ну а т.к. очереди нет, то сейчас так генерируется событие этого типа (92GameEvent) сервером:
        //          1. от клиента поступает сообщение 71SetGameMatchIsPlaying
        //             с возможно какими-то кастомными параметрами для матча,
        //          2. сервер:
        //          2.1. переводит состояние матча в состояние 08GameMatchIsPlaying
        //               и отправляет клиенту событие типа 71SetGameMatchIsPlaying,
        //          2.2. отправляет клиенту одно или несколько игровых событий типа 92GameEvent.
        //               Каждое из таких игровых событий напраляется для конкретной выборки.
        //          3. клиент, получив событие типа 71SelecttGameMatchIsPlaying (2.1),
        //             должен построить одну или несколько выборок, в которых и будут потом отображаться игровые события.
        //          В этом коде 'localClientStateAutomaton.getView(viewName)' может вернуть null.
        //          Так может получиться, если сервер успевает отправить клиенту событие типа 92GameEvent (п. 2.2)
        //          (и клиент его принимает), но клиент ещё не успел создать выборку, для которой поступило событие
        //          (т.е. не выполнил п. 3).
        //          Поэтому сейчас, если 'localClientStateAutomaton.getView(viewName == null',
        //          то немного ждём в этом потоке, пока другой поток (обрабатывающий предыдущее событие),
        //          не создаст выборки.
        try {
            View view;
            while ((view = localClientStateAutomaton.getView(viewName)) == null) {
                //  Warning:(48, 24) Call to 'Thread.sleep()' in a loop, probably busy-waiting
                Thread.sleep(100);
            }
            view.update(gameEvent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "viewName='" + viewName + '\'' +
                ", gameEvent=" + gameEvent +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(viewName);
        out.writeObject(gameEvent);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        viewName = (String) in.readObject();
        gameEvent = (GameEvent) in.readObject();
    }
}
