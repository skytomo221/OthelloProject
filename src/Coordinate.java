import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Coordinate {
	public int x;
	public int y;
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Function<Coordinate, Coordinate> leftUp = (c) -> new Coordinate(c.x - 1, c.y + 1);
	public Function<Coordinate, Coordinate> up = (c) -> new Coordinate(c.x, c.y + 1);
	public Function<Coordinate, Coordinate> rightUp = (c) -> new Coordinate(c.x + 1, c.y + 1);
	public Function<Coordinate, Coordinate> left = (c) -> new Coordinate(c.x - 1, c.y);
	public Function<Coordinate, Coordinate> right = (c) -> new Coordinate(c.x + 1, c.y);
	public Function<Coordinate, Coordinate> downLeft = (c) -> new Coordinate(c.x - 1, c.y - 1);
	public Function<Coordinate, Coordinate> down = (c) -> new Coordinate(c.x, c.y - 1);
	public Function<Coordinate, Coordinate> downRight = (c) -> new Coordinate(c.x + 1, c.y - 1);
	public List<Function<Coordinate, Coordinate>> arrows = Arrays.asList(
			leftUp, up, rightUp, left, right, downLeft, down, downRight);
}
