import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.HandshakeResponse;


@ClientEndpoint(configurator = MyClientEndpoint.Configurator.class)
public class MyClientEndpoint {


    Session session1;

    public static class Configurator extends javax.websocket.ClientEndpointConfig.Configurator {
        @Override
        public void beforeRequest(Map<String, List<String>> headers) {
            List<String> values = new ArrayList<String>();
            values.add("f5b7c119e858b9f3");
            headers.put("token", values);
//            List<String> values1 = new ArrayList<String>();
//            values1.add("/v1/ws/");
//            headers.put("GET", values1);


            super.beforeRequest(headers);
            System.out.println("beforeRequest ......");
            System.out.println(headers);
        }

        @Override
        public void afterResponse(HandshakeResponse hr) {
            Map<String, List<String>> headers = hr.getHeaders();
//            log.info("headers -> "+headers);
            System.out.println("afterRequest ......");
            System.out.println(headers);
        }

    }


    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to endpoint: " + session.getBasicRemote());
        System.out.println("id session " + session.getId());
        try {
//            String name = "ozzyk";
//            String name = "{ \"receiver\":\"2\", \"message\":\"helloworld\" }" ;
            String name = "";

            System.out.println("Sending message to endpoint: " + name);
            session.getBasicRemote().sendText(name);
            session1 = session;
            System.out.println(session1.getRequestURI());

        } catch (IOException ex) {
            ex.printStackTrace();
//            Logger.getLogger(MyClientEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @OnMessage
    public void processMessage(String message) {
        System.out.println("Received message in client: " + message);
        Client.messageLatch.countDown();
    }

    @OnError
    public void processError(Throwable t) {
        t.printStackTrace();
    }

    public void sendMSG(String mess) {
        try {
            session1.getBasicRemote().sendText(mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
