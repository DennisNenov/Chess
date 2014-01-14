// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Knight subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Knight extends Piece
{
	public Knight (boolean isColorWhite)
	{
		super(isColorWhite);
		scopeList = new ArrayList<Integer>();
		scopeList.add(4);
	}
	public String toString() {
		if (isColorWhite) {
			return "H";
		}
		else {
			return "h";
		}
	}
}
