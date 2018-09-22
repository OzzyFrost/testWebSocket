//import com.mycom.websockets.client.HelloEndpoint;
//import com.mycom.websockets.client.HelloEndpointConfigurator;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;


import javax.websocket.ClientEndpointConfig;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;


//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;



public class SocketsSmokeTest
{
    WebSocketContainer container;
//    private HelloEndpoint endpoint;
    private ClientEndpointConfig config;
    private SSLContext sslContext;


    public SocketsSmokeTest() {

        try
        {
            sslContext = SSLContext.getInstance("SSL");
            System.out.println("Protocol = " + sslContext.getProtocol());
        }
        catch (NoSuchAlgorithmException ex)
        {
//            Logger.getLogger(HelloEndpointConfigurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    //note noSuchAlgorithm is due to SSLContext.getInstance()
    public void setUp() throws NoSuchAlgorithmException
    {
        this.container  = ContainerProvider.getWebSocketContainer();
//        this.endpoint = new HelloEndpoint();


        System.out.println("setting up config");


//        this.config = (ClientEndpointConfig) ClientEndpointConfig.Builder.create().configurator(new HelloEndpointConfigurator()).build().getUserProperties().put("org.apache.websocket.SSL_CONTEXT", sslContext);


        System.out.println("config setup complete");


    }




    public void connectToServer() throws DeploymentException, URISyntaxException, IOException
    {
        System.out.println("Now try to connect to server");
//        this.container.connectToServer(this.endpoint, this.config,  new URI("wss://xxxxxxxxxxxxxxxx"));
        System.out.println("Now try to send message");
//        this.endpoint.sendMessage("Hello from client");
    }
}
