// make it possible to throw an IO exception
import java.io.IOException;

/**
 * This class performs a search on a given map file. It uses an ArrayStack to keep track of the MapCells that have been visited
 * or attempted to be visited.
 * @author sammi
 *
 */
public class StartSearch{
	
	// create an instance variable of type Map
	private Map map;

	/**
	 * Constructor starts the search with the given mapFile 
	 * @param mapFile the name of the map file to search
	 */
	public StartSearch(String mapFile) {
		
		// try to initialize the map object with the given file name
		try {
			map = new Map(mapFile);
		}
		// if there's an InvalidMapException, catch it
		catch (InvalidMapException e1) {
			System.out.println(e1);
		}
		// if there's a IOException, catch it
		catch (IOException e2) {
			System.out.println(e2);
		}

	}
	
	/**
	 * This method determines the best (unmarked) cell from the current cell to continue the path.
	 * @param currentCell current cell that we are on
	 * @return the best (unmarked) cell to go to next
	 */
	public MapCell bestCell(MapCell currentCell) {

		// create a new MapCell variable for the best(unmarked) cell to continue the path
		MapCell possibleNextCell;
		
		// loop through all the neighbours indexes
		for (int i = 0; i < 4; i++) {
			try {
				// try getting the neighbour
				possibleNextCell = currentCell.getNeighbour(i);
				// if it's not null
				if (possibleNextCell != null) {
					// check if the neighbour is Covid
					if (possibleNextCell.isCovid()) {
						// if so, return null
						return null;
					}
				}
			}
			// catch the InvalidNeighbourIndexException if it is thrown
			catch (InvalidNeighbourIndexException e) {
				System.out.println(e);
			}

		}
		
		// loop through all the neighbours indexes
		for (int i = 0; i < 4; i++) {
			try {
				// try getting the neighbour
				possibleNextCell = currentCell.getNeighbour(i);
				// if it's not null and not marked
				if (possibleNextCell != null && !possibleNextCell.isMarked()) {
					// check if it's of first priority (is the Destination cell)
					if (possibleNextCell.isExit()) {
						// if it is, check if it's a valid option using the helper method
						if (isValidPath (currentCell, possibleNextCell, i)) {
							// if it is, return the cell and exit the bestCell method
							return possibleNextCell;
						}
					}
				}
			}
			// catch the InvalidNeighbourIndexException if it is thrown
			catch (InvalidNeighbourIndexException e) {
				System.out.println(e);
			}

		}
		
		// loop through all the neighbours indexes
		for (int i = 0; i < 4; i++) {
			try {
				// try getting the neighbour
				possibleNextCell = currentCell.getNeighbour(i);
				// if it's not null and not marked
				if (possibleNextCell != null && !possibleNextCell.isMarked()) {
					// check if it's of second priority (is a Donut cell)
					if (possibleNextCell.isDonut()) {
						// if it is, check if it's a valid option using the helper method
						if (isValidPath (currentCell, possibleNextCell, i)) {
							// if it is, return the cell and exit the bestCell method
							return possibleNextCell;
						}
					}
				}
			}
			// catch the InvalidNeighbourIndexException if it is thrown
			catch (InvalidNeighbourIndexException e) {
				System.out.println(e);
			}

		}
		
		// loop through the neighbour indexes
		for (int i = 0; i < 4; i++) {
			try {
				// try getting the neighbour
				possibleNextCell = currentCell.getNeighbour(i);
				// if it's not null and not marked
				if (possibleNextCell != null && !possibleNextCell.isMarked()) {
					// check if it's of third priority (is a CrossPath cell)
					if (possibleNextCell.isCrossPath()) {
						// if it is, check if it's a valid option using the helper method
						if (isValidPath (currentCell, possibleNextCell, i)) {
							// if it is, return the cell and exit the bestCell method
							return possibleNextCell;
						}
					}
				}
			}
			// catch the InvalidNeighbourIndexException if it is thrown
			catch (InvalidNeighbourIndexException e) {
				System.out.println(e);
			}

		}
		
		// loop through all the neighbour indexes
		for (int i = 0; i < 4; i++) {
			try {
				// try getting the neighbour
				possibleNextCell = currentCell.getNeighbour(i);
				// if it's not null and not marked
				if (possibleNextCell != null && !possibleNextCell.isMarked()) {
					// check if it's of fourth priority (is a Vertical or Horizontal path)
					if (possibleNextCell.isVerticalPath() || possibleNextCell.isHorizontalPath()) {
						// if it is, check if it's a valid option using the helper method
						if (isValidPath (currentCell, possibleNextCell, i)) {
							// if it is, return the cell and exit the bestCell method
							return possibleNextCell;
						}
					}
				}
			}
			// catch the InvalidNeighbourIndexException if it is thrown
			catch (InvalidNeighbourIndexException e) {
				System.out.println(e);
			}

		}
		
		// if none of the neighbours are valid options, return null
		return null;

	}
	
	/**
	 * Private helper boolean method to check if the neighbour is a valid path option
	 * @param currentCell current cell that we are on
	 * @param possibleNextCell the possible next cell to go to
	 * @param i the index of possible next cell
	 * @return true if that path option is valid, false if it is not
	 */
	private boolean isValidPath (MapCell currentCell, MapCell possibleNextCell, int i) {
			
		// from the start point, the destination, a cross path or donut cell
			if (currentCell.isStart() || currentCell.isExit() || currentCell.isCrossPath() || currentCell.isDonut()) {
				// the path can go to a start point, destination, crosspath, donut cell OR
				if (possibleNextCell.isStart() || possibleNextCell.isExit() || possibleNextCell.isCrossPath() || possibleNextCell.isDonut()) {
					return true;
				}
				// vertical path if the path index is the north or south cell
				else if (possibleNextCell.isVerticalPath() && (i == 0 || i == 2)) {
					return true;
				}
				// horizontal path if the path index is the east or west cell
				else if (possibleNextCell.isHorizontalPath() && (i == 1 || i == 3)) {
					return true;
				}
			}
			
			// otherwise, if the current cell is a vertical path cell,
			else if (currentCell.isVerticalPath() && (i == 0 || i == 2)) {
				
				// it can go to pretty much any cell except the covid cell and horizontal path cell
				// check if it's a valid path and return true if it is
				if (possibleNextCell.isStart() || possibleNextCell.isExit() || possibleNextCell.isCrossPath() || possibleNextCell.isDonut() || possibleNextCell.isVerticalPath()) {
					return true;
				}
				
			}
			
			// otherwise, if the current cell is a horizontal path cell, 
			else if (currentCell.isHorizontalPath() && (i == 1 || i == 3)) {
				
				// it can go to pretty much any cell except the covid cell and vertical path cell
				// check if it's a valid path and return true if it is
				if (possibleNextCell.isStart() || possibleNextCell.isExit() || possibleNextCell.isCrossPath() || possibleNextCell.isDonut() || possibleNextCell.isHorizontalPath()) {
					return true;
				}
			}
		
		// if it's not a valid path, return false
		return false;
	}
	
	/**
	 * Method to run the algorithm that searches for a valid path from the start path to the 
	 * destination while following all the rules and restrictions.
	 * @return a string of actions that contains the entire sequence of visited cells separated with hyphens and suffixed with the energy level
	 */
	public String findPath() {
		
		// Create and initialize a stack
		ArrayStack<MapCell> stack = new ArrayStack<MapCell>();
		

		// Create an actionString variable.
		String actionString = "";
		
		// Create a status flag to indicate whether or not the destination has been found
		boolean statusFlag = false;
		
		// Get the start cell using the methods of the supplied class Map.
		MapCell startCell = map.getStart();
		
		// push the starting cell into the stack
		stack.push(startCell);
		// mark the cell as inStack (and Initial)
		startCell.markInitial();
		
		// Create a variable for the energy level and set it to 10 at the start
		int energyLevel = 10;
		
		
		// create variable for the current cell
		MapCell currentCell;
		// create variable for the next cell to go to
		MapCell unmarkedNeighbour;
		// create variable for the top cell
		MapCell topCell;
		
		// While the stack is not empty and the destination has not been reached
		while (!stack.isEmpty() && statusFlag == false) {
			
			// Peek at the top of the stack to get the current cell.
			currentCell = stack.peek();
			// Find the next unmarked neighbouring cell 
			unmarkedNeighbour = bestCell(currentCell);
			
			// Update the actionString to contain the cell being visited.
			actionString += currentCell.toString() + "-";
			
			// If such a next cell exists and the energy level is above 0:
			if (unmarkedNeighbour != null && energyLevel > 0) {
				//  Check if the next cell is the destination. If so, set the status flag to true.
				if (unmarkedNeighbour.isExit()) {
					statusFlag = true;
				}
				// Check if the next cell is a donut cell. If so, increase the energy level by 3.
				if (unmarkedNeighbour.isDonut()) {
					energyLevel += 3;
				}
				//  Push the neighbouring cell into the stack 
				stack.push(unmarkedNeighbour);
				// mark it as inStack
				unmarkedNeighbour.markInStack();
				//  Decrease the energy level by 1 because of this single movement.
				energyLevel--;
			}
			// Otherwise, since there are no unmarked neighbouring cells that can be added to the path, perform the following steps:
			else {
				//  Pop the top cell from the stack
				topCell = stack.pop();
				// mark it as out of stack
				topCell.markOutStack();
				// If that top cell is a donut cell,
				if (topCell.isDonut()) {
					// decrease the energy level by 3 (because we are undoing a donut consumption).
					energyLevel -= 3;
				}
				// If that top cell is anything other than the start point
				if (!topCell.isStart()) {
					// increase the energy level by 1 (because we are undoing a single movement).
					energyLevel ++;
				}
			}
		}
		
		// While the stack is not empty, perform the following
		while (!stack.isEmpty()) {
			// Pop the top cell from the stack
			topCell = stack.pop();
			// mark it as out of stack
			topCell.markOutStack();
		}
		
		// append the energy level to the end of the string in the format "E#"
		actionString += "E" + energyLevel;
		
		// return the final string
		return actionString;
	}
	
	/**
	 *  This is the method that starts the program. 
	 * @param args
	 */
	public static void main (String[] args) {
		// If one argument is given, use it to initialize a StartSearch object with that map name. 
		if (args.length < 1) {
			System.out.println("You must provide the name of the input file");
			}
			String mapFile = args[0];
			// create a new StartSearch object
			StartSearch ss = new StartSearch(mapFile);
			// Call the findPath method on the StartSearch object.
			ss.findPath();
		
	}
	

}
