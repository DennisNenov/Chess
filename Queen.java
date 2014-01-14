// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Queen subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Queen extends Piece
{
	public Queen (boolean isColorWhite)
	{
		super(isColorWhite);
		//scopeList = new ArrayList<Integer>();
		//scopeList.add(2);
		//scopeList.add(3);
	}
	public String toString() {
		if (_isWhite) {
			return "Q";
		}
		else {
			return "q";
		}
	}
}
