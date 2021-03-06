// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Queen subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Queen extends Piece {

	// constructor
	public Queen (String color, String color1, String color2, String[][] snapshotToEnter, int rowvalueToEnter, int columnvalueToEnter ) {
		super(color, color1, color2, snapshotToEnter, rowvalueToEnter, columnvalueToEnter);
		_scopeCache = new ArrayList<Object[]>();
		_scopeCache.add(new Object[]{1,0,true,false,true,true});
		_scopeCache.add(new Object[]{-1,0,true,false,true,true});
		_scopeCache.add(new Object[]{0,1,true,false,true,true});
		_scopeCache.add(new Object[]{0,-1,true,false,true,true});
		_scopeCache.add(new Object[]{1,1,true,false,true,true});
		_scopeCache.add(new Object[]{1,-1,true,false,true,true});
		_scopeCache.add(new Object[]{-1,1,true,false,true,true});
		_scopeCache.add(new Object[]{-1,-1,true,false,true,true});
	}

	public void refreshCache () {};

	public Piece copyPiece ()
	{
		Queen newPiece = new Queen(this._color, this._color1, this._color2,  new String[5][5], this._rowvalue, this._columnvalue);
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
			return "Q";
		}
		else {
			return "q";
		}
	}
}
