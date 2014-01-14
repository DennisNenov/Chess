// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Board class for use with the Chess.java driver file.

public class Board {

	private Piece[][] _board;

	public Board() {

		_board = new Piece[8][8];

		_board[0][0] = new Rook("Black");
		_board[0][1] = new Knight("Black");
		_board[0][2] = new Bishop("Black");
		_board[0][3] = new Queen("Black");
		_board[0][4] = new King("Black");
		_board[0][5] = new Bishop("Black");
		_board[0][6] = new Knight("Black");
		_board[0][7] = new Rook("Black");

		for (int x = 0; x < 8; x++) {
			_board[1][x] = new Pawn("Black");
		}

		_board[7][0] = new Rook("White");
		_board[7][1] = new Knight("White");
		_board[7][2] = new Bishop("White");
		_board[7][3] = new Queen("White");
		_board[7][4] = new King("White");
		_board[7][5] = new Bishop("White");
		_board[7][6] = new Knight("White");
		_board[7][7] = new Rook("White");

		for (int x = 0; x < 8; x++) {
			_board[6][x] = new Pawn("White");
		}
	}
}
