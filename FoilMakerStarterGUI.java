package Psych;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * Created by Chris Nitta on 10/19/2016.
 */
public class FoilMakerStarterGUI extends JFrame {
    private String userToken;
    private String gameToken;
    Server s = new Server();
    /*Main panel */
    JPanel mainPanel = new JPanel();
    CardLayout layout = new CardLayout();

    /*Login or Register Panel*/
    JPanel panel1 = new JPanel();
    JLabel username = new JLabel("Username: ");
    JTextField enterUsername = new JTextField(20);
    JLabel password = new JLabel("Password: ");
    JPasswordField enterPassword = new JPasswordField(20);
    JButton loginButton = new JButton("Login");
    JButton registerButton = new JButton("Register");

    /*Join or Start New Game Panel*/
    JPanel panel2 = new JPanel();
    JButton joinButton = new JButton("Join Game");
    JButton startButton = new JButton("Start New Game");

    /*Waiting panel*/
    JPanel panel4 = new JPanel();
    JLabel waiting = new JLabel("Waiting for leader ... ");
    public FoilMakerStarterGUI() {
        /*Add option to login and register to panel1*/
        panel1.add(username);
        panel1.add(enterUsername);
        panel1.add(password);
        panel1.add(enterPassword);
        panel1.add(loginButton);
        panel1.add(registerButton);

        /*Add Join Game and Start new game to panel2*/
        panel2.add(joinButton);
        panel2.add(startButton);

        /*Add waiting to panel4*/
        panel4.add(waiting);

        /*Add sub-panels to main panel*/
        mainPanel.setLayout(layout);
        mainPanel.add(panel1, "Login or Register");
        mainPanel.add(panel2, "Start or Join");

        /**
         * Action Listeners
         */
        /*Login button action listener*/
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = enterUsername.getText();
                char[] charPassword = enterPassword.getPassword();
                String password = String.valueOf(charPassword);
                String serverMessage = s.login(username, password);
                System.out.println(serverMessage);
                if (serverMessage.equals("RESPONSE--LOIN--INVALIDMESSAGEFORMAT--LOGIN--" + username + "--" + password)) {
                    JOptionPane.showMessageDialog(null,"Request does not comply with the format given above","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(serverMessage.equals("RESPONSE--LOGIN--UNKNOWNUSER--LOGIN--" + username + "--" + password)) {
                    JOptionPane.showMessageDialog(null,"Invalid Username","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(serverMessage.equals("RESPONSE--LOGIN--INVALIDUSERPASSWORD--LOGIN--" + username + "--" + password)) {
                    JOptionPane.showMessageDialog(null,"Invalid Password","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(serverMessage.equals("RESPONSE--LOGIN--USERALREADYLOGGEDIN--LOGIN--" + username + "--" + password)) {
                    JOptionPane.showMessageDialog(null,"User already logged in","Error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    int lastDash = serverMessage.lastIndexOf('-');
                    userToken = serverMessage.substring(lastDash + 1, serverMessage.length());
                    layout.show(mainPanel, "Start or Join");
                }
            }
        });
        /*Register button action listener*/
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = enterUsername.getText();
                char[] charPassword = enterPassword.getPassword();
                String password = String.valueOf(charPassword);
                String serverMessage = s.register(username,password);
                if (serverMessage.equals("RESPONSE--CREATENEWUSER--INVALIDMESSAGEFORMAT--CREATENEWUSERR--" + username + "--" + password)) {
                    JOptionPane.showMessageDialog(null,"Request does not comply with the format given above","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if (serverMessage.equals("RESPONSE--CREATENEWUSER--INVALIDUSERNAME--CREATENEWUSER--" + username + "--" + password)) {
                    JOptionPane.showMessageDialog(null,"Username empty","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if (serverMessage.equals("RESPONSE--CREATENEWUSER--INVALIDUSERPASSWORD--CREATENEWUSER--" + username + "--" + password)) {
                    JOptionPane.showMessageDialog(null,"Password empty","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if (serverMessage.equals("RESPONSE--CREATENEWUSER--USERALREADYEXISTS--CREATENEWUSER--" + username + "--" + password)) {
                    JOptionPane.showMessageDialog(null,"User already exists","Error",JOptionPane.ERROR_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(null, "User created successfully","Success",JOptionPane.PLAIN_MESSAGE);
            }
        });
        /*Start Game action Listener*/
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serverMessage = s.startGame(userToken);
                int lastDash = serverMessage.lastIndexOf('-');
                gameToken = serverMessage.substring(lastDash + 1, serverMessage.length());
            }
        });
        /*Join Game action Listener*/
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.joinGame(userToken,gameToken);
            }
        } );

        add(mainPanel);

        setLocation(0,0);
        setMinimumSize(new Dimension(350,300));
        setResizable(false);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        layout.show(mainPanel, "Login or Register");
        setTitle("FoilMaker");
        setVisible(true);
    }
    public static void main(String[] args) {
        new FoilMakerStarterGUI();
    }
}
