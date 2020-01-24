
public class OthelloPiece {
    public enum Color {
        BLACK, WHITE;

        public static Color reverse(Color color) {
            return (color == OthelloPiece.Color.BLACK)
                    ? OthelloPiece.Color.WHITE
                    : OthelloPiece.Color.BLACK;
        }
    }

    private Color color;

    public Color getColor() {
        return color;
    }

    public OthelloPiece(Color color) {
        this.color = color;
    }

    public OthelloPiece(OthelloPiece piece) {
        this.color = piece.getColor();
    }

    public void reverse() {
        color = Color.reverse(getColor());
    }
}
