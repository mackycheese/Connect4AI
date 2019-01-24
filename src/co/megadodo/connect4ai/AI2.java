package co.megadodo.connect4ai;

import java.util.ArrayList;

public class AI2 extends AI {
	
	int positions = 0;
	int calls = 0;
	int pruned = 0;
//	
//	public float rate(Board board,int player) {
//		return 0;
//	}
//	
	public float minimax(int depth, Board game, float alpha, float beta, boolean isMaxPlayer,int player) {
		return (float)Math.random();
	}
	
	public int getBestMove(Board board, int player) {
		positions=0;
		calls=0;
		pruned=0;
		
		ArrayList<Integer>moves=board.getMoves();
		float bestF=Float.NEGATIVE_INFINITY;
		int bestMove=0;
		
		for(Integer move:moves) {
			float f=minimax(5,board.place(move),Float.NEGATIVE_INFINITY,Float.POSITIVE_INFINITY,false,player);
			if(f>=bestF) {
				bestF=f;
				bestMove=move;
			}
		}
		
		int total=positions+calls+pruned;

		System.out.println();
		System.out.println("# of leaf nodes          : "+positions);
		System.out.println("# of intermediate nodes  : "+calls);
		System.out.println("# of pruned nodes        : "+pruned);
		System.out.println("# of total nodes         : "+total);
		System.out.println();
		System.out.println("% of leaf nodes          : "+(float)(100.0f*positions/total));
		System.out.println("% of intermediate nodes  : "+(float)(100.0f*calls/total));
		System.out.println("% pruned nodes           : "+(float)(100.0f*pruned/total));
		
		return bestMove;
	}

}
