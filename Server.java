package Psych;

import org.jetbrains.annotations.Contract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * Created by Chris Nitta on 10/19/2016.
 */
public class Server {
    public static String login(String username, String password) {
        String serverIP = "localhost";
        int serverPort = 420;

        try {
            Socket socket = new Socket(serverIP, serverPort);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader in = new BufferedReader(isr);

            out.println("LOGIN--" + username + "--" + password);

            String serverMessage = in.readLine();
            return serverMessage;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String register(String username, String password) {
        String serverIP = "localhost";
        int serverPort = 420;

        try {
            Socket socket = new Socket(serverIP, serverPort);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader in = new BufferedReader(isr);

            out.println("CREATENEWUSER--" + username + "--" + password);

            String serverMessage = in.readLine();
            return serverMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String joinGame(String userToken, String gameToken) {
        String serverIP = "localhost";
        int serverPort = 420;

        try {
            Socket socket = new Socket(serverIP, serverPort);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader in = new BufferedReader(isr);

            out.println("JOINGAME--" + userToken + "--" + gameToken);

            String serverMessage = in.readLine();
            return serverMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String startGame(String userToken) {
        String serverIP = "localhost";
        int serverPort = 420;

        try {
            Socket socket = new Socket(serverIP, serverPort);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader in = new BufferedReader(isr);

            out.println("STARTNEWGAME--" + userToken);

            String serverMessage = in.readLine();
            return serverMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String allPlayersHaveJoined() {
        return null;
    }
    public static String playerSuggestion() {
        return null;
    }
    public static String playerChoice() {
        return null;
    }

}