import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Gui {
    private JFrame frame;
    private JLabel label;
    private JLabel[][] cells;

    // Constructor
    public Gui(Board board) {
        // Create the frame
        frame = new JFrame("2048");
        // Set the default close operation and size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the size and location of the frame
        frame.setSize(500, 500);
        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Create a label and add it to the content panel
        label = new JLabel("2048", JLabel.CENTER);
        // Create a panel to hold the board
        JPanel boardPanel = new JPanel();
        // Set the layout of the board panel to a 4x4 grid
        boardPanel.setLayout(new GridLayout(4, 4));
        cells = new JLabel[4][4];

        // Add 16 labels to the board panel to represent the tiles
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                cells[row][col] = new JLabel("", JLabel.CENTER);
                cells[row][col].setOpaque(true);
                cells[row][col].setBackground(Color.LIGHT_GRAY);
                cells[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                boardPanel.add(cells[row][col]);
            }
        }

        // Create a content panel and add the label and board panel to it
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(label, BorderLayout.NORTH);
        contentPanel.add(boardPanel, BorderLayout.CENTER);
        frame.setContentPane(contentPanel);

        // Make the frame visible
        frame.setVisible(true);

        frame.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                System.out.println("Key pressed: " + e.getKeyCode());
                switch (e.getKeyCode()) {
                    case java.awt.event.KeyEvent.VK_LEFT:
                    case java.awt.event.KeyEvent.VK_A:
                        board.moveLeft();
                        board.spawnTile();
                        board.printBoard();
                        updateBoard(board);
                        if (board.hasWon()) {
                            int choice = JOptionPane.showConfirmDialog(
                                frame,
                                "Congratulations! You've reached 2048!\nRestart?",
                                "You Win!",
                                JOptionPane.YES_NO_OPTION
                            );

                            if (choice == JOptionPane.YES_OPTION) {
                                board.reset();
                                updateBoard(board);
                            } else {
                                System.exit(0);
                            }
                        }

                        if (!board.canMove()) {
                            int choice = JOptionPane.showConfirmDialog(
                                frame,
                                "Game Over! No more moves available.\nRestart?",
                                "Game Over",
                                JOptionPane.YES_NO_OPTION
                            );

                            if (choice == JOptionPane.YES_OPTION) {
                                board.reset();
                                updateBoard(board);
                            } else {
                                System.exit(0);
                            }
                        }
                        break;
                    case java.awt.event.KeyEvent.VK_RIGHT:
                    case java.awt.event.KeyEvent.VK_D:
                        board.moveRight();
                        board.spawnTile();
                        board.printBoard();
                        updateBoard(board);
                        if (board.hasWon()) {
                            int choice = JOptionPane.showConfirmDialog(
                                frame,
                                "Congratulations! You've reached 2048!\nRestart?",
                                "You Win!",
                                JOptionPane.YES_NO_OPTION
                            );

                            if (choice == JOptionPane.YES_OPTION) {
                                board.reset();
                                updateBoard(board);
                            } else {
                                System.exit(0);
                            }
                        }

                        if (!board.canMove()) {
                            int choice = JOptionPane.showConfirmDialog(
                                frame,
                                "Game Over! No more moves available.\nRestart?",
                                "Game Over",
                                JOptionPane.YES_NO_OPTION
                            );

                            if (choice == JOptionPane.YES_OPTION) {
                                board.reset();
                                updateBoard(board);
                            } else {
                                System.exit(0);
                            }
                        }
                        break;
                    case java.awt.event.KeyEvent.VK_UP:
                    case java.awt.event.KeyEvent.VK_W:
                        board.moveUp();
                        board.spawnTile();
                        board.printBoard();
                        updateBoard(board);
                        if (board.hasWon()) {
                            int choice = JOptionPane.showConfirmDialog(
                                frame,
                                "Congratulations! You've reached 2048!\nRestart?",
                                "You Win!",
                                JOptionPane.YES_NO_OPTION
                            );

                            if (choice == JOptionPane.YES_OPTION) {
                                board.reset();
                                updateBoard(board);
                            } else {
                                System.exit(0);
                            }
                        }

                        if (!board.canMove()) {
                            int choice = JOptionPane.showConfirmDialog(
                                frame,
                                "Game Over! No more moves available.\nRestart?",
                                "Game Over",
                                JOptionPane.YES_NO_OPTION
                            );

                            if (choice == JOptionPane.YES_OPTION) {
                                board.reset();
                                updateBoard(board);
                            } else {
                                System.exit(0);
                            }
                        }
                        break;
                    case java.awt.event.KeyEvent.VK_DOWN:
                    case java.awt.event.KeyEvent.VK_S:
                        board.moveDown();
                        board.spawnTile();
                        board.printBoard();
                        updateBoard(board);
                        if (board.hasWon()) {
                            int choice = JOptionPane.showConfirmDialog(
                                frame,
                                "Congratulations! You've reached 2048!\nRestart?",
                                "You Win!",
                                JOptionPane.YES_NO_OPTION
                            );

                            if (choice == JOptionPane.YES_OPTION) {
                                board.reset();
                                updateBoard(board);
                            } else {
                                System.exit(0);
                            }
                        }

                        if (!board.canMove()) {
                            int choice = JOptionPane.showConfirmDialog(
                                frame,
                                "Game Over! No more moves available.\nRestart?",
                                "Game Over",
                                JOptionPane.YES_NO_OPTION
                            );

                            if (choice == JOptionPane.YES_OPTION) {
                                board.reset();
                                updateBoard(board);
                            } else {
                                System.exit(0);
                            }
                        }
                        break;
                }
            }
        });
    }

    public void updateBoard(Board board) {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                int value = board.getTile(row, col);
                cells[row][col].setText(value == 0 ? "" : String.valueOf(value));
            }
        }
    }
}
