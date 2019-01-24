package co.megadodo.connect4ai;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Connect4Panel extends JPanel implements MouseListener {
	
	public Board board;
	
	public AI ai1, ai2;
	public boolean humanExists;
	public int humanPlayer;
	
	public int lastAIMove=-1;
	
	//Score
	// AI  Person
	// 1/2 1/2
	// 1   0
	// 1   0
	// 0   1
	// TOTAL
	// 2+1/2  1+1/2
	
	public Connect4Panel() {
		board=new Board();
		addMouseListener(this);
		repaint();
		
		humanExists=true;
		humanPlayer=2;
		ai1=new AI2();
		ai2=new AI2();
		
		if(humanPlayer!=1||!humanExists)makeAIMove();
	}
	
	public void makeAIMove() {
		repaint();
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				

				repaint();
				long start,end;
				int move;
				AI ai=null;
				if(!humanExists) {
					if(board.player==1)ai=ai1;
					if(board.player==2)ai=ai2;
				}else {
					ai=ai1;
				}
				start=System.currentTimeMillis();
				move = ai.getBestMove(board, board.player);
				end=System.currentTimeMillis();
				float time=(end-start)/1000.0f;
				System.out.println("Time for move: "+time);
//				float delay=0.1f;
//				float timerLength=0;
//				if(time>delay)timerLength=0;
//				else timerLength=delay-time;
//				new Timer().schedule(new TimerTask() {
//					
//					@Override
//					public void run() {
				board = board.place(move);
				repaint();
				handleWin();
				repaint();
				if(!humanExists) {
					makeAIMove();
				}
//					}
//				}, (int)(timerLength*1000));
				
				
			}
		}, 10);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d=(Graphics2D)g;
		g2d.setColor(new Color(0.2f,0.2f,0.8f));
		g2d.fillRect(0, 0, Board.w*Connect4.size, Board.h*Connect4.size);
		for(int x=0;x<Board.w;x++) {
			for(int y=0;y<Board.h;y++){
				g2d.setColor(Color.white);
				if(board.board[x][y]==1) {
					g2d.setColor(Color.red);
				}else if(board.board[x][y]==2) {
					g2d.setColor(Color.yellow);
				}
				int s=Connect4.size*4/5;
				g2d.fillOval(x*Connect4.size+Connect4.size/2-s/2, (Board.h-y-1)*Connect4.size+Connect4.size/2-s/2, s, s);
			}
		}
	}
	
	public void handleWin() {
		if(board.getNumInRow(4, 1)>0) {
			JOptionPane.showMessageDialog(this, "Red won");
			board=new Board();
			repaint();
		}else if(board.getNumInRow(4, 2)>0) {
			JOptionPane.showMessageDialog(this, "Yellow won");
			board=new Board();
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
	}

	@Override
	public void mouseEntered(MouseEvent evt) {
	}

	@Override
	public void mouseExited(MouseEvent evt) {
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		int mx=evt.getX()/Connect4.size;
		if(board.player!=humanPlayer)return;
		if(!board.allowed(mx))return;
		board=board.place(mx);
		board.print();
		repaint();
		handleWin();
		repaint();
		makeAIMove();
	}

	@Override
	public void mouseReleased(MouseEvent evt) {
	}
	
	

}
