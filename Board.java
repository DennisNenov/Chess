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
