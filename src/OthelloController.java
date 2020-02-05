import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.regex.Pattern;

public class OthelloController implements ActionListener, ComponentListener, KeyListener, MouseListener, MouseMotionListener {
	protected OthelloViewer viewer;

	protected Othello othello;

	public OthelloController() {
		this.othello = new Othello();
	}

	public void setViewer(OthelloViewer viewer) {
		this.viewer = viewer;
		viewer.addActionListener(this);
		viewer.addMouseListener(this);
		viewer.boardDrawer.addMouseListener(this);
		viewer.boardDrawer.addMouseMotionListener(this);
		viewer.sendButton.addMouseListener(this);
        viewer.textField.addKeyListener(this);
	}

	public void parseInput() {
		String text = viewer.textField.getText();
		if (text.equals("reset")) {
		    othello.initialization();
        }
        else if (text.equals("undo")) {
            othello.undo();
        }
		else if (Pattern.matches("\\d+", text)) {
			int number = Integer.parseInt(text);
			int index = number - 1;
			if (index < othello.getCanPutPieceList().size()) {
				othello.putPiece(othello.getCanPutPieceList().get(index));
			}
		}
	}

	public void repaint() {
        viewer.repaint();
        if (othello.finish()) {
            int black = othello.count(OthelloPiece.Color.BLACK);
            int white = othello.count(OthelloPiece.Color.WHITE);
            String message = othello.turn + "ターン目で，終了しました．" + black + "対" + white + "で";
            if (black > white) {
                message += "黒の勝ちです！";
            } else if (black < white) {
                message += "白の勝ちです！";
            } else {
                message += "引き分けです！";
            }
            viewer.northLabel.setText(message);
        } else {
            int black = othello.count(OthelloPiece.Color.BLACK);
            int white = othello.count(OthelloPiece.Color.WHITE);
            String message = (othello.currentColor == OthelloPiece.Color.BLACK ? "黒" : "白") + "の番です．";
            message += "現在，" + othello.turn + "ターン目です．" + black + "対" + white + "です．";
            viewer.northLabel.setText(message);
        }
    }

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

	}

	@Override
	public void componentResized(ComponentEvent componentEvent) {

	}

	@Override
	public void componentMoved(ComponentEvent componentEvent) {

	}

	@Override
	public void componentShown(ComponentEvent componentEvent) {

	}

	@Override
	public void componentHidden(ComponentEvent componentEvent) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof BoardDrawer) {
			BoardDrawer p = (BoardDrawer) e.getSource();
			Coordinate c = p.getCoordinate(e.getPoint());
			othello.putPiece(c);
		} else if (e.getSource() == viewer.sendButton) {
			parseInput();
		}
        repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void mouseDragged(MouseEvent mouseEvent) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void mouseMoved(MouseEvent mouseEvent) {
		if (mouseEvent.getSource() instanceof BoardDrawer) {
			BoardDrawer p = (BoardDrawer) mouseEvent.getSource();
			Coordinate c = p.getCoordinate(mouseEvent.getPoint());
			p.enteredCell = c;
			p.repaint();
		}
	}

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                parseInput();
                repaint();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
