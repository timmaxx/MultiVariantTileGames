package timmax.tilegame.basemodel.util;

import timmax.tilegame.basemodel.credential.User;
import timmax.tilegame.basemodel.dto.UserDtoId;
import timmax.tilegame.basemodel.dto.UserDtoIdPassword;

public class UserUtil {
    private UserUtil() {
    }

    public static UserDtoId createUserDtoId(User user) {
        return new UserDtoId(user.getId());
    }

    public static UserDtoId createUserDtoId(UserDtoIdPassword userDtoIdPassword) {
        return new UserDtoId(userDtoIdPassword.getId());
    }

    public static UserDtoIdPassword createUserDtoIdPassword(String userId, String userPassword) {
        return new UserDtoIdPassword(userId, userPassword);
    }

    public static User createUser(UserDtoIdPassword userDtoIdPassword) {
        return new User(userDtoIdPassword.getId(), userDtoIdPassword.getPassword());
    }

    public static boolean equals(User user, UserDtoId userDtoId) {
        return user.getId().equals(userDtoId.getId());
    }

    public static boolean equals(UserDtoId userDtoId, User user) {
        return equals(user, userDtoId);
    }
}
