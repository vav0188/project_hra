package Frogger;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class Frog extends Object{
	
	private String fileName = "frog.png";
	private Image img;
	private String keyPressed;
	private View view;
	private int score = 0;
	private int lifes = 3;
	
	Random r = new Random();
	private int startX = r.nextInt(360);
	private int startY = 500;
	
	
	public enum Orientation {
		UP, DOWN, LEFT, RIGHT
	}
	
	public Frog(ID id, View v, int poradi)
	{
		super(id);
		view = v;
		setX(startX);
		setY(startY);
		if(poradi == 2)
			fileName = "frog2.png";
		
	}
	public void setScore(int s)
	{
		score+=s;
	}
	public void startScore(int score)
	{
		this.score = score;
	}
	public int getScore()
	{
		return score;
	}
	public void setNumberOfLives(int liv)
	{
		lifes = liv;
	}
	public void setLives()
	{
		if(lifes > 0)
			lifes--;
	}
	public int getLives()
	{
		return lifes;
	}
	
	@Override
	void update() 
	{
			
		if (this.keyPressed.equals("u")) {	
			if(this.getY() > 20)
				this.setMoveY(-60);
			}
		else if (this.keyPressed.equals("d")) {	
			if(this.getY() < 500)
				this.setMoveY(+60);
			}
		else if (this.keyPressed.equals("l")) {	
			if(this.getX() > 20)
				this.setMoveX(-10);}
		else if (this.keyPressed.equals("r")) {
			if(this.getX() < 380)
				this.setMoveX(+10);}

		
		}
	
	@Override
	public Image loadImage() {
		try {
			img = ImageIO.read(new File(this.fileName));
		} catch (IOException exc) {
			System.out.println("Can't load image.");
		}
		return img;
	}

	public void setKeyPressed(String keyPressed) {
		this.keyPressed = keyPressed;
	}
	
}
