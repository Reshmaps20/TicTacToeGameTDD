package com.tdd.tictactoegametdd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdd.tictactoegametdd.model.PlayerMove;
import com.tdd.tictactoegametdd.service.TicTacToeService;

@RestController
@RequestMapping("/tictactoe")
public class TicTacToeController {

	@Autowired
	TicTacToeService ticTacToeService;

	@PostMapping("/playgame")
	public String makeMove(@RequestBody PlayerMove playerMove) {
		return ticTacToeService.makeMove(playerMove);
	}

}
