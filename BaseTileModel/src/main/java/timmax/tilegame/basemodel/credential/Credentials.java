package timmax.tilegame.basemodel.credential;

import java.util.HashMap;
import java.util.Map;

import static timmax.tilegame.basemodel.credential.ResultOfCredential.AUTHORISED;
import static timmax.tilegame.basemodel.credential.ResultOfCredential.NOT_AUTHORISED;

public class Credentials {
    private final static Map< String, String> mapOfCredential;

    static {
        mapOfCredential = new HashMap< >( );
        mapOfCredential.put( "u1", "1");
        mapOfCredential.put( "u2", "2");
        mapOfCredential.put( "u3", "3");
    }

    public static ResultOfCredential verifyUserAndPassword( String userName, String password) {
        if ( !mapOfCredential.containsKey( userName)) {
            return NOT_AUTHORISED;
        }
        if ( !mapOfCredential.get( userName).equals( password)) {
            return NOT_AUTHORISED;
        }
        return AUTHORISED;
    }
}