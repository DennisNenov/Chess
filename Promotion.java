import java.util.ArrayList;

public class Promotion
{
	public static ArrayList<Object[]> eventCheck(Board board)
	{
		ArrayList<Object[]> feasibleCodes = new ArrayList<Object[]>();
		String topColor = board.getColor1();
		String bottomColor = board.getColor2();
		for (int i = 0; i <= 7; i++)
		{
			if ((!(board.isEmpty(0, i))) && (board.getPieceColor(0,i).equals(bottomColor)) && (board.getPiece(0,i) instanceof Pawn))
			{
				feasibleCodes.add(new Object[]{bottomColor,0,i, new Queen(bottomColor, topColor, bottomColor,  new String[5][5], 0, i)});
			}

			if ((!(board.isEmpty(7, i))) && (board.getPieceColor(7,i).equals(topColor)) && (board.getPiece(7,i) instanceof Pawn))
			{
				feasibleCodes.add(new Object[]{topColor,7,i,new Queen(topColor, topColor, bottomColor,  new String[5][5], 7, i)});
			}
		}
		return feasibleCodes;

	}
	//the code you submit is [color, rowcord, col cord, a newly created object that the pawn wants to transform into]
	public  static void eventExecute(ArrayList<Object[]> codes, Board board)
	{
		for (int i = 0; i < codes.size(); i++)
		{
			int x = (int) codes.get(i)[1];
			int y = (int) codes.get(i)[2];
			Piece newValue = (Piece) codes.get(i)[3];
			board.setXY(x,y,newValue);
		}
	}
}