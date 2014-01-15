// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Rook subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Rook extends Piece {

	// constructor
	public Rook (String color) {
		super(color);
		_scopeCache = new ArrayList<Object[]>();
		_scopeCache.add(new Object[]{1,0,true,false,true});
		_scopeCache.add(new Object[]{-1,0,true,false,true});
		_scopeCache.add(new Object[]{0,1,true,false,true});
		_scopeCache.add(new Object[]{0,-1,true,false,true});

	}

	public void refreshCache () {}

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
