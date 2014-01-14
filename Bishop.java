// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Bishop subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Bishop extends Piece
{
	public Bishop (boolean isColorWhite)
	{
		super(isColorWhite);
		//scopeList = new ArrayList<Integer>();
		//scopeList.add(3);
	}

	public String toString() {
		if (_isWhite) {
			return "B";
		}
		else {
			return "b";
		}
	}
}
