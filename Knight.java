// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Knight subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Knight extends Piece {

	// constructor
	public Knight (String color) {
		super(color);
		scopeList = new ArrayList<Integer>();
		scopeList.add(4);
	}

	// toString
	public String toString() {
		if (_color == "White") {
			return "H";
		}
		else {
			return "h";
		}
	}
}
