import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;

//public class Main {
//
//    public static void main(String[] args) {
//        String host = "www.lib.ru";
//        Socket socket = new Socket(host, 80);
//        String request = "GET / HTTP/1.0\r\n\r\n";
//        OutputStream os = socket.getOutputStream();
//        os.write(request.getBytes());
//        os.flush();
//
//        InputStream is = socket.getInputStream();
//        int ch;
//        while( (ch=is.read())!= -1)
//            System.out.print((char)ch);
//        socket.close();
//    }
//}
