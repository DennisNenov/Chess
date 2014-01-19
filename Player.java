// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Piece superclass for use with the Chess.java driver file.

import cs1.Keyboard;
import java.util.ArrayList;

public abstract class Player {

	// instance variables
	protected String _color;

	// default constructor
	public Player() {
		System.out.print("choose your team color: ");
		_color = Keyboard.readString();
	}

	// accessor methods
	public String getColor() {
		return _color;
	}

	// createMove helper method
	public int[] createMove(int[] coordinates, int[] input) {
		int[] move = new int[4];
		move[0] = coordinates[0];
		move[1] = coordinates[1]; 
		move[2] = input[0];
		move[3] = input[1];
		return move;
	}
		
	// abstract methods
	public abstract void movePiece(Board board);
}
