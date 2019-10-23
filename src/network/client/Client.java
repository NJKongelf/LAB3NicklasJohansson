package network.client;


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket ("localhost", 8000)) {
            OutputStream output = socket.getOutputStream ();
            PrintWriter  writer = new PrintWriter (output, true);
            writer.println ("Hello World - åäö@£$€");
            //String string =;

            InputStream input = socket.getInputStream ();

            //InputStreamReader reader = new InputStreamReader(input);
            //int character = reader.read();  // reads a single character
            BufferedReader reader = new BufferedReader (new InputStreamReader (input));

            while (true) {
                String line = reader.readLine ();    // reads a line of text
                System.out.println (line);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
