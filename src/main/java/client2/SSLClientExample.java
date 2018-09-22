package client2;

import sun.security.ssl.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class SSLClientExample {

    /*
     * Keystore with certificate created like so (in JKS format):
     *
     *keytool -genkey -keyalg RSA -validity 3650 -keystore "keystore.jks" -storepass "storepassword" -keypass "keypassword" -alias "default" -dname "CN=127.0.0.1, OU=MyOrgUnit, O=MyOrg, L=MyCity, S=MyRegion, C=MyCountry"
     */
    public static void main(String[] args) throws Exception {
        WebSocketChatClient chatclient = new WebSocketChatClient(new URI("wss://pocketmsg.ru:8888/v1/ws/"));

        // load up the key store
//        String STORETYPE = "JKS";
//        String KEYSTORE = "keystore.jks";
//        String STOREPASSWORD = "storepassword";
//        String KEYPASSWORD = "keypassword";

//        KeyStore ks = KeyStore.getInstance( STORETYPE );
//        File kf = new File( KEYSTORE );
//        ks.load( new FileInputStream( kf ), STOREPASSWORD.toCharArray() );
//
//        KeyManagerFactory kmf = KeyManagerFactory.getInstance( "SunX509" );
//        kmf.init( ks, KEYPASSWORD.toCharArray() );
//        TrustManagerFactory tmf = TrustManagerFactory.getInstance( "SunX509" );
//        tmf.init( ks );

        SSLContext sslContext = null;
        sslContext = SSLContext.getInstance("TLS");

//        sslContext.init( kmf.getKeyManagers(), tmf.getTrustManagers(), null );
        // sslContext.init( null, null, null ); // will use java's default key and trust store which is sufficient unless you deal with self-signed certificates

        sslContext.init(null, null, null);

        SSLSocketFactory factory = sslContext.getSocketFactory();// (SSLSocketFactory) SSLSocketFactory.getDefault();

//        вот тут ошибка пишет нет такоо метода, в мавене проиасл что Вы написали, но не помогло
//        в чем может быть моя ошибка?
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
