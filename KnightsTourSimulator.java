import java.util.Random;

public class KnightsTourSimulator {
	
	// board size
	private static final int ROWS = 8;
	private static final int COLUMNS = 8;

	private static KnightsTour run(KnightTourAlgorithm algorithm, Position start) {
		// state
		KnightsTour tour = new KnightsTour(ROWS, COLUMNS, start);
		algorithm.init(tour); // initialization

		// loop
		while (!tour.isComplete()) { // while not complete
			KnightMove move = algorithm.decide(tour); // make the decision
			if (move == null) // no possible move
				break; // stop the loop break
			tour.move(move); // make the move
		}

		// results
		return tour;
	}

	public static void main(String[] args) {
		{	// Random algorithm
			System.out.println("-- Random Algorithm: --");
			KnightsTour tour = run(new RandomAlgorithm(), new Position(0, 0));
			System.out.println("Move Count: " + tour.moveCount());
			System.out.println("Tour Completed: " + tour.isComplete());
			System.out.println(tour);
			System.out.println();
		}

		{	// (e.g. N=1000) Random algorithms
			final int N = 1000;
			final long seed = System.currentTimeMillis() + System.nanoTime();  // seed is the start to generate, help get the same result everytime, initialize
			System.out.println("-- " + N + " Random Algorithms: --");
			int count = 0;  // successful completion count
			int sum = 0;  // sum of move counts to calculate an average
			int max = 0;  // maximum move count achieved
			KnightsTour best = null;  // store best tour
			for (int i = 0; i < N; i++) {
				Random rng = new Random(seed + i);
				KnightsTour tour = run(new RandomAlgorithm(rng), new Position(rng.nextInt(ROWS), rng.nextInt(COLUMNS)) );
				if (tour.isComplete())
					count++;
				sum += tour.moveCount();
				if (tour.moveCount() > max) {
					max = tour.moveCount();
					best = tour;
				}
			}
			System.out.printf("Completion Percentage: %.1f%%%n", (double) count / N);
			System.out.println("Max move count: " + max);
			System.out.printf("Average move count: %.2f%n", (double) sum / N);
			System.out.println("Best tour:");
			System.out.println(best);
			System.out.println();
		}

		{	// Accessibilty Heuristic
			System.out.println("-- Accessibilty Heuristic: --");
			int count = 0;  // number of tours that completed successfully
			for (int row = 0; row < ROWS; row++)
				for (int column = 0; column < COLUMNS; column++) {
					Position start = new Position(row, column);
					KnightsTour tour = run(new AccessibilityAlgorithm(), start);
					if (tour.isComplete())
						count++;
					else
						System.out.println("Tour failed when starting from " + start);
				}
			System.out.println("Completed tours: " + count);
			System.out.println();
		}

		{	// Brute-Force Random Algorithm
			final int MAX_N = 10000000;  // limit: so program doesn't run forever
			final long seed = System.currentTimeMillis() + System.nanoTime();
			System.out.println("-- Brute-Force Random Algorithms: --");
			int sum = 0;  // sum of move counts to calculate an average
			int max = 0;  // maximum move count achieved
			KnightsTour best = null;  // store best tour
			int i;
			for (i = 0; i < MAX_N; ) {
				i++;

				Random rng = new Random(seed + i);
				KnightsTour tour = run(
						new RandomAlgorithm(rng),
						new Position(rng.nextInt(ROWS), rng.nextInt(COLUMNS)) );
				sum += tour.moveCount();
				if (tour.moveCount() > max) {
					System.out.println("working (" + max + ")...");
					max = tour.moveCount();
					best = tour;
				}

				if (tour.isComplete())
					break;
			}
			System.out.println("Iterations: " + i);
			System.out.printf("Average move count: %.2f%n", (double) sum / i);
			System.out.println("Best tour:");
			System.out.println(best);
			System.out.println();

		}
	}

}
