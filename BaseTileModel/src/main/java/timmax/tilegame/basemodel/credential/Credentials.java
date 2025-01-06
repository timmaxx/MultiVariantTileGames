package timmax.tilegame.basemodel.credential;

import timmax.tilegame.basemodel.dto.BaseDto;
import timmax.tilegame.basemodel.dto.UserDtoPassword;
import timmax.tilegame.basemodel.util.BaseUtil;
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

    public static boolean isUserAndPasswordCorrect(UserDtoPassword userDtoPassword) {
        return userSet.contains(UserUtil.createUser(userDtoPassword));
    }

    public static User getUserByUserId(BaseDto userDto) {
        if (userDto == null || userDto.isIdNullOrEmpty()) {
            return null;
        }
        return userSet
                .stream()
                .filter(user -> BaseUtil.equals(user, userDto))
                .findAny()
                .orElse(null);
    }
}
