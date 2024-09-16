package com.tdd.tictactoegametdd.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.tictactoegametdd.model.PlayerMove;
import com.tdd.tictactoegametdd.service.TicTacToeService;

@WebMvcTest(TicTacToeController.class)
public class TicTacToeControllerTest {

	@MockBean
	TicTacToeService ticTacToeService;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testMakeMove_PlayerMakesMove_ReturnMoveCompleted() throws Exception {

		PlayerMove playerMove = new PlayerMove(0, 0);
		when(ticTacToeService.makeMove(any(PlayerMove.class))).thenReturn("Move completed!");

		mockMvc.perform(post("/tictactoe/playgame").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(playerMove))).andExpect(status().isOk())
				.andExpect(content().string("Move completed!"));

		verify(ticTacToeService, times(1)).makeMove(any(PlayerMove.class));
	}

	@Test
	public void testResetGame_HandleGameReset_ReturnsGameReset() throws Exception {
		
		when(ticTacToeService.resetGame()).thenReturn("Game Reset!");
	    mockMvc.perform(post("/tictactoe/reset")
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content().string("Game Reset!"));

	    verify(ticTacToeService, times(1)).resetGame();
	}
}
