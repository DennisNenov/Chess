// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Rook subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Rook extends Piece
{
	public Rook (boolean isColorWhite)
	{
		super(isColorWhite);
		scopeList = new ArrayList<Integer>();
		scopeList.add(2);
	}

	public String toString() {
		if (isColorWhite) {
			return "R";
		}
		else {
			return "r";
		}
	}
}
