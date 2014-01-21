// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Pawn subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Pawn extends Piece {

	// constructor
	public Pawn (String color, String color1, String color2) 
	{
		super(color, color1, color2);
		_scopeCache = new ArrayList<Object[]>();
		_scopeCache.add(new Object[]{0,1,false,false, false, false});
		_scopeCache.add(new Object[]{0,-1,false,false, false, false});
		//
		_scopeCache.add(new Object[]{0,2,false,false, false, false});
		_scopeCache.add(new Object[]{0,-2,false,false, false, false});
		//
		_scopeCache.add(new Object[]{-1,1,false,false, true, false});
		_scopeCache.add(new Object[]{-1,-1,false,false, true, false});
		//
		_scopeCache.add(new Object[]{1,1,false,false, true, false});
		_scopeCache.add(new Object[]{1,-1,false,false, true, false});			
	}


	public void refreshCache () 
	{
		_scopeCache.set(0, new Object[]{0,1,false,false,false,_color.equals(_color2)});
		_scopeCache.set(1, new Object[]{0,-1,false,false,false,_color.equals(_color1)});

		_scopeCache.set(2, new Object[]{0,2,false,false,false,(_rowvalue == 6 && _color.equals(_color2))});
		_scopeCache.set(3, new Object[]{0,-2,false,false,false,(_rowvalue == 1 && _color.equals(_color1))});

		_scopeCache.set(4, new Object[]{-1,1,false,false,true,( (_color.equals(_color2)) && ((_snapshot[1][1]).equals(_color1)))});
		_scopeCache.set(5, new Object[]{-1,-1,false,false,true,( (_color.equals(_color1)) && ((_snapshot[3][1]).equals(_color2)))});

		_scopeCache.set(6, new Object[]{1,1,false,false,true,( (_color.equals(_color2)) && ((_snapshot[1][3]).equals(_color1)))});
		_scopeCache.set(7, new Object[]{1,-1,false,false,true,( (_color.equals(_color1)) && ((_snapshot[3][3]).equals(_color2)))});
	}
			

	// toString
	public String toString() {
		if (_color.equals(_color2)) {
			return "P";
		}
		else {
			return "p";
		}
	}
}
