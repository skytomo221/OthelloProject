import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.function.BiFunction;

public class BoardDrawer extends JPanel { //パネルのクラスJPanelを継承してクラスを作る
    Othello othello;
    Point boardPoint;
    Dimension boardDimension;
    Dimension cellDimension;
    public Coordinate enteredCell;

    public BoardDrawer(Othello othello) {
        super();
        this.othello = othello;
    }

    protected void calculation() {
        int cellSize = Math.min(getWidth(), getHeight());
        boardPoint = new Point((getWidth() - cellSize) / 2, (getHeight() - cellSize) / 2);
        boardDimension = new Dimension(cellSize, cellSize);
        cellDimension = new Dimension(cellSize / 8, cellSize / 8);
    }

    public void paintComponent(Graphics g) { //描画するメソッドをオーバーライドする
        calculation();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.GREEN); //ペンの色を設定する
        g.fillRect(boardPoint.x, boardPoint.y, boardDimension.width, boardDimension.height);
        Image img = Toolkit.getDefaultToolkit().getImage("./board.jpg");
        g.drawImage(img, boardPoint.x, boardPoint.y, boardDimension.width, boardDimension.height, this);
        {
            int i = 0;
            for (Coordinate coordinate : othello.getCanPutPieceList()) {
                i++;
                paintNumber(g, coordinate.x, coordinate.y, i);
            }
        }
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                g.setColor(Color.LIGHT_GRAY); //ペンの色を設定する
                g.drawRect(boardPoint.x + cellDimension.width * x,
                        boardPoint.y + cellDimension.height * y, cellDimension.width,
                        cellDimension.height);
                if (othello.getBoard().getCell(x, y).getPiece() != null) {
                    paintPiece(g, x, y,
                            othello.getBoard().getCell(x, y).getPiece().getColor() == OthelloPiece.Color.WHITE);
                }
            }
        }
    }

    public Coordinate getCoordinate(Point p) {
        return new Coordinate((p.x - boardPoint.x) / cellDimension.width, (p.y - boardPoint.y) / cellDimension.height);
    }

    public void paintNumber(Graphics g, int x, int y, int n) {
        int offsetX = (boardDimension.width / 8) * x + boardDimension.width / 80;
        int offsetY = (boardDimension.height / 8) * y + boardDimension.height / 80;
        BiFunction<Integer, Integer, Integer> getFontSize = (width, length) -> (int) (width / (1 * (length + 12)));
        Font boldFont = new Font("Agency FB", Font.BOLD, getFontSize.apply(boardDimension.width, Integer.toString(n).length()));
        if (enteredCell != null && enteredCell.equals(new Coordinate(x, y))) {
			g.setColor(othello.currentColor == OthelloPiece.Color.BLACK ? new Color(16, 16, 16, 200) : new Color(211, 211, 211, 100));
		}else{
			g.setColor(othello.currentColor == OthelloPiece.Color.BLACK ? new Color(16, 16, 16, 100) : new Color(211, 211, 211, 50));
		}
        g.fillRect(boardPoint.x + cellDimension.width * x,
                boardPoint.y + cellDimension.height * y, cellDimension.width,
                cellDimension.height);
        g.setFont(boldFont);
        g.setColor(othello.currentColor == OthelloPiece.Color.BLACK ? Color.LIGHT_GRAY : new Color(16, 16, 16));
        g.drawString(Integer.toString(n), boardPoint.x + cellDimension.width * x, boardPoint.y + cellDimension.height * (y + 1));
    }

    public void paintPiece(Graphics g, int x, int y, boolean white) {
        int offsetX = (boardDimension.width / 8) * x + boardDimension.width / 80;
        int offsetY = (boardDimension.height / 8) * y + boardDimension.height / 80;
        if (white) {
            g.setColor(Color.BLACK);
            g.fillOval(boardPoint.x + offsetX, boardPoint.y + offsetY + boardDimension.height / 80,
                    boardDimension.width / 10, boardDimension.height / 12);
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(boardPoint.x + offsetX, boardPoint.y + offsetY + boardDimension.height / 160,
                    boardDimension.width / 10, boardDimension.height / 12);
            g.setColor(Color.WHITE);
            g.fillOval(boardPoint.x + offsetX, boardPoint.y + offsetY, boardDimension.width / 10,
                    boardDimension.height / 12);
        } else {
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(boardPoint.x + offsetX, boardPoint.y + offsetY + boardDimension.height / 80,
                    boardDimension.width / 10, boardDimension.height / 12);
            g.setColor(Color.BLACK);
            g.fillOval(boardPoint.x + offsetX, boardPoint.y + offsetY + boardDimension.height / 160,
                    boardDimension.width / 10, boardDimension.height / 12);
            g.setColor(new Color(16, 16, 16));
            g.fillOval(boardPoint.x + offsetX, boardPoint.y + offsetY, boardDimension.width / 10,
                    boardDimension.height / 12);
        }
    }
}
