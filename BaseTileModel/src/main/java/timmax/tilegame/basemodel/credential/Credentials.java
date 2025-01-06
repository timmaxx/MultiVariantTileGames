package timmax.tilegame.basemodel.credential;

import timmax.tilegame.basemodel.dto.BaseDtoId;
import timmax.tilegame.basemodel.dto.UserDtoIdPassword;
import timmax.tilegame.basemodel.util.UserUtil;

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

    public static boolean isUserAndPasswordCorrect(UserDtoIdPassword userDtoIdPassword) {
        return userSet.contains(UserUtil.createUser(userDtoIdPassword));
    }

    public static User getUserByUserId(BaseDtoId userDtoId) {
        if (userDtoId == null || userDtoId.getId().isEmpty()) {
            return null;
        }
        return userSet
                .stream()
                .filter(user -> UserUtil.equals(user, userDtoId))
                .findAny()
                .orElse(null);
    }
}
