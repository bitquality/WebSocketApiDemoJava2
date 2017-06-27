
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.glassfish.tyrus.server.Server;

/**
 * Created by gopim on 2017-06-25.
 */

public class MyServer {

    public static void main(String[] args) {
        runServer();
    }

    public static void runServer() {
        Server server;
        server = new Server("localhost", 8025, "/websockets", MyServerEndpoint.class);

        try {
            server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Please press a key to stop the server.");
            reader.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }
}