// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Human subclass for use with the Chess.java driver file.

import cs1.Keyboard;
import java.util.ArrayList;

public class Human extends Player {

	// instance variables
	private int _selectionStage;
	// 0 - hasn't selected a piece
	// 1 - piece is selected
	// 2 - not player's turn
	private int[] _coordinatesSelected;
	// {-1,-1} indicates that it's not the person's turn
	private boolean _isGUI;

	// constructor
	public Human(String color) {
		super(color);
		_selectionStage = 2;
	}

	// methods

	public int getSelectionStage() {
		return _selectionStage;
	}

	public int[] getCoordinatesSelected() {
		return _coordinatesSelected;
	}

	public boolean setIsGUI(boolean gui) {
		_isGUI = gui;
		return gui;
	}

	public void movePiece(Board board) {
		_selectionStage = 0;
		if (_isGUI) {
			System.out.println("Please click the piece that you would like to move.");
		}
		else {
			System.out.println("Type in the coordinates of the piece that you would like to move :");
		}
		while (_selectionStage == 0) {
			if (!(_isGUI)) {
				selectPieceKeyboard(board);
			}
			if (_selectionStage == 1) {
				break;
			}
		}
		if (_isGUI) {
			System.out.println("\nPlease click the square where you would like to move this piece.");
		}
		else {
			System.out.println("Type in the coordinates of the square where you would like to move this piece.");
		}
		while (_selectionStage == 1) {
			if (!(_isGUI)) {
				selectMoveKeyboard(board);
			}
			if (_selectionStage == 2) {
				break;
			}
		}
	}

	// methods for moving a piece with GUI input

	public void selectPieceGUI(int x, int y, Board board) {
		int[] input = {x, y};
		if (board.isValidPiece(input, _color)) {
			System.out.println("\nPiece at (" + x + "," + y + ") selected.");
			_coordinatesSelected = input;
			_selectionStage = 1;
		}
		else {
			feedback(input, board);
		}
	}

	public void selectMoveGUI(int x, int y, Board board) {
		int[] input = {x, y};
		int[] move = createMove(_coordinatesSelected, input);
		if (board.isValidMove(move)) {
			System.out.println("\nPiece has been moved to (" + x + "," + y + ")");
			_selectionStage = 2;
			board.executeMove(move, _opponent);
		}
		else {
			feedback(move, board);
		}
	}

	// methods for moving a piece with keyboard input

	public void selectPieceKeyboard(Board board) {
		int[] input = inputCoordinates();
		if (board.isValidPiece(input, _color)) {
			_coordinatesSelected = input;
			_selectionStage = 1;
		}
		else {
			feedback(input, board);
		}
	}

	public void selectMoveKeyboard(Board board) {
		int[] move = createMove(_coordinatesSelected, inputCoordinates());
		if (board.isValidMove(move)) {
			board.executeMove(move, _opponent);
			resetCS();
			_selectionStage = 2;
		}
		else {
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

	// helper method for returning incorrect input with GUI or keyboard
	public void feedback(int[] coordinates, Board board) {
		int x = coordinates[0];
		int y = coordinates[1];
		if (board.isOut(x,y)) {
			System.out.println("Error: invalid choice - not in bounds.\n");
			System.out.println("valid input = {0, 1, 2, 3, 4, 5, 6, 7}\n");
		}
		else if (board.isEmpty(x,y)) {
			System.out.println("Error: invalid choice - tile contains no piece.\n");
			System.out.println("to move first select one of your pieces!\n");
		}
		else {
			System.out.println("Error: invalid choice - this piece can't be moved by you!\n");
			System.out.println(_color + ",please choose  another piece.");
		}
	}

	public boolean checkForCastle (String color, Board _board) {
		ArrayList<Object[]> codes = Castling.eventCheck(_board);
		for (int i = 0; i < codes.size(); i++) {
			if (!(codes.get(i)[0].equals(color))) {
				codes.remove(i);
			}
		}
		if (codes.size() == 1) {
			String side = "";
			if (codes.get(0)[1] == 0) {
				side = "kingside";
			}
			if (codes.get(0)[1] == 1) {
				side = "queenside";
			}
			System.out.println("You have the option of castling " + side + ", would you like to do so?");
			System.out.println("Press 0 for yes, and 1 for no.");
			int input = Keyboard.readInt();
			if (input == 0) {
				Castling.eventExecute(codes, _board);
				return true;
			}
		}
		else if (codes.size() == 2) {

			System.out.println("You have the option of castling on either side, would you like to do so?");
			System.out.println("Press 0 for kingside, 1 for queenside, and 2 for no.");
			int input = Keyboard.readInt();
			if (input == 0 || input == 1) {
				for (int i = 0; i < codes.size(); i++) {
					if (!(codes.get(i)[1] == input)) {
						codes.remove(i);
					}
				}
				Castling.eventExecute(codes, _board);
				return true;
			}
		}
		return false;
	}		

	// helper method for reseting _coordinatesSelected instance
	public void resetCS() {
		int[] reset = {-1, -1};
		_coordinatesSelected = reset;
	}
	
}
