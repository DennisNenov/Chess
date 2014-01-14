// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Rook subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Rook extends Piece {

	// constructor
	public Rook (String color) {
		super(color);
		scopeList = new ArrayList<Integer>();
		scopeList.add(2);
	}

	// toString
	public String toString() {
		if (_color == "White") {
			return "R";
		}
		else {
			return "r";
		}
	}
}
