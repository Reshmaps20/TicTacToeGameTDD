package com.tdd.tictactoegametdd.service;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.tdd.tictactoegametdd.constants.GameConstants;
import com.tdd.tictactoegametdd.exception.InvalidMoveException;
import com.tdd.tictactoegametdd.model.PlayerMove;

public class TicTacToeService {

	private char[][] board = new char[3][3];
	private char currentPlayer = GameConstants.Player_X;
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

		if (gameWon || gameDraw) {
			return GameConstants.GAME_OVER;
		}

		if (!isValidMove(row, col)) {
			throw new InvalidMoveException(GameConstants.INVALID_MOVE);
		}
		board[row][col] = currentPlayer;
		String result = checkWin(currentPlayer) ? handleWin(currentPlayer) : handleDrawOrContinue();
		getNextPlayer();
		return result;
	}

	private String handleWin(char currentPlayer) {
		gameWon = true;
		return String.format(GameConstants.WINNER, currentPlayer, boardToString());
	}

	private String handleDrawOrContinue() {
		if (isBoardFull()) {
			gameDraw = true;
			return GameConstants.DRAW;
		}
		return String.format(GameConstants.MOVE_COMPELETED, boardToString());
	}

	private boolean checkWin(char currentPlayer) {
		return (checkRowsColumns(currentPlayer) || checkDiagonal(currentPlayer));
	}

	private boolean checkDiagonal(char currentPlayer) {
		return IntStream.range(0, 3).allMatch(i -> board[i][i] == currentPlayer)
				|| IntStream.range(0, 3).allMatch(i -> board[i][2 - i] == currentPlayer);
	}

	private boolean checkRowsColumns(char currentPlayer) {
		return IntStream.range(0, 3).anyMatch(i -> (IntStream.range(0, 3).allMatch(j -> board[i][j] == currentPlayer))
				|| (IntStream.range(0, 3).allMatch(j -> board[j][i] == currentPlayer)));
	}

	private boolean isBoardFull() {
		return Arrays.stream(board).flatMapToInt(row -> IntStream.range(0, row.length).map(i -> row[i]))
				.noneMatch(cell -> cell == ' ');
	}

	private boolean isValidMove(int row, int col) {
		return IntStream.of(row, col).allMatch(i -> i >= 0 && i < 3) && board[row][col] == ' ';
	}

	public String resetGame() {
		initializeBoard();
		gameWon = false;
		gameDraw = false;
		return GameConstants.GAME_RESET;
	}

	public char[][] getBoard() {
		return board;
	}

	public char getCurrentPlayer() {
		return currentPlayer;
	}

	private void getNextPlayer() {
		currentPlayer = (currentPlayer == GameConstants.Player_X) ? GameConstants.Player_O : GameConstants.Player_X;
	}

	private Object boardToString() {
		return Arrays.stream(board).map(row -> new String(row).replace("", " ").trim())
				.collect(Collectors.joining("\n"));
	}

}
