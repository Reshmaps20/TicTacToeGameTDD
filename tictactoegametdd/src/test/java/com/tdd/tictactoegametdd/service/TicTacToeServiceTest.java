package com.tdd.tictactoegametdd.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import com.tdd.tictactoegametdd.model.PlayerMove;

public class TicTacToeServiceTest {

	TicTacToeService ticTacToeService = new TicTacToeService();

	@Test
	public void testInitializeBoard_InitializeTheGameBoardWithEmptyCell_ReturnEmptyGameBoard() {

		char[][] board = ticTacToeService.getBoard();
		long emptyCellCount = IntStream.range(0, 3).flatMap(i -> IntStream.range(0, 3).filter(j -> board[i][j] == ' '))
				.count();

		assertEquals(9, emptyCellCount);
	}

	@Test
	public void testMakeMove_PlayerMakesMove_ReturnMoveCompleted() {

		PlayerMove player = new PlayerMove(0, 0);
		String result = ticTacToeService.makeMove(player);
		assertTrue(result.contains("Move completed!"));
	}

}
