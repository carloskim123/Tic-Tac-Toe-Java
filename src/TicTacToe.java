import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe implements ActionListener {

    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;

    TicTacToe() throws InterruptedException {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(50, 50, 50));
        textfield.setForeground(new Color(72, 174, 59));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 50));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setVisible(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBackground(new Color(50, 50, 50));
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1_turn) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(3, 37, 127));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("O turn");
                        check();
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(127, 3, 36));
                        buttons[i].setText("O");
                        player1_turn = true;
                        textfield.setText("X turn");
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn() throws InterruptedException {
        Thread.sleep(2000);
        player1_turn = Math.random() < 0.5;
        if (player1_turn) {
            textfield.setText("X turn");
        } else {
            textfield.setText("O turn");
        }
    }

    public void check() {
        if (checkWinCondition("X")) {
            xWins();
        } else if (checkWinCondition("O")) {
            oWins();
        } else if (isBoardFull()) {
            textfield.setText("It's a draw!");
        }
    }

    public boolean checkWinCondition(String player) {
        return checkRows(player) || checkColumns(player) || checkDiagonals(player);
    }

    public boolean checkRows(String player) {
        for (int i = 0; i < 9; i += 3) {
            if (buttons[i].getText().equals(player) &&
                    buttons[i + 1].getText().equals(player) &&
                    buttons[i + 2].getText().equals(player)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkColumns(String player) {
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(player) &&
                    buttons[i + 3].getText().equals(player) &&
                    buttons[i + 6].getText().equals(player)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDiagonals(String player) {
        return (buttons[0].getText().equals(player) &&
                buttons[4].getText().equals(player) &&
                buttons[8].getText().equals(player)) ||
                (buttons[2].getText().equals(player) &&
                        buttons[4].getText().equals(player) &&
                        buttons[6].getText().equals(player));
    }

    public boolean isBoardFull() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    public void xWins() {
        textfield.setText("X Wins!");
        disableButtons();
    }

    public void oWins() {
        textfield.setText("O Wins!");
        disableButtons();
    }

    public void disableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(() -> {
                try {
                    new TicTacToe();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
