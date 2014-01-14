import java.util.ArrayList;

public class Knight extends Piece
{
	public Knight (boolean isColorWhite)
	{
		super(isColorWhite);
		scopeList = new ArrayList<Integer>();
		scopeList.add(4);
	}
}