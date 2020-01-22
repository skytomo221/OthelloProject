
public class OthelloBoard {
    private int size = 8;
    private int height = size;
    private int width = size;
    private OthelloCell[][] cells;


    public OthelloBoard() {
        setup();
    }

    public void setup() {
        cells = new OthelloCell[width][];
        for (int x = 0; x < width; x++) {
            cells[x] = new OthelloCell[height];
            for (int y = 0; y < height; y++) {
                cells[x][y] = new OthelloCell();
            }
        }
        System.out.println("OK!");
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
