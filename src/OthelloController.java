import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class OthelloController implements ActionListener, ComponentListener {
    protected OthelloViewer viewer;

    protected Othello othello;

    public OthelloController() {
        this.othello = new Othello();
    }

    public void setViewer(OthelloViewer viewer) {
        this.viewer = viewer;
        viewer.addActionListener(this);
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
}
