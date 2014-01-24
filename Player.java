// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Piece superclass for use with the Chess.java driver file.

import cs1.Keyboard;
import java.util.ArrayList;

public abstract class Player {

	// instance variables
	protected String _color;
<<<<<<< HEAD
	
=======
	protected int moveCounter;
>>>>>>> 6905642b9fdf7ea1b2c3a712de13e16b7c4bb418

	// default constructor
	public Player(String color) {
		_color = color;
	}

	// accessor methods
	public String getColor() {
		return _color;
	}

	public void resetCounter()
	{
		moveCounter = 0;
	}

	public void increaseCounter()
	{
		moveCounter++;
	}

	public boolean getDraw()
	{
		return moveCounter >= 50;
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
