// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Rook subclass for use with the Chess.java driver file.

import java.util.ArrayList;

public class Rook extends Piece {

	// constructor
	public Rook (String color, String color1, String color2) 
	{
		super(color, color1, color2);
		_scopeCache = new ArrayList<Object[]>();
		_scopeCache.add(new Object[]{1,0,true,false,true,true});
		_scopeCache.add(new Object[]{-1,0,true,false,true,true});
		_scopeCache.add(new Object[]{0,1,true,false,true,true});
		_scopeCache.add(new Object[]{0,-1,true,false,true,true});

	}

	public Piece copyPiece ()
	{
		Rook newPiece = new Rook(this._color, this._color1, this._color2);
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

	public void refreshCache () {}

	// toString
	public String toString() {
		if (_color.equals(_color2)) {
			return "R";
		}
		else {
			return "r";
		}
	}
}
