// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Chess.java driver file.

import cs1.Keyboard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Chess implements ActionListener {

	// instance variables
	private Board _board;	
	private Player _player1;
	private Player _player2;
	private JPanel panel;
	private JButton[][] _buttonBoard;

	// default constructor
	public Chess() {
		System.out.println("\nChess by Nicholas Romanoff & Dennis Nenov\n");
		System.out.println("\nSelect from the game modes:");
		System.out.println("\n1 - Human vs. Human");
		System.out.println("2 - Human vs. Computer");
		System.out.println("3 - Computer vs. Computer");
		while (true) {
			System.out.println("\nMode number: ");
			int mode = Keyboard.readInt();
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
		_board = new Board(_player1.getColor(), _player2.getColor());
		setupGUI();
	}

	// accessor method
	public Board getBoard() {
		return _board;
	}

	public boolean gameToRun()
	{
		return (! ((_board.isCheckMated(_player1.getColor(), _player2.getColor())) || (_board.isCheckMated(_player2.getColor(), _player1.getColor())) || (_board.isTied(_player1, _player2))));
	}
	// turns
	public void run() {
		int turns = 0;
		System.out.println("\nlowercase: " + _player1.getColor());
		System.out.println("uppercase: " + _player2.getColor() + "\n");
		System.out.println("Board before move:\n" + _board);
		while (gameToRun()) {
			System.out.println("Board before move:\n" + _board);
			if ((turns % 2) == 0) {
				if (_board.isChecked(_player1.getColor(), _player2.getColor()))
					System.out.println(_player1.getColor() + ", you are checked. Your turn.");
				else
					System.out.println(_player1.getColor() + ", your turn.");
				_player1.movePiece(_board);
				
			}
			else {
				if (_board.isChecked(_player2.getColor(), _player1.getColor()))
					System.out.println(_player2.getColor() + ", you are checked.");
				else
					System.out.println(_player2.getColor() + ", your turn.");
				_player2.movePiece(_board);
			}
			System.out.println("Board after move:\n" + _board);
			turns++;
		}

		if ((_board.isCheckMated(_player1.getColor(), _player2.getColor())))
			System.out.println(_player2.getColor() + ", you have won.");
		else if ((_board.isCheckMated(_player2.getColor(), _player1.getColor())))
			System.out.println(_player1.getColor() + ", you have won.");
		else if ((_board.isTied(_player1, _player2)))
			System.out.println(_player1.getColor() + " and " + _player2.getColor() + ", you have both tied.");
	}

	// GUI

	public void setupGUI() {

		JFrame frame = new JFrame();
		GridLayout grid = new GridLayout(8,8);
		grid.setVgap(1);
		grid.setHgap(2);
		panel = new JPanel(grid);

		_buttonBoard = new JButton[8][8];
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				_buttonBoard[x][y] = new JButton(_board.getPieceString(x,y));
				_buttonBoard[x][y].addActionListener(this);
			}
		}		

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				panel.add(_buttonBoard[x][y]);
			}
		}

		panel.setBackground(Color.darkGray);	
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(640,640);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (event.getSource() == _buttonBoard[x][y]) {
					_buttonBoard[x][y].setText("!");
				}
			}	
		}
	}

	public static void main(String [] args) {
		Chess chess = new Chess();
		chess.run();
	}
}
