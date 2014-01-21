// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Computer subclass for use with the Chess.java driver file.

import cs1.Keyboard;
import java.util.ArrayList;

public class Computer extends Player {

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

	public void selectMove(ArrayList<int[]> moves,Board board) {
		int move = ((int) (Math.random() * moves.size()));
		board.executeMove(moves.get(move));
		System.out.println(board);
	}
		
		
		

}
