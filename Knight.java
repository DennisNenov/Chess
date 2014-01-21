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

	public Piece copyPiece ()
	{
		Knight newPiece = new Knight(this._color, this._color1, this._color2);
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
			return "H";
		}
		else {
			return "h";
		}
	}
}
