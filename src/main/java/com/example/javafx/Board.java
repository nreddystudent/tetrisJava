package com.example.javafx;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements KeyListener{
	
	private BufferedImage blocks;
	private boolean isPaused = false;
	
	private final int blockSize = 30;
	
	private final int boardWidth = 10, boardHeight = 20;
	
	private int[][] board = new int[boardHeight][boardWidth];
	
	private Shape[] shapes = new Shape[10];
	private Shape[] nextShapes = new Shape[3];
	private Shape currentShape;
	
	private Timer timer;
	
	private final int FPS = 60;
	
	private final int delay = 1000/FPS;
	
	private boolean gameOver = false;

	public int score = 0;

	public Board(){
		setBackground(Color.black);
		try {
			blocks = ImageIO.read(Board.class.getResource("/tiles1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		timer = new Timer(delay, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				repaint();
			}
		});
		
		timer.start();
		
		// shapes
		
		shapes[0] = new Shape(blocks.getSubimage(0, 0, blockSize, blockSize), new int[][]{
			{1, 1, 1, 1} // IShape
		}, this, 1);
		
		shapes[1] = new Shape(blocks.getSubimage(blockSize, 0, blockSize, blockSize), new int[][]{
			{1, 1, 0},
			{0, 1, 1}   // ZShape
		}, this, 2);
		
		shapes[2] = new Shape(blocks.getSubimage(blockSize*2, 0, blockSize, blockSize), new int[][]{
			{0, 1, 1},
			{1, 1, 0}   // S-Shape
		}, this, 3);
		
		shapes[3] = new Shape(blocks.getSubimage(blockSize*3, 0, blockSize, blockSize), new int[][]{
			{1, 1, 1},
			{0, 0, 1}   // J-Shape
		}, this, 4);
		
		shapes[4] = new Shape(blocks.getSubimage(blockSize*4, 0, blockSize, blockSize), new int[][]{
			{1, 1, 1},
			{1, 0, 0}   // L-Shape
		}, this, 5);
		
		shapes[5] = new Shape(blocks.getSubimage(blockSize*5, 0, blockSize, blockSize), new int[][]{
			{1, 1, 1},
			{0, 1, 0}   // T-Shape
		}, this, 6);
		
		shapes[6] = new Shape(blocks.getSubimage(blockSize*6, 0, blockSize, blockSize), new int[][]{
			{1, 1},
			{1, 1}   // O-Shape
		}, this, 7);
		shapes[7] = new Shape(blocks.getSubimage(blockSize*7, 0, blockSize, blockSize), new int[][]{
				{1, 0},
				{1, 1}   // l-Shape
		}, this, 8);
		shapes[8] = new Shape(blocks.getSubimage(blockSize*8, 0, blockSize, blockSize), new int[][]{
				{0, 1, 0},
				{1, 1, 1},
				{0, 1, 0}// X-Shape
		}, this, 9);
		shapes[9] = new Shape(blocks.getSubimage(blockSize*9, 0, blockSize, blockSize), new int[][]{
				{ 1 ,0 , 0},
				{ 1, 1, 0} ,
				{ 0, 1, 1}// W-Shape
		}, this, 10);

		int index;
		index = (int)(Math.random()*shapes.length);
		nextShapes[0] = new Shape(shapes[index].getBlock(), shapes[index].getCoords(),
				this, shapes[index].getColor());
		index = (int)(Math.random()*shapes.length);
		nextShapes[1] = new Shape(shapes[index].getBlock(), shapes[index].getCoords(),
				this, shapes[index].getColor());
		index = (int)(Math.random()*shapes.length);
		nextShapes[2] = new Shape(shapes[index].getBlock(), shapes[index].getCoords(),
				this, shapes[index].getColor());
		this.score = setNextShape(score);
	}
	
	public void update(){
		score = currentShape.update(score);
		if(gameOver) {
			new Window();
			timer.stop();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);	
		
		currentShape.render(g, 0, 0);
		
		for(int row = 0; row < board.length; row++)
			for(int col = 0; col < board[row].length; col++)
				if(board[row][col] != 0)
					g.drawImage(blocks.getSubimage((board[row][col]-1)*blockSize, 0, blockSize, blockSize),
					col*blockSize, row*blockSize, null);


		for(int i = 0; i < boardHeight; i++){
			g.drawLine(0, i*blockSize, boardWidth*blockSize, i*blockSize);
		}
		for(int j = 0; j < boardWidth; j++){
			g.drawLine(j*blockSize, 0, j*blockSize, boardHeight*blockSize);
		}
		g.setColor(Color.WHITE);
		g.drawString("Score: " + score, 400, 50);

		nextShapes[0].render(g, 300, 100);
		nextShapes[1].render(g, 300, 225);
		nextShapes[2].render(g, 300, 350);
	}
	
	public int setNextShape(int score){
		
		int index = (int)(Math.random()*shapes.length);
		Shape newShape = new Shape(shapes[index].getBlock(), shapes[index].getCoords(),
				this, shapes[index].getColor());

		currentShape = nextShapes[0];
		nextShapes[0] = nextShapes[1];
		nextShapes[1] = nextShapes[2];
		nextShapes[2] = newShape;

		score++;
		System.out.println(score);
		for(int row = 0; row < currentShape.getCoords().length; row++)
			for(int col = 0; col < currentShape.getCoords()[row].length; col++)
				if(currentShape.getCoords()[row][col] != 0){
					if(board[row][col + 3] != 0)
						gameOver = true;
				}
		
		return score;
		
	}
	
	
	public int getBlockSize(){
		return blockSize;
	}

	public int[][] getBoard(){
		return board;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			currentShape.setDeltaX(-1);
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			currentShape.setDeltaX(1);
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			currentShape.speedDown();
		if(e.getKeyCode() == KeyEvent.VK_UP)
			currentShape.rotate();
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			if (!gameOver) {
				if (isPaused == false) {
					timer.stop();
				} else {
					timer.start();
				}
				isPaused = !isPaused;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			currentShape.normalSpeed();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
