import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Othello {
    /**
     * 現在のターンの石の色を表します。
     */
    OthelloPiece.Color currentColor = OthelloPiece.Color.BLACK;
    /**
     * 現在のターン数を表します。
     */
    int turn = 1;
    /**
     * 対戦の履歴を表します。
     */
    List<OthelloBoard> history = new ArrayList<OthelloBoard>();
    /**
     * ゲームが終了したかを表します。
     */
    boolean finish = false;

    public OthelloBoard getBoard() {
        return history.get(getTurn());
    }

    public Othello() {
        history.add(new OthelloBoard());
        history.add(new OthelloBoard(history.get(0)));
        getBoard().getCell(3, 3).putPiece(new OthelloPiece(OthelloPiece.Color.WHITE));
        getBoard().getCell(4, 4).putPiece(new OthelloPiece(OthelloPiece.Color.WHITE));
        getBoard().getCell(3, 4).putPiece(new OthelloPiece(OthelloPiece.Color.BLACK));
        getBoard().getCell(4, 3).putPiece(new OthelloPiece(OthelloPiece.Color.BLACK));
    }

    /**
     * 現在のターンの石の色を返します。
     *
     * @return 現在のターンの石の色
     */
    public OthelloPiece.Color getCurrentColor() {
        return currentColor;
    }

    public boolean finish() {
        return finish;
    }

    public int count(OthelloPiece.Color color) {
        int count = 0;
        for (int x = 0; x < getBoard().getWidth(); x++) {
            for (int y = 0; y < getBoard().getHeight(); y++) {
                OthelloCell cell = getBoard().getCell(x, y);
                if (cell.getPiece() != null && cell.getPiece().getColor() == color) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean canPutPiece(Coordinate coordinate) {
        if (getBoard().getCell(coordinate) == null || getBoard().getCell(coordinate).getPiece() != null) {
            return false;
        }
        for (Function<Coordinate, Coordinate> arrow : coordinate.arrows) {
            if (canPutPiece(arrow.apply(coordinate), arrow)) {
                return true;
            }
        }
        return false;
    }

    public boolean canPutPiece(Coordinate coordinate, Function<Coordinate, Coordinate> arrow) {
        Coordinate neighbor = arrow.apply(coordinate);
        if (getBoard().getCell(neighbor) == null || getBoard().getCell(neighbor).getPiece() == null) {
            return false;
        } else if (getBoard().getCell(coordinate).getPiece() == null || getBoard().getCell(coordinate).getPiece().getColor() == getCurrentColor()) {
            return false;
        } else if (getBoard().getCell(neighbor).getPiece().getColor() == getCurrentColor()) {
            return true;
        } else {
            return canPutPiece(neighbor, arrow);
        }
    }

    public List<Coordinate> getCanPutPieceList() {
        List<Coordinate> list = new ArrayList<Coordinate>();
        for (int y = 0; y < getBoard().getHeight(); y++) {
            for (int x = 0; x < getBoard().getWidth(); x++) {
                Coordinate coordinate = new Coordinate(x, y);
                if (canPutPiece(coordinate)) {
                    list.add(coordinate);
                }
            }
        }
        return list;
    }

    public void putPiece(Coordinate coordinate) {
        if (canPutPiece(coordinate)) {
            for (Function<Coordinate, Coordinate> arrow : coordinate.arrows) {
                getBoard().getCell(coordinate).putPiece(new OthelloPiece(currentColor));
                reversePiece(coordinate, arrow);
            }
            currentColor = OthelloPiece.Color.reverse(currentColor);
            pass();
            updateFinish();
            history.add(new OthelloBoard(getBoard()));
            turn++;
        }
    }

    protected void reversePiece(Coordinate coordinate, Function<Coordinate, Coordinate> arrow) {
        if (getBoard().getCell(arrow.apply(coordinate)) != null &&
                getBoard().getCell(arrow.apply(coordinate)).getPiece() != null &&
                canPutPiece(arrow.apply(coordinate), arrow)) {
            getBoard().getCell(arrow.apply(coordinate)).getPiece().reverse();
            reversePiece(arrow.apply(coordinate), arrow);
        }
    }

    protected void pass() {
        for (int x = 0; x < getBoard().getWidth(); x++) {
            for (int y = 0; y < getBoard().getWidth(); y++) {
                if (canPutPiece(new Coordinate(x, y))) {
                    return;
                }
            }
        }
        currentColor = OthelloPiece.Color.reverse(currentColor);
    }

    protected void updateFinish() {
        for (int x = 0; x < getBoard().getWidth(); x++) {
            for (int y = 0; y < getBoard().getWidth(); y++) {
                if (canPutPiece(new Coordinate(x, y))) {
                    finish = false;
                    return;
                }
                currentColor = OthelloPiece.Color.reverse(currentColor);
                if (canPutPiece(new Coordinate(x, y))) {
                    finish = false;
                    currentColor = OthelloPiece.Color.reverse(currentColor);
                    return;
                }
                currentColor = OthelloPiece.Color.reverse(currentColor);
            }
        }
        finish = true;
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
        return (getBoard().getCell(x, y) == null)
                ? null
                : getBoard().getCell(x, y).getPiece().getColor();
    }
}
