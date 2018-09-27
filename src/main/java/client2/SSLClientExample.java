package client2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class SSLClientExample {
    public static void main(String[] args) throws Exception {

        Map<String,String> httpHeaders = new HashMap<String, String>();

//        uid | username | email | token
//                -----+-----------+----------+----------+------------------
//        2 | testuser1  | testmail | 2d1ea610bc493d76
//        3 | testuser2 | testmail | f5b7c119e858b9f3
//        формат сообщения
//          { "receiver":"2", "message":"helloworld" }
//      регистрация
//        { "account_name": username,"email": email,"password": password }
//       авторизация
//    { "account_name": username,"password": password }

//        httpHeaders.put("Token","36a6908c783ba6e5");
        httpHeaders.put("Token","f5b7c119e858b9f3");
//        httpHeaders.put("Token","2d1ea610bc493d76");

        System.out.println("httpHEADER"+httpHeaders);
//        WebSocketChatClient chatclient = new WebSocketChatClient(new URI("wss://echo.websocket.org:443/"),httpHeaders);
        WebSocketChatClient chatclient = new WebSocketChatClient(new URI("wss://pocketmsg.ru:8888/v1/ws/"),httpHeaders);


//        SSLSocketFactory factory = sslContext.getSocketFactory();//
         SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();


        chatclient.setSocketFactory(factory);

        chatclient.connectBlocking();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = reader.readLine();
            if (line.equals("close")) {
                chatclient.close();
            } else {
                chatclient.send(line);
            }
        }

    }
}
