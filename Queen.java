// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Queen subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Queen extends Piece {

	// constructor
	public Queen (String color) {
		super(color);
		_scopeCache = new ArrayList<Object[]>();
	}

	public void refreshCache () {}

	public void refreshCache (int codeToRefresh) {}

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
