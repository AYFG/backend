import java.io.*;
import java.net.*;

public class RawHttpClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("example.com", 80);

        String request = "GET / HTTP/1.1\r\n"
                        + "Host: example.com\r\n"
                        + "Connection: close\r\n"
                        + "\r\n";
        OutputStream out = socket.getOutputStream();
        out.write(request.getBytes());
        out.flush();

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(socket.getInputStream())
        );
        
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        socket.close();
    }
}