// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Board class for use with the Chess.java driver file.

import java.util.ArrayList;

public class Board 
{

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
		_board[0][0] = new Rook(color1, color1, color2, genSnapshot(0,0), 0, 0);
		_board[0][1] = new Knight(color1, color1, color2, genSnapshot(0,1), 0, 1);
		_board[0][2] = new Bishop(color1, color1, color2, genSnapshot(0,2), 0, 2);
		_board[0][3] = new Queen(color1, color1, color2, genSnapshot(0,3), 0, 3);
		_board[0][4] = new King(color1, color1, color2, genSnapshot(0,4), 0, 4);
		_board[0][5] = new Bishop(color1, color1, color2, genSnapshot(0,5), 0, 5);
		_board[0][6] = new Knight(color1, color1, color2, genSnapshot(0,6), 0, 6);
		_board[0][7] = new Rook(color1, color1, color2, genSnapshot(0,7), 0, 7);

		// instantiates the front row of pieces with the color specified in color1
		for (int x = 0; x < 8; x++) {
			_board[1][x] = new Pawn(color1, color1, color2, genSnapshot(1,x), 1, x);
		}
		
		// instantiates the back row of pieces with the color specified in color2
		_board[7][0] = new Rook(color2, color1, color2, genSnapshot(7,0), 7, 0);
		_board[7][1] = new Knight(color2, color1, color2, genSnapshot(7,1), 7, 1);
		_board[7][2] = new Bishop(color2, color1, color2, genSnapshot(7,2), 7, 2);
		_board[7][3] = new Queen(color2, color1, color2 , genSnapshot(7,3), 7, 3);
		_board[7][4] = new King(color2, color1, color2,  genSnapshot(7,4), 7, 4);
		_board[7][5] = new Bishop(color2, color1, color2, genSnapshot(7,5), 7, 5);
		_board[7][6] = new Knight(color2, color1, color2, genSnapshot(7,7), 7, 6);
		_board[7][7] = new Rook(color2, color1, color2, genSnapshot(7,7), 7, 7);

		// instantiates the front row of pieces with the color specified in color2
		for (int x = 0; x < 8; x++) {
			_board[6][x] = new Pawn(color2, color1, color2, genSnapshot(6,x), 6, x);
		}
	}

	// Helper Functions

	//Mutator method that changes the board
	public void setXY (int x, int y, Object value)
	{
		if (value == null)
		{
			_board[x][y] = null;	
		}
		else
		{
			_board[x][y] = (Piece) value;
		}
		 
	}

	//Method that provides for a deep-copy
	public static Board copyBoard( Board toCopy)
	{
		Board newBoard = new Board(toCopy._color1, toCopy._color2);
		newBoard._color1 = toCopy._color1;
		newBoard._color2 = toCopy._color2;
		for (int r = 0; r <= 7; r++)
		{
			for (int c = 0; c <= 7; c++)
			{
				if (toCopy._board[r][c] == null)
				{
					newBoard._board[r][c] = null;
				}
				else
				{
					newBoard._board[r][c] = toCopy._board[r][c].copyPiece();
				}
			}

		}
		return newBoard;
	}

	//accessor methods that give the colors of the two teams
	public String getColor1()
	{
		return _color1;
	}

	public String getColor2()
	{
		return _color2;
	}

	//method that checks if the specified row and column number are out of bounds
	public boolean isOut (int row, int column)
	{
		return (row > 7 || row < 0 || column > 7 || column < 0);

	}

	///method that returns whether the specified row and column are empty
	public boolean isEmpty(int row, int column) 
	{
			return (_board[row][column] == null);
	}

	//method that 
	public Piece getPiece(int row, int column) {
		return _board[row][column];
	}

	public String getPieceString(int row, int column) {
		if (isEmpty(row, column)) {
			return " ";
		}
		else {
			return _board[row][column].toString();
		}
	}

	public String getPieceColor(int row, int column) 
	{
		return _board[row][column].getColor();
	}

	public String flipColor(String colorToFlip)
	{
		if (colorToFlip.equals(_color1))
			return _color2;
		else
			return _color1;
	}


	// -----------------------------------------------------------------------
	// Player Helper Methods:
	// -----------------------------------------------------------------------

	public boolean isValidPiece(int[] coordinates, String color) {
		int x = coordinates[0];
		int y = coordinates[1];
		if ((isOut(x,y)) || (isEmpty(x,y)) || (!(getPieceColor(x,y) == color)))
		{
			return false;
		}
		boolean[][] pieceScope = getScope(x,y);
		for (int r = 0; r < pieceScope.length; r++)
		{
			for (int c = 0; c < pieceScope.length; c++)
			{
				if (pieceScope[r][c] == true)
				{
					return true;
				}
			}
		}
		return false;
	}

	public boolean isValidMove(int[] move) {
		int x1 = move[0];
		int y1 = move[1];
		int x2 = move[2];
		int y2 = move[3];
		if (!(getScope(x1,y1)[x2][y2])) {
			System.out.println("Error: invalid choice (you cannot move this piece like that) - please try again.");
			System.out.println();
			System.out.println("Here, this board displays your options:");
			System.out.println(printer(getScope(x1,y1)));
			return false;
		}
		if (projectCheck(x1,y1,x2,y2,getPieceColor(x1,y1)))
		{
			System.out.println("Error: invalid choice (you cannot check yourself) - please try again.");
			System.out.println();
			return false;
		}
		if (getPiece(x2,y2) instanceof King)
		{
			System.out.println("Error: invalid choice (you cannot capture a king) - please try again.");
			System.out.println();
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

	public void executeMove(int[] move, Player player) {
		int x1 = move[0];
		int y1 = move[1];
		int x2 = move[2];
		int y2 = move[3];
		if ((getPiece(x2,y2) instanceof Piece) || (getPiece(x1,y1) instanceof Pawn))
		{
			player.resetCounter();
		}
		else
		{
			player.increaseCounter();
		}

		if ((getPiece(x1,y1)) instanceof Piece)
		{
			getPiece(x1,y1).increaseCounter();
		}
		_board[x2][y2] = getPiece(x1,y1);
		_board[x1][y1] = null;
	}

	// -----------------------------------------------------------------------

	//creates a new board to see whether the specified move would produce a check
	public boolean projectCheck(int x1, int y1, int x2, int y2, String color)
	{
		Board newBoard = copyBoard(this);
		newBoard.setXY(x2,y2, newBoard.getPiece(x1,y1));
		newBoard.setXY(x1,y1, null);
		return newBoard.isChecked(color, newBoard.flipColor(color));
	}

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

	//obtains the scope of a piece i.e. the range of values that
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
			Integer scopeXChange = (int) scopeCode[0];
			Integer scopeYChange = (int) scopeCode[1];
			Boolean scopeContFlag = (boolean) scopeCode[2];
			Boolean scopeJumpFlag = (boolean) scopeCode[3];
			Boolean scopeCaptFlag = (boolean) scopeCode[4];
			Boolean scopeSpecFlag = (boolean) scopeCode[5];

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
					scopePossible[newRow][newCol] = scopeCaptFlag;
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

	//function that returns the coordinates of all the pieces that can move to another piece
	public ArrayList<Integer[]> getAllWhoReach (int row, int column)
	{
		ArrayList<Integer[]> reachList = new ArrayList<Integer[]>();
		//System.out.println("row: " + row + "col: " + column);
		String color = flipColor(getPieceColor(row, column));

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if ((!(isEmpty(x,y))) && (!(isOut(x,y))) && (getPieceColor(x, y).equals(color)) && (getScope(x,y)[row][column] == true) ) {
					reachList.add(new Integer[]{x,y});
				}
			}
		}
		return reachList;
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

	//gets the scope of a color excluding a specified piece that has that same color
	public boolean[][] getColorScopeWithout(String color, int row, int column) 
	{
		boolean[][] colorScope = new boolean[8][8];
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if ((!(isEmpty(x,y))) && (!((x == row) && (y == column))) && (getPieceColor(x, y).equals(color))) {
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

	
	//determines whether a piece at row1, col1 can go to the piece at row2, col2 without being intercepted by another piece. useful for checkmate
	public boolean isNotInterupt(int row1, int col1, int row2, int col2)
	{

		if (isOut(row1, col1)||isOut(row2, col2)||isEmpty(row1, col1)||isEmpty(row2, col2))
		{
			return false;
		}
		boolean[][] enemyScope = getColorScopeWithout(getPieceColor(row2, col2), row2, col2);
		//System.out.println(printer(enemyScope));

		//gets the piece and its color
		Piece pieceToUse = getPiece(row1, col1);
		String pieceColor = pieceToUse.getColor();

		//refreshes the piece's cache
		pieceToUse.setSnapshot(genSnapshot(row1, col1), row1, col1);
		pieceToUse.refreshCache();

		///gets the cache, so this method can access it
		ArrayList<Object[]> scopeCache = pieceToUse.getCache();

		//setup location variables for square ahead
		int newRow, newCol;

		//iterates through every code in the cache
		for (int i = 0; i < scopeCache.size(); i++)
		{
			Object[] scopeCode = scopeCache.get(i);
			//relevant code data
			Integer scopeXChange = (int) scopeCode[0];
			Integer scopeYChange = (int) scopeCode[1];
			Boolean scopeContFlag = (boolean) scopeCode[2];
			Boolean scopeJumpFlag = (boolean) scopeCode[3];
			Boolean scopeCaptFlag = (boolean) scopeCode[4];
			Boolean scopeSpecFlag = (boolean) scopeCode[5];


			newRow = row1;
			newCol = col1;
			//loop only runs if certain conditions are met (i.e. when the spot ahead isn't out of bounds or when SpecFlag is true, which because of refreshing the cache is accurate)
			//loop sees if new potential places are valid, and mark them as true if they are
			while (scopeSpecFlag && (!(isOut(newRow - scopeYChange, newCol + scopeXChange))))
			{
				newRow -= scopeYChange;
				newCol += scopeXChange;

				if ((newRow == row2) && (newCol == col2))
				{
					return true;
				}
				if (enemyScope[newRow][newCol] == true)
				{
					break;
				}
				//break when you hit a piece (either enemy or friendly)
				if (isEmpty(newRow, newCol)) {}
				else if ((pieceColor.equals(getPieceColor(newRow, newCol))))
				{
					break;
				}
				else if (!(pieceColor.equals(getPieceColor(newRow, newCol))))
				{
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
		return false;

	}

	public int[] getNumPieces ()
	{
		int[] numPieces = new int[2];
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if ((!isEmpty(x,y)) && (getPieceColor(x, y).equals(_color1))) 
				{
					numPieces[0]++;
				}
				else if ((!isEmpty(x,y)) && (getPieceColor(x, y).equals(_color2)))
				{
					numPieces[1]++;
				}
			}
		}
		return numPieces;
	}

	public boolean isTied(Player player1, Player player2)
	{
		int[] numPieces = getNumPieces();
		//System.out.println(numPieces[0] + " and for color 2 " + numPieces[1]);
		return (player1.getDraw() || player2.getDraw() || (numPieces[0] == 1 && numPieces[1] == 1));
	}

	// check and checkmate functions, we check to see if color1 is checked
	public boolean isChecked(String color1,  String color2) 
	{
		int[] corking = getKingCor(color1);
		//System.out.println(printer(getAllWhoReach(corking[0], corking[1])));
		return getColorScope(color2)[corking[0]][corking[1]];
	}
	//determines if it's impossibleto move for the king
	public boolean isImpossibleToMove (String color1, String color2)
	{
		int[] corking = getKingCor(color1);
		boolean[][] scopeKing = getScope(corking[0], corking[1]);
		boolean[][] scopeOther = getColorScope(color2);

		int[] posrow = {corking[0] - 1 , corking[0], corking[0] + 1};
		int[] poscol = {corking[1] - 1, corking[1], corking[1] + 1};

		for (int r = 0; r <= 2; r++)
		{
			for (int c = 0; c <= 2; c++)
			{
				int x = posrow[r];
				int y = poscol[c];
				//System.out.println("x: " + x + "y: " + y);
				//System.out.println(((!(isOut(x,y))) + " " + scopeKing[x][y] + " " + (isEmpty(x,y)) + (scopeOther[x][y])));
				if ((!(isOut(x,y))) && (scopeKing[x][y] == true))
				{
					Board newBoard = copyBoard(this);
					newBoard._board[x][y] = getPiece(corking[0],corking[1]);
					newBoard._board[corking[0]][corking[1]] = null;
					//System.out.println("new board:\n" + newBoard);
					if (newBoard.getColorScope(color2)[x][y] == false)
					{
						return false;
					}
				}
			}
		}

		boolean[][] scopeGood = getColorScopeWithout(color1, corking[0], corking[1]);

		ArrayList<Integer[]> attackers = getAllWhoReach(corking[0], corking[1]);
		for (int i = 0; i < attackers.size() ; i++)
		{
			//System.out.println(isNotInterupt(attackers.get(i)[0], attackers.get(i)[1], corking[0], corking[1]));
			if ((!(isNotInterupt(attackers.get(i)[0], attackers.get(i)[1], corking[0], corking[1]))) && (scopeGood[attackers.get(i)[0]][attackers.get(i)[0]] == true))
				return false;
		}		
		/*
		System.out.println("\ncolor: " + color1);
		System.out.println(this);
		System.out.println("\nking cor: " + corking[0] + " and " + corking[1]);
		System.out.println("scopeKing: \n" + printer(scopeKing));
		System.out.println("scopeOther: \n" + printer(scopeOther));
		System.out.println("scopeGood: \n" + printer(scopeGood));
		*/
		return true;
	}


	public boolean isCheckMated(String color1, String color2) 
	{
			return isChecked(color1, color2) & isImpossibleToMove(color1, color2);
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

	public static String printer (ArrayList<Integer[]> arrayToPrint)
	{
		String returnString = "";
		for (int i = 0; i < arrayToPrint.size(); i++)
		{
			returnString += "Pair " + (i + 1) + ": " + "(" + arrayToPrint.get(i)[0] + " , " + arrayToPrint.get(i)[1] + ")\n";
		}
		return returnString;
	}

	public static String printer (ArrayList<Object[]> arrayToPrint, int n)
	{
		String returnString = "";
		for (int i = 0; i < arrayToPrint.size(); i++)
		{
			returnString += "Pair " + (i + 1) + ": " + "(" + arrayToPrint.get(i)[0] + " , " + arrayToPrint.get(i)[1] + arrayToPrint.get(i)[2] + ")\n";
		}
		return returnString;
	}

	public String toString() {
		String retStr = "\n    0 1 2 3 4 5 6 7 X" + "\n   ----------------- ";
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
		retStr += "\nY  ----------------- \n";
		return retStr;
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
		System.out.println("The Board for Scope Testing");
		System.out.println(test);
		System.out.println("Testing: getColorScope() - White");
		System.out.println(printer(test.getColorScope("White")));
		System.out.println("Testing: getColorScope() - Black");
		System.out.println(printer(test.getColorScope("Black")));
		System.out.println("Testing: isCheck() - White");
		System.out.println(test.isChecked("White", "Black"));
		System.out.println("Testing: isCheck() - Black");
		System.out.println(test.isChecked("Black", "White"));
		System.out.println("Testing: isCheckMated() - White");
		System.out.println(test.isCheckMated("White", "Black"));
		System.out.println("Testing: isCheckMated() - Black");
		System.out.println(test.isCheckMated("Black", "White"));

		System.out.println("Testing: isNotInterupt() - (4,4) to (2,2)");
		System.out.println(test.isNotInterupt(4, 4, 2, 2));
		System.out.println("Testing: isNotInterupt() - (1,1) to (3,1)");
		System.out.println(test.isNotInterupt(1, 1, 3, 1));
		System.out.println("Testing: isNotInterupt() - (1,0) to (3,0)");
		System.out.println(test.isNotInterupt(1, 0, 3, 0));
		System.out.println("Testing: isNotInterupt() - (3,4) to (3,3)");
		System.out.println(test.isNotInterupt(3, 4, 3, 3));
		System.out.println("Testing: isNotInterupt() - (5,6) to (3,4)");
		System.out.println(test.isNotInterupt(5, 6, 3, 4));
		System.out.println("Testing: isNotInterupt() - (4,6) to (2,4)");
		System.out.println(test.isNotInterupt(4, 6, 2, 4));

		/*
		System.out.println("Testing: getAllWhoReach() - (2,4)");
		System.out.println(printer(test.getAllWhoReach(2, 4)));

		System.out.println("Testing: getAllWhoReach() - (2,2)");
		System.out.println(printer(test.getAllWhoReach(2, 2)));

		System.out.println("Testing: getAllWhoReach() - (5,5)");
		System.out.println(printer(test.getAllWhoReach(5, 5)));

		System.out.println("Testing: getAllWhoReach() - (1,2)");
		System.out.println(printer(test.getAllWhoReach(1, 2)));
		*/

		System.out.println("Testing: checkEvent()");
		System.out.println(printer(Castling.eventCheck(test), 1));


    }
}
