package Frogger;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;

public abstract class Object  extends Observable{
	protected int x, y;
	protected ID id;
	protected int mx,my;
	
	public Object(ID id)
	{
		this.id = id;
	}
	
	abstract void update();
	abstract Image loadImage();
	
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void setID(ID id)
	{
		this.id = id;
	}
	public ID getID()
	{
		return id;
	}
	public void setMoveX(int mx)
	{
		this.mx = mx;
		x += mx;
	}
	public int getMoveX()
	{
		return mx;
	}
	public void setMoveY(int my)
	{
		this.my = my;
		y += my;
	}
	public int getMoveY()
	{
		return my;
	}
	
}
