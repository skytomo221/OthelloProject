import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class OthelloViewer extends JFrame {

    public OthelloViewer(Othello othello) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Othello");
        setBounds(100, 100, 450, 300);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        setContentPane(panel);
        JPanel southPanel = new JPanel();
        JLabel label = new JLabel("コマンドを入力して戦闘開始．23を入力すると(x = 2, y = 3)のところにコマを置く．");
        JTextField textField = new JTextField();
        textField.setText("");
        JButton button = new JButton("送信");
        //textField.setColumns(30);
        southPanel.setLayout(new BorderLayout());
        southPanel.add(label, BorderLayout.NORTH);
        southPanel.add(textField, BorderLayout.CENTER);
        southPanel.add(button, BorderLayout.EAST);
        BoardDrawer boardDrawer = new BoardDrawer(othello);
        panel.add(boardDrawer, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);
    }

    public void addActionListener(OthelloController othelloController) {
    }

    class BoardDrawer extends JPanel { //パネルのクラスJPanelを継承してクラスを作る

        int boardWidth;
        int boardHeight;
        int cellSize;
        Othello othello;

        public BoardDrawer(Othello othello) {
            super();
            this.othello = othello;
        }

        public void paintComponent(Graphics g) { //描画するメソッドをオーバーライドする
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.GREEN); //ペンの色を設定する
            cellSize = Math.min(getWidth(), getHeight());
            boardWidth = (getWidth() - cellSize) / 2;
            boardHeight = (getHeight() - cellSize) / 2;
            g.fillRect(boardWidth, boardHeight, cellSize, cellSize); //楕円を描く
            Image img = Toolkit.getDefaultToolkit().getImage("./board.jpg");
            g.drawImage(img, boardWidth, boardHeight, cellSize, cellSize, this);
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    g.setColor(Color.LIGHT_GRAY); //ペンの色を設定する
                    g.drawRect(boardWidth + (cellSize / 8) * x, boardHeight + (cellSize / 8) * y, cellSize / 8, cellSize / 8);
                    if (othello.board.getCell(x, y).getPiece() != null) {
                        paintCell(g, x, y, othello.board.getCell(x, y).getPiece().getColor() == OthelloPiece.Color.WHITE);
                    }
                }
            }
        }

        public void paintCell(Graphics g, int x, int y, boolean white) {
            int pieceSize = cellSize / 10;
            int offsetX = (cellSize / 8) * x + cellSize / 80;
            int offsetY = (cellSize / 8) * y + cellSize / 80;
            if (white) {
                g.setColor(Color.BLACK);
                g.fillOval(boardWidth + offsetX, boardHeight + offsetY + cellSize / 80, cellSize / 10, cellSize / 12);
                g.setColor(Color.LIGHT_GRAY);
                g.fillOval(boardWidth + offsetX, boardHeight +offsetY + cellSize / 160, cellSize / 10, cellSize / 12);
                g.setColor(Color.WHITE);
                g.fillOval(boardWidth + offsetX, boardHeight +offsetY, cellSize / 10, cellSize / 12);
            }
            else {
                g.setColor(Color.LIGHT_GRAY);
                g.fillOval(boardWidth + offsetX, boardHeight + offsetY + cellSize / 80, cellSize / 10, cellSize / 12);
                g.setColor(Color.BLACK);
                g.fillOval(boardWidth + offsetX, boardHeight +offsetY + cellSize / 160, cellSize / 10, cellSize / 12);
                g.setColor(new Color(16 ,16 ,16));
                g.fillOval(boardWidth + offsetX, boardHeight + offsetY, cellSize / 10, cellSize / 12);
            }
        }
    }
}
