// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Board class for use with the Chess.java driver file.

import java.util.ArrayList;

public class Board {

	// instance variables
	private Piece[][] _board;
	private String _color1, _color2;

	// default constructor
	public Board(String color1, String color2) {

		_color1 = color1;
		_color2 = color2;

		// initializes 2d array of Pieces
		_board = new Piece[8][8];

		// instantiates the back row of pieces with the color specified in color1
		_board[0][0] = new Rook(color1, color1, color2);
		_board[0][1] = new Knight(color1, color1, color2);
		_board[0][2] = new Bishop(color1, color1, color2);
		_board[0][3] = new Queen(color1, color1, color2);
		_board[0][4] = new King(color1, color1, color2);
		_board[0][5] = new Bishop(color1, color1, color2);
		_board[0][6] = new Knight(color1, color1, color2);
		_board[0][7] = new Rook(color1, color1, color2);

		// instantiates the front row of pieces with the color specified in color1
		for (int x = 0; x < 8; x++) {
			_board[1][x] = new Pawn(color1, color1, color2);
		}

		
		/* checkmate test
		_board[3][4] = new Rook(color1, color1, color2);
		_board[5][6] = new Bishop(color2, color1, color2);
		_board[4][4] = new Queen(color1, color1, color2);
		_board[2][2] = new King(color2, color1, color2);
		_board[5][5] = new Pawn(color2, color1, color2);
		*/
		//check test
		//_board[3][2] = new King(color2, color1, color2);

		


		
		// instantiates the back row of pieces with the color specified in color2
		_board[7][0] = new Rook(color2, color1, color2);
		_board[7][1] = new Knight(color2, color1, color2);
		_board[7][2] = new Bishop(color2, color1, color2);;
		_board[7][3] = new Queen(color2, color1, color2);
		_board[7][4] = new King(color2, color1, color2);
		_board[7][5] = new Bishop(color2, color1, color2);
		_board[7][6] = new Knight(color2, color1, color2);
		_board[7][7] = new Rook(color2, color1, color2);

		// instantiates the front row of pieces with the color specified in color2
		for (int x = 0; x < 8; x++) {
			_board[6][x] = new Pawn(color2, color1, color2);
		}

		//test for checkmate
		/*
		_board[3][3] = new King(color1, color1, color2);
		_board[2][3] = new Queen(color2, color1, color2);
		_board[4][3] = new Queen(color2, color1, color2);
		_board[3][4] = new Queen(color2, color1, color2);
		_board[3][2] = new Queen(color2, color1, color2);
		*/
	}

	// methods
	//checks if the pair is out of bounds
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

	// -----------------------------------------------------------------------
	// Player Helper Methods:
	// -----------------------------------------------------------------------

	public boolean isValidPiece(int[] coordinates, String color) {
		int x = coordinates[0];
		int y = coordinates[1];
		return ((isOut(x,y) == false) &&
		        (isEmpty(x,y) == false) &&
		        (getPieceColor(x,y) == color));
	}

	public boolean isValidMove(int[] move) {
		int x1 = move[0];
		int y1 = move[1];
		int x2 = move[2];
		int y2 = move[3];
		if (!(getScope(x1,y1)[x2][y2])) {
			System.out.println("Error: invalid choice - please try again.");
			System.out.println();
			System.out.println("Here, this board displays your options:");
			System.out.println(printer(getScope(x1,y1)));
			return false;
		}
		return true;
	}

	public boolean isValidMoveComp(int[] move) {
		int x1 = move[0];
		int y1 = move[1];
		int x2 = move[2];
		int y2 = move[3];
		return (getScope(x1,y1)[x2][y2]);
	}

	public void executeMove(int[] move) {
		int x1 = move[0];
		int y1 = move[1];
		int x2 = move[2];
		int y2 = move[3];
		_board[x2][y2] = getPiece(x1,y1);
		_board[x1][y1] = null;
		System.out.println("\nMove complete.");
	}

	// -----------------------------------------------------------------------

	//generates 3 by 3 snapshot of the area around the piece
	public String[][] genSnapshot(int row, int column)
	{
		String[][] snapshot = new String[5][5];

		int[] posrow = {row - 2, row - 1 , row, row + 1, row + 2};
		int[] poscol = {column -2, column - 1, column, column + 1, column + 2};

		for (int r = 0; r <= 4; r++)
		{
			for (int c = 0; c <= 4; c++)
			{
				if (isOut(posrow[r], poscol[c]))
				{
					//out means out of bounds
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

		scopePossible[row][column] = true;

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
			while (scopeSpecFlag && (!(isOut(newRow - scopeYChange, newCol + scopeXChange))))
			{
				newRow -= scopeYChange;
				newCol += scopeXChange;

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

	// for use in determining check and checkmate
	public boolean[][] getColorScope(String color) {
		boolean[][] colorScope = new boolean[8][8];
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if ((!(isEmpty(x,y))) && (getPieceColor(x, y).equals(color))) {
					incorporateScope(x, y, colorScope);
				}
			}
		}
		return colorScope;
	}

	// helper function for getColorScope
	public boolean[][] incorporateScope(int row, int column, boolean[][] colorScope) {
		boolean[][] scopeReceiver = colorScope;
		boolean[][] scopeSender = getScope(row, column);
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if ((scopeReceiver[x][y] == false) && (scopeSender[x][y] == true)) {
					scopeReceiver[x][y] = true;
				}
			}
		}
		return scopeReceiver;
	}

	// helper function for check and checkmate
	public boolean isKing(int row, int column) {
		return (getPiece(row, column) instanceof King);
	}

	public int[] getKingCor (String colorInput)
	{
		int[] cor = new int[2];
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if ((isKing(x, y) == true) && (getPieceColor(x, y).equals(colorInput))) {
					cor[0] = x;
					cor[1] = y;
					break;
				}
			}
		}
		return cor;
	}

	// check and checkmate functions, we check to see if color1 is checked
	public boolean isChecked(String color1,  String color2) 
	{
		int[] corking = getKingCor(color1);
		return getColorScope(color2)[corking[0]][corking[1]];
	}


	public boolean isCheckMated(String color1, String color2) 
	{
		if (!(isChecked(color1, color2))) 
		{
			return false;
		}

		int[] corking = getKingCor(color1);
		boolean[][] scopeKing = getScope(corking[0], corking[1]);
		boolean[][] scopeOther = getColorScope(color2);

		int[] posrow = {corking[0] - 1 , corking[0], corking[0] + 1};
		int[] poscol = {corking[1] - 1, corking[0], corking[0] + 1};

		for (int r = 0; r <= 2; r++)
		{
			for (int c = 0; c <= 2; c++)
			{
				boolean sKing = scopeKing[posrow[r]][poscol[c]];
				boolean sOther = scopeOther[posrow[r]][poscol[c]];
				if ((!(isOut(posrow[r], poscol[c]))) && (sKing == true) && (sOther == false))
				{
					return false;
				}
			}
		}
		return true;
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
