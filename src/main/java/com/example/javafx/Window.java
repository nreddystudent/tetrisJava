package com.example.javafx;

import javax.swing.JFrame;

public class Window {
	
	public static final int WIDTH = 317*2, HEIGHT = 640;
	private static final JFrame window = new JFrame("Tetris Game");
	private Board board;
	
	public Window(){
		window.getContentPane().removeAll();
		window.repaint();
		window.setSize(WIDTH, HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);

		board = new Board();
		window.add(board);
		window.addKeyListener(board);
		
		window.setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		new Window();
	}

}
