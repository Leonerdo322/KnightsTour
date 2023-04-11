
/**
 * Used to make decisions about how to complete a knight
 */
public interface KnightTourAlgorithm {
	

	default void init(KnightsTour board) {
		// do nothing by default
	}


	KnightMove decide(KnightsTour board);
}
