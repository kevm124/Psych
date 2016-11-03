package Psych;

import jdk.nashorn.internal.scripts.JO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Created by Chris Nitta on 10/19/2016.
 */

public class FoilMakerStarterGUI {
    FoilMakerModel m = new FoilMakerModel();
    ServerResponseReader r = new ServerResponseReader();
    JFrame frame = new JFrame();
    Server s = new Server();

    /*Main panel */
    JPanel mainPanel = new JPanel();
    CardLayout layout = new CardLayout();

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
    JButton quitButton = new JButton("Quit");

    /*Waiting panel*/
    JPanel waitingPanel = new JPanel();
    JLabel waiting = new JLabel("Waiting for leader ... ");
    JLabel waitingInfo = new JLabel("Joined game: waiting for leader.");
    JButton enter = new JButton("Enter Game");

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
    JTextField guess = new JTextField(25);
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
    JTextArea gameKeyText = new JTextArea(m.getGameToken());
    JPanel playerPanel = new JPanel();
    JTextArea playersInGame = new JTextArea();
    JButton leaderStartButton = new JButton("Start");

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

        /*Add Join Game and Start new game to panel2*/
        startPanel.add(joinButton);
        startPanel.add(startButton);

        /*Add waiting to panel4*/
        waitingPanel.setLayout(new BorderLayout());
        waitingPanel.add(waiting, BorderLayout.CENTER );
        waitingPanel.add(waitingInfo,BorderLayout.PAGE_END);
        enter.setEnabled(false);
        waitingPanel.add(enter);
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
        resultsPanel.add(quitButton);

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
        optionsNames = m.getOptions(); //Insert response from server
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
        playersInGame.setBackground(Color.pink);
        playersInGame.setFont(new Font("Comic Sans", Font.BOLD, 16));

        playerPanel.add(playersInGame);
        playerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Players in Game"));
        playerPanel.setBackground(Color.pink);
        playerPanel.setPreferredSize(new Dimension(300,300));
        playerPanel.add(leaderStartButton);


        leader.add(label2);
        leader.add(gameKeyText);
        leader.add(playerPanel);

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
        mainPanel.add(main, "Leader");

        frame.setTitle("FoilMaker");
        frame.add(mainPanel);

        frame.setLocation(0,0);
        frame.setMinimumSize(new Dimension(350,300));
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        layout.show(mainPanel, "Login or Register");

        /**
         * Action Listeners
         */
        /*Login button action listener*/
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.setUsername(enterUsername.getText());
                char[] charPassword = enterPassword.getPassword();
                m.setPassword(String.valueOf(charPassword));
                String serverMessage = s.login(m.getUsername(), m.getPassword());
                if (r.getCommandStatus(serverMessage).equals("INVALIDMESSAGEFORMAT")) {
                    JOptionPane.showMessageDialog(null,"Request does not comply with the format given above","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(r.getCommandStatus(serverMessage).equals("UNKNOWNUSER")) {
                    JOptionPane.showMessageDialog(null,"Invalid Username","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(r.getCommandStatus(serverMessage).equals("INVALIDUSERPASSWORD")) {
                    JOptionPane.showMessageDialog(null,"Invalid Password","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(r.getCommandStatus(serverMessage).equals("USERALREADYLOGGEDIN")) {
                    JOptionPane.showMessageDialog(null,"User already logged in","Error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    m.setUserToken(r.getSessionCookie(serverMessage));
                    layout.show(mainPanel, "Start or Join");
                }
            }
        });
        /*Register button action listener*/
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.setUsername(enterUsername.getText());
                char[] charPassword = enterPassword.getPassword();
                m.setPassword(String.valueOf(charPassword));
                String serverMessage = s.register(m.getUsername(), m.getPassword());
                if (r.getCommandStatus(serverMessage).equals("INVALIDMESSAGEFORMAT")) {
                    JOptionPane.showMessageDialog(null,"Request does not comply with the format given above","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if (r.getCommandStatus(serverMessage).equals("INVALIDUSERNAME")) {
                    JOptionPane.showMessageDialog(null,"Username empty","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if (r.getCommandStatus(serverMessage).equals("INVALIDUSERPASSWORD")) {
                    JOptionPane.showMessageDialog(null,"Password empty","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if (r.getCommandStatus(serverMessage).equals("USERALREADYEXISTS")) {
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
                String serverMessage = s.startGame(m.getUserToken());
                if (r.getCommandStatus(serverMessage).equals("USERNOTLOGGEDIN")) {
                    JOptionPane.showMessageDialog(null,"Invalid user Token","Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (r.getCommandStatus(serverMessage).equals("FAILURE")) {
                    JOptionPane.showMessageDialog(null,"User already playing or server failed to create a game session due to an internal error","error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    m.setGameToken(r.getGameToken(serverMessage));
                    gameKeyText.setText(m.getGameToken());
                    layout.show(mainPanel, "Leader");
                }
            }
        });
        /*Join Game action Listener*/
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(mainPanel, "Enter Token");
            }
        } );

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(420);
            }
        });

        //Add action listener to Join game Button
        joinGameToken.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String serverMessage = s.joinGame(m.getUserToken(),token.getText());
                if (r.getCommandStatus(serverMessage).equals("USERNOTLOGGEDIN")) {
                    JOptionPane.showMessageDialog(null,"Invalid user token","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if (r.getCommandStatus(serverMessage).equals("GAMEKEYNOTFOUND")) {
                    JOptionPane.showMessageDialog(null,"Invalid game toke","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if (r.getCommandStatus(serverMessage).equals("FAILURE")) {
                    JOptionPane.showMessageDialog(null,"User already playing the game","Error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    System.out.println(serverMessage);
                    layout.show(mainPanel, "Waiting for leader");
                    m.incPlayersWaiting();
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (r.getCommandStatus(serverMessage).equals("SUCCESS")) {
                                if (s.checkForGame().substring(0,11).equals("NEWGAMEWORD")) {
                                    layout.show(mainPanel,"Guess");
                                    break;
                                }
                            }
                        }
                    });
                    t.start();
                }
            }
        });
        //Add action listener to start button on leader screen
        leaderStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serverMessage = s.startGame2(m.getUserToken(),m.getGameToken());
                m.setStartGameMessage(serverMessage);
                m.setLeaderStartedGame(true);
                System.out.println(serverMessage);
                layout.show(mainPanel, "Guess");
                definitionText.setText(r.getDefinition(r.getGameWord(serverMessage)));
            }
        });
        //Add action listener to button 1 on panel first
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serverMessage = s.suggest(m.getUserToken(),m.getGameToken(),guess.getText());
                layout.show(mainPanel, "Answers");
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
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (waitingPanel.isVisible()) {
                    if (s.checkForGame().substring(0,11).equals("NEWGAMEWORD")) {
                        enter.setEnabled(true);
                        layout.show(mainPanel, "Guess");
                    }
                }
            }
        });
    }
    public void showGame() {
        frame.setTitle("FoilMaker");
        frame.setVisible(true);
    }
    public void changeToGame() {
        while (!m.getLeaderStartedGame()) {

        }
        layout.show(mainPanel, "Guess");
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
