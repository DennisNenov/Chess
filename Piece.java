// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Piece superclass for use with the Chess.java driver file.

import java.util.ArrayList;

//Movement Codes
// 1 - pawn
// 2 - rook
// 3 - bishop
// 4 - knight
// 5 - king

public abstract class Piece
{
	protected String _color;
	protected ArrayList<Integer> _scopeList;
	protected Board _scopePossible;

	public Piece (String color) {
		_color = color;
	}

	public String getColor() {
		return _color;
	}

	public Board generatePossibleScope() {
		return new Board();
	}

}

