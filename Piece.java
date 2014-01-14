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
	protected boolean isWhite;
	protected ArrayList<Integer> scopeList;
	protected Board scopePossible;

	public Piece (boolean isColorWhite)
	{
		isWhite = isColorWhite;
	}

	public Board generatePossibleScope()
	{
		return new Board();
	}

}

