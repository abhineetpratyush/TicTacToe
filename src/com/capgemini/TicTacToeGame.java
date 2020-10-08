package com.capgemini;

public class TicTacToeGame {
	private static final char EMPTY = ' ';

	//uc1
	private static char[] createBoard() {
		char [] ticTacToeBoard = new char[10];
		for(int i = 1; i < ticTacToeBoard.length; i++) 
			ticTacToeBoard[i] = EMPTY;
		return ticTacToeBoard;
	}

	public static void main (String[] args) {
		createBoard();
	}	
}
