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
		System.out.println(_board);
		while (! ((_board.isCheckMated(_player1.getColor(), _player2.getColor())) || 
		         (_board.isCheckMated(_player2.getColor(), _player1.getColor())))) {
			if ((turns % 2) == 0) {
				if (_board.isChecked(_player1.getColor(), _player2.getColor()))
					System.out.println(_player1.getColor() + ", you are checked. Your turn.");
				else
					System.out.println(_player1.getColor() + ", your turn.");
				_player1.movePiece(_board);
			}
			else {
				if (_board.isChecked(_player2.getColor(), _player1.getColor()))
					System.out.println(_player2.getColor() + ", you are checked.");
				else
					System.out.println(_player2.getColor() + ", your turn.");
				_player2.movePiece(_board);
			}
			System.out.println(_board);
			turns++;
		}
		if ((_board.isCheckMated(_player1.getColor(), _player2.getColor())))
			System.out.println(_player2.getColor() + ", you have won.");
		else if ((_board.isCheckMated(_player2.getColor(), _player1.getColor())))
			System.out.println(_player1.getColor() + ", you have won.");
	}

	public static void main(String [] args) {
		Chess chess = new Chess();
		chess.run();
	}
}
