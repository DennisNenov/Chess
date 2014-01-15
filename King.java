// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// King subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class King extends Piece {

	// constructor
	public King (String color) {
		super(color);
		_scopeCache = new ArrayList<Object[]>();
		_scopeCache.add(new Object[]{-1,-1,false,false,true});
		_scopeCache.add(new Object[]{-1,0,false,false,true});
		_scopeCache.add(new Object[]{-1,1,false,false,true});
		_scopeCache.add(new Object[]{0,-1,false,false,true});
		_scopeCache.add(new Object[]{0,1,false,false,true});
		_scopeCache.add(new Object[]{1,-1,false,false,true});
		_scopeCache.add(new Object[]{1,0,false,false,true});
		_scopeCache.add(new Object[]{1,1,false,false,true});
	}

	public void refreshCache () {};

	public void refreshCache(ArrayList<Integer> addCache) {};


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
