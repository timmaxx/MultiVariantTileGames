package timmax.tilegame.basemodel.util;

import timmax.tilegame.basemodel.credential.User;
import timmax.tilegame.basemodel.dto.BaseDtoId;
import timmax.tilegame.basemodel.dto.UserDtoIdPassword;

public class UserUtil {
    private UserUtil() {
    }

    public static BaseDtoId createUserDtoId(User user) {
        return new BaseDtoId(user.getId());
    }

    public static BaseDtoId createUserDtoId(UserDtoIdPassword userDtoIdPassword) {
        return new BaseDtoId(userDtoIdPassword.getId());
    }

    public static UserDtoIdPassword createUserDtoIdPassword(String userId, String userPassword) {
        return new UserDtoIdPassword(userId, userPassword);
    }

    public static User createUser(UserDtoIdPassword userDtoIdPassword) {
        return new User(userDtoIdPassword.getId(), userDtoIdPassword.getPassword());
    }

    public static boolean equals(User user, BaseDtoId userDtoId) {
        return user.getId().equals(userDtoId.getId());
    }

    public static boolean equals(BaseDtoId userDtoId, User user) {
        return equals(user, userDtoId);
    }
}
