// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Pawn subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Pawn extends Piece {

	// constructor
	public Pawn (String color) 
	{
		super(color);
		_scopeCache = new ArrayList<Object[]>();
		_scopeCache.add(new Object[]{0,1,false,false,true});
		_scopeCache.add(new Object[]{0,2,false,false,false});
		_scopeCache.add(new Object[]{-1,1,false,false,false});
		_scopeCache.add(new Object[]{1,1,false,false,false});			
	}


	public void refreshCache () 
	{
		_scopeCache.set(0, new Object[]{0,1,false,false,true});
		_scopeCache.set(1, new Object[]{0,2,false,false,((_rowvalue == 1 && _color.equals("Black")) || (_rowvalue == 6 && _color.equals("White")))});
		_scopeCache.set(2, new Object[]{-1,1,false,false,((!(_snapshot[0][0]).equals(_color))&&(!(_snapshot[0][0]).equals("Empty"))&&(!(_snapshot[0][0]).equals("Out")))});
		_scopeCache.set(3, new Object[]{1,1,false,false,((!(_snapshot[0][0]).equals(_color))&&(!(_snapshot[0][0]).equals("Empty"))&&(!(_snapshot[0][0]).equals("Out")))});
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
