// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Board class for use with the Chess.java driver file.

import java.util.ArrayList;

public class Board {

	// instance variables
	private Piece[][] _board;

	// default constructor
	public Board(String color1, String color2) {

		// initializes 2d array of Pieces
		_board = new Piece[8][8];

		// instantiates the back row of black pieces
		_board[0][0] = new Rook(color1);
		_board[0][1] = new Knight(color1);
		_board[0][2] = new Bishop(color1);
		_board[0][3] = new Queen(color1);
		_board[0][4] = new King(color1);
		_board[0][5] = new Bishop(color1);
		_board[0][6] = new Knight(color1);
		_board[0][7] = new Rook(color1);

		// instantiates the front row of black pieces
		for (int x = 0; x < 8; x++) {
			_board[1][x] = new Pawn(color1);
		}

		// test pieces
		_board[3][4] = new Rook(color1);
		_board[5][6] = new Bishop(color2);
		_board[4][4] = new Queen(color1);
		_board[2][2] = new King(color2);
		_board[5][5] = new Pawn(color2);


		// instantiates the back row of white pieces
		_board[7][0] = new Rook(color2);
		_board[7][1] = new Knight(color2);
		_board[7][2] = new Bishop(color2);;
		_board[7][3] = new Queen(color2);
		_board[7][4] = new King(color2);;
		_board[7][5] = new Bishop(color2);
		_board[7][6] = new Knight(color2);
		_board[7][7] = new Rook(color2);

		// instantiates the front row of white pieces
		for (int x = 0; x < 8; x++) {
			_board[6][x] = new Pawn(color2);
		}
	}

	// methods

	public boolean isOut (int row, int column)
	{
		return (row > 7 || row < 0 || column > 7 || column < 0);

	}

	public boolean isEmpty(int row, int column) 
	{
			return (_board[row][column] == null);
	}

	public Piece getPiece(int row, int column) {
		return _board[row][column];
	}

	public String getPieceColor(int row, int column) 
	{
		return _board[row][column].getColor();
	}


	//generates 3 by 3 snapshot of the area around the piece
	public String[][] genSnapshot(int row, int column)
	{
		String[][] snapshot = new String[3][3];

		int[] posrow = {row - 1 , row, row + 1};
		int[] poscol = {column - 1, column, column + 1};

		for (int r = 0; r <= 2; r++)
		{
			for (int c = 0; c <= 2; c++)
			{
				if (isOut(posrow[r], poscol[c]))
				{
					snapshot[r][c] = "Out";
				}
				else if (isEmpty(posrow[r], poscol[c]))
				{
					snapshot[r][c] = "Empty";
				}
				else
				{
					snapshot[r][c] = getPieceColor(posrow[r], poscol[c]);
				}
			}
		}

		return snapshot;

	}

	public boolean[][] getScope(int row, int column) 
	{
		boolean[][] scopePossible = new boolean[8][8];

		for (int r = 0; r < scopePossible.length; r++)
		{
			for (int c = 0; c < scopePossible[0].length; c++)
			{
				scopePossible[r][c] = false;
			}
		}

		//if the user selects an empty square or an out of bounds sqaure, return a scope board completely false
		if (isEmpty(row, column) || isOut(row,column)) 
		{
			return scopePossible;
		}

		//gets the piece and its color
		Piece pieceToUse = getPiece(row, column);
		String pieceColor = pieceToUse.getColor();

		//refreshes the piece's cache
		pieceToUse.setSnapshot(genSnapshot(row, column), row, column);
		pieceToUse.refreshCache();

		///gets the cache, so this method can access it
		ArrayList<Object[]> scopeCache = pieceToUse.getCache();

		//setup location variables for square ahead
		int newRow = row;
		int newCol = column;

		//iterates through every code in the cache
		for (int i = 0; i < scopeCache.size(); i++)
		{
			Object[] scopeCode = scopeCache.get(i);
			//relevant code data
			int scopeXChange = (int) scopeCode[0];
			int scopeYChange = (int) scopeCode[1];
			boolean scopeContFlag = (boolean) scopeCode[2];
			boolean scopeJumpFlag = (boolean) scopeCode[3];
			boolean scopeSpecFlag = (boolean) scopeCode[4];

			newRow = row;
			newCol = column;
			//loop only runs if certain conditions are met (i.e. when the spot ahead isn't out of bounds or when SpecFlag is true, which because of refreshing the cache is accurate)
			//loop sees if new potential places are valid, and mark them as true if they are
			while (scopeSpecFlag && (!(isOut(newRow - scopeYChange, newCol - scopeXChange))))
			{
				newRow -= scopeYChange;
				newCol -= scopeXChange;

				if (isEmpty(newRow, newCol))
				{
					scopePossible[newRow][newCol] = true;					
				}
				//break when you hit a piece (either enemy or friendly)
				else if ((pieceColor.equals(getPieceColor(newRow, newCol))))
				{
					break;
				}
				else if (!(pieceColor.equals(getPieceColor(newRow, newCol))))
				{
					scopePossible[newRow][newCol] = true;
					break;
				}
				//break if the motion isn't continous (this way the loop only runs once)
				if (scopeContFlag == false)
				{
					break;
				}

			} 
		}
				
		//return array of trues/falses about where the piece can land
		return scopePossible;
	}

	public static void main (String[] args)
	{
		Board test = new Board("Black", "White");
		System.out.println();
		System.out.println("The Board for Scope Testing");
		System.out.println();
		System.out.println("Note: uppercase = White");
		System.out.println("      lowercase = Black");
		System.out.println(test);

		System.out.println("Testing: getScope(3,4) - Black Rook");
		System.out.println(printer(test.getScope(3,4)));
		System.out.println("Testing: getScope(5,6) - White Bishop");
		System.out.println(printer(test.getScope(5,6)));
		System.out.println("Testing: getScope(4,4) - Black Queen");
		System.out.println(printer(test.getScope(4,4)));
		System.out.println("Testing: getScope(0,1) - Black Knight");
		System.out.println(printer(test.getScope(0,1)));
		System.out.println("Testing: getScope(2,2) - White King");
		System.out.println(printer(test.getScope(2,2)));
		System.out.println("Testing: getScope(5,5) - White Pawn");
		System.out.println(printer(test.getScope(5,5)));
		System.out.println("Testing: getScope(6,3) - White Pawn");
		System.out.println(printer(test.getScope(6,3)));
		System.out.println("Testing: getScope(2,6) - Empty Square");
		System.out.println(printer(test.getScope(2,6)));

	}

	
	//prints arrays (temp testing function)
	public static String printer (boolean[][] arrayToPrint)
	{
		String returnString = "\n    0 1 2 3 4 5 6 7  " + "\n   ----------------- ";
		for (int r = 0; r < arrayToPrint.length; r++) {
			String retRow = r + " | ";
			for (int c = 0; c < arrayToPrint[0].length; c++) {
				if (arrayToPrint[r][c] == true) {
					retRow += "T "; 
				}
				else {
					retRow += "f ";
				}
			}
			returnString += "\n" + retRow + "|";
		}
		returnString += "\n   ----------------- \n";
		return returnString;
	}

	public static String printer (String[][] arrayToPrint)
	{
		String returnString = "\n    0 1 2 3 4 5 6 7  " + "\n   ----------------- ";
		for (int r = 0; r < arrayToPrint.length; r++) {
			String retRow = r + " | ";
			for (int c = 0; c < arrayToPrint[0].length; c++) 
			{
					retRow = retRow + " " + arrayToPrint[r][c] + " "; 
			}
			returnString += "\n" + retRow + "|";
		}
		returnString += "\n   ----------------- \n";
		return returnString;
	}

	public String toString() { 
		String retStr = "\n    0 1 2 3 4 5 6 7  " + "\n   ----------------- ";
       		for (int x = 0; x < 8; x++) {
			String retRow = x + " | ";
			for (int y = 0; y < 8; y++) {
				if (isEmpty(x,y)) {
					retRow += "  ";
				}
				else {
					retRow += _board[x][y] + " ";
				}
			}
			retStr += "\n" + retRow + "|";
		}
		retStr += "\n   ----------------- \n";
		return retStr;
    	}
}