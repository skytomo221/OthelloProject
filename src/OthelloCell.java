
public class OthelloCell {
    private OthelloPiece piece;

    public OthelloCell() {
    }

    public OthelloCell(OthelloCell cell) {
        if (cell.getPiece() != null) {
            piece = new OthelloPiece(cell.getPiece());
        }
    }

    public OthelloPiece getPiece() {
        return this.piece;
    }

    public OthelloPiece putPiece(OthelloPiece piece) {
        return this.piece = piece;
    }
}
