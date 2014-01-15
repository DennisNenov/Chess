// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Pawn subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Pawn extends Piece {

	// constructor
	public Pawn (String color) {
		super(color);
		_scopeCache = new ArrayList<Object[]>();
		_scopeCache.add(new Object[]{0,1,true,false,true});
		_scopeCache.add(new Object[]{0,2,true,false,false});
		_scopeCache.add(new Object[]{-1,1,true,false,false});
		_scopeCache.add(new Object[]{1,1,true,false,false});
	}

	public void refreshCache () 
	{

	}

	public void refreshCache (int codeToRefresh) 
	{
		if (codeToRefresh == 1)
		{
			
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
