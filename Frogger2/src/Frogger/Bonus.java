package Frogger;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bonus extends Object{
	
	Image img;
	String fileName = "bonus.png";
	boolean bonus = true;
	
	public Bonus(ID id, boolean bonus)
	{
		super(id);
		this.bonus = bonus;
	}
	
	public void haveBonus(boolean bon)
	{
		if(bon == false)
			fileName = "bonus1.png";
		else
			fileName = "bonus.png";
	}
	public boolean createBonus()
	{
		return bonus;
	}

	@Override
	void update() {
		
	}

	@Override
	Image loadImage() {
		try {
			img = ImageIO.read(new File(this.fileName));
		} catch (IOException exc) {
			System.out.println("Can't load image.");
		}
		return img;
	}

}
