package timmax.tilegame.websocket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameServerWebSocketStarter {
    public static void main( String[ ] args) throws InterruptedException, IOException {
        int port;
        try {
            port = Integer.parseInt( args[ 0]);
        } catch (Exception ex) {
            port = 8887; // 843 flash policy port
        }
        GameServerWebSocket gameServerWebSocket = new GameServerWebSocket( port);
        gameServerWebSocket.start( );
        System.out.println( "GameServerWebSocket started on port: " + gameServerWebSocket.getPort( ));

        BufferedReader sysInBufferedReader = new BufferedReader( new InputStreamReader( System.in));
        while ( true) {
            String readLine = sysInBufferedReader.readLine( );
            gameServerWebSocket.broadcast( readLine);
            if ( readLine.equals( "exit")) {
                gameServerWebSocket.stop( 1000);
                break;
            }
        }
    }
}