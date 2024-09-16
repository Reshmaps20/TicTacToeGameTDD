package com.tdd.tictactoegametdd.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerMove {

	private int row;
	private int column;

}
