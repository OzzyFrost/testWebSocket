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



@ClientEndpoint(configurator = MyClientEndpoint.Configurator.class)
public class MyClientEndpoint{


    Session session1;
    public static class Configurator extends javax.websocket.ClientEndpointConfig.Configurator {
        @Override
        public void beforeRequest(Map<String,List<String>> headers)
        {
            List<String> values = new ArrayList<String>();
            values.add("f5b7c119e858b9f3");
            headers.put("token", values);
            super.beforeRequest(headers);
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
            session1 = session;
//            for (int i = 0; i <1 ; i++) {}
//                String mess = sc.nextLine();
//                session.getBasicRemote().sendText(mess+" "+1);
//            mess = sc.nextLine();
//            session.getBasicRemote().sendText(mess+" "+2);
//            mess = sc.nextLine();
//            session.getBasicRemote().sendText(mess+" "+3);



        } catch (IOException ex) {
            ex.printStackTrace();
//            Logger.getLogger(MyClientEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @OnMessage
    public void processMessage(String message) {
        System.out.println("Received message in client: " + message);
        Client.messageLatch.countDown();
//        return "dfdf"+message;
    }

    @OnError
    public void processError(Throwable t) {
        t.printStackTrace();
    }

    public void sendMSG(String mess){
        try {
            session1.getBasicRemote().sendText(mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
