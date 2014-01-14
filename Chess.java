// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Chess.java driver file.

public class Chess {

	// instance variables
	private Board _board;

	// default constructor
	public Chess() {
		_board = new Board();
	}

	// accessor method
	public Board getBoard() {
		return _board;
	}

	public static void main(String [] args) {
		Chess chess = new Chess();
		System.out.println(chess.getBoard());
	}
}
