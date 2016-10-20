package Psych;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * Created by Chris Nitta on 10/19/2016.
 */
public class Server {
    public static void main(String[] args) {
        String serverIP = "localhost";
        int serverPort = 420;

        try {
            Socket socket = new Socket(serverIP,serverPort);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader in = new BufferedReader(isr);

            out.println("message to server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
