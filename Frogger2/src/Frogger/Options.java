package Frogger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Options {
	String fileName = "options.txt";
	int numberOfCarI = 1;
	int startingSpeedI = 40;
	int pointsForWinI = 1;
	int numberOfLivesI = 3;
	String orientationOfCarsS = "right";
	boolean bonusesB = true;
	int pointForBonusI = 5;
	int speedUpAfterPointsI = 10;
	int numberOfPlayersI = 2;
	
	String line = null;
	public Options()
	{
		try {
			this.fillInfo();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	int tmp = 0;
	public void fillInfo() throws IOException
	{
		FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) 
            {
            	tmp++;
            	String[] st = line.split("=");
            	if(tmp == 1)
            	{
            		numberOfCarI = Integer.parseInt(st[1]);
            		if(numberOfCarI <= 0 && numberOfCarI > 50)
            			numberOfCarI = 20;
            	}
            	if(tmp == 2)
            	{
            		startingSpeedI = Integer.parseInt(st[1]);
            		if(startingSpeedI <= 0 && startingSpeedI > 6)
            			startingSpeedI = 1;
            	}
            	if(tmp == 3)
            	{
            		pointsForWinI = Integer.parseInt(st[1]);
            		if(pointsForWinI <= 0)
            			pointsForWinI = 1;
            	}
            	if(tmp == 4)
            	{
            		numberOfLivesI = Integer.parseInt(st[1]);
            		if(numberOfLivesI < 0)
            			numberOfLivesI = 3;
            	}
            	if(tmp == 5)
            	{
            		if((st[1].toLowerCase()).equals("right"))
            			orientationOfCarsS = "right";
            		if((st[1].toLowerCase()).equals("left"))
            			orientationOfCarsS = "left";
            		if((st[1].toLowerCase()).equals("both"))
            			orientationOfCarsS = "both";
            	}
            	if(tmp == 6)
            	{
            		if((st[1].toLowerCase()).equals("true"))
            			bonusesB = true;
            		if((st[1].toLowerCase()).equals("false"))
            			bonusesB = false;
            	}
            	if(tmp == 7)
            	{
            		pointForBonusI = Integer.parseInt(st[1]);
            		if(pointForBonusI <= 0)
            			pointForBonusI = 4;
            	}
            	if(tmp == 8)
            	{
            		speedUpAfterPointsI = Integer.parseInt(st[1]);
            		if(speedUpAfterPointsI <= 0)
            			speedUpAfterPointsI = 20;
            	}
            }
        bufferedReader.close(); 
		
	}
	
	public int numberOfCars()
	{
		return numberOfCarI;
	}
	
	public int startingSpeed()
	{
		return startingSpeedI;
	}
	
	public int numberOfPlayers()
	{
		return numberOfPlayersI;
	}
	
	public void setNumberOfPlayers(int n)
	{
		numberOfPlayersI = n;
	}	
	
	public int pointsForWin()
	{
		return pointsForWinI;
	}
	
	public int numberOfLives()
	{
		return numberOfLivesI;
	}
	
	public String orientationOfCars()
	{
		return orientationOfCarsS;
	}
	
	public boolean bonuses()
	{
		return bonusesB;
	}
	
	public int pointsForBonus()
	{
		return pointForBonusI;
	}
	
	public int speedUpAfterPoints()
	{
		return speedUpAfterPointsI;
	}
	
	
	
	

}
