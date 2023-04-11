
/**
 *
 */
public class Position {
	
	public int row;
	public int column;

	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public Position() {
		this(0, 0);
	}


	public Position offset(KnightMove move) {
		// compute the destination row and column
		int r = row + move.rowOffset;
		int c = column + move.columnOffset;

		// convert to a Position object, and return
		return new Position(r, c);
	}

	@Override
	public Position clone() {
		return new Position(row, column);
	}

	@Override
	public String toString() {
		return String.format("(row=%s, column=%s)", row, column);
	}
}
