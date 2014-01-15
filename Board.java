// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Board class for use with the Chess.java driver file.

import java.util.ArrayList;

public class Board {

	// instance variables
	private Piece[][] _board;

	// default constructor
	public Board() {

		// initializes 2d array of Pieces
		_board = new Piece[8][8];

		// instantiates the back row of black pieces
		_board[0][0] = new Rook("Black");
		_board[0][1] = new Knight("Black");
		_board[0][2] = new Bishop("Black");
		_board[0][3] = new Queen("Black");
		_board[0][4] = new King("Black");
		_board[0][5] = new Bishop("Black");
		_board[0][6] = new Knight("Black");
		_board[0][7] = new Rook("Black");

		// instantiates the front row of black pieces
		for (int x = 0; x < 8; x++) {
			_board[1][x] = new Pawn("Black");
		}

		// test piece
		_board[3][3] = new Rook("Black");


		// instantiates the back row of white pieces
		_board[7][0] = new Rook("White");
		_board[7][1] = new Knight("White");
		_board[7][2] = new Bishop("White");;
		_board[7][3] = new Queen("White");
		_board[7][4] = new King("White");;
		_board[7][5] = new Bishop("White");
		_board[7][6] = new Knight("White");
		_board[7][7] = new Rook("White");

		// instantiates the front row of white pieces
		for (int x = 0; x < 8; x++) {
			_board[6][x] = new Pawn("White");
		}
	}

	// methods

	public boolean isEmpty(int row, int column) {
		return (_board[row][column] == null);
	}

	public Piece getPiece(int row, int column) {
		return _board[row][column];
	}

	public String getPieceColor(int row, int column) {
		return _board[row][column].getColor();
	}

	public boolean[][] getScope(int row, int column) 
	{
		//gets the piece and it's color
		Piece pieceToUse = getPiece(row, column);
		String pieceColor = pieceToUse.getColor();

		//refreshes the cache
		pieceToUse.refreshCache();

		//setups the return array of booleans and makes it initially all false
		boolean[][] scopePossible = new boolean[8][8];

		for (int r = 0; r < scopePossible.length; r++)
		{
			for (int c = 0; c < scopePossible[0].length; c++)
			{
				scopePossible[r][c] = false;
			}
		}
		// current spot is true
		scopePossible[row][column] = true;

		///gets the cache
		ArrayList<Object[]> scopeCache = pieceToUse.getCache();

		int newRow = row;
		int newCol = column;

		//iterates trhough every code in the cache
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
			//loop only runs if code conditions are met ( which occurs when SpecFlag is true, which because of refreshing the cache is accurate)
			while (scopeSpecFlag)
			{
				//if the motion is continous (e.g. the piece goes in a line) keep looping and checking
				if (scopeContFlag)
				{

					//see if the new potential places are valid, if they are mark them as true
					while ((newRow < 7 && newCol < 7) && (newRow > 0 && newCol > 0) )
					{
						newRow += scopeYChange;
						newCol += scopeXChange;

						//System.out.println("newRow: " + newRow + " newCol: " + newCol);

						if (isEmpty(newRow, newCol))
						{
							scopePossible[newRow][newCol] = true;					
						}
						else if ((pieceColor.equals(getPieceColor(newRow, newCol))))
						{
							break;
						}
						else if (!(pieceColor.equals(getPieceColor(newRow, newCol))))
						{
							scopePossible[newRow][newCol] = true;
							break;
						}

					} 
				}
				// if the motion isn't continous then only check the piece directly ahead
				else
				{
					newRow += scopeYChange;
					newCol += scopeXChange;

					if (newRow > 7 || newCol > 7)
						break;

					else if (isEmpty(newRow, newCol))
					{
						scopePossible[newRow][newCol] = true;					
					}

					else if (!(pieceColor.equals(getPieceColor(newRow, newCol))))
					{
						scopePossible[newRow][newCol] = true;
					}
				}
				break;
			}
		}
		//return array of trues/falses about where the piece can land
		return scopePossible;
	}

	public static void main (String[] args)
	{
		Board test = new Board();
		System.out.println(test);
		System.out.println(printer(test.getScope(3,3)));
	}

	
	//prints arrays (temp testing function)
	public static String printer (boolean[][] arrayToPrint)
	{
		String returnString = "";
		for (int r = 0; r < arrayToPrint.length; r++)
		{
			String retRow = "| ";
			for (int c = 0; c < arrayToPrint[0].length; c++)
			{

				retRow += arrayToPrint[r][c] + " ";
			}
			returnString += "\n" + retRow + "|";
		}
		return returnString;
	}

	public String toString() { 
		String retStr = "";
       		for (int x = 0; x < 8; x++) {
			String retRow = "| ";
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
		retStr += "\n";
		return retStr;
    	}
}
