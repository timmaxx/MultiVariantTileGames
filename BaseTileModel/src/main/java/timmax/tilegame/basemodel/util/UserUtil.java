package timmax.tilegame.basemodel.util;

import timmax.tilegame.basemodel.credential.User;
import timmax.tilegame.basemodel.dto.BaseDto;
import timmax.tilegame.basemodel.dto.UserDtoPassword;

public class UserUtil {
    private UserUtil() {
    }

    public static BaseDto createBaseDto(UserDtoPassword userDtoPassword) {
        return new BaseDto(userDtoPassword.getId());
    }

    public static UserDtoPassword createUserDtoPassword(String userId, String userPassword) {
        return new UserDtoPassword(userId, userPassword);
    }

    public static User createUser(UserDtoPassword userDtoPassword) {
        return new User(userDtoPassword.getId(), userDtoPassword.getPassword());
    }
}
