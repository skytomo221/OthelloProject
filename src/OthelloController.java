import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OthelloController implements ActionListener, ComponentListener, MouseListener {
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
			p.repaint();
		}
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
}
