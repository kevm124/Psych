package Psych;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Created by Chris Nitta on 10/19/2016.
 */
public class FoilMakerStarterGUI {
    public String[] options = {"lick","me"};
    public String[] players = {"penis"};
    private String userToken;
    private String gameToken;
    JFrame frame = new JFrame();
    Server s = new Server();
    JButton test = new JButton("Test");

    /*Main panel */
    JPanel mainPanel = new JPanel();
    CardLayout layout = new CardLayout();
    BoxLayout boxlayout = new BoxLayout(frame.getContentPane(), 1);

    /*Login or Register Panel*/
    JPanel loginPanel = new JPanel();
    JLabel username = new JLabel("Username: ");
    JTextField enterUsername = new JTextField(20);
    JLabel password = new JLabel("Password: ");
    JPasswordField enterPassword = new JPasswordField(20);
    JButton loginButton = new JButton("Login");
    JButton registerButton = new JButton("Register");

    /*Join or Start New Game Panel*/
    JPanel startPanel = new JPanel();
    JButton joinButton = new JButton("Join Game");
    JButton startButton = new JButton("Start New Game");

    /*Waiting panel*/
    JPanel waitingPanel = new JPanel();
    JLabel waiting = new JLabel("Waiting for leader ... ");
    JLabel waitingInfo = new JLabel("Joined game: waiting for leader.");

    /*Join game with token panel*/
    JPanel enterTokenPanel = new JPanel();
    JLabel enterToken = new JLabel("Enter the game key that you wish to join:");
    JTextField token = new JTextField(3);
    JButton joinGameToken = new JButton("Join Game");

    /*Panel where user enters suggestion*/
    JPanel panelFirst = new JPanel();
    JLabel firstPanelHeader = new JLabel("What is the word for");
    JPanel wordPanel = new JPanel();
    JLabel definitionText = new JLabel();
    JPanel guessPanel = new JPanel();
    JTextField guess = new JTextField(10);
    JButton sendButton = new JButton("Submit");

    //Second Panel where user selects guess
    JLabel answerTitle = new JLabel();
    JPanel answerPanel = new JPanel();
    JPanel optionPanel = new JPanel();
    String[] optionsNames;
    JButton buttonSecond = new JButton("Send Guess");
    String answer;

    //Leader Gui
    JPanel main = new JPanel();
    JPanel leader = new JPanel();

    JLabel label2 = new JLabel("Others should use this key to join your game");
    JTextArea gameKeyText = new JTextArea("Insert game key here");

    JPanel playerPanel = new JPanel();
    JTextArea playersInGame = new JTextArea();
    JButton startButton2 = new JButton("Start Game");

    /*Results Panel*/
    JPanel resultsPanel = new JPanel();
    JLabel roundResult = new JLabel("Round Result: ");
    JLabel givenRoundResults = new JLabel(getResults());
    JLabel overallResult = new JLabel("Overall Results: ");
    JLabel givenOverallResults = new JLabel(getOverallResults());
    JButton nextRound = new JButton("Next Round");


    public FoilMakerStarterGUI() {
        /*Add option to login and register to panel1*/
        loginPanel.add(username);
        loginPanel.add(enterUsername);
        loginPanel.add(password);
        loginPanel.add(enterPassword);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);
        loginPanel.add(test);

        /*Add Join Game and Start new game to panel2*/
        startPanel.add(joinButton);
        startPanel.add(startButton);

        /*Add waiting to panel4*/
        waitingPanel.setLayout(new BorderLayout());
        waitingPanel.add(waiting, BorderLayout.CENTER );
        waitingPanel.add(waitingInfo,BorderLayout.PAGE_END);
        /*Add elements to Token Panel*/
        enterTokenPanel.add(enterToken);
        enterTokenPanel.add(token);
        enterTokenPanel.add(joinGameToken);

        /*Add elements to Results Panel*/
        resultsPanel.add(roundResult);
        resultsPanel.add(givenRoundResults);
        resultsPanel.add(overallResult);
        resultsPanel.add(givenOverallResults);
        resultsPanel.add(nextRound);

        //Add elements to enter guess panel
        panelFirst.add(firstPanelHeader);
        wordPanel.add(definitionText);
        panelFirst.add(wordPanel);
        guessPanel.add(guess);
        panelFirst.add(guessPanel);
        panelFirst.add(sendButton);
        sendButton.setEnabled(false);
        panelFirst.setBackground(Color.cyan);

        answerPanel.add(answerTitle);
        optionsNames = options; //Insert response from server
        JButton[] optionButtons = new JButton[optionsNames.length];
        for (int i =0; i < optionsNames.length; i++) {
            JButton btn = new JButton(optionsNames[i]);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //..
                }
            });
            optionPanel.add(btn);
            optionButtons[i] = btn;
        }
        answerPanel.add(optionPanel);
        answerPanel.add(buttonSecond);
        answerPanel.setBackground(Color.DARK_GRAY);

        //Leader Gui Stuff
        gameKeyText.setEditable(false);
        playersInGame.setEditable(false);
        for (int i = 0; i < players.length; i += 2) {
            playersInGame.append(players[i] + "\n");
        }
        playersInGame.setBackground(Color.pink);
        playersInGame.setFont(new Font("Comic Sans", Font.BOLD, 16));

        playerPanel.add(playersInGame);
        playerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Players in Game"));
        playerPanel.setBackground(Color.pink);
        playerPanel.setPreferredSize(new Dimension(300,300));

        leader.add(label2);
        leader.add(gameKeyText);
        leader.add(playerPanel);
        leader.add(startButton2);

        main.setLayout(layout);
        main.add(leader, "1");

        /*Add sub-panels to main panel*/
        mainPanel.setLayout(layout);
        mainPanel.add(loginPanel, "Login or Register");
        mainPanel.add(startPanel, "Start or Join");
        mainPanel.add(waitingPanel, "Waiting for leader");
        mainPanel.add(enterTokenPanel, "Enter Token");
        mainPanel.add(resultsPanel, "Results");
        mainPanel.add(answerPanel, "Answers");
        mainPanel.add(panelFirst, "Guess");

        frame.setTitle("FoilMaker");
        frame.add(mainPanel);
        frame.add(main);

        frame.setLocation(0,0);
        frame.setMinimumSize(new Dimension(350,300));
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        layout.show(mainPanel, "Login or Register");
        frame.setTitle("FoilMaker");
        frame.setVisible(true);

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
        /*Test button action listener*/
        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(mainPanel,"Results");
            }
        });

        //Add action listener to button 1 on panel first
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelFirst.setVisible(false);
                answerPanel.setVisible(true);
                System.out.println(guess.getText());
            }
        });

        buttonSecond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //layout.show(mainPanel,"1");
            }
        });
        guess.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (guess.getText().isEmpty()) {
                    sendButton.setEnabled(false);
                }
                else
                    sendButton.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (guess.getText().isEmpty()) {
                    sendButton.setEnabled(false);
                }
                else
                    sendButton.setEnabled(true);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (guess.getText().isEmpty()) {
                    sendButton.setEnabled(false);
                }
                else
                    sendButton.setEnabled(true);
            }
        });
    }
    public static String getResults() {
        return null;
    }
    public static String getOverallResults() {
        return null;
    }
    public static void main(String[] args) {
        new FoilMakerStarterGUI();
    }
}
