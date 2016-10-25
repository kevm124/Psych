/**
 * Created by Kevin on 10/19/2016.
 */
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame {
    //Testing Values
    String word = "zaggle", definition = " A group of zebras";
    String [] options = {"A zippy do", "A zig zag", "A dazzle"};

    /* Main panel */
    BoxLayout layout = new BoxLayout(getContentPane(), 1);
    //BoxLayout layout2 = new BoxLayout(getContentPane(), 1);

    //First Frame elements where user enters suggestion
    JFrame guessFrame = new JFrame();
    JPanel panelFirst = new JPanel();
    JLabel firstPanelHeader = new JLabel("What is the word for");
    JPanel wordPanel = new JPanel();
    JLabel definitionText = new JLabel(definition);
    JPanel guessPanel = new JPanel();
    JTextField guess = new JTextField(10);

    JButton sendButton = new JButton("Submit");

    //Second Frame elements where user selects guess
    JLabel answerTitle = new JLabel();
    JFrame answerFrame = new JFrame();
    JPanel panelSecond = new JPanel();
    JPanel optionPanel = new JPanel();
    String [] optionsNames;
    JButton buttonSecond = new JButton("Send Guess");
    String answer;

    //Third frame elements where users see results of previous round
    JFrame resultFrame = new JFrame();

    public Game() {
	 	/* Add elements to Frame 1 */
	 	panelFirst.add(firstPanelHeader);
        wordPanel.add(definitionText);
        panelFirst.add(wordPanel);
        guessPanel.add(guess);
        guessPanel.setBorder(BorderFactory.createTitledBorder("Your Guess:"));
        panelFirst.add(guessPanel);
        panelFirst.add(sendButton);
        sendButton.setEnabled(false);
        panelFirst.setBackground(Color.CYAN);

        guessFrame.add(panelFirst);
        guessFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //guessFrame.setLayout(layout);
        guessFrame.setSize(500,300);
        guessFrame.setVisible(true);

        panelSecond.add(answerTitle);
	 	/* Add button to Frame 2 */
	 	optionsNames = options; //Insert response from server
        JButton [] optionButtons = new JButton[optionsNames.length];
        for(int i = 0; i < optionsNames.length; i++){
            JButton btn = new JButton(optionsNames[i]);
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    //..
                }
            });
            optionPanel.add(btn);
            optionButtons[i] = btn;
        }
	 	panelSecond.add(optionPanel);
        panelSecond.add(buttonSecond);
        panelSecond.setBackground(Color.ORANGE);

        //answerFrame.setLayout(layout2);
        answerFrame.add(panelSecond);
        answerFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        answerFrame.setSize(500,300);


	 	/* Add action listener to button 1 on panel 1 */
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guessFrame.setVisible(false);
                answerFrame.setVisible(true);/* Show panel 2 when button clicked */
                System.out.println(guess.getText());
            }
        });

	 	/* Add action listener to button 2 on panel 2 */
        buttonSecond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //layout.show(mainPanel, "1"); /* Show panel 1 when button clicked */
            }
        });

        guess.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(guess.getText().isEmpty())
                    sendButton.setEnabled(false);
                else
                    sendButton.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(guess.getText().isEmpty())
                    sendButton.setEnabled(false);
                else
                    sendButton.setEnabled(true);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(guess.getText().isEmpty())
                    sendButton.setEnabled(false);
                else
                    sendButton.setEnabled(true);
            }
        });

    }
    public static void main(String[] args) {
        new Game(); /* Show GUI */
    }
}
