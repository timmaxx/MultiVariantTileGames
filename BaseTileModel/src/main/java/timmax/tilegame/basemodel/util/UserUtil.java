package timmax.tilegame.basemodel.util;

import timmax.tilegame.basemodel.credential.User;
import timmax.tilegame.basemodel.dto.BaseDto;
import timmax.tilegame.basemodel.dto.UserDtoPassword;

public class UserUtil {
    private UserUtil() {
    }

    public static BaseDto createUserDtoId(User user) {
        return new BaseDto(user.getId());
    }

    public static BaseDto createUserDtoId(UserDtoPassword userDtoPassword) {
        return new BaseDto(userDtoPassword.getId());
    }

    public static UserDtoPassword createUserDtoIdPassword(String userId, String userPassword) {
        return new UserDtoPassword(userId, userPassword);
    }

    public static User createUser(UserDtoPassword userDtoPassword) {
        return new User(userDtoPassword.getId(), userDtoPassword.getPassword());
    }

    public static boolean equals(User user, BaseDto userDtoId) {
        return user.getId().equals(userDtoId.getId());
    }

    public static boolean equals(BaseDto userDtoId, User user) {
        return equals(user, userDtoId);
    }
}
