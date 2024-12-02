package timmax.tilegame.basemodel.credential;

import java.util.Set;

public class Credentials {
    private final static Set<User> userSet;

    static {
        userSet = Set.of(
                new User("u1", "1"),
                new User("u2", "2"),
                new User("u3", "3")
        );
    }

    public static boolean isUserAndPasswordCorrect(String userName, String password) {
        return userSet.contains(new User(userName, password));
    }

    public static User getUserByUserName(String userName) {
        return userSet
                .stream()
                .filter(user -> user.getUserName().equals(userName))
                .findAny()
                .orElse(null);
    }
}
