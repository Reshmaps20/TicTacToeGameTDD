package com.tdd.tictactoegametdd.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class TicTacToeServiceTest {

	TicTacToeService ticTacToeService = new TicTacToeService();

	@Test
	public void testInitializeBoard_InitializeTheGameBoardWithEmptyCell_ReturnEmptyGameBoard() {

		char[][] board = ticTacToeService.getBoard();
		long emptyCellCount = IntStream.range(0, 3)
									.flatMap(i -> IntStream.range(0, 3)
									.filter(j -> board[i][j] == ' '))
									.count();

		assertEquals(9, emptyCellCount);

	}

}
