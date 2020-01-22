
public class OthelloCell {
	private OthelloPiece piece;
	public OthelloCell() {}
	public OthelloPiece getPiece() {
		return this.piece;
	}
	public OthelloPiece putPiece(OthelloPiece piece) {
		return this.piece = piece;
	}
}
