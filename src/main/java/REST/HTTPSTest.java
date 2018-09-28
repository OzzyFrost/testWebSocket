package REST;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;


public class HTTPSTest {

    public static void main(String[] args) {
       HTTPSTest httpsTest =  new HTTPSTest();

//        try {
//            httpsTest.sendGet();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            httpsTest.sendPut();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            httpsTest.sendGetPut();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    private void testIt() {

        String https_url = "https://www.google.com/";
        URL url;
        try {

            url = new URL(https_url);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

//            //dumpl all cert info
//            print_https_cert(con);
//            //dump all the content
//            print_content(con);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void print_https_cert(HttpsURLConnection con) {

        if (con != null) {

            try {

                System.out.println("Response Code : " + con.getResponseCode());
                System.out.println("Cipher Suite : " + con.getCipherSuite());
                System.out.println("\n");

                Certificate[] certs = con.getServerCertificates();
                for (Certificate cert : certs) {
                    System.out.println("Cert Type : " + cert.getType());
                    System.out.println("Cert Hash Code : " + cert.hashCode());
                    System.out.println("Cert Public Key Algorithm : "
                            + cert.getPublicKey().getAlgorithm());
                    System.out.println("Cert Public Key Format : "
                            + cert.getPublicKey().getFormat());
                    System.out.println("\n");
                }

            } catch (SSLPeerUnverifiedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void print_content(HttpsURLConnection con) {
        if (con != null) {

            try {

                System.out.println("****** Content of the URL ********");
                BufferedReader br =
                        new BufferedReader(
                                new InputStreamReader(con.getInputStream()));

                String input;

                while ((input = br.readLine()) != null) {
                    System.out.println(input);
                }
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    // HTTP GET request
    private void sendGet() throws Exception {

//        String url = "http://www.google.com/search?q=mkyong";
//
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        String https_url = "https://www.google.com/";
        URL url;
        url = new URL(https_url);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
//        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    // HTTP POST request
    private void sendPost() throws Exception {
//        {
//            "account_name": username,
//                "email": email,
//                "password": password
//        }
//        String url = "https://selfsolve.apple.com/wcResults.do";
        String url = "https://pocketmsg.ru:8888/v1/users/";

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
//        con.setRequestProperty("User-Agent", USER_AGENT);
//        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "{" +
                "\"account_name\": \"OzzyFrost\"," +
                "\"email\": \"5kla@mail.ru\"," +
                "\"password\": \"12345\"" +
                "}";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    private void sendPut() throws Exception {
//        {
//            "account_name": username,
//                "email": email,
//                "password": password
//        }
//        String url = "https://selfsolve.apple.com/wcResults.do";
//        String url = "https://pocketmsg.ru:8888/v1/auth/";
        String url = "https://pocketmsg.ru:8888/v1/users/";

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("PUT");
        con.setRequestProperty("Token", "3c4e0e034757a628");
//        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

//        запрос на авторизацию
//        String urlParameters = "{" +
//                "\"user\": \"OzzyFrost\"," +
//                "\"password\": \"12345\"" +
//                "}";
//        формат запроса на добавление контакта
//        header: token
//        {
//            "contact":e-mail
//        }
        String urlParameters = "{" +
                "\"contact\": \"email@email.com\"" +
                "}";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'PUT' request to URL : " + url);
        System.out.println("Put parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    private void sendGetPut() throws Exception {
        String urlParameters = "{" +
                "\"account_name\": \"OzzyFrost\"," +
                "\"password\": \"12345\"" +
                "}";

        String https_url = "https://pocketmsg.ru:8888/v1/auth/";
        URL url;
        url = new URL(https_url);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        // optional default is GET
        con.setDoOutput(true);
        con.setRequestMethod("PUT");
        OutputStreamWriter out = new OutputStreamWriter(
                con.getOutputStream());
        out.write(urlParameters);
        out.close();
        //add request header
//        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }
}
