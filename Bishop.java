// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Bishop subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Bishop extends Piece {

	// constructor
	public Bishop (String color, String color1, String color2 ) {
		super(color, color1, color2);
		_scopeCache = new ArrayList<Object[]>();
		_scopeCache.add(new Object[]{1,1,true,false, true, true});
		_scopeCache.add(new Object[]{1,-1,true,false, true,true});
		_scopeCache.add(new Object[]{-1,1,true,false,true,true});
		_scopeCache.add(new Object[]{-1,-1,true,false,true,true});
	}

	public void refreshCache () {};

	// toString
	public String toString() {
		if (_color.equals(_color2)) {
			return "B";
		}
		else {
			return "b";
		}
	}
}
