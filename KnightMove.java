/**
 * The 8 possible knight moves, enumerated.
 */
public enum KnightMove {
	NNE(-2, +1), ENE(-1, +2), ESE(+1, +2), SSE(+2, +1),
	SSW(+2, -1), WSW(+1, -2), WNW(-1, -2), NNW(-2, -1);


	public final int rowOffset;


	public final int columnOffset;

	private KnightMove(int rowOffset, int columnOffset) {
		this.rowOffset = rowOffset;
		this.columnOffset = columnOffset;
	}
}
