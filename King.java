import java.util.ArrayList;

public class King extends Piece
{
	public King (boolean isColorWhite)
	{
		super(isColorWhite);
		scopeList = new ArrayList<Integer>();
		scopeList.add(5);
	}
}