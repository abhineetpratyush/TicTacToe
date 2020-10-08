package com.capgemini;

import java.util.Scanner;

public class TicTacToeGame {
	private static final char EMPTY = ' ';
	private static final char CHARACTER_X = 'X';
	private static final char CHARACTER_O = 'O';

	//uc1
	private static char[] createBoard() {
		char [] ticTacToeBoard = new char[10];
		for(int i = 1; i < ticTacToeBoard.length; i++) 
			ticTacToeBoard[i] = EMPTY;
		return ticTacToeBoard;
	}

	//uc2
	private static char selectLetter(char playerLetter) {
		char computerLetter;
		if(playerLetter == CHARACTER_X)
			computerLetter = CHARACTER_O;
		else
			computerLetter = CHARACTER_X;
		return computerLetter;
	}

	//uc3
	private static void showBoard(char[] ticTacToeBoard) {
		for(int rowHead = 1; rowHead <= 7; rowHead += 3) {
			for(int cellHead = rowHead; cellHead < rowHead + 3; cellHead++) {
				System.out.print(ticTacToeBoard[cellHead]);
				if(cellHead % 3 != 0)
					System.out.print(" | ");
			}
			System.out.print("\n");
		}
	}
	
	//uc4
	private static void makeMove(char[] ticTacToeBoard, char playerLetter, int moveIndex) {
		if(ticTacToeBoard[moveIndex] == EMPTY) 
			ticTacToeBoard[moveIndex] = playerLetter; 
		else
			System.out.println("Cell is occupied!");
	}

	public static void main (String[] args) {
		Scanner takeInput = new Scanner(System.in);
		char[] ticTacToeBoard = createBoard();
		System.out.println("Player letter (X or O): ");
		char playerLetter = takeInput.next().charAt(0);
		char computerLetter = selectLetter(playerLetter);
		System.out.println("Computer Letter: " + computerLetter);
		showBoard(ticTacToeBoard);
		System.out.println("Enter index to place letter " + playerLetter);
		int moveIndex = takeInput.nextInt();
		makeMove(ticTacToeBoard, playerLetter, moveIndex);
		showBoard(ticTacToeBoard);
	}	
}
