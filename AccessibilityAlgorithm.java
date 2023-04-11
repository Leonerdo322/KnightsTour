
public class AccessibilityAlgorithm implements KnightTourAlgorithm {
	
	private int[][] accessibilityMatrix = null;

	/**
	 * Build the accessibility matrix used by the decide method.
	 */
	@Override
	public void init(KnightsTour board) {
		final int ROWS = board.rows();
		final int COLUMNS = board.columns();
		accessibilityMatrix = new int[ROWS][COLUMNS];

		for (int row = 0; row < ROWS; row++)
			for (int column = 0; column < COLUMNS; column++) {
				// for each position `p` in `board`
				Position p = new Position(row, column);
				
				// Count the number of valid knight moves from this posistion.
				// (Which is the same as the number of valid knight moves to this
				// position, because all knight moves are invertable.)
				int count = 0;
				for (KnightMove move : KnightMove.values())
					if (board.isValid(p.offset(move)))
						count++;
				
				// update accessibility matrix
				accessibilityMatrix[row][column] = count;
			}
	}

	@Override
	public KnightMove decide(KnightsTour board) {
		// Use low-water-mark algorithm to find the (valid) move
		// with the lowest accessibility socre. 
		KnightMove bestMove = null;  // i.e. least accessible valid move
		int bestScore = Integer.MAX_VALUE;  // accessibilty score of `bestMove`
		for (KnightMove move : KnightMove.values()) {
			Position destination = board.knight().offset(move);
			if (!board.isValid(destination))
				continue;  // we ignore invalid moves from consideration
			
			// accessibilty score for the given move
			int score = accessibilityMatrix[destination.row][destination.column];
			
			// have we found a better (i.e. more accessible) move?
			if (score < bestScore) {
				// then update the `bestMove` and its associated `bestScore`.
				bestMove = move;
				bestScore = score;
			}
		}

		if (bestMove != null) {
			// Now we need to update the accessibiltyMatirx to reflect this new move.
			// All the "adjacent" squares have one less valid way to get to them
			// since this square is no longer valid. (It has been visited.)
			Position knight = board.knight().offset(bestMove);  // new knight position
			accessibilityMatrix[knight.row][knight.column] = 0;
			for (KnightMove move : KnightMove.values()) {
				Position destination = knight.offset(move);
				if (board.contains(destination))
					accessibilityMatrix[destination.row][destination.column]--;
			}
		}
		
		// Done.
		return bestMove;
	}

}
