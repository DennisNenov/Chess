// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Piece superclass for use with the Chess.java driver file.

import java.util.ArrayList;

//scopecode [x-change, y-change, is the motion continous, can the piece jump other pieces, capturable flag, special restriction value]

public abstract class Piece {

	protected String _color, _color1, _color2;
	protected ArrayList<Object[]> _scopeCache;
	protected String[][] _snapshot;
	protected int _rowvalue, _columnvalue;
	protected int _moveCounter;

	public Piece (String color, String color1, String color2, String[][] snapshotToEnter, int rowvalueToEnter, int columnvalueToEnter) 
	{
		_color = color;
		_color1 = color1;
		_color2 = color2;
		_snapshot = snapshotToEnter;
		_rowvalue = rowvalueToEnter;
		_columnvalue = columnvalueToEnter;
		_moveCounter = 0;
	}

	public int getCounter()
	{
		return _moveCounter;
	}

	public void increaseCounter()
	{
		_moveCounter++;
	}

	public String getColor() {
		return _color;
	}

	public String[][] getSnapshot()
	{
		return _snapshot;
	}
	public void setSnapshot (String[][] snapshotToEnter, int rowvalueToEnter, int columnvalueToEnter)
	{
		_snapshot = snapshotToEnter;
		_rowvalue = rowvalueToEnter;
		_columnvalue = columnvalueToEnter;
		
	}

	public abstract Piece copyPiece ();

	public ArrayList<Object[]> getCache()
	{
		return _scopeCache;
	}

	public abstract void refreshCache();
}

