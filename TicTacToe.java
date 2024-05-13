//TIC TAC TOE IS FUN!
// TO SHOW THE WINNER, RE ADJUST THE LAYOUT , THANK YOU!

import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends Frame implements ActionListener {
    Button[] buttons = new Button[9];
    boolean turn = true; 
    Label turnIndicator; 
    Label winnerLabel; 
    Button newGameButton; 
    Font labelFont; 
    TicTacToe() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        labelFont = new Font("Arial", Font.BOLD, 50); 

        
        Panel gridPanel = new Panel(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button();
            buttons[i].setFont(labelFont); 
            buttons[i].addActionListener(this);
            gridPanel.add(buttons[i]);
        }

       
        Panel controlPanel = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        newGameButton = new Button("New Game");
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        turnIndicator = new Label("Player X's turn");
        turnIndicator.setFont(new Font("Arial", Font.PLAIN, 20)); 
        winnerLabel = new Label("");
        winnerLabel.setFont(new Font("Arial", Font.PLAIN, 20)); 

        controlPanel.add(newGameButton);
        controlPanel.add(turnIndicator);
        controlPanel.add(winnerLabel);

        gbc.gridy = 0;
        add(gridPanel, gbc);

        gbc.gridy = 1;
        add(controlPanel, gbc);

        setSize(300, 350); 
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Button b = (Button) e.getSource();
        if (turn) {
            b.setLabel("X");
            b.setForeground(Color.BLUE); 
            turnIndicator.setText("Player O's turn");
        } else {
            b.setLabel("O");
            b.setForeground(Color.RED); 
            turnIndicator.setText("Player X's turn");
        }
        turn = !turn;
        b.setEnabled(false);
        checkWinner();
    }

    void checkWinner() {

        for (int i = 0; i < 9; i += 3)
            if (checkLine(i, i+1, i+2))
                endGame(buttons[i].getLabel());

   
        for (int i = 0; i < 3; ++i)
            if (checkLine(i, i+3, i+6))
                endGame(buttons[i].getLabel());

  
        if (checkLine(0, 4, 8) || checkLine(2, 4, 6))
            endGame(buttons[4].getLabel());
    }

    boolean checkLine(int a, int b, int c) {
        return buttons[a].getLabel().equals(buttons[b].getLabel()) &&
               buttons[a].getLabel().equals(buttons[c].getLabel()) &&
               !buttons[a].getLabel().equals("");
    }

    void endGame(String winner) {
        for (Button b : buttons)
            b.setEnabled(false);
        winnerLabel.setText(winner + " wins!");
        turnIndicator.setText(""); 
    }

    void resetGame() {
        for (Button b : buttons) {
            b.setEnabled(true);
            b.setLabel("");
            b.setForeground(Color.BLACK); 
        }
        turn = true;
        turnIndicator.setText("Player X's turn");
        winnerLabel.setText("");
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
