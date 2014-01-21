// Nicholas Romanoff & Dennis Nenov
// AP CS1 Final Project

// Chess.java driver file.

import cs1.Keyboard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Chess {

	// instance variables
	private Board _board;	
	private Player _player1;
	private Player _player2;
	private JButton[][] _buttonBoard;

	// default constructor
	public Chess() {
		System.out.println("\nChess by Nicholas Romanoff & Dennis Nennov\n");
		System.out.println("Player One");
		_player1 = new Human();
		System.out.println("\nPlayer Two");
		_player2 = new Computer();
		_board = new Board(_player1.getColor(), _player2.getColor());
		setupGUI();
	}

	// accessor method
	public Board getBoard() {
		return _board;
	}

	// turns
	public void run() {
		int turns = 0;
		System.out.println("\nlowercase: " + _player1.getColor());
		System.out.println("uppercase: " + _player2.getColor() + "\n");
		System.out.println(_board);
		while (! ((_board.isCheckMated(_player1.getColor(), _player2.getColor())) || 
		         (_board.isCheckMated(_player2.getColor(), _player1.getColor())))) {
			if ((turns % 2) == 0) {
				if (_board.isChecked(_player1.getColor(), _player2.getColor()))
					System.out.println(_player1.getColor() + ", you are checked. Your turn.");
				else
					System.out.println(_player1.getColor() + ", your turn.");
				_player1.movePiece(_board);
				System.out.println(_board);
			}
			else {
				if (_board.isChecked(_player2.getColor(), _player1.getColor()))
					System.out.println(_player2.getColor() + ", you are checked.");
				else
					System.out.println(_player2.getColor() + ", your turn.");
				_player2.movePiece(_board);
			}
			turns++;
		}
		if ((_board.isCheckMated(_player1.getColor(), _player2.getColor())))
			System.out.println(_player2.getColor() + ", you have won.");
		else if ((_board.isCheckMated(_player2.getColor(), _player1.getColor())))
			System.out.println(_player1.getColor() + ", you have won.");
	}

	// GUI

	public void setupGUI() {

		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setBackground(Color.darkGray);

		_buttonBoard = new JButton[8][8];
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				_buttonBoard[x][y] = new JButton(_board.getPieceString(x,y));
			}
		}		

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				panel.add(_buttonBoard[x][y]);
			}
		}

		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(640,640);
		frame.setVisible(true);
	}


		

	







	public static void main(String [] args) {
		Chess chess = new Chess();
		chess.run();
	}
}
