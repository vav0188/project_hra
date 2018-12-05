package Frogger;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Car extends Object{
	private Image img;
	private String fileName = "";
	private int width = 74;
	private String orientation;
	private Image car;
	private int vel;
	private int currentSpeedOfGame;
	private int widthOfGame = 400;
	private int heightOfGame = 560;
	private int numberOfCars;
	private boolean both = true;
	Random r = new Random();
	
	public Car(int speed, ID id, int numberOfCars, String orient)
	{
		super(id);
		this.currentSpeedOfGame = speed;
		
		
		//this.orientation = orientation;
		int o = 0;
		if(orient.equals("both"))
			{o = r.nextInt(2); both = true;}
		if(orient.equals("left"))
			{o = 0; both = false;}
		if(orient.equals("right"))
			{o = 1; both = false;}		
		
		if(o == 0)
		{
			fileName = "carlf.png";
			orientation = "left";
		}
		if(o == 1)
		{
			fileName = "carrg.png";
			orientation = "right";
		}
			this.setPosition(orientation);
			this.setInitialVelocity(currentSpeedOfGame);
			this.numberOfCars = numberOfCars;
	}
	
	@Override
	void update() 
	{
		if (this.isOutOfBounds()) {
			if (orientation.equals("left")) {
				setX(300 + width);
			}
			if (orientation.equals("right")) {
				setX(-width);
			}
			return;
		}
		if (orientation.equals("left"))
			this.setMoveX(-vel);
		if (orientation.equals("right"))
			this.setMoveX(vel);
		
	}
	
	boolean isOutOfBounds() {
		if ((this.getX() >= (305 + width) && orientation.equals("right"))
				|| (this.getX() <= (-width - 5) && orientation.equals("left"))) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setInitialVelocity(int currentSpeedOfGame) {
			vel = currentSpeedOfGame;
	}
	
	public void setPosition(String orientation)
	{
		if(orientation.equals("right"))
		{			
			int o = 0;
			if(both == false)
				o = r.nextInt(6);
			if(both == true)
				o = r.nextInt(3);
			int l = r.nextInt(widthOfGame);
			this.setX(width+o*50+l*50);
			if(o == 0)
				this.setY(80);
			if(o == 1)
				this.setY(140);
			if(o == 2)
				this.setY(200);
			if(o == 3)
				this.setY(320);
			if(o == 4)
				this.setY(380);
			if(o == 5)
				this.setY(440);
			
		}
		if(orientation.equals("left"))
		{
			int o = 0;
			if(both == false)
				o = r.nextInt(6);
			if(both == true)
				o = r.nextInt(3)+3;
			int l = r.nextInt(5);
			this.setX(width+o*50+l*100);
			if(o == 0)
				this.setY(80);
			if(o == 1)
				this.setY(140);
			if(o == 2)
				this.setY(200);
			if(o == 3)
				this.setY(320);
			if(o == 4)
				this.setY(380);
			if(o == 5)
				this.setY(440);
		}		
	}
	
	public void setSpeed(int speedOfCar) {
		if(this.getY()==140 || this.getY() == 380)
			vel = 2+speedOfCar;
		else
			vel = speedOfCar;
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

}