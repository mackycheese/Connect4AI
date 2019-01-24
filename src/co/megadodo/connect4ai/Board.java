package co.megadodo.connect4ai;

import java.util.ArrayList;

public class Board {
	
	public static final int w=7;
	public static final int h=6;

	public int[][]board;
	public int player=1;
	
	public Board() {
		board=new int[w][h];
		for(int x=0;x<w;x++) {
			for(int y=0;y<h;y++) {
				board[x][y]=0;
			}
		}
	}
	
	public int height(int column) {
		for(int y=0;y<h;y++) {
			if(board[column][y]==0)return y;
		}
		return h;
	}
	
	public boolean allowed(int column) {
		return height(column)<h;
	}
	
	public Board place(int column) {
		Board b=new Board();
		for(int x=0;x<w;x++) {
			for(int y=0;y<h;y++) {
				b.board[x][y]=board[x][y];
			}
		}
		b.player=player;
		if(b.allowed(column)) {
			b.board[column][b.height(column)]=b.player;
			b.player=opp(b.player);
		}
		return b;
	}
	
	public void print() {
		for(int x=0;x<w;x++) {
			System.out.print(" ");
			for(int y=h-1;y>=0;y--) {
				if(board[x][y]==0) {
					System.out.print("  ");
				}else if(board[x][y]==1) {
					System.out.print("R ");
				}else if(board[x][y]==2) {
					System.out.print("Y ");
				}
			}
			System.out.println();
		}
	}
	
	public static int opp(int player) {
		if(player==1)return 2;
		if(player==2)return 1;
		return 0;
	}
	
	public ArrayList<Integer>getMoves(){
		ArrayList<Integer>moves=new ArrayList<Integer>();
		for(int x=0;x<Board.w;x++) {
			if(allowed(x))moves.add(x);
		}
		return moves;
	}
	
	public boolean checkInDir(int x,int y,int dx,int dy,int l,int player) {
		for(int i=0;i<l;i++) {
			if(x<0||y<0||x>=w||y>=h)return false;
			if(board[x][y]!=player)return false;
			x+=dx;
			y+=dy;
		}
		return true;
	}
	
	public int getNumInRow(int l,int player) {
		int num=0;
		for(int x=0;x<w;x++) {
			for(int y=0;y<h;y++) {
				if(checkInDir(x,y, 1,0, l, player))num++;
				if(checkInDir(x,y, -1,0, l, player))num++;
				if(checkInDir(x,y, 0,1, l, player))num++;
				if(checkInDir(x,y, 0,-1, l, player))num++;
				if(checkInDir(x,y, -1,-1, l, player))num++;
				if(checkInDir(x,y, -1,1, l, player))num++;
				if(checkInDir(x,y, 1,-1, l, player))num++;
				if(checkInDir(x,y, 1,1, l, player))num++;
			}
		}
		return num;
	}
	
}
