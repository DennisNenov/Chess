import java.util.ArrayList;

public class Castling
{

	private static boolean canCastle (Board board, String color, int side)
	{

		if (board.isChecked(color, board.flipColor(color)))
		{
			return false;
		}
		int rowToCheck = 0;
		ArrayList<Integer> colToCheck  = new ArrayList<Integer>();
		int rookCol = 0;
		int kingMoveCol = 4;
		int rookMoveCol = 5;

		if (color == board.getColor1())
		{
			rowToCheck = 0;
		}

		else if (color == board.getColor2())
		{
			rowToCheck = 7;
		}

		//side 0 is kingside
		if (side == 0)
		{
			colToCheck.add(5);
			colToCheck.add(6);
			rookCol = 7;
			kingMoveCol = 6;
			rookMoveCol = 5;
		}		
		//side 1 is queenside
		else if (side == 1)
		{
			colToCheck.add(1);
			colToCheck.add(2);
			colToCheck.add(3);
			rookCol = 0;
			kingMoveCol = 2;
			rookMoveCol = 3;
		}


		if ( (board.isEmpty(rowToCheck, rookCol)) || (!((board.getPiece(rowToCheck, rookCol) instanceof Rook)) || (board.getPiece(rowToCheck, rookCol).getCounter() > 0)))
		{
			return false;
		}

		if ( (board.isEmpty(rowToCheck, 4)) || (!((board.getPiece(rowToCheck, 4) instanceof King)) || (board.getPiece(rowToCheck, 4).getCounter() > 0)))
		{
			return false;
		}

		boolean[][] enemyScope = board.getColorScope(board.flipColor(color));

		for (int i = 0; i < colToCheck.size(); i++)
		{
			if ((!(board.isEmpty(rowToCheck, colToCheck.get(i)))) || enemyScope[rowToCheck][colToCheck.get(i)])
			{
				return false;
			}
		}

		Board newBoard = Board.copyBoard(board);
		newBoard.setXY(rowToCheck,kingMoveCol, newBoard.getPiece(rowToCheck,4));
		newBoard.setXY(rowToCheck,4, null);

		newBoard.setXY(rowToCheck,rookMoveCol, newBoard.getPiece(rowToCheck,rookCol));
		newBoard.setXY(rowToCheck,rookCol, null);

		if(newBoard.isChecked(color, newBoard.flipColor(color)))
		{
			return false;
		}
		return true;
	}

	public static ArrayList<Object[]> eventCheck(Board board)
	{
		ArrayList<Object[]> feasibleCodes = new ArrayList<Object[]>();
		String[] colorList = {board.getColor1(), board.getColor2()};
		for (int color = 0; color < colorList.length; color++)
		{
			for (int side = 0; side <= 1; side++)
			{
				if (canCastle(board, colorList[color], side))
				{
					feasibleCodes.add(new Object[]{colorList[color],side});
				}
			}
		}
		return feasibleCodes;
	}

	public  static void eventExecute(ArrayList<Object[]> codes, Board board)
	{

		for (int i = 0; i < codes.size(); i++)
		{

			String color = (String) codes.get(i)[0];
			Integer side = (Integer) codes.get(i)[1];

			int rookCol = 0;
			int kingMoveCol = 4;
			int rookMoveCol = 5;
			int rowToCheck = 0;

			if (color == board.getColor1())
			{
				rowToCheck = 0;
			}

			else if (color == board.getColor2())
			{
				rowToCheck = 7;
			}

			//side 0 is kingside
			if (side == 0)
			{
				rookCol = 7;
				kingMoveCol = 6;
				rookMoveCol = 5;
			}		
			//side 1 is queenside
			else if (side == 1)
			{
				rookCol = 0;
				kingMoveCol = 2;
				rookMoveCol = 3;
			}

			board.setXY(rowToCheck,kingMoveCol, board.getPiece(rowToCheck,4));
			board.setXY(rowToCheck,4, null);

			board.setXY(rowToCheck,rookMoveCol, board.getPiece(rowToCheck,rookCol));
			board.setXY(rowToCheck,rookCol, null);
		}

	}
}