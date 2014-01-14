// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Queen subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Queen extends Piece {

	// constructor
	public Queen (String color) {
		super(color);
		scopeList = new ArrayList<Integer>();
		scopeList.add(2);
		scopeList.add(3);
	}

	// toString
	public String toString() {
		if (_color == "White") {
			return "Q";
		}
		else {
			return "q";
		}
	}
}
