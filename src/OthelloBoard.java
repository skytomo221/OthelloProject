
public class OthelloBoard {
    private int size = 8;
    private int height = size;
    private int width = size;
    private OthelloCell[][] cells;

    public int getSize() {
        return size;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public OthelloBoard() {
        setup();
    }

    public OthelloBoard(OthelloBoard board) {
        cells = new OthelloCell[width][];
        for (int x = 0; x < width; x++) {
            cells[x] = new OthelloCell[height];
            for (int y = 0; y < height; y++) {
                cells[x][y] = new OthelloCell(board.getCell(x, y));
            }
        }
    }

    public void setup() {
        cells = new OthelloCell[width][];
        for (int x = 0; x < width; x++) {
            cells[x] = new OthelloCell[height];
            for (int y = 0; y < height; y++) {
                cells[x][y] = new OthelloCell();
            }
        }
    }

    public boolean isOutOfIndex(int n) {
        return n < 0 || size <= n;
    }

    public OthelloCell getCell(int x, int y) {
        if (isOutOfIndex(x) || isOutOfIndex(y)) {
            return null;
        } else {
            return cells[x][y];
        }
    }

    public OthelloCell getCell(Coordinate coordinate) {
        return getCell(coordinate.x, coordinate.y);
    }

}
