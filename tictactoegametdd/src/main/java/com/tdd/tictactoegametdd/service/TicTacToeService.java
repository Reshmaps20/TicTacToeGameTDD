package com.tdd.tictactoegametdd.service;

import java.util.Arrays;

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

		if (board[row][col] == ' ') {
			board[row][col] = currentPlayer;
			getNextPlayer();
			return "Move completed!";
		} else {
			throw new InvalidMoveException("Invalid move!Selected position is not empty");
		}
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
