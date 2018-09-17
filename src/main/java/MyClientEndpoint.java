import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;


@ClientEndpoint(configurator = MyClientEndpoint.Configurator.class)
public class MyClientEndpoint{
    public static class Configurator extends javax.websocket.ClientEndpointConfig.Configurator {
        @Override
        public void beforeRequest(Map<String,List<String>> headers)
        {
            List<String> values = new ArrayList<String>();
            values.add("2d1ea610bc493d76");
            headers.put("token", values);
        }
    }


    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to endpoint: " + session.getBasicRemote());
        try {
//            String name = "ozzyk";
            String name = "{ \"receiver\":\"2\", \"message\":\"helloworld\" }" ;

            System.out.println("Sending message to endpoint: " + name);
            session.getBasicRemote().sendText(name);
        } catch (IOException ex) {
            Logger.getLogger(MyClientEndpoint.class.getName()).log(Level.SEVERE, null, ex);
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

}
