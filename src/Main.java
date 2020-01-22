
public class Main {

	public static void main(String[] args) {
		OthelloController controller = new OthelloController();
		OthelloViewer viewer = new OthelloViewer(controller.othello);
		controller.setViewer(viewer);
		viewer.setVisible(true);
	}

}
