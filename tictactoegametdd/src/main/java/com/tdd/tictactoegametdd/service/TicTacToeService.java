package com.tdd.tictactoegametdd.service;

import java.util.Arrays;
import java.util.stream.IntStream;

import com.tdd.tictactoegametdd.exception.InvalidMoveException;
import com.tdd.tictactoegametdd.model.PlayerMove;

public class TicTacToeService {

	private char[][] board = new char[3][3];
	private char currentPlayer = 'X';
	private boolean gameDraw = false;
	private boolean gameWon = false;

	public TicTacToeService() {
		initializeBoard();
	}

	private void initializeBoard() {
		Arrays.stream(board).forEach(row -> Arrays.fill(row, ' '));
	}

	public String makeMove(PlayerMove playerMove) throws InvalidMoveException {

		int row = playerMove.getRow();
		int col = playerMove.getColumn();

		if (!isValidMove(row, col)) {
			throw new InvalidMoveException(
					"Invalid move!Row and column must be between 0 and 2, and the cell must be empty.");
		}

		board[row][col] = currentPlayer;

		if (isBoardFull()) {
			gameDraw = true;
			return "The game is a draw!";
		}

		if (checkRows(currentPlayer)) {
			gameWon = true;
			return "Player " + currentPlayer + " wins!";
		}
		getNextPlayer();
		return "Move completed!";
	}

	private boolean checkRows(char currentPlayer) {
		return IntStream.range(0, 3).anyMatch(i -> (IntStream.range(0, 3).allMatch(j -> board[i][j] == currentPlayer)));
	}

	private boolean isBoardFull() {
		return Arrays.stream(board).flatMapToInt(row -> IntStream.range(0, row.length).map(i -> row[i]))
				.noneMatch(cell -> cell == ' ');
	}

	private boolean isValidMove(int row, int col) {
		return IntStream.of(row, col).allMatch(i -> i >= 0 && i < 3) && board[row][col] == ' ';
	}

	public String resetGame() {
		return null;
	}

	public char[][] getBoard() {
		return board;
	}

	public char getCurrentPlayer() {
		return currentPlayer;
	}

	private void getNextPlayer() {
		currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
	}

}
