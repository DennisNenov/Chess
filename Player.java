// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Piece superclass for use with the Chess.java driver file.

import cs1.Keyboard;

public abstract class Player {

	// instance variables
	protected String _color;

	// default constructor
	public Player() {
		System.out.print("Choose your team color: ");
		_color = Keyboard.readString();
	}

	// accessor methods
	public String getColor() {
		return _color;
	}
		
	// abstract methods
	public abstract void movePiece(Board board);
}
