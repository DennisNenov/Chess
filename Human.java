// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Human subclass for use with the Chess.java driver file.

import cs1.Keyboard;

public class Human extends Player {

	// default constructor
	public Human() {
		super();
	}

	// methods
	public void movePiece(Board board) {
		String coordinates = selectPiece(board);
		selectMove(coordinates, board);
	}

	public String selectPiece(Board board) {
		boolean complete = false;
		String coordinates = "";
		while (true) {
			System.out.println("To select a piece type its coordinates [0,7] :");
			System.out.print("x = ");
			int xCor = Keyboard.readInt();
			System.out.print("y = ");
			int yCor = Keyboard.readInt();
			System.out.println();
			if (board.isValidPiece(xCor, yCor, getColor())) {
				coordinates = xCor + "," + yCor;
				break;
			}
			else {
				System.out.println("Error: invalid choice - please try again.");
			}
		}
		return coordinates;
	}

	public void selectMove(String coordinates, Board board) {
		boolean complete = false;
		while (!(complete)) {
			System.out.println("Type coordinates of piece destination [0,7] :");
			System.out.print("x = ");
			int xCor = Keyboard.readInt();
			System.out.print("y = ");
			int yCor = Keyboard.readInt();
			System.out.println();
			if (board.isValidMove(xCor, yCor, coordinates)) {
				complete = true;
			}
		}
	}
				

		
	
		
}
