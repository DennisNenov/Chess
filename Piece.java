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
=======
// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Piece superclass for use with the Chess.java driver file.

public class Piece {

	private String _color;

	public Piece(String color) {
		_color = color;
	}
}
>>>>>>> 9b89bbbae07235841b98b3064c993208a6ea14b4
