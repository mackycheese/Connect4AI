package co.megadodo.connect4ai;

import java.util.ArrayList;

public class AI2 extends AI {
	
	int positions = 0;
	int calls = 0;
	int pruned = 0;
	
	public int me=-1;
	public int opp=-1;
	
	public static final float negInf=-100000;
	public static final float posInf=100000;
	
	public float rate(Board board) {
		if(board.getNumInRow(4, me)>0)return posInf;
		if(board.getNumInRow(4, opp)>0)return negInf;
		return board.getNumInRow(3, me)-board.getNumInRow(3, opp);
	}
	
	public float minimax(int depth, Board game, float alpha,float beta,boolean isMaxPlayer) {
		if(depth==0||game.getNumInRow(4, 1)>0||game.getNumInRow(4, 2)>0) {
			positions+=1;
			return rate(game);
		}
		
		ArrayList<Board>children=game.getChildren();
		if(isMaxPlayer) {
			float value=negInf;
			for(Board child:children) {
				value=Math.max(value, minimax(depth-1,child,alpha,beta,false));
				alpha=Math.max(alpha, value);
				if(alpha>=beta) {
					pruned+=1;
					return value;
				}
			}
			calls+=1;
			return value;
		}else {
			float value=posInf;
			for(Board child:children) {
				value=Math.min(value, minimax(depth-1,child,alpha,beta,true));
				beta=Math.min(value, beta);
				if(alpha>=beta) {
					pruned+=1;
					return value;
				}
			}
			calls+=1;
			return value;
		}
		
	}
	
	public int getBestMove(Board board, int player) {
		me=player;
		opp=Board.opp(me);
		positions=0;
		calls=0;
		pruned=0;
		
		ArrayList<Integer>moves=board.getMoves();
		float bestF=negInf;
		int bestMove=0;
		
		for(Integer move:moves) {
			float f=minimax(8,board.place(move),negInf,posInf,false);
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
		System.out.println();
		System.out.println("Best F-value             : "+bestF);
		
		return bestMove;
	}

}
