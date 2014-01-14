// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// King subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class King extends Piece
{
	public King (boolean isColorWhite)
	{
		super(isColorWhite);
		scopeList = new ArrayList<Integer>();
		scopeList.add(5);
	}

	public String toString() {
		if (isColorWhite) {
			return "K";
		}
		else {
			return "k";
		}
	}
}
