import javax.swing.*;
import java.awt.*;

/**
 * Created by Kevin on 10/24/2016.
 */
public class LeaderGUI extends JFrame{
    String gameKey = "ypw";
    String [] players = {"Alice", "0", "Bob", "0"};

    CardLayout layout = new CardLayout();
    JPanel main = new JPanel();
    JPanel leader = new JPanel();

    JLabel label = new JLabel("Others should use this key to join your game");
    JTextArea gameKeyText = new JTextArea(gameKey);

    JPanel playersPanel = new JPanel();
    JTextArea playersInGame = new JTextArea();
    JButton startButton = new JButton("Start Game");
    public  LeaderGUI(){
        gameKeyText.setEditable(false);
        playersInGame.setEditable(false);
        for(int i = 0; i < players.length; i += 2){
            playersInGame.append(players[i] + "\n");
        }
        playersInGame.setBackground(Color.pink);
        playersInGame.setFont(new Font("Serif", Font.BOLD, 16));


        playersPanel.add(playersInGame);
        playersPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Players in Game"));
        playersPanel.setBackground(Color.pink);
        playersPanel.setPreferredSize(new Dimension(300, 300));

        leader.add(label);
        leader.add(gameKeyText);
        leader.add(playersPanel);
        leader.add(startButton);

        main.setLayout(layout);
        main.add(leader, "1");

        add(main);
        setLocation(200,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        layout.show(main, "1");
        setVisible(true);
    }

    public static void main(String [] args){
        new LeaderGUI();
    }
}
