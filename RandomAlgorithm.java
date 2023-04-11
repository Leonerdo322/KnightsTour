import java.util.Random;

/**
 * Attempts to solve the Knight Tour problem by making random moves.
 */
public class RandomAlgorithm implements KnightTourAlgorithm {
	/**
	 * Random number generator.
	 */
	private Random rng;

	/**
	 * Use the given random number generator to decide random valid moves.

	 */
	public RandomAlgorithm(Random rng) {
		this.rng = rng;
	}


	public RandomAlgorithm() {
		this(new Random());
	}

	/**
	 * Uses a random number generator to pick a random valid move at each step.
	 */
	@Override
	public KnightMove decide(KnightsTour board) {
		KnightMove[] possibleMoves = KnightMove.values();  // the 8 possible KnightMoves

		// filter out all invalid moves, so only valid moves are in the `validMoves` array
		KnightMove[] validMoves = new KnightMove[possibleMoves.length];
		int n = 0;  // number of valid moves
		for (KnightMove move : possibleMoves)
			if (board.isValid(move))
				validMoves[n++] = move;  // add to valid moves array, and increment `n`
		
		if (n == 0)
			return null;  // there are no more valid moves from this position
		return validMoves[rng.nextInt(n)];  // return a random valid move
	}

}
