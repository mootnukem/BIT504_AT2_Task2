import java.awt.*;

public class Board {
	// grid line width
	public static final int GRID_WIDTH = 8;
	// grid line half width
	public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;
	
	//2D array of ROWS-by-COLS Cell instances
	Cell [][] cells;
	
	/** Constructor to create the game board */
	public Board() {
		
	 //initialise the 2D array 'Cell' using ROWS and COLS constants 
		cells = new Cell[GameMain.ROWS][GameMain.COLS];//Array created cells of Cell, size determined by constants.
		
		//nested loops for each element of the 2D array
		for (int row = 0; row < GameMain.ROWS; ++row) { //iterates through each row
			for (int col = 0; col < GameMain.COLS; ++col) { //iterates through each column within current row
				cells[row][col] = new Cell(row, col); //create new instance of Cell, row and col values passed to Cell constructor
			}
		}
	}
	

	 /** Return true if it is a draw (i.e., no more EMPTY cells) */ 
	public boolean isDraw() { 
		 
		//Check whether the game has ended in a draw. 
		for (int row = 0; row < GameMain.ROWS; ++row) {//for loops iterate through each cell
			for (int col = 0; col < GameMain.COLS; ++ col) {
				if (cells[row][col].content == Player.Empty) { //If any cell is still empty, game is not a draw
					return false; // isDraw return false
				}
			}
		}
		return true; //If no empty cell exists, isDraw returns true
	}
	
	/** Return true if the current player "thePlayer" has won after making their move  */
	public boolean hasWon(Player thePlayer, int playerRow, int playerCol) {
		 // check if player has 3-in-that-row
		if(cells[playerRow][0].content == thePlayer && cells[playerRow][1].content == thePlayer && cells[playerRow][2].content == thePlayer )
			return true; 
		
		 //Check if the player has 3 in the playerCol.
		if (cells[0][playerCol].content == thePlayer && cells[1][playerCol].content == thePlayer && cells[2][playerCol].content == thePlayer) 
			return true;
						
		 // Check if player has 3-in-the-diagonal
		if( cells[0][0].content == thePlayer && cells[1][1].content == thePlayer && cells[2][2].content == thePlayer) 
			return true;
				
		//Check the diagonal in the other direction
		if (cells[0][2].content == thePlayer && cells[1][1].content == thePlayer && cells[2][0].content == thePlayer) 
			return true;
				
		//no winner, keep playing
		return false;
	}
	
	/**
	 * Draws the grid (rows then columns) using constant sizes, then call on the
	 * Cells to paint themselves into the grid
	 */
	public void paint(Graphics g) {
		//draw the grid
		g.setColor(Color.gray);
		for (int row = 1; row < GameMain.ROWS; ++row) {          
			g.fillRoundRect(0, GameMain.CELL_SIZE * row - GRID_WIDTH_HALF,                
					GameMain.CANVAS_WIDTH - 1, GRID_WIDTH,                
					GRID_WIDTH, GRID_WIDTH);       
			}
		for (int col = 1; col < GameMain.COLS; ++col) {          
			g.fillRoundRect(GameMain.CELL_SIZE * col - GRID_WIDTH_HALF, 0,                
					GRID_WIDTH, GameMain.CANVAS_HEIGHT - 1,                
					GRID_WIDTH, GRID_WIDTH);
		}
		
		//Draw the cells
		for (int row = 0; row < GameMain.ROWS; ++row) {          
			for (int col = 0; col < GameMain.COLS; ++col) {  
				cells[row][col].paint(g);
			}
		}
	}
	

}
