import java.util.Arrays;

public class KnightsTour {

	public static final int UNVISITED = 0;

	/**
	 * Each cell's number represents the turn on which the knight moved to the corresponding space.
	 * e.g. board[0][5] == 3, then the knight moved to position (row=0, column=5) on the 3rd move.
	 * Any cells marked with 0 haven't been visited yet.
	 * "board" must be a rectangular matrix.
	 */
	private int[][] board; // we have board


	private Position knight; // we have this as locrow, locol


	private int moveCount = 0; // we have movecounter


	public KnightsTour(int rows, int columns, Position start) {
		this.board = new int[rows][columns];  // filled with 0's ("UNVISITED by default
		moveTo(start);
	}


	private void moveTo(Position destination) {
		knight = destination;
		this.board[knight.row][knight.column] = ++moveCount;
	}


	public int rows() {
		return board.length;
	}


	public int columns() {
		if (rows() <= 0)
			return 0;
		return board[0].length;
	}


	public int get(Position p) {
		return board[p.row][p.column];
	}


	public Position knight() {
		return knight.clone();
	}


	public int moveCount() {
		return moveCount;
	}


	public boolean contains(Position p) {
		return (0 <= p.row && p.row < rows()) && (0 <= p.column && p.column < columns());
	}


	public boolean isValid(Position p) {
		return contains(p) && get(p) == UNVISITED;
	}


	public boolean isValid(KnightMove move) {
		return isValid(knight.offset(move));
	}


	  //@throws IllegalMoveException if the given KnightMove would lead to the knight moving off the board,

	public void move(KnightMove move) {
		Position destination = knight.offset(move);
		if (!isValid(destination))
			throw new IllegalMoveException();
		moveTo(destination);
	}

	public boolean isComplete() {
		return moveCount == rows() * columns();
	}


	public void reset(Position start) {
		for (int i = 0; i < board.length; i++)
			Arrays.fill(board[i], UNVISITED);
		moveCount = 0;
		moveTo(start);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int[] row : board)
			str.append(Arrays.toString(row)).append('\n');
		str.append("Knight: " + knight).append('\n');
		return str.toString();
	}
}
