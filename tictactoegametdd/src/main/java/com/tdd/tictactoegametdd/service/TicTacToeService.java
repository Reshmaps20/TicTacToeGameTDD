package com.tdd.tictactoegametdd.service;

import java.util.Arrays;
import java.util.stream.IntStream;

import com.tdd.tictactoegametdd.exception.InvalidMoveException;
import com.tdd.tictactoegametdd.model.PlayerMove;

public class TicTacToeService {

	private char[][] board = new char[3][3];
	private char currentPlayer = 'X';

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
			throw new InvalidMoveException("Invalid move!Row and column must be between 0 and 2, and the cell must be empty.");
		}

		board[row][col] = currentPlayer;
		getNextPlayer();
		return "Move completed!";
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
