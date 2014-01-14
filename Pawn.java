// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Pawn subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Pawn extends Piece
{
	public Pawn (boolean isColorWhite)
	{
		super(isColorWhite);
		//scopeList = new ArrayList<Integer>();
		//scopeList.add(1);
	}
	public String toString() {
		if (_isWhite) {
			return "P";
		}
		else {
			return "p";
		}
	}
}
