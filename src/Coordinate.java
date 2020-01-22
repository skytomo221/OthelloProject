import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Coordinate {
	public int x;
	public int y;
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Supplier<Coordinate> leftUp = () -> new Coordinate(x - 1, y + 1);
	public Supplier<Coordinate> up = () -> new Coordinate(x, y + 1);
	public Supplier<Coordinate> rightUp = () -> new Coordinate(x + 1, y + 1);
	public Supplier<Coordinate> left = () -> new Coordinate(x - 1, y);
	public Supplier<Coordinate> right = () -> new Coordinate(x + 1, y);
	public Supplier<Coordinate> downLeft = () -> new Coordinate(x - 1, y - 1);
	public Supplier<Coordinate> down = () -> new Coordinate(x, y - 1);
	public Supplier<Coordinate> downRight = () -> new Coordinate(x + 1, y - 1);
	public List<Supplier<Coordinate>> arraws = Arrays.asList(
			leftUp, up, rightUp, left, right, downLeft, down, downRight);
}
