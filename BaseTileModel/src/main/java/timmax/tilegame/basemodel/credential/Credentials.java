package timmax.tilegame.basemodel.credential;

import java.util.Map;

public class Credentials {
    private final static Map<String, String> mapOfCredential;

    static {
        mapOfCredential = Map.of(
                "u1", "1",
                "u2", "2",
                "u3", "3"
        );
    }

    public static boolean isUserAndPasswordCorrect(String userName, String password) {
        return mapOfCredential.containsKey(userName) && mapOfCredential.get(userName).equals(password);
    }
}