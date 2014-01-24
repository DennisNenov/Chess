// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Human subclass for use with the Chess.java driver file.

import cs1.Keyboard;
import java.util.ArrayList;

public class Human extends Player {

	// constructor
	public Human(String color) {
		super(color);
	}

	// methods
	public void movePiece(Board board) {
		int[] coordinates = selectPiece(board);
		selectMove(coordinates, board);
	}

	public int[] selectPiece(Board board) {
		int[] coordinates = new int[2];
		while (true) {
			System.out.println("To select a piece type its coordinates [0,7] :");
			int[] input = inputCoordinates();
			if (board.isValidPiece(input, _color)) {
				coordinates = input;
				break;
			}
			else {
				feedback(input, board);
			}
		}
		return coordinates;
	}

	public void selectMove(int[] coordinates, Board board) {
		while (true) {
			System.out.println("Type coordinates of piece destination [0,7] :");
			int[] move = createMove(coordinates, inputCoordinates());
			if (board.isValidMove(move)) {
				board.executeMove(move, this);
				break;
			}
			feedback(move, board);
		}
	}

	public int[] inputCoordinates() {
		int[] coordinates = new int[2];
		System.out.print("x = ");
		coordinates[0] = Keyboard.readInt();
		System.out.print("y = ");
		coordinates[1] = Keyboard.readInt();
		return coordinates;
	}

	public void feedback(int[] coordinates, Board board) {
		int x = coordinates[0];
		int y = coordinates[1];
		if (board.isOut(x,y)) {
			System.out.println("Error: invalid choice - not in bounds.");
			System.out.println("valid input = {0, 1, 2, 3, 4, 5, 6, 7}\n");
		}
		else if (board.isEmpty(x,y)) {
			System.out.println("Error: invalid choice - tile contains no piece.");
			System.out.println("to move first select one of your pieces\n");
		}
		else {
			System.out.println("Error: invalid choice - piece is wrong color.");
			System.out.println("please choose a " + _color + " piece\n");
		}
	}				
}
