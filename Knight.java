// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Knight subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Knight extends Piece {

	// constructor
	public Knight (String color, String color1, String color2) {
		super(color, color1, color2);
		_scopeCache = new ArrayList<Object[]>();
		_scopeCache.add(new Object[]{2,1,false,true, true, true});
		_scopeCache.add(new Object[]{1,2,false,true, true, true});
		_scopeCache.add(new Object[]{2,-1,false,true, true, true});
		_scopeCache.add(new Object[]{1,-2,false,true, true, true});
		_scopeCache.add(new Object[]{-2,1,false,true,true, true});
		_scopeCache.add(new Object[]{-1,2,false,true,true, true});
		_scopeCache.add(new Object[]{-2,-1,false,true, true, true});
		_scopeCache.add(new Object[]{-1,-2,false,true, true, true});
	}

	public void refreshCache () {};


	// toString
	public String toString() {
		if (_color.equals(_color2)) {
			return "H";
		}
		else {
			return "h";
		}
	}
}
