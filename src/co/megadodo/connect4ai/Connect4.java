package co.megadodo.connect4ai;

import javax.swing.JFrame;

public class Connect4 {
	
	public static int size=100;
	
	public static void main(String[]args) {
		JFrame frame=new JFrame("Connect 4 AI");
		frame.setSize(size*Board.w,size*Board.h+22);
		frame.add(new Connect4Panel());
		frame.setVisible(true);
	}

}
