package Psych;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * Created by Chris Nitta on 10/19/2016.
 */
public class FoilMakerStarterGUI extends JFrame {
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
                s.login(username,password);
                layout.show(mainPanel, "Start or Join");
            }
        });
        /*Register button action listener*/
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = enterUsername.getText();
                char[] charPassword = enterPassword.getPassword();
                String password = String.valueOf(charPassword);
                s.register(username, password);
            }
        });
        /*Start Game action Listener*/
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        /*Join Game action Listener*/
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

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
