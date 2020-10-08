package com.capgemini;

import java.util.Scanner;

enum PlayerNames{
	PLAYER, COMPUTER
}

public class TicTacToeGame {
	private static final char EMPTY = ' ';
	private static final char CHARACTER_X = 'X';
	private static final char CHARACTER_O = 'O';
	private static final int HEADS = 0;

	/**
	 * uc1
	 * @return
	 */
	private static char[] createBoard() {
		char [] ticTacToeBoard = new char[10];
		for(int i = 1; i < ticTacToeBoard.length; i++) 
			ticTacToeBoard[i] = EMPTY;
		return ticTacToeBoard;
	}

	/**
	 * uc2
	 * @param playerLetter
	 * @return
	 */
	private static char selectLetter(char playerLetter) {
		char computerLetter;
		if(playerLetter == CHARACTER_X)
			computerLetter = CHARACTER_O;
		else
			computerLetter = CHARACTER_X;
		return computerLetter;
	}

	/**
	 * uc3
	 * @param ticTacToeBoard
	 */
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

	/**
	 * uc4
	 * @param ticTacToeBoard
	 * @param moveIndex
	 * @return
	 */
	private static int checkFree(char[] ticTacToeBoard, char chosenLetter, String currentPlayer) {
		Scanner takeInput = new Scanner(System.in);
		int moveIndex;
		boolean emptyStatus;
		do {
			System.out.println("Enter index to place letter " + chosenLetter + " for " + currentPlayer);
			moveIndex = takeInput.nextInt();
			if(ticTacToeBoard[moveIndex] == EMPTY) {
				emptyStatus = true;
				System.out.println("Index available");
			}
			else {
				emptyStatus = false;
				System.out.println("Index not available");
			}
		} while(emptyStatus == false);
		return moveIndex;
	}

	/**
	 * uc5
	 * @param ticTacToeBoard
	 * @param playerLetter
	 * @param moveIndex
	 */
	private static void makeMove(char[] ticTacToeBoard, char playerLetter, int moveIndex) {
		ticTacToeBoard[moveIndex] = playerLetter;
	}

	/**
	 * uc6
	 * @return
	 */
	private static String getWhoPlaysFirst() {
		int randomInt = (int)Math.floor((Math.random()*10)%2);
		if(randomInt == HEADS)
			return  PlayerNames.PLAYER.name();
		else
			return PlayerNames.COMPUTER.name();
	}

	/**
	 * uc7
	 * @param ticTacToeBoard
	 * @param chosenLetter
	 * @param lastPlayer
	 * @return
	 */
	private static String gameManager(char[] ticTacToeBoard, char chosenLetter) {
		int counter = 0;
		String gameMessage = "change";
		if((ticTacToeBoard[1] == chosenLetter && ticTacToeBoard[2] == chosenLetter && ticTacToeBoard[3] == chosenLetter) || 
				(ticTacToeBoard[4] == chosenLetter && ticTacToeBoard[5] == chosenLetter && ticTacToeBoard[6] == chosenLetter) || 
				(ticTacToeBoard[7] == chosenLetter && ticTacToeBoard[8] == chosenLetter && ticTacToeBoard[9] == chosenLetter) || 
				(ticTacToeBoard[1] == chosenLetter && ticTacToeBoard[4] == chosenLetter && ticTacToeBoard[7] == chosenLetter) ||
				(ticTacToeBoard[2] == chosenLetter && ticTacToeBoard[5] == chosenLetter && ticTacToeBoard[8] == chosenLetter) ||
				(ticTacToeBoard[3] == chosenLetter && ticTacToeBoard[6] == chosenLetter && ticTacToeBoard[9] == chosenLetter) ||
				(ticTacToeBoard[1] == chosenLetter && ticTacToeBoard[5] == chosenLetter && ticTacToeBoard[9] == chosenLetter) ||
				(ticTacToeBoard[3] == chosenLetter && ticTacToeBoard[5] == chosenLetter && ticTacToeBoard[7] == chosenLetter)) {
			gameMessage = "win";
		}
		else {
			for(int i = 1; i <=9; i++) 
				if(ticTacToeBoard[i] == EMPTY)
					counter++;
			if(counter == 0) {
				gameMessage = "tie";
			} 
		}
		return gameMessage;
	}

	private static char swapPlayerLetter(char chosenLetter) {
		if(chosenLetter == CHARACTER_X)
			chosenLetter = CHARACTER_O;
		else
			chosenLetter = CHARACTER_X;
		return chosenLetter;
	}

	private static String swapPlayerTurn(String currentPlayer) {
		if(currentPlayer.contains(PlayerNames.PLAYER.name()))
			currentPlayer = PlayerNames.COMPUTER.name();
		else
			currentPlayer = PlayerNames.PLAYER.name();
		return currentPlayer;
	}

	/**
	 * uc8
	 * @param ticTacToeBoard
	 * @param chosenLetter
	 * @return
	 */
	private static int computerPlayToWin(char[] ticTacToeBoard, char chosenLetter){
		String computerWinPossibility;
		int cellNoForComputerWin = 0;
		char [] ticTacToeBoardCopy = ticTacToeBoard.clone();
		for(int i = 1; i <=9; i++) {
			if(ticTacToeBoardCopy[i] == EMPTY) {
				ticTacToeBoardCopy[i] = chosenLetter; 
				computerWinPossibility = gameManager(ticTacToeBoardCopy, chosenLetter); 
				if(computerWinPossibility.contains("win")) {
					cellNoForComputerWin = i;
				}
				ticTacToeBoardCopy[i] = EMPTY;
			}
		}
		System.out.println("cell for computer win: " + cellNoForComputerWin);
		return cellNoForComputerWin; 
	}

	/**
	 * uc9
	 * @param ticTacToeBoard
	 * @param chosenLetter
	 * @return
	 */
	private static int computerPlayToBlock(char[] ticTacToeBoard, char chosenLetter) {
		char swappedLetter = swapPlayerLetter(chosenLetter);
		String playerWinPossibility;
		int cellNoForPlayerWin = 0;
		char [] ticTacToeBoardCopy = ticTacToeBoard.clone();
		for(int i = 1; i <=9; i++) {
			if(ticTacToeBoardCopy[i] == EMPTY) {
				ticTacToeBoardCopy[i] = swappedLetter; 
				playerWinPossibility = gameManager(ticTacToeBoardCopy, swappedLetter); 
				if(playerWinPossibility.contains("win")) {
					cellNoForPlayerWin = i;
				}
				ticTacToeBoardCopy[i] = EMPTY;
			}
		}
		System.out.println("cell for computer block: " + cellNoForPlayerWin);
		return cellNoForPlayerWin; 
	}

	/**
	 * uc10
	 * @param ticTacToeBoard
	 * @return
	 */
	private static int computerPlayToCorner(char[] ticTacToeBoard) {
		int freeCornerIndex = 0;
		if(ticTacToeBoard[1] == EMPTY)
			freeCornerIndex = 1;
		else if(ticTacToeBoard[3] == EMPTY)
			freeCornerIndex = 3;
		else if(ticTacToeBoard[7] == EMPTY)
			freeCornerIndex = 7;
		else if(ticTacToeBoard[9] == EMPTY)
			freeCornerIndex = 9;
		System.out.println(freeCornerIndex);
		return freeCornerIndex;
	}

	/**
	 * uc11
	 * @param ticTacToeBoard
	 * @return
	 */
	private static int computerPlayToCentreOrSide(char[] ticTacToeBoard) {
		int freeCentreOrSideIndex = 0;
		if(ticTacToeBoard[5] == EMPTY)
			freeCentreOrSideIndex = 5;
		else if(ticTacToeBoard[2] == EMPTY)
			freeCentreOrSideIndex = 2;
		else if(ticTacToeBoard[4] == EMPTY)
			freeCentreOrSideIndex = 4;
		else if(ticTacToeBoard[6] == EMPTY)
			freeCentreOrSideIndex = 6;
		else if(ticTacToeBoard[8] == EMPTY)
			freeCentreOrSideIndex = 8;
		System.out.println(freeCentreOrSideIndex);
		return freeCentreOrSideIndex;
	}

	public static void main (String[] args) {
		Scanner takeInput = new Scanner(System.in);
		char[] ticTacToeBoard = createBoard();
		System.out.println("Player letter: ");
		char playerLetter = takeInput.next().charAt(0);
		char computerLetter = selectLetter(playerLetter);
		System.out.println("Computer Letter: " + computerLetter);
		String currentPlayer = getWhoPlaysFirst();
		char chosenLetter;
		if(currentPlayer.contains("PLAYER"))
			chosenLetter = playerLetter;
		else
			chosenLetter = computerLetter;
		System.out.println("First Chance for " + currentPlayer);
		System.out.println("\n--Initial status of board--");
		showBoard(ticTacToeBoard);
		String gameStatus;
		int computerPlayReturnWin = 0, computerPlayReturnBlock = 0, computerFreeCornerAvailable = 0, computerFreeCentreOrSideAvailable = 0, moveIndex = 0;
		do {
			if(currentPlayer.contains(PlayerNames.COMPUTER.name())) {
				computerPlayReturnWin = computerPlayToWin(ticTacToeBoard, chosenLetter);
				computerPlayReturnBlock = computerPlayToBlock(ticTacToeBoard, chosenLetter);
				computerFreeCornerAvailable = computerPlayToCorner(ticTacToeBoard);
				computerFreeCentreOrSideAvailable = computerPlayToCentreOrSide(ticTacToeBoard);
				if(computerPlayReturnWin != 0) {
					moveIndex = computerPlayReturnWin;
				}
				else if(computerPlayReturnBlock != 0) {
					moveIndex = computerPlayReturnBlock;
					computerPlayReturnBlock = 0; //this is important to reset
				}
				else if(computerFreeCornerAvailable != 0) {
					moveIndex = computerFreeCornerAvailable;
					computerFreeCornerAvailable = 0; //this is important to reset
				}
				else if(computerFreeCentreOrSideAvailable != 0) {
					moveIndex = computerFreeCentreOrSideAvailable;
					computerFreeCentreOrSideAvailable = 0; //this is important to reset
				}
			}
			else {
				moveIndex = checkFree(ticTacToeBoard, chosenLetter, currentPlayer);
			}
			makeMove(ticTacToeBoard, chosenLetter, moveIndex);
			System.out.println("\n--Updated board after the move--");
			showBoard(ticTacToeBoard);
			gameStatus = gameManager(ticTacToeBoard, chosenLetter);
			chosenLetter = swapPlayerLetter(chosenLetter);
			currentPlayer = swapPlayerTurn(currentPlayer);
		}while(gameStatus.contains("change"));
	}	
}
