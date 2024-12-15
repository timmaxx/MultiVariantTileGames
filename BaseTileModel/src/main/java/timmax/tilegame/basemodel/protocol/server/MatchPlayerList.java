package timmax.tilegame.basemodel.protocol.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timmax.tilegame.basemodel.credential.User;

import java.util.ArrayList;
import java.util.Objects;

//  ToDo:   Можно было-бы сделать его implements Iterable (что-бы foreach циклы использовать)
//  Список игроков для матча
public class MatchPlayerList {
    protected static final Logger logger = LoggerFactory.getLogger(MatchPlayerList.class);

    final private int countOfPlayers;

    private final ArrayList<User> userArrayList;
    private boolean isReadOnly;

    public MatchPlayerList(int countOfPlayers) {
        if (countOfPlayers < 1 || countOfPlayers > 2) {
            throw new RuntimeException("countOfPlayers must be > 0 and < 3.");
        }
        userArrayList = new ArrayList<>(countOfPlayers);
        for (int i = 0; i < countOfPlayers; i++) {
            userArrayList.add(null);
        }
        isReadOnly = false;
        this.countOfPlayers = countOfPlayers;
    }

    public void setPlayer(int indexOfPlayer, User user) {
        if (isReadOnly) {
            logger.error("You cannot invoke setPlayer() because isReadOnly = true.");
            throw new RuntimeException("You cannot invoke setPlayer() because isReadOnly = true.");
        }
        if (indexOfPlayer < 0 || indexOfPlayer >= countOfPlayers) {
            logger.error("indexOfPlayer must be > 0 and <= countOfPlayers.");
            throw new RuntimeException("indexOfPlayer must be > 0 and <= countOfPlayers.");
        }
        for (int i = 0; i < userArrayList.size(); i++) {
            if (i == indexOfPlayer) {
                continue;
            }
            if (userArrayList.get(i).equals(user)) {
                userArrayList.set(i, null);
            }
        }
        userArrayList.set(indexOfPlayer, user);
    }

    public User get(int indexOfPlayer) {
        return userArrayList.get(indexOfPlayer);
    }

    public boolean isNotFull() {
        return userArrayList.size() != countOfPlayers ||
                userArrayList
                        .stream()
                        .anyMatch(Objects::isNull)
                ;
    }

    public void setReadOnlyTrue() {
        if (isNotFull()) {
            logger.error("You cannot invoke setReadOnlyTrue() because isFull() = false.");
            throw new RuntimeException("You cannot invoke setReadOnlyTrue() because isFull() = false.");
        }
        isReadOnly = true;
    }

    public int size() {
        return userArrayList.size();
    }
}
