<<<<<<< HEAD
import java.util.ArrayList;

//Movement Codes
// 1 - pawn
// 2 - rook
// 3 - bishop
// 4 - knight
// 5 - king

public abstract class Piece
{
	protected boolean _isWhite;
	protected ArrayList<Integer> _scopeList;
	protected Board _scopePossible;

	public Piece (boolean isColorWhite)
	{
		isWhite = isColorWhite;
	}

	public boolean getIsWhite() {
		return _isWhite;
	}

	public Board generatePossibleScope()
	{
		return new Board();
	}

}

