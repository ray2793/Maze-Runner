package raymond_li;
/**
 * Contains the space class that creates the space used to populate the maze
 * Contains various methods that change the type of the space
 * @author Raymond Li
 * @version 02/28/24
 */
public class Space {
	private char symbol;
	private int row;
	private int column;
	private SpaceRep spaceRep;

	/**
	 * Space class accepts SpaceRep argument that defines type of space
	 * and holds the coordinates of the space
	 * @param s
	 *            SpaceRep type argument that defines type of space
	 */
	public Space(SpaceRep s, int row, int column) {
		this.row = row;
		this.column = column;
		this.spaceRep = s;

		switch (s) {
		case INITIAL_POSITION:
			this.symbol = '*';
			break;
		case WALL:
			this.symbol = 'X';
			break;
		case CORRIDOR:
			this.symbol = ' ';
			break;
		case VISITED_CORRIDOR:
			this.symbol = '.';
			break;
		case WAY_OUT:
			this.symbol = 'o';
			break;
		case DISCOVERED_EXIT:
			this.symbol = 'E';
			break;
		}
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return symbol + "";
	}

	public SpaceRep getSpaceRep() {
		return spaceRep;
	}
	/**
	 * Changes the character display type of the space
	 * @param s
	 * 		the type of space being changed to
	 */
	public void setSpaceRep(SpaceRep s) {
		spaceRep = s;
		switch (s) {
		case INITIAL_POSITION:
			this.symbol = '*';
			break;
		case WALL:
			this.symbol = 'X';
			break;
		case CORRIDOR:
			this.symbol = ' ';
			break;
		case VISITED_CORRIDOR:
			if (this.symbol != '*')
			this.symbol = '.';
			break;
		case WAY_OUT:
			this.symbol = 'o';
			break;
		case DISCOVERED_EXIT:
			this.symbol = 'E';
			break;
		}

	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}