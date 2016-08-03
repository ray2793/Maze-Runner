package raymond_li;
/**
 * SpaceRep type indicates the type of space within the maze:
 * either it is an initial position, wall, corridor,
 * visited corridor, way out, or discovered exit
 * @author Raymond Li
 * @version 02/28/14
 *
 */
public enum SpaceRep {
	
		INITIAL_POSITION,
		WALL, 
		CORRIDOR, 
		VISITED_CORRIDOR, 
		WAY_OUT,
		DISCOVERED_EXIT
	
}

