// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Chess.java driver file.

import cs1.Keyboard;

public class Chess {

	// instance variables
	private Board _board;	
	private Player _player1;
	private Player _player2;

	// default constructor
	public Chess() {
		_player1 = new Human();
		_player2 = new Human();
		_board = new Board(_player1.getColor(), _player2.getColor());
	}

	// accessor method
	public Board getBoard() {
		return _board;
	}

	// turns
	public void run() {
		int turns = 0;
		while (! (_board.isCheckMated(_player1.getColor(), _player2.getColor())) || 
		         (_board.isCheckMated(_player2.getColor(), _player1.getColor()))) {
			if ((turns % 2) == 0) {
				_player1.movePiece(_board);
			}
			else {
				_player2.movePiece(_board);
			}
			System.out.println(_board);
			turns++;
		}
	}

	public static void main(String [] args) {
		Chess chess = new Chess();
		System.out.println(chess.getBoard());
		chess._player1.movePiece(chess.getBoard());
	}
}
