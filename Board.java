// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Board class for use with the Chess.java driver file.

public class Board {

	// instance variables
	private Piece[][] _board;

	// default constructor
	public Board() {

		// initializes 2d array of Pieces
		_board = new Piece[8][8];

		// instantiates the back row of black pieces
		_board[0][0] = new Rook(false);
		_board[0][1] = new Knight(false);
		_board[0][2] = new Bishop(false);
		_board[0][3] = new Queen(false);
		_board[0][4] = new King(false);
		_board[0][5] = new Bishop(false);
		_board[0][6] = new Knight(false);
		_board[0][7] = new Rook(false);

		// instantiates the front row of black pieces
		for (int x = 0; x < 8; x++) {
			_board[1][x] = new Pawn(false);
		}

		// instantiates the back row of white pieces
		_board[7][0] = new Rook(true);
		_board[7][1] = new Knight(true);
		_board[7][2] = new Bishop(true);
		_board[7][3] = new Queen(true);
		_board[7][4] = new King(true);
		_board[7][5] = new Bishop(true);
		_board[7][6] = new Knight(true);
		_board[7][7] = new Rook(true);

		// instantiates the front row of white pieces
		for (int x = 0; x < 8; x++) {
			_board[6][x] = new Pawn(true);
		}
	}

	// methods

	public boolean isEmpty(int row, int column) {
		return (_board[row][column] == null);
	}

	public Piece getPiece(int row, int column) {
		return _board[row][column];
	}

	public boolean isPieceWhite(int row, int column) {
		return _board[row][column].getIsWhite();
	}

	public String toString {
		String retStr = "";
		for (int x = 0; x < 8; x++) {
			for (int y = 0;

	public String toString() { 
		String retStr = "";
       		for (int x = 0; x < 8; x++) {
			String retRow = "";
			for (int y = 0; y < 8; y++) {
				if (isEmpty[x][y]) {
					retRow += "  ";
				else {
					retRow += _board[x][y] + " ";
				}
			}
			retStr += "\n" + retRow;
		}
    	}
}
