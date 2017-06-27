import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

/**
 * Created by gopim on 2017-06-25.
 */

@ClientEndpoint
public class MyClientEndpoint {

    private static CountDownLatch latch;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @OnOpen
    public void onOpen(Session session) {
        // same as above
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        // same as above
        return "Message from Client";
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s close because of %s", session.getId(), closeReason));
        latch.countDown();
    }

    public static void main(String[] args) throws DeploymentException, URISyntaxException, InterruptedException {
        latch = new CountDownLatch(1);

        ClientManager client = ClientManager.createClient();
        client.connectToServer(MyClientEndpoint.class, new URI("ws://localhost:8025/websockets/SmartServer"));
        latch.await();

    }
}