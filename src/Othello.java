
public class Othello {
	OthelloBoard board = new OthelloBoard();
	/**
	 * 現在のターンの石の色を表します。
	 */
	OthelloPiece.Color color = OthelloPiece.Color.BLACK;
	/**
	 * 現在のターン数を表します。
	 */
	int turn = 1;

	public Othello() {
		board.getCell(3, 3).putPiece(new OthelloPiece(OthelloPiece.Color.WHITE));
		board.getCell(4, 4).putPiece(new OthelloPiece(OthelloPiece.Color.WHITE));
		board.getCell(3, 4).putPiece(new OthelloPiece(OthelloPiece.Color.BLACK));
		board.getCell(4, 3).putPiece(new OthelloPiece(OthelloPiece.Color.BLACK));
	}

	/**
	 * 現在のターンの石の色を返します。
	 * @return 現在のターンの石の色
	 */
	public OthelloPiece.Color getCurrentColor() {
		return color;
	}

	public void putPiece(Coordinate coordinate) {
		board.getCell(coordinate.x, coordinate.y).putPiece(new OthelloPiece(getCurrentColor()));
		color = (color == OthelloPiece.Color.BLACK) ? OthelloPiece.Color.WHITE : OthelloPiece.Color.BLACK;
	}

	/**
	 * 現在のターン数を返します。
	 * @return 現在のターン数
	 */
	public int getTurn() {
		return turn;
	}

	public OthelloPiece.Color getOthelloPieceColor(int x, int y) {
		return (board.getCell(x, y) == null)
				? null
				: board.getCell(x, y).getPiece().getColor();
	}
}
