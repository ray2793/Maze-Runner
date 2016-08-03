package raymond_li;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the maze game itself and calls the main method.
 * This class is responsible for reading the input file, initializing the maze,
 * applying the algorithm that searches for an exit, printing each state of the search to console,
 * and printing the list of visited spaces to the output file.
 * Two output files are generated: one for the stack and one for the queue.
 * @author Raymond Li
 * @version 02/28/14
 */
public class Maze_Console {

	static int initRow = 0;				//	row of initial position
	static int initCol = 0;				//	column of initial position
	static int rowCounter = 0;			// 	counter keeps track of total rows
	static int columns = 0; 			// 	total columns
	static int column1 = 0;				//	number of columns in 1st row of maze used to ensure consistency of columns in each row
	static boolean equalCol = true;		// 	equals true if equal number of columns in each maze row
	static char initialPosition = '0';	//	contains initial position to check that it is an empty space
	static String line = null;			//	used to store each lien of the text maze from input file
	static String mazeString = "";		//	used to store entire maze as string
	static boolean hasValidChars = true;//used to check that all characters in maze in the text file are either 'x', 'o', or ' '
	
	/**
	 * This verifies that the initial position is valid, the maze contains valid characters,
	 * 
	 * 
	 * @param file
	 *            Is the input maze txt file
	 * @return Returns string representation of maze
	 * @throws FileNotFoundException
	 *             throws exception if file cannot be found
	 */
	public static String VerifyInput(String file) throws FileNotFoundException {
		// Read the file input as a string and find the row and column
		// coordinates
		Scanner input = new Scanner(new FileReader(file));
		initRow = input.nextInt();
		initCol = input.nextInt();

		input.nextLine(); // skip to next line where maze begins
		while (input.hasNextLine()) {
			rowCounter++;
			line = input.nextLine(); // store each line in a string
			//checks each line for valid characters
			for (int i=0; i<line.length(); i++){
				char c = line.charAt(i);
				if (c != ' ' && c != 'x' && c != 'o') hasValidChars = false;		
			}
			mazeString += line + "\n"; // add each string to the maze
			// Check that there are equal columns in every row of the maze 
			column1 = line.length();
			if (rowCounter >= 2) {
				columns = line.length();
				if (columns != column1) {
					equalCol = false;
				}
			}
			// check that initial position is valid
			if (initRow + 1 == rowCounter)
				initialPosition = line.charAt(initCol);
		}
		// if valid maze, return mazeString. Else print error statement and return null
		if ((initialPosition == ' ' && equalCol == true
				&& initRow <= rowCounter && initCol <= columns && hasValidChars == true))
			return mazeString;
		else
			System.out.println("Invalid maze entered. Please enter valid maze.");
			return null;
	}
	/**
	 * Converts the string maze from text file to a 2D character array representation of the maze
	 * @param maze
	 * 		the string maze read from the input file
	 * @return
	 * 		returns a 2D array of characters representing the maze
	 * @throws IOException
	 */
	public static char[][] convertToChar(String maze) throws IOException {
		String line = null;
		char[][] mazeChars = new char[rowCounter][columns];
		int counter = 0;
		BufferedReader buffReader = new BufferedReader(new StringReader(maze));
		while ((line = buffReader.readLine()) != null) {
			char[] characters = line.toCharArray();

			mazeChars[counter] = characters;
			// System.out.println(charArray[counter]);
			counter++;
			// System.out.println(grid.length);
		}
		return mazeChars;
	}
/**
 * converts 2d array of characters to maze object that contains a 2D array of spaces
 * @param mazeChars
 * 		Contains 2D array of characters generated from reading input text file
 * @return
 * 		Returns the maze object 
 */
	public static MazeRepresentation convertToMaze(char[][] mazeChars) {
		Space[][] mazeSpace = new Space[Maze_Console.rowCounter][Maze_Console.columns];
		MazeRepresentation maze = new MazeRepresentation(rowCounter, columns,
				mazeSpace);
		SpaceRep spacePos = null;
		for (int i = 0; i < mazeChars.length; i++) {
			for (int j = 0; j < mazeChars[0].length; j++) {
				char character = mazeChars[i][j];

				switch (character) {
				case '*':
					spacePos = SpaceRep.INITIAL_POSITION;
					break;
				case 'x':
					spacePos = SpaceRep.WALL;
					break;
				case ' ':
					spacePos = SpaceRep.CORRIDOR;
					break;
				case '.':
					spacePos = SpaceRep.VISITED_CORRIDOR;
					break;
				case 'o':
					spacePos = SpaceRep.WAY_OUT;
					break;
				case 'E':
					spacePos = SpaceRep.DISCOVERED_EXIT;
					break;
				}
				Space space = new Space(spacePos, i, j);
				maze.add(space, i, j);
			}
		}
		return maze;
	}
	/**
	 * Method that executes search algorithm on given maze
	 * @param maze
	 * 		is maze object
	 * @param stackOrQueue
	 * 		is either the stack or queue where neighbored are stored
	 * @param outputFile
	 * 		output file where coordinates of visited spaces are printed to
	 * @throws FileNotFoundException
	 */
	public static void searchForExit(MazeRepresentation maze,
			SetOfSpaces stackOrQueue, File outputFile)
			throws FileNotFoundException {

		PrintWriter outputPrinter = new PrintWriter(outputFile);

		Space current;
		ArrayList<Space> neighbors;
		// Find initial position within maze and set type to initial position
		maze.changeSpaceType(maze.getMaze()[initRow][initCol],
				SpaceRep.INITIAL_POSITION);
		Space start = maze.getMaze()[initRow][initCol];

		stackOrQueue.add(start);
		//System.out.println(maze);

		int outputRow = 0;
		int outputCol = 0;
		//	While stack or queue is not empty, perform search algorithm
		while (!stackOrQueue.isEmpty()) {

			current = stackOrQueue.remove();
			if (current.getSpaceRep() == SpaceRep.VISITED_CORRIDOR)
				// continue search if space has already been visited
				continue;
			else if (current.getSpaceRep() == SpaceRep.WAY_OUT) {
				// if space is an exit, print to output, and stop program
				current.setSpaceRep(SpaceRep.DISCOVERED_EXIT);
				outputRow = current.getRow();
				outputCol = current.getColumn();
				outputPrinter.println(outputRow + " " + outputCol);
				System.out.println(maze);
				break;
			} else {
				//	if space has not been visited or is not an exit, examine neighbors and add
				//	spaces to stack or queue if they aren't walls
				neighbors = maze.findNeighbors(current);
				for (int i = 0; i < neighbors.size(); i++) {
					if (neighbors.get(i).getSpaceRep() != SpaceRep.WALL) {
						stackOrQueue.add(neighbors.get(i));
					}
				}
				// change popped space to visited
				current.setSpaceRep(SpaceRep.VISITED_CORRIDOR);

				// store these coordinates for output
				outputRow = current.getRow();
				outputCol = current.getColumn();
				outputPrinter.println(outputRow + " " + outputCol);

				// print out the maze after you change the space to visited with
				// 500 milliseconds of sleep before printing again
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {}
				clearScreen(maze);
				System.out.println(maze);
			}
		}
		outputPrinter.close();
	}
	/**
	 * Clears screen by printing out a number of blank lines equal to height of maze
	 * @param maze
	 * 		parameter is the maze
	 */
	public static void clearScreen(MazeRepresentation maze) {
		for (int i = 0; i < maze.getMaze().length; i++ )		{
			System.out.println(" ");
		}
	}
	/**
	 * Driver program for the maze game
	 * @param args
	 * 		input files entered into the command line
	 * @throws IOException
	 * 		throws exception in case file cannot be read
	 */
	public static void main(String[] args) throws IOException {

		// read commandline arguments and check if they exist. If they don't
		// exit the program
		File inputMaze = new File(args[0]);
		if (!inputMaze.exists()) {
			System.out.println("1st File does not exist");
			System.exit(2);
		}

		File stackOutput = new File(args[1]);
		if (!stackOutput.exists()) {
			System.out.println("2nd File does not exist");
			System.exit(2);
		}

		File queueOutput = new File(args[2]);
		if (!queueOutput.exists()) {
			System.out.println("3rd File does not exist");
			System.exit(2);
		}
		//verify the maze has no errors in it
		String mazeString = VerifyInput(args[0]);
		// convert the maze to a 2d array of characters
		char[][] mazeChars = convertToChar(mazeString);

		// create maze for stack, create stack, and call search algorithm using
		// the maze and queue
		MazeRepresentation mazeForStacks = convertToMaze(mazeChars);
		SpaceStack listofSpacesStack = new SpaceStack();
		searchForExit(mazeForStacks, listofSpacesStack, stackOutput);

		// create maze for queue, create queue, and call search algorithm using
		// the maze and queue
		MazeRepresentation mazeForQueues = convertToMaze(mazeChars);
		SpaceQueue listofSpacesQueues = new SpaceQueue();
		searchForExit(mazeForQueues, listofSpacesQueues, queueOutput);

	}// main
}// class	