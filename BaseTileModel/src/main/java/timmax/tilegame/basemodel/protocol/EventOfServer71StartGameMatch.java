package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.dto.GameMatchExtendedDto;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

//  Событие сервера с расширенным DTO матча - матч стартовал.
public class EventOfServer71StartGameMatch extends EventOfServer {
    //  ToDo:   Исправить тип передаваемых данных:
    //          1. Должен быть один и тот-же базовый тип.
    //          2. Должен быть DTO (может один и тот-же, но может быть и разный).
    //          В классах EventOfClient71StartGameMatch и EventOfServer71StartGameMatch разные типы для передачи
    //          используются.
    private GameMatchExtendedDto gameMatchExtendedDto;

    public EventOfServer71StartGameMatch() {
        super();
    }

    public EventOfServer71StartGameMatch(GameMatchExtendedDto gameMatchExtendedDto) {
        this();
        this.gameMatchExtendedDto = gameMatchExtendedDto;
    }

    // class EventOfServer
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.startGameMatch(gameMatchExtendedDto);
    }

    // class Object
    @Override
    public String toString() {
        return
                EventOfServer71StartGameMatch.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                        "gameMatchExtendedDto=" + gameMatchExtendedDto +
                        '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(gameMatchExtendedDto);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        gameMatchExtendedDto = (GameMatchExtendedDto) in.readObject();
    }
}
