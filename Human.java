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
			if (board.isValidPiece(xCor, yCor, getColor()) == "valid") {
				coordinates = xCor + "," + yCor;
				break;
			}
			else {
				selectError(board.isValidPiece(xCor, yCor, getColor()));
			}
		}
		return coordinates;
	}

	public void selectError(String error) {
		if (error == "out") {
			System.out.println("Error: invalid choice - not in bounds.");
			System.out.println("valid input = {0, 1, 2, 3, 4, 5, 6, 7}\n");
		}
		else if (error == "empty") {
			System.out.println("Error: invalid choice - tile contains no piece.");
			System.out.println("to move first select one of your pieces\n");
		}
		else {
			System.out.println("Error: invalid choice - piece is wrong color.");
			System.out.println("please choose a " + _color + " piece\n");
		}
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
