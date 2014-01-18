// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Queen subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Queen extends Piece {

	// constructor
	public Queen (String color, String color1, String color2 ) {
		super(color, color1, color2);
		_scopeCache = new ArrayList<Object[]>();
		_scopeCache.add(new Object[]{1,0,true,false,true});
		_scopeCache.add(new Object[]{-1,0,true,false,true});
		_scopeCache.add(new Object[]{0,1,true,false,true});
		_scopeCache.add(new Object[]{0,-1,true,false,true});
		_scopeCache.add(new Object[]{1,1,true,false,true});
		_scopeCache.add(new Object[]{1,-1,true,false,true});
		_scopeCache.add(new Object[]{-1,1,true,false,true});
		_scopeCache.add(new Object[]{-1,-1,true,false,true});
	}

	public void refreshCache () {};


	// toString
	public String toString() {
		if (_color.equals(_color2)) {
			return "Q";
		}
		else {
			return "q";
		}
	}
}
