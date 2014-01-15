// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Bishop subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Bishop extends Piece {

	// constructor
	public Bishop (String color) {
		super(color);
		_scopeCache = new ArrayList<Object[]>();
		_scopeCache.add(new Object[]{1,1,true,false,true});
		_scopeCache.add(new Object[]{1,-1,true,false,true});
		_scopeCache.add(new Object[]{-1,1,true,false,true});
		_scopeCache.add(new Object[]{-1,-1,true,false,true});
	}

	public void refreshCache () {};

	// toString
	public String toString() {
		if (_color == "White") {
			return "B";
		}
		else {
			return "b";
		}
	}
}
