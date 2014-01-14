import java.util.ArrayList;

public class Pawn extends Piece
{
	public Pawn (boolean isColorWhite)
	{
		super(isColorWhite);
		scopeList = new ArrayList<Integer>();
		scopeList.add(1);
	}
	public String toString() {
		if (isColorWhite) {
			return "P";
		}
		else {
			return "p";
		}
	}
}
