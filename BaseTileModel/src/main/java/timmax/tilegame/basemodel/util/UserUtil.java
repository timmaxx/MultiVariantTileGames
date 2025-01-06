package timmax.tilegame.basemodel.util;

import timmax.tilegame.basemodel.credential.User;
import timmax.tilegame.basemodel.dto.BaseDtoId;
import timmax.tilegame.basemodel.dto.UserDtoPassword;

public class UserUtil {
    private UserUtil() {
    }

    public static BaseDtoId createUserDtoId(User user) {
        return new BaseDtoId(user.getId());
    }

    public static BaseDtoId createUserDtoId(UserDtoPassword userDtoPassword) {
        return new BaseDtoId(userDtoPassword.getId());
    }

    public static UserDtoPassword createUserDtoIdPassword(String userId, String userPassword) {
        return new UserDtoPassword(userId, userPassword);
    }

    public static User createUser(UserDtoPassword userDtoPassword) {
        return new User(userDtoPassword.getId(), userDtoPassword.getPassword());
    }

    public static boolean equals(User user, BaseDtoId userDtoId) {
        return user.getId().equals(userDtoId.getId());
    }

    public static boolean equals(BaseDtoId userDtoId, User user) {
        return equals(user, userDtoId);
    }
}
