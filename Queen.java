import java.util.ArrayList;

public class Queen extends Piece
{
	public Queen (boolean isColorWhite)
	{
		super(isColorWhite);
		scopeList = new ArrayList<Integer>();
		scopeList.add(2);
		scopeList.add(3);
	}
}