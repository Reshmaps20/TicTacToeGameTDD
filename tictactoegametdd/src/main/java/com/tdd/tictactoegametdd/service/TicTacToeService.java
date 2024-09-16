package com.tdd.tictactoegametdd.service;

import java.util.Arrays;

import com.tdd.tictactoegametdd.model.PlayerMove;

public class TicTacToeService {

	private char[][] board = new char[3][3];

	public TicTacToeService() {
		initializeBoard();
	}

	private void initializeBoard() {
		Arrays.stream(board).forEach(row -> Arrays.fill(row, ' '));
	}

	public String makeMove(PlayerMove playerMove) {
		return null;
	}

	public String resetGame() {
		return null;
	}

	public char[][] getBoard() {
		return board;
	}

}
