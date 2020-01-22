
public class OthelloPiece {
	public enum Color {
		BLACK, WHITE,
	}
	private Color status;
	public Color getColor() {
		return status;
	}
	public OthelloPiece(Color status) {
		this.status = status;
	}
	public void reverse() {
		switch (status) {
		case BLACK:
			status = Color.WHITE;
			break;
		case WHITE:
			status = Color.BLACK;
			break;
		}
	}
}
