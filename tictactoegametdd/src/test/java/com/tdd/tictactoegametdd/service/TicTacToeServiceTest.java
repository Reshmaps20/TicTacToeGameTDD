package com.tdd.tictactoegametdd.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.tdd.tictactoegametdd.model.PlayerMove;

public class TicTacToeServiceTest {

	private TicTacToeService ticTacToeService;
	
	@BeforeEach
	public void setup() {
		ticTacToeService = new TicTacToeService();
	}

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
	
	@Test
	public void testMakeMove_PlayerChangeAlternatively_ReturnMoveCompletedForAlternatePlayer() {

		PlayerMove player = new PlayerMove(0, 1);
		ticTacToeService.makeMove(player);
		assertEquals(ticTacToeService.getCurrentPlayer(),'O');
		
		ticTacToeService.makeMove(new PlayerMove(1, 1));
		assertEquals(ticTacToeService.getCurrentPlayer(),'X');
	}
	
	@Test
	public void testMakeMove_CheckTheSelectedPositionByThePlayerIsEmpty_ReturnValidMove() {

		PlayerMove player = new PlayerMove(0,1);
		String result = ticTacToeService.makeMove(player);
		assertTrue(result.contains("Move completed!"));
	}
	
	

}
