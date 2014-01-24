// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Pawn subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Pawn extends Piece {

	// constructor
	public Pawn (String color, String color1, String color2, String[][] snapshotToEnter, int rowvalueToEnter, int columnvalueToEnter) 
	{
		super(color, color1, color2,  snapshotToEnter, rowvalueToEnter, columnvalueToEnter);
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

	public Piece copyPiece ()
	{
		Pawn newPiece = new Pawn(this._color, this._color1, this._color2,  new String[5][5], this._rowvalue, this._columnvalue);
		String[][] newSnapshot = new String[5][5];
		for (int r = 0; r < this._snapshot.length; r++)
		{
			for (int c = 0; c < this._snapshot.length; c++)
			{
				newSnapshot[r][c] = this._snapshot[r][c];
			}
		}
		newPiece._snapshot = newSnapshot;
		ArrayList<Object[]> newCache = new ArrayList<Object[]>();
		for (int x = 0; x < newPiece._scopeCache.size(); x++)
		{
			newCache.add(this._scopeCache.get(x));
		}
		newPiece._scopeCache = newCache;
		newPiece._rowvalue = this._rowvalue;
		newPiece._columnvalue = this._columnvalue;
		return newPiece;
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
