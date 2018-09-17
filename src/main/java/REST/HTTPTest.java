package REST;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPTest {
    public static void main(String[] args) {
//        System.out.println(executePost("http://www.lib.ru:80","GET","/ HTTP/1.0\\r\\n\\r\\n"));

        String mess ="{ \"account_name\": \"username\", \"email\": \"email@email.com\",\"password\": \"password\" }";
        System.out.println(executePost("https://pocketmsg.ru:8888/v1/users","POST",mess));

//        System.out.println(executePost("https://pocketmsg.ru:8888","GET","/ HTTP/1.0\\r\\n\\r\\n"));
    }

    public static String executePost(String targetURL,String method, String urlParameters) {
        HttpURLConnection connection = null;

        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length",
                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
