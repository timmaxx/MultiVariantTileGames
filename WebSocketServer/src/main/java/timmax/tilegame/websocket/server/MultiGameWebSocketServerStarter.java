package timmax.tilegame.websocket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MultiGameWebSocketServerStarter {
    public static void main( String[ ] args) throws InterruptedException, IOException {
        int port;
        try {
            port = Integer.parseInt( args[ 0]);
        } catch ( Exception ex) {
            port = 8887; // 843 flash policy port
        }
        MultiGameWebSocketServer multiGameWebSocketServer = new MultiGameWebSocketServer( port);
        multiGameWebSocketServer.start( );

        BufferedReader sysInBufferedReader = new BufferedReader( new InputStreamReader( System.in));
        while ( true) {
            String readLine = sysInBufferedReader.readLine( );
            multiGameWebSocketServer.broadcast( readLine);
            if ( readLine.equals( "exit")) {
                multiGameWebSocketServer.stop( 1000);
                break;
            }
        }
    }
}