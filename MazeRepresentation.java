package raymond_li;
import java.util.ArrayList;

/**
 * This is the maze class that creates the maze object.Contains methods for 
 * getting the maze, changing space types within the maze, adding spaces,and
 * getting neighbors of spaces
 * @author Raymond Li
 * @version 02/28/14
 */
public class MazeRepresentation {

	private int rows;		//contains number of rows in maze
	private int columns;	//contains number of columns in maze
	private Space[][] maze;	//contains maze

	/**
	 * Creates the maze that holds space objects
	 * @param rows
	 * 		# of rows in maze
	 * @param columns
	 * 		# of columns in maze
	 * @param spaceMaze
	 * 		#2D space array maze
	 */
	public MazeRepresentation(int rows, int columns, Space[][] spaceMaze) {
		this.rows = rows;
		this.columns = columns;
		this.maze = spaceMaze;

	}
	
	public Space[][] getMaze() {
		return maze;
	}

	public void setMaze(Space[][] maze) {
		this.maze = maze;
	}
	/**
	 * Add a space to a specific coordinate location
	 * @param s
	 * @param row
	 * @param column
	 */
	public void add(Space s, int row, int column) {
		maze[row][column] = s;
	}

	public SpaceRep returnSpaceType(int row, int column) {
		return maze[row][column].getSpaceRep();
	}
/**
 * Changes type of space
 * @param space
 * 		Current space to be changed
 * @param s
 * 		The new type of space desired
 */
	public void changeSpaceType(Space space, SpaceRep s) {
		switch (s) {
		case INITIAL_POSITION:
			space.setSpaceRep(SpaceRep.INITIAL_POSITION);
			space.setSymbol('*');
			break;
		case WALL:
			space.setSpaceRep(SpaceRep.WALL);
			space.setSymbol('X');
			break;
		case CORRIDOR:
			space.setSpaceRep(SpaceRep.CORRIDOR);
			space.setSymbol(' ');
			break;
		case VISITED_CORRIDOR:
			space.setSpaceRep(SpaceRep.VISITED_CORRIDOR);
			space.setSymbol('.');
			break;
		case WAY_OUT:
			space.setSpaceRep(SpaceRep.WAY_OUT);
			space.setSymbol('o');
			break;
		case DISCOVERED_EXIT:
			space.setSpaceRep(SpaceRep.DISCOVERED_EXIT);
			space.setSymbol('E');
			break;
		}
	}

	/**
	 * Returns an arraylist of Space objects that represent neighbors to the
	 * space given in the parameter. Checks order in North, East, South, West
	 * 
	 * @param space
	 * @return
	 */
	public ArrayList<Space> findNeighbors(Space space) {

		ArrayList<Space> neighbors = new ArrayList<Space>();

		Space north = maze[space.getRow() - 1][space.getColumn()];
		Space east = maze[space.getRow()][space.getColumn() + 1];
		Space south = maze[space.getRow() + 1][space.getColumn()];
		Space west = maze[space.getRow()][space.getColumn() - 1];

		if (north.getSpaceRep() != SpaceRep.WALL) {
			neighbors.add(north);
		}
		if (east.getSpaceRep() != SpaceRep.WALL) {
			neighbors.add(east);
		}
		if (south.getSpaceRep() != SpaceRep.WALL) {
			neighbors.add(south);
		}
		if (west.getSpaceRep() != SpaceRep.WALL) {
			neighbors.add(west);
		}
		return neighbors;
	}

	public String toString() {
		String mazeString = "";
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				mazeString += (maze[i][j].toString());
			}
			mazeString += "\n";
		}
		return mazeString;
	}

}

