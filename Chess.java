// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Chess.java driver file.

import cs1.Keyboard;
import javax.swing.*;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.event.*;

public class Chess implements ActionListener {

	// instance variables
	private Board _board;	
	private Player _player1;
	private Player _player2;
	private JFrame _frame;
	private JPanel _panel;
	private GridLayout _grid;
	private JButton[][] _buttonBoard;
	private int _turn = 1;
	private boolean _isGUI;

	// default constructor
	public Chess() {
		System.out.println("\nChess by Nicholas Romanoff & Dennis Nennov\n");
		System.out.println("\nSelect from the game modes:");
		System.out.println("\n1 - Human vs. Human");
		System.out.println("2 - Human vs. Computer");
		System.out.println("3 - Computer vs. Computer");
		int temp = 0;
		while (true) {
			System.out.println("\nMode number: ");
			int mode = Keyboard.readInt();
			temp = mode;
			if (mode == 1) {
				System.out.println("\nPlayer One");
				System.out.println("choose your team color: ");
				String color = Keyboard.readString();
				_player1 = new Human(color);
				System.out.println("\nPlayer Two");
				System.out.println("choose your team color: ");
				color = Keyboard.readString();
				_player2 = new Human(color);
				break;
			}
			else if (mode == 2) {
				System.out.println("\nPlayer One");
				System.out.println("choose your team color: ");
				String color = Keyboard.readString();
				_player1 = new Human(color);
				System.out.println("\nPlayer Two: Red");
				if (_player1.getColor() == "Red") {
					_player2 = new Computer("Blue");
				}
				else {
					_player2 = new Computer("Red");
				}
				break;
			}
			else if (mode == 3) {
				System.out.println("\nPlayer One: Red");
				_player1 = new Computer("Red");
				System.out.println("\nPlayer Two: Blue");
				_player2 = new Computer("Blue");
				break;
			}
			else {
				System.out.println("\nError - invalid input.");
				System.out.println("Try again: select a game mode.");
			}	
		}
		while (true) {
			System.out.println("\nSelect a method of gameplay:");
			System.out.println("\n1 - Mouse: clicking in graphic window");
			System.out.println("2 - Keyboard: typing in coordinates");
			int mode = Keyboard.readInt();
			if (mode == 1) {
				_isGUI = true;
				if (temp == 1) {
					((Human)_player1).setIsGUI(true);
					((Human)_player2).setIsGUI(true);
				}
				else if (temp == 2) {
					((Human)_player1).setIsGUI(true);
				}
				break;
			}
			else if (mode == 2) {
				_isGUI = false;
				break;
			}
			else {
				System.out.println("\nError - invalid input.");
				System.out.println("Try again: select a game mode.");
			}
		}
				
		_board = new Board(_player1.getColor(), _player2.getColor());
		setupGUI();
	}

	// accessor method
	public Board getBoard() {
		return _board;
	}

	// turns
	public void run() {
		System.out.println("\nlowercase: " + _player1.getColor());
		System.out.println("uppercase: " + _player2.getColor() + "\n");
		System.out.println(_board);
		while (! ((_board.isCheckMated(_player1.getColor(), _player2.getColor())) || 
		         (_board.isCheckMated(_player2.getColor(), _player1.getColor())))) {
			if (_turn == 1) {
				if (_board.isChecked(_player1.getColor(), _player2.getColor()))
					System.out.println(_player1.getColor() + ", you are checked. Your turn.");
				else
					System.out.println(_player1.getColor() + ", your turn.");
				_player1.movePiece(_board);
				System.out.println(_board);
			_turn++;
			updateGUI();
			}
			if (_turn == 2) {
				if (_board.isChecked(_player2.getColor(), _player1.getColor()))
					System.out.println(_player2.getColor() + ", you are checked.");
				else
					System.out.println(_player2.getColor() + ", your turn.");
				_player2.movePiece(_board);
			_turn--;
			updateGUI();
			}
		}

		if ((_board.isCheckMated(_player1.getColor(), _player2.getColor())))
			System.out.println(_player2.getColor() + ", you have won.");
		else if ((_board.isCheckMated(_player2.getColor(), _player1.getColor())))
			System.out.println(_player1.getColor() + ", you have won.");
	}



	// GUI

	public void setupGUI() {

		_frame = new JFrame("Chess");
		_grid = new GridLayout(8,8);
		_grid.setVgap(1);
		_grid.setHgap(2);
		_panel = new JPanel(_grid);

		_buttonBoard = new JButton[8][8];
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				_buttonBoard[x][y] = new JButton(_board.getPieceString(x,y));
				_buttonBoard[x][y].addActionListener(this);
				if (_board.isEmpty(x,y) == true) {
					_buttonBoard[x][y].setBackground(Color.lightGray);			
				}
				else if (_board.getPieceColor(x,y) == _player1.getColor()) {
					_buttonBoard[x][y].setBackground(Color.red);
				}
				else if (_board.getPieceColor(x,y) == _player2.getColor()) {
					_buttonBoard[x][y].setBackground(Color.blue);
				}
			}
		}		

		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				_panel.add(_buttonBoard[x][y]);
			}
		}

		_frame.getContentPane().add(BorderLayout.CENTER, _panel);
		_frame.setSize(640,640);
		_frame.setVisible(true);
	}

	public void updateGUI() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {	
				_buttonBoard[x][y].setText(_board.getPieceString(x,y));	
				if (_board.isEmpty(x,y)) {
					_buttonBoard[x][y].setBackground(Color.red);			
				}
				else if (_board.getPieceColor(x,y) == _player1.getColor()) {
					_buttonBoard[x][y].setBackground(Color.red);
				}
				else if (_board.getPieceColor(x,y) == _player2.getColor()) {
					_buttonBoard[x][y].setBackground(Color.blue);
				}
			}
		}
	}

	// listener method for the 2d array of buttons
	public void actionPerformed(ActionEvent event) {
		if (_isGUI) {
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					if (event.getSource() == _buttonBoard[x][y]) {
						applyActionInfo(x,y);
						updateGUI();
					}
				}	
			}
		}
	}

	// helper function for actionPerformed
	public void applyActionInfo(int x, int y) {
		if (_turn == 1) {
			if (((Human)_player1).getSelectionStage() == 0) {
				System.out.print(x + "\n");
				System.out.println("y = " + y);
				((Human)_player1).selectPieceGUI(x, y, _board);
			}
			else if (((Human)_player1).getSelectionStage() == 1) {
				System.out.println("x =  " + x);
				System.out.println("y = " + y);
				((Human)_player1).selectMoveGUI(x, y, _board);
			}
		}
		else {
			if (((Human)_player2).getSelectionStage() == 0) {
				System.out.print(x + "\n");
				System.out.println("y = " + y);
				((Human)_player2).selectPieceGUI(x, y, _board);
			}
			else if (((Human)_player2).getSelectionStage() == 1) {
				System.out.println("x =  " + x);
				System.out.println("y = " + y);
				((Human)_player2).selectMoveGUI(x, y, _board);
			}
		}
	}

	public static void main(String [] args) {
		Chess chess = new Chess();
		chess.run();
	}
}
