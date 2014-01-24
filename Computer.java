// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Computer subclass for use with the Chess.java driver file.

import cs1.Keyboard;
import java.util.ArrayList;

public class Computer extends Player {

	int removalcount = 0;

	// constructor
	public Computer(String color) {
		super(color);
	}

	public void movePiece(Board board) {
		ArrayList<int[]> moves = new ArrayList<int[]>();
		moves = generateMoves(board);
		selectMove(moves, board);
	}

	public ArrayList<int[]> generateMoves(Board board) {
		ArrayList<int[]> moves = new ArrayList<int[]>();
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				int[] piece = {x,y};
				if (board.isValidPiece(piece, _color)) {
					for (int p = 0; p < 8; p++) {
						for (int q = 0; q < 8; q++) {
							int[] input  = {p,q};
							int[] move = createMove(piece, input);
							if (board.isValidMoveComp(move)) {
								moves.add(move);
							}
						}
					}
				}
			}
		}
		return moves;
	}

	public ArrayList<int[]> filterMoves(ArrayList<int[]> moves, Board board)
	{
		for (int i = 0; i < moves.size(); i++)
		{
			int x1 = moves.get(i)[0];
			int y1 = moves.get(i)[1];
			int x2 = moves.get(i)[2];
			int y2 = moves.get(i)[3];

			Board newBoard = Board.copyBoard(board);
			newBoard.setXY(x2,y2, newBoard.getPiece(x1,y1));
			newBoard.setXY(x1,y1, null);
			//System.out.println("x1: " + x1 + "y1: " + y1 + "x2: " + x2 + "y2: " + y2);
			//System.out.println("\n\nThe Computer Check Board: " + newBoard + "\n\n");
			if (newBoard.isChecked(_color, newBoard.flipColor(_color)))
			{
				removalcount++;
				try { 
					System.out.println("pot check removed: " + "(" + x1 + "," + y1 + ") to" + "(" + x2 + "," + y2 + ")");
					Thread.sleep(2); 
					}
				catch(InterruptedException e) 
					{ 
					} 
				
				//System.out.println(newBoard);
				moves.remove(i);
			} 
		}
		return moves;
	}

	public void selectMove(ArrayList<int[]> moves,Board board) {
		ArrayList<int[]> newMoves = filterMoves(moves, board);
		if (newMoves.size() > 0)
			moves = newMoves;
		for (int i = 0; i < moves.size(); i++)
		{
			if (board.getPiece(moves.get(i)[2], moves.get(i)[3]) instanceof King)
				moves.remove(i);
		} 	
		int move = ((int) (Math.random() * moves.size()));
		board.executeMove(moves.get(move), this);
		int x1 = moves.get(move)[0];
		int y1 = moves.get(move)[1];
		int x2 = moves.get(move)[2];
		int y2 = moves.get(move)[3];
		System.out.println("Moved from (" + x1 + "," + y1 + ") to" + "(" + x2 + "," + y2 + ")");
		//System.out.println(board);
	}

		
		
		

}
