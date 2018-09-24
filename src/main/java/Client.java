//import org.glassfish.tyrus.core.websocket.ContainerProvider;
//import org.glassfish.tyrus.websockets.WebSocket;

import java.io.IOException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.websocket.*;
import javax.net.*;

public class Client {

    static Scanner sc = new Scanner(System.in);
    final static CountDownLatch messageLatch = new CountDownLatch(2);
    static MyClientEndpoint mce = new MyClientEndpoint();

    private ClientEndpointConfig config;
    private static SSLContext sslContext;

    public static void main(String[] args) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            try
            {
                sslContext = SSLContext.getInstance("SSL");
                System.out.println("Protocol = " + sslContext.getProtocol());
            }
            catch (NoSuchAlgorithmException ex)
            {}
//            String uri = "wss://echo.websocket.org:80/";
//            String uri = "ws://pocketmsg.ru:8888/v1/auth/";
            String uri = "wss://pocketmsg.ru:8888/v1/ws/";
//            String uri = "wss://185.212.148.78:8888/v1/ws/";
//            String uri = "wss://pocketmsg.ru:8888/";
//            String uri = "wss://pocketmsg.ru/v1/ws";
//            String uri = "ws://127.0.0.1:8080/WebSocketServer/endpoint";

            System.out.println("Connecting to " + uri);
//            ClientEndpointConfig config = ClientEndpointConfig.Builder.create().build();

            // container.connectToServer(MyClientEndpoint.class , URI.create(uri));
            URI uri1= URI.create(uri);

//            Session session= container.connectToServer(mce, uri1);
            Session session= container.connectToServer(mce, uri1);
            System.out.println(" ses uri "+session.getRequestURI());
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
