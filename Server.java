import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.jetbrains.annotations.Contract;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
    public String startGame2(String userToken, String gameToken) {
        try {
            out.println("ALLPARTICIPANTSHAVEJOINED--" + userToken + "--" + gameToken);

            String serverMessage = in.readLine();
            return serverMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String suggest(String userToken, String gameToken, String suggestion) {
        try {
            out.println("PLAYERSUGGESTION--" + userToken + "--" + gameToken + "--" + suggestion);

            String serverMessage = in.readLine();
            return serverMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String playerChoice(String userToken, String gameToken, String choice) {
        try {
            out.println("PLAYERCHOICE--" + userToken + "--" + gameToken + "--" + choice);

            String serverMessage = in.readLine();
            return serverMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String checkForGame() {
        try {
            String serverMessage = in.readLine();
            return serverMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String readFromServer() {
        try {
            String serverMessage = in.readLine();
            return serverMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getGameWord() {
        try {
            String serverMessage = in.readLine();
            return serverMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        FoilMakerStarterGUI game = new FoilMakerStarterGUI();
        game.showGame();
    }
}
