// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Pawn subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Pawn extends Piece {

	// constructor
	public Pawn (String color) {
		super(color);
		_scopeCache = new ArrayList<Object[]>();
		if (color == "Black") {
			_scopeCache.add(new Object[]{0,1,false,false,true});
			_scopeCache.add(new Object[]{0,2,false,false,true});
		}
		else {
			_scopeCache.add(new Object[]{0,-1,false,false,true});
			_scopeCache.add(new Object[]{0,-2,false,false,true});
		}			
	}


	public void refreshCache () {};

	public void refreshCache(ArrayList<Integer> instructions) {
		for (int x = 0; x < instructions.size(); x++) {
			if (instructions.get(x) == 1) {
				_scopeCache.add(new Object[]{0,2,false,false,true});
			}
			if (instructions.get(x) == 2) {
				_scopeCache.add(new Object[]{0,1,false,false,true});
			}
			if (instructions.get(x) == 3) {
				_scopeCache.add(new Object[]{1,1,false,false,true});			
			}
			if (instructions.get(x) == 4) {
				_scopeCache.add(new Object[]{1,-1,false,false,true});	
			}
			if (instructions.get(x) == 5) {
				_scopeCache.add(new Object[]{0,-2,false,false,true});				
			}
			if (instructions.get(x) == 6) {
				_scopeCache.add(new Object[]{0,-1,false,false,true});				
			}
			if (instructions.get(x) == 7) {
				_scopeCache.add(new Object[]{-1,1,false,false,true});
			}
			if (instructions.get(x) == 8) {
				_scopeCache.add(new Object[]{-1,-1,false,false,true});
			}
		}
	}
			

	// toString
	public String toString() {
		if (_color == "White") {
			return "P";
		}
		else {
			return "p";
		}
	}
}
