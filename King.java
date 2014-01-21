// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// King subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class King extends Piece {

	// constructor
	public King (String color, String color1, String color2 ) {
		super(color, color1, color2);
		_scopeCache = new ArrayList<Object[]>();
		_scopeCache.add(new Object[]{-1,-1,false,false,true,true});
		_scopeCache.add(new Object[]{-1,0,false,false,true,true});
		_scopeCache.add(new Object[]{-1,1,false,false,true,true});
		_scopeCache.add(new Object[]{0,-1,false,false,true,true});
		_scopeCache.add(new Object[]{0,1,false,false,true,true});
		_scopeCache.add(new Object[]{1,-1,false,false,true,true});
		_scopeCache.add(new Object[]{1,0,false,false,true,true});
		_scopeCache.add(new Object[]{1,1,false,false,true,true});
	}

	public void refreshCache () {};

	// toString
	public String toString() {
		if (_color.equals(_color2)) {
			return "K";
		}
		else {
			return "k";
		}
	}
}
