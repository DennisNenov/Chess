// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Piece superclass for use with the Chess.java driver file.

import java.util.ArrayList;

//scopecode [x-change, y-change, is the motion continous, can the piece jump other pieces, special restriction value]

public abstract class Piece {

	protected String _color;
	protected ArrayList<Object[]> _scopeCache;

	public Piece (String color) {
		_color = color;
	}

	public String getColor() {
		return _color;
	}

	public ArrayList<Object[]> getCache()
	{
		return _scopeCache;
	}

	public abstract void refreshCache();

	public abstract void refreshCache (int codeToRefresh);





}

