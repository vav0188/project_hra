package Frogger;

import java.awt.Graphics;
import java.util.ArrayList;

public class Objects {
	ArrayList<Object> objects;
	
	public Objects() {
		objects = new ArrayList<Object>();
	}
	
	public void addObject(Object obj)
	{
		this.objects.add(obj);
	}
	public void removeObject(Object obj)
	{
		this.objects.remove(obj);
	}
	public ArrayList<Object> getObjects()
	{
		return objects;
	}
}
