package com.tdd.tictactoegametdd.service;

import java.util.Arrays;

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

	public String makeMove(PlayerMove playerMove) {

		int row = playerMove.getRow();
		int col = playerMove.getColumn();

		board[row][col] = currentPlayer;

		return "Move completed!";
	}

	public String resetGame() {
		return null;
	}

	public char[][] getBoard() {
		return board;
	}

}
