package Psych;

import org.jetbrains.annotations.Contract;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * Created by Chris Nitta on 10/19/2016.
 */
public class Server {
    String serverIP = "localhost";
    int serverPort = 420;
    Socket socket;
    PrintWriter out;
    InputStreamReader isr;
    BufferedReader in;


    public Server() {

        try {
            socket = new Socket(serverIP, serverPort);

            out = new PrintWriter(socket.getOutputStream(), true);

            isr = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(isr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String login(String username, String password) {
        try {
            out.println("LOGIN--" + username + "--" + password);

            String serverMessage = in.readLine();
            return serverMessage;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String register(String username, String password) {
        try {
            out.println("CREATENEWUSER--" + username + "--" + password);

            String serverMessage = in.readLine();
            return serverMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String joinGame(String userToken, String gameToken) {

        try {
            out.println("JOINGAME--" + userToken + "--" + gameToken);

            String serverMessage = in.readLine();
            return serverMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String startGame(String userToken) {
        try {
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