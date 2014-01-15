// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Knight subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Knight extends Piece {

	// constructor
	public Knight (String color) {
		super(color);
		_scopeCache = new ArrayList<Object[]>();
		_scopeCache.add(new Object[]{2,1,false,true,true});
		_scopeCache.add(new Object[]{1,2,false,true,true});
		_scopeCache.add(new Object[]{2,-1,false,true,true});
		_scopeCache.add(new Object[]{1,-2,false,true,true});
		_scopeCache.add(new Object[]{-2,1,false,true,true});
		_scopeCache.add(new Object[]{-1,2,false,true,true});
		_scopeCache.add(new Object[]{-2,-1,false,true,true});
		_scopeCache.add(new Object[]{-1,-2,false,true,true});
	}

	public void refreshCache () {};

	public void refreshCache(ArrayList<Integer> addCache) {};


	// toString
	public String toString() {
		if (_color == "White") {
			return "H";
		}
		else {
			return "h";
		}
	}
}
