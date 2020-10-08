package com.capgemini;

public class TicTacToeGame {
	private static final char EMPTY = ' ';
	private static char[] ticTacToeBoard;

	//uc1
	public static void createBoard() {
		ticTacToeBoard = new char[10];
		for(int i = 1; i < ticTacToeBoard.length; i++) 
			ticTacToeBoard[i] = EMPTY;
	}
	
	public static void main (String[] args) {
		createBoard();
	}	
}
