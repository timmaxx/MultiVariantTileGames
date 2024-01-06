package timmax.tilegame.server.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MultiGameWebSocketServerStarter {
    public static void main(String[] args) throws InterruptedException, IOException {
        int port;
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception ex) {
            port = 8887; // 843 flash policy port
        }
        MultiGameWebSocketServer multiGameWebSocketServer = new MultiGameWebSocketServer(port);
/*
        // Вариант работы с непойманными исключениями, которые могли возникнуть в дочерних потоках-нитях:
        {
            Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
                public void uncaughtException(Thread th, Throwable ex) {
                    System.out.println("Необработанное исключение: " + ex);
                }
            };
            Thread thread = new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                // Делаем что-то
                            } catch (Exception e) {
                                throw new RuntimeException();
                            }
                        }
                    }
            );
            thread.setUncaughtExceptionHandler(h);
            thread.start();
        }
*/
        // Но он не подходит в таком виде, т.к. WebSocketServer не является наследником Thread,
        // в котором определён setUncaughtExceptionHandler.
        // Однако внутри класса WebSocketServer есть вызов setUncaughtExceptionHandler.
        // Это в конструкторе WebSocketWorker(). И там это делается для хотя-бы логирования.
        // А мне бы хотелось, чтобы было не только логирование, но и падение приложения, т.к. нет смысла работать
        // приложению, в протоколе сетевого взаимодействия (MapOfStructOfTransportPackage и его наследники)
        // есть противоречие с фактической структурой пакета (TransportPackage и его наследники).

        // multiGameWebSocketServer.setUncaughtExceptionHandler();
        multiGameWebSocketServer.start();

        BufferedReader sysInBufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String readLine = sysInBufferedReader.readLine();
            if (readLine.equals("exit")) {
                multiGameWebSocketServer.stop(1000);
                break;
            }
        }
    }
}
