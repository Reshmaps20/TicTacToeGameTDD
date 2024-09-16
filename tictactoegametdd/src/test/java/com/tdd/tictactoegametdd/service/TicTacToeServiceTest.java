package com.tdd.tictactoegametdd.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tdd.tictactoegametdd.exception.InvalidMoveException;
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
	public void testMakeMove_PlayerMakesMove_ReturnMoveCompleted() throws InvalidMoveException {

		PlayerMove player = new PlayerMove(0, 0);
		String result = ticTacToeService.makeMove(player);
		assertTrue(result.contains("Move completed!"));
	}

	@Test
	public void testMakeMove_PlayerChangeAlternatively_ReturnMoveCompletedForAlternatePlayer()
			throws InvalidMoveException {

		PlayerMove player = new PlayerMove(0, 1);
		ticTacToeService.makeMove(player);
		assertEquals(ticTacToeService.getCurrentPlayer(), 'O');

		ticTacToeService.makeMove(new PlayerMove(1, 1));
		assertEquals(ticTacToeService.getCurrentPlayer(), 'X');
	}

	@Test
	public void testMakeMove_CheckTheSelectedPositionByThePlayerIsEmpty_ReturnValidMove() throws InvalidMoveException {

		PlayerMove player = new PlayerMove(0, 1);
		String result = ticTacToeService.makeMove(player);
		assertTrue(result.contains("Move completed!"));
	}

	@Test
	public void testMakeMove_ThrowsExceptionWhenTheSelectedPositionByThePlayerIsNotEmpty_ReturnInvalidMoveException()
			throws InvalidMoveException {

		PlayerMove player = new PlayerMove(0, 1);
		ticTacToeService.makeMove(player);
		assertThrows(InvalidMoveException.class, () -> {
			ticTacToeService.makeMove(new PlayerMove(0, 1));
		});
	}

	@Test
	public void testMakeMove_CheckTheSelectedPositionByThePlayerIsWithinTheRangeOfBoard_ReturnValidMove()
			throws InvalidMoveException {

		PlayerMove player = new PlayerMove(0, 1);
		String result = ticTacToeService.makeMove(player);
		assertTrue(result.contains("Move completed!"));
	}

	@Test
	public void testMakeMove_ThrowsExceptionWhenTheSelectedPositionIsOutOfRange_ReturnInvalidMoveException()
			throws InvalidMoveException {

		PlayerMove player = new PlayerMove(3, 1);
		assertThrows(InvalidMoveException.class, () -> {
			ticTacToeService.makeMove(player);
		});
	}

	@Test
	public void testMakeMove_CheckTheBoardIsFull_ReturnGameIsDraw() throws InvalidMoveException {

		PlayerMove firstMove = new PlayerMove(0, 0);
		ticTacToeService.makeMove(firstMove);
		
		PlayerMove secondMove = new PlayerMove(0, 1);
		ticTacToeService.makeMove(secondMove);
		
		PlayerMove thirdMove = new PlayerMove(0, 2);
		ticTacToeService.makeMove(thirdMove);
		
		PlayerMove forthMove = new PlayerMove(1, 0);
		ticTacToeService.makeMove(forthMove);
		
		PlayerMove fifthMove = new PlayerMove(1, 2);
		ticTacToeService.makeMove(fifthMove);
		
		PlayerMove sixthMove = new PlayerMove(1, 1);
		ticTacToeService.makeMove(sixthMove);
		
		PlayerMove seventhMove = new PlayerMove(2, 0);
		ticTacToeService.makeMove(seventhMove);
		
		PlayerMove eighthMove = new PlayerMove(2, 2);
		ticTacToeService.makeMove(eighthMove);
		
		PlayerMove ninthMove = new PlayerMove(2, 1);
		String result = ticTacToeService.makeMove(ninthMove);
		
		assertTrue(result.contains("The game is a draw!"));
	}

	@Test
	public void testMakeMove_PlayerWinsByCompletingARow_ReturnPlayerWhoWins() throws InvalidMoveException {

		PlayerMove firstMove = new PlayerMove(0, 0);
		ticTacToeService.makeMove(firstMove);

		PlayerMove secondMove = new PlayerMove(1, 0);
		ticTacToeService.makeMove(secondMove);

		PlayerMove thirdMove = new PlayerMove(0, 1);
		ticTacToeService.makeMove(thirdMove);

		PlayerMove forthMove = new PlayerMove(1, 1);
		ticTacToeService.makeMove(forthMove);

		PlayerMove fifthMove = new PlayerMove(0, 2);
		String result = ticTacToeService.makeMove(fifthMove);

		assertTrue(result.contains("Player " + ticTacToeService.getCurrentPlayer() + " wins!"));
	}
	
	@Test
	public void testMakeMove_PlayerWinsByCompletingAColumn_ReturnPlayerWhoWins() throws InvalidMoveException {

		PlayerMove firstMove = new PlayerMove(0, 0);
		ticTacToeService.makeMove(firstMove);

		PlayerMove secondMove = new PlayerMove(0, 1);
		ticTacToeService.makeMove(secondMove);

		PlayerMove thirdMove = new PlayerMove(1, 0);
		ticTacToeService.makeMove(thirdMove);

		PlayerMove forthMove = new PlayerMove(1, 1);
		ticTacToeService.makeMove(forthMove);

		PlayerMove fifthMove = new PlayerMove(2, 0);
		String result = ticTacToeService.makeMove(fifthMove);

		assertTrue(result.contains("Player " + ticTacToeService.getCurrentPlayer() + " wins!"));
	}
	
	@Test
	public void testMakeMove_PlayerWinsByCompletingLeftTopToRightBottomDiagonalPosition_ReturnPlayerWhoWins() throws InvalidMoveException {

		PlayerMove firstMove = new PlayerMove(0, 0);
		ticTacToeService.makeMove(firstMove);

		PlayerMove secondMove = new PlayerMove(0, 1);
		ticTacToeService.makeMove(secondMove);

		PlayerMove thirdMove = new PlayerMove(1, 1);
		ticTacToeService.makeMove(thirdMove);

		PlayerMove forthMove = new PlayerMove(2, 1);
		ticTacToeService.makeMove(forthMove);

		PlayerMove fifthMove = new PlayerMove(2, 2);
		String result = ticTacToeService.makeMove(fifthMove);

		assertTrue(result.contains("Player " + ticTacToeService.getCurrentPlayer() + " wins!"));
	}
	
	@Test
	public void testMakeMove_PlayerWinsByCompletingRightTopToLeftBottomDiagonalPosition_ReturnPlayerWhoWins() throws InvalidMoveException {

		PlayerMove firstMove = new PlayerMove(0, 2);
		ticTacToeService.makeMove(firstMove);

		PlayerMove secondMove = new PlayerMove(0, 1);
		ticTacToeService.makeMove(secondMove);

		PlayerMove thirdMove = new PlayerMove(1, 1);
		ticTacToeService.makeMove(thirdMove);

		PlayerMove forthMove = new PlayerMove(2, 1);
		ticTacToeService.makeMove(forthMove);

		PlayerMove fifthMove = new PlayerMove(2, 0);
		String result = ticTacToeService.makeMove(fifthMove);

		assertTrue(result.contains("Player " + ticTacToeService.getCurrentPlayer() + " wins!"));
	}
	
	@Test
	public void testMakeMove_CheckTheGameIsWonOrDraw_ReturnGameOver() throws InvalidMoveException {

		PlayerMove firstMove = new PlayerMove(0, 0);
		ticTacToeService.makeMove(firstMove);

		PlayerMove secondMove = new PlayerMove(0, 1);
		ticTacToeService.makeMove(secondMove);

		PlayerMove thirdMove = new PlayerMove(1, 1);
		ticTacToeService.makeMove(thirdMove);

		PlayerMove forthMove = new PlayerMove(2, 1);
		ticTacToeService.makeMove(forthMove);

		PlayerMove fifthMove = new PlayerMove(2, 2);
		ticTacToeService.makeMove(fifthMove);
		
		PlayerMove sixthMove = new PlayerMove(1, 0);
		String result = ticTacToeService.makeMove(forthMove);
		
		assertTrue(result.contains("Game is already over. Please restart to play again."));
	}
	
	@Test
	public void testReset_ResetTheGame_ReturnGameReset() {

		String result = ticTacToeService.resetGame();
		assertTrue(result.contains("Game Reset!"));
	}

}
