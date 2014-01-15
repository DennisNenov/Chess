// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// King subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class King extends Piece {

	// constructor
	public King (String color) {
		super(color);
		_scopeCache = new ArrayList<Object[]>();
	}

	public void refreshCache () {}

	public void refreshCache (int codeToRefresh) {}

	// toString
	public String toString() {
		if (_color == "White") {
			return "K";
		}
		else {
			return "k";
		}
	}
}
