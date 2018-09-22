package sslClient1;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;



public class SSLClient {

    private static final int HTTPS_PORT = 8888;
    public static void main(String args[]) {
//        if (args.length == 0) {
//            System.err.println
//                    ("Please provide a hostname to connect to");
//            System.exit(-1);
//        }
//        String hostname = args[0];
        String hostname = "pocketmsg.ru";
        try {
            // Get the default SSL socket factory
            SocketFactory factory = SSLSocketFactory.getDefault();
            // Using socket factory, get SSL socket to port on host
            Socket socket = factory.createSocket(hostname, HTTPS_PORT);
            // Send request to get root
            // Be sure to end string with two sets of carriage
            // return - newline characters.
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream);

            String getMess ="GET /v1/ws/ HTTP/1.1\r\n\r\n"+
                    "Host: pocketmsg.ru:8888\r\n" +
                    "Upgrade: websocket\r\n" +
                    "Connection: Upgrade\r\n" +
                    "Origin: wss://pocketmsg.ru:8888\r\n" +
                    "Sec-Websocket-Key: +2xrMQJHrDi7d2krCT8AUQ==\r\n" +
                    "Sec-Websocket-Version: 13\r\n" +
                    "token: 36a6908c783ba6e5";


            out.print(getMess);
            out.flush();
            // Fetch response
            InputStream inputStream =
                    socket.getInputStream();
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream);
            BufferedReader in =
                    new BufferedReader(inputStreamReader);
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
           // Close everything
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Problems talking to " + hostname);
            e.printStackTrace();
        }
    }
}
