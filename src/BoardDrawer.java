import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class BoardDrawer extends JPanel { //パネルのクラスJPanelを継承してクラスを作る
	Othello othello;
	Point boardPoint;
	Dimension boardDimension;
	Dimension cellDimension;

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
		g.fillRect(boardPoint.x, boardPoint.y, boardDimension.width, boardDimension.height); //楕円を描く
		Image img = Toolkit.getDefaultToolkit().getImage("./board.jpg");
		g.drawImage(img, boardPoint.x, boardPoint.y, boardDimension.width, boardDimension.height, this);
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				g.setColor(Color.LIGHT_GRAY); //ペンの色を設定する
				g.drawRect(boardPoint.x + cellDimension.width * x,
						boardPoint.y + cellDimension.height * y, cellDimension.width,
						cellDimension.height);
				if (othello.board.getCell(x, y).getPiece() != null) {
					paintCell(g, x, y,
							othello.board.getCell(x, y).getPiece().getColor() == OthelloPiece.Color.WHITE);
				}
			}
		}
	}

	public Coordinate getCoordinate(Point p) {
		return new Coordinate((p.x - boardPoint.x) / cellDimension.width, (p.y - boardPoint.y) / cellDimension.height);
	}

	public void paintCell(Graphics g, int x, int y, boolean white) {
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
