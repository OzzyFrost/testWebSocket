import org.glassfish.tyrus.websockets.WebSocket;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.*;

public class Client {

    static Scanner sc = new Scanner(System.in);
    final static CountDownLatch messageLatch = new CountDownLatch(7);
    static MyClientEndpoint mce = new MyClientEndpoint();

    public static void main(String[] args) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();

            String uri = "wss://echo.websocket.org:80/";
//            String uri = "ws://pocketmsg.ru:8888/v1/auth/";
//            String uri = "wss://pocketmsg.ru:8888/v1/ws/";
//            String uri = "wss://pocketmsg.ru/v1/ws";
//            String uri = "ws://127.0.0.1:8080/WebSocketServer/endpoint";

            System.out.println("Connecting to " + uri);

//            container.connectToServer(MyClientEndpoint.class , URI.create(uri));
            container.connectToServer(mce, URI.create(uri));

            String msg;
            do {
                msg = sc.nextLine();
                mce.sendMSG(msg);
            } while (!msg.equals("end"));


            messageLatch.await(100, TimeUnit.SECONDS);
        } catch (DeploymentException | InterruptedException | IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
