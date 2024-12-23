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

    public static boolean isUserAndPasswordCorrect(String userId, String userPassword) {
        return userSet.contains(new User(userId, userPassword));
    }

    public static User getUserByUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            return null;
        }
        return userSet
                .stream()
                .filter(user -> user.getId().equals(userId))
                .findAny()
                .orElse(null);
    }
}
