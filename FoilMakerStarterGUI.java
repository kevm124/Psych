package Psych;

import jdk.nashorn.internal.scripts.JO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
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
    JLabel mainTitle = new JLabel("FoilMaker");
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
    
    Border blackLine = BorderFactory.createLineBorder(Color.black);

    public FoilMakerStarterGUI() {
        frame.setSize(450,700);
        /*Add option to login and register to panel1*/
        SpringLayout spring = new SpringLayout();
        loginPanel.setLayout(spring);
        loginPanel.setBackground(Color.pink);
        mainTitle.setFont(new Font("Courier New", Font.ITALIC, 55));
        mainTitle.setForeground(Color.white);
        loginPanel.add(mainTitle);
        username.setForeground(Color.white);
        loginPanel.add(username);
        loginPanel.add(enterUsername);
        password.setForeground(Color.white);
        loginPanel.add(password);
        loginPanel.add(enterPassword);
        loginButton.setPreferredSize(new Dimension(90,30));
        loginPanel.add(loginButton);
        registerButton.setPreferredSize(new Dimension(90,30));
        loginPanel.add(registerButton);

        spring.putConstraint(SpringLayout.NORTH, mainTitle, 75, SpringLayout.NORTH, loginPanel);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainTitle, 225, SpringLayout.WEST, loginPanel);
        spring.putConstraint(SpringLayout.NORTH, username, 250, SpringLayout.NORTH, loginPanel);
        spring.putConstraint(SpringLayout.EAST, username, -350, SpringLayout.EAST, loginPanel);
        spring.putConstraint(SpringLayout.NORTH, enterUsername, 250, SpringLayout.NORTH, loginPanel);
        spring.putConstraint(SpringLayout.EAST, enterUsername, -20, SpringLayout.EAST, loginPanel);
        spring.putConstraint(SpringLayout.NORTH, password, 275, SpringLayout.NORTH, loginPanel);
        spring.putConstraint(SpringLayout.EAST, password, -350, SpringLayout.EAST, loginPanel);
        spring.putConstraint(SpringLayout.NORTH, enterPassword, 275, SpringLayout.NORTH, loginPanel);
        spring.putConstraint(SpringLayout.EAST, enterPassword, -20, SpringLayout.EAST, loginPanel);
        spring.putConstraint(SpringLayout.NORTH, loginButton, 425, SpringLayout.NORTH, loginPanel);
        spring.putConstraint(SpringLayout.EAST, loginButton, 210, SpringLayout.WEST, loginPanel);
        spring.putConstraint(SpringLayout.NORTH, registerButton, 425, SpringLayout.NORTH, loginPanel);
        spring.putConstraint(SpringLayout.EAST, registerButton, -130, SpringLayout.EAST, loginPanel);

        Timer t = new Timer(420, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                //loginPanel.setBackground(new Color(gen.nextInt(256), gen.nextInt(256), gen.nextInt(256)));
                mainTitle.setForeground(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
            }
        });
        t.setRepeats(true);
        t.start();

       /*Add Join Game and Start new game to panel2*/
        startPanel.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.add(joinButton);
        buttonPanel.add(startButton);
        startPanel.add(buttonPanel, BorderLayout.NORTH);

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
        //Insert response from server

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

        leader.setLayout(new BorderLayout());
        JPanel leaderHeader = new JPanel();
        leaderHeader.add(label2);
        leaderHeader.add(gameKeyText);
        leader.add(leaderHeader, BorderLayout.NORTH);
        leader.add(playerPanel, BorderLayout.CENTER);
        leader.add(leaderStartButton, BorderLayout.SOUTH);

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

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
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
                    JOptionPane.showMessageDialog(frame,"Request does not comply with the format given above","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(r.getCommandStatus(serverMessage).equals("UNKNOWNUSER")) {
                    JOptionPane.showMessageDialog(frame,"Invalid Username","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(r.getCommandStatus(serverMessage).equals("INVALIDUSERPASSWORD")) {
                    JOptionPane.showMessageDialog(frame,"Invalid Password","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(r.getCommandStatus(serverMessage).equals("USERALREADYLOGGEDIN")) {
                    JOptionPane.showMessageDialog(frame,"User already logged in","Error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    m.setUserToken(r.getSessionCookie(serverMessage));
                    layout.show(mainPanel, "Start or Join");
                    TitledBorder titledBorder = new TitledBorder(blackLine, m.getUsername());
                    startPanel.setBorder(titledBorder);
                    waitingPanel.setBorder(titledBorder);
                    enterTokenPanel.setBorder(titledBorder);
                    panelFirst.setBorder(titledBorder);
                    main.setBorder(titledBorder);
                    answerPanel.setBorder(titledBorder);
                    resultsPanel.setBorder(titledBorder);
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
                    JOptionPane.showMessageDialog(frame,"Request does not comply with the format given above","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if (r.getCommandStatus(serverMessage).equals("INVALIDUSERNAME")) {
                    JOptionPane.showMessageDialog(frame,"Username empty","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if (r.getCommandStatus(serverMessage).equals("INVALIDUSERPASSWORD")) {
                    JOptionPane.showMessageDialog(frame,"Password empty","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if (r.getCommandStatus(serverMessage).equals("USERALREADYEXISTS")) {
                    JOptionPane.showMessageDialog(frame,"User already exists","Error",JOptionPane.ERROR_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(frame, "User created successfully","Success",JOptionPane.PLAIN_MESSAGE);
            }
        });
        /*Start Game action Listener*/
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serverMessage = s.startGame(m.getUserToken());
                if (r.getCommandStatus(serverMessage).equals("USERNOTLOGGEDIN")) {
                    JOptionPane.showMessageDialog(frame,"Invalid user Token","Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (r.getCommandStatus(serverMessage).equals("FAILURE")) {
                    JOptionPane.showMessageDialog(frame,"User already playing or server failed to create a game session due to an internal error","error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    m.setGameToken(r.getGameToken(serverMessage));
                    gameKeyText.setText(m.getGameToken());
                    layout.show(mainPanel, "Leader");
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            boolean dontStartGame = true;
                            whileOuterLoop:
                            while (dontStartGame == true) {
                                String playerServerMessage = s.readFromServer();
                                outerLoop:
                                if (playerServerMessage.substring(0,14).equals("NEWPARTICIPANT")) {
                                    String[] info = r.getNewParticipantInfo(playerServerMessage);
                                    String player = info[0];
                                    playersInGame.append(player + "\n");
                                    m.incPlayersWaiting();
                                    if (leaderStartButton.isSelected() || m.getPlayersWaiting() == m.getMAX_PLAYER_SIZE()) {
                                        dontStartGame = false;
                                    }
                                    if (dontStartGame == false) {
                                        break outerLoop;
                                    }
                                }
                                break;
                            }
                        }
                    });
                    t.start();
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
                    JOptionPane.showMessageDialog(frame,"Invalid user token","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if (r.getCommandStatus(serverMessage).equals("GAMEKEYNOTFOUND")) {
                    JOptionPane.showMessageDialog(frame,"Invalid game toke","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if (r.getCommandStatus(serverMessage).equals("FAILURE")) {
                    JOptionPane.showMessageDialog(frame,"User already playing the game","Error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    System.out.println(serverMessage);
                    layout.show(mainPanel, "Waiting for leader");
                    m.incPlayersWaiting();
                    m.setGameToken(token.getText());
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (r.getCommandStatus(serverMessage).equals("SUCCESS")) {
                                String gameWord = s.checkForGame();
                                if (gameWord.substring(0,11).equals("NEWGAMEWORD")) {
                                    definitionText.setText(r.getDefinition(r.getGameWord(gameWord)));
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
                layout.show(mainPanel, "Guess");
                definitionText.setText(r.getDefinition(r.getGameWord(serverMessage)));
            }
        });
        //Add action listener to button 1 on panel first
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serverMessage = s.suggest(m.getUserToken(),m.getGameToken(),guess.getText());
                optionsNames = r.getRoundOptions(serverMessage);
                JButton[] optionButtons = new JButton[optionsNames.length];
                for (int i =0; i < optionsNames.length; i++) {
                    JButton btn = new JButton(optionsNames[i]);
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            s.playerChoice(m.getUserToken(),m.getGameToken(),btn.getText());
                            layout.show(mainPanel, "Results");
                        }
                    });
                    optionPanel.add(btn);
                    optionButtons[i] = btn;
                }
                layout.show(mainPanel, "Answers");
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
