package co.megadodo.connect4ai;

import java.util.ArrayList;

public class AI1 extends AI {
	
	public int getBestMove(Board board,int player) {
		ArrayList<Integer>moves=board.getMoves();
		return moves.get((int)(Math.random()*moves.size()));
	}

}
