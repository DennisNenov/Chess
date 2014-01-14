import java.util.ArrayList;

public class Bishop extends Piece
{
	public Bishop (boolean isColorWhite)
	{
		super(isColorWhite);
		scopeList = new ArrayList<Integer>();
		scopeList.add(3);
	}
}