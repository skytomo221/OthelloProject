import java.util.function.Function;

public class Othello {
    OthelloBoard board = new OthelloBoard();
    /**
     * 現在のターンの石の色を表します。
     */
    OthelloPiece.Color currentColor = OthelloPiece.Color.BLACK;
    /**
     * 現在のターン数を表します。
     */
    int turn = 1;
    /**
     * ゲームが終了したかを表します。
     */
    boolean finish = false;

    public Othello() {
        board.getCell(3, 3).putPiece(new OthelloPiece(OthelloPiece.Color.WHITE));
        board.getCell(4, 4).putPiece(new OthelloPiece(OthelloPiece.Color.WHITE));
        board.getCell(3, 4).putPiece(new OthelloPiece(OthelloPiece.Color.BLACK));
        board.getCell(4, 3).putPiece(new OthelloPiece(OthelloPiece.Color.BLACK));
    }

    /**
     * 現在のターンの石の色を返します。
     *
     * @return 現在のターンの石の色
     */
    public OthelloPiece.Color getCurrentColor() {
        return currentColor;
    }

    public boolean canPutPiece(Coordinate coordinate) {
        if (board.getCell(coordinate) == null || board.getCell(coordinate).getPiece() != null) {
            return false;
        }
        for (Function<Coordinate, Coordinate> arrow : coordinate.arrows) {
            if (canPutPiece(coordinate, arrow)) {
                return true;
            }
        }
        return false;
    }

    public boolean canPutPiece(Coordinate coordinate, Function<Coordinate, Coordinate> arrow) {
        Coordinate neighbor = arrow.apply(coordinate);
        if (board.getCell(neighbor) == null || board.getCell(neighbor).getPiece() == null) {
            return false;
        } else if (currentColor == board.getCell(neighbor).getPiece().getColor()) {
            if (board.getCell(coordinate).getPiece() == null) {
                return false;
            } else {
                return true;
            }
        } else {
            return canPutPiece(neighbor, arrow);
        }
    }

    public void putPiece(Coordinate coordinate) {
        if (canPutPiece(coordinate)) {
            for (Function<Coordinate, Coordinate> arrow : coordinate.arrows) {
                board.getCell(coordinate).putPiece(new OthelloPiece(currentColor));
                reversePiece(coordinate, arrow);
            }
            currentColor = OthelloPiece.Color.reverse(currentColor);
        }
        //pass();
        //updateFinish();
    }

    protected void reversePiece(Coordinate coordinate, Function<Coordinate, Coordinate> arrow) {
        if (canPutPiece(arrow.apply(coordinate), arrow)) {
            board.getCell(arrow.apply(coordinate)).getPiece().reverse();
            reversePiece(arrow.apply(coordinate), arrow);
        }
    }

    public boolean finish() {
        return finish;
    }

    protected void pass() {
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getWidth(); y++) {
                if (canPutPiece(new Coordinate(x, y))) {
                    return;
                }
            }
        }
        currentColor = OthelloPiece.Color.reverse(currentColor);
    }

    protected void updateFinish() {
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getWidth(); y++) {
                for (int i = 0; i < 2; i++) {
                    if (canPutPiece(new Coordinate(x, y))) {
                        finish = true;
                        return;
                    }
                    currentColor = OthelloPiece.Color.reverse(currentColor);
                }
            }
        }
        finish = false;
    }

    /**
     * 現在のターン数を返します。
     *
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
