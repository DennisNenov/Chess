import java.util.ArrayList;

public class Rook extends Piece
{
	public Rook (boolean isColorWhite)
	{
		super(isColorWhite);
		scopeList = new ArrayList<Integer>();
		scopeList.add(2);
	}
}