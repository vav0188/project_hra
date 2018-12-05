package Frogger;

import java.util.Random;
import java.util.TimerTask;


public class Handler extends TimerTask{
	private View view;
	private Objects objects;
	private Frog frog[];
	private Options options;
	private Bonus bonus;
	private double timeOfGame = 0;
	private int speedOfCar = 1;
	private Game game;
	private int numcars = 0;
	private Car cars[];
	private int i = 0;
	private boolean bon = true, rest = false;
	Random r = new Random();
	
	public Handler(View view, Objects objects, Frog frog,Frog frog2,Options options, Game game)
	{
		
		this.view = view; 
		this.objects = objects; 
		this.game = game;
		this.options = options;
		this.frog = new Frog[options.numberOfPlayers()];
		this.numcars = options.numberOfCars();
		this.frog[0] = frog;
		if(options.numberOfPlayers() == 2)
		{	
			this.frog[1] = frog2;
			this.frog[1].setNumberOfLives(options.numberOfLives());
			
			view.setPlayers(2);
		}
		
		this.frog[0].setNumberOfLives(options.numberOfLives());
		this.bonus= new Bonus(ID.Bonus, options.bonuses());
		
		cars = new Car[numcars];
		for(int i = 0; i < numcars; i++)
		{
			cars[i] = new Car(speedOfCar,ID.Car,numcars, options.orientationOfCars());
		}
		
	}

	public boolean didFrogWin() 
	{
		for(int i = 0; i < options.numberOfPlayers(); i++)
		{
			if(frog[i].getY() < 60)
			{			
				frog[i].setScore(options.pointsForWin());
				frog[i].setX(180);
				frog[i].setY(500);
				int by = 0;
				int bx = r.nextInt(360);
				int by1 = r.nextInt(3);
				int by2 = r.nextInt(2)+1;
				if(by2 == 1)
					by = 80+60*by1;
				if(by2 == 2)
					by = 320+60*by1;
				bonus.setX(bx);
				bonus.setY(by);
				bon = true;
				bonus.haveBonus(bon);
				if(options.numberOfPlayers() == 2)
					view.setWins(frog[0].getScore(), frog[1].getScore());
				else if(options.numberOfPlayers() == 1)
					view.setWins(frog[0].getScore(), frog[0].getScore());
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void run() {
		
		if(i == 0) {
			this.addCars();i++;
			if(options.numberOfPlayers() == 2)
				view.setLives(frog[0].getLives(), frog[1].getLives());
			if(options.numberOfPlayers() == 1)
				view.setLives(frog[0].getLives(), frog[0].getLives());
			if(bonus.createBonus())
				{bonus.setX(100); bonus.setY(320);}
				view.giveBonus(bonus);
		}
		this.didFrogWin();
		this.isFrogHit();
		this.isOver();
		if(options.bonuses())
			this.haveBonus();
		this.updateSpeed();
		for (Object o : objects.getObjects()) {
			if (o instanceof Car) {
				((Car) o).setSpeed(speedOfCar);
				o.update();
			}
		}
		timeOfGame += new Double(.0006);
		view.repaint();
	}
	
	private void updateSpeed() {
		
		//for(int i = 0; i < options.numberOfPlayers(); i++)
		//{
		int d = 0;
		if(options.numberOfPlayers() == 2 && (frog[0].getScore() < frog[1].getScore()))
			d = 1;
		if(options.numberOfPlayers() == 2 && (frog[0].getScore() > frog[1].getScore()))
			d = 0;
		
			if(frog[d].getScore() >= 0 && frog[0].getScore() < 1*options.speedUpAfterPoints())
				speedOfCar = options.startingSpeed();
			if(frog[d].getScore() > 1*options.speedUpAfterPoints() && frog[d].getScore() <= 2*options.speedUpAfterPoints())
				speedOfCar = options.startingSpeed()+1;
			if(frog[d].getScore() > 2*options.speedUpAfterPoints() && frog[d].getScore() <= 3*options.speedUpAfterPoints())
				speedOfCar = options.startingSpeed()+3;
			if(frog[d].getScore() > 3*options.speedUpAfterPoints() && frog[d].getScore() <= 4*options.speedUpAfterPoints())
				speedOfCar = options.startingSpeed()+5;
			if(frog[d].getScore() > 4*options.speedUpAfterPoints())
				speedOfCar = options.startingSpeed()+8;
		//}
		
	}

	public double getTimeOfGame() {
		return timeOfGame;
	}
	
	public void addCars()
	{
		for(int i = 0; i < numcars; i++)
		{
			objects.addObject(cars[i]);
		}
		//objects.addObject(car);
	}
	public boolean isFrogHit() {
		
		for(int i = 0; i < options.numberOfPlayers(); i++)
		{
		for (Object s : objects.getObjects()) {
			if (!(s instanceof Frog)) {
				if ((-25 <= (frog[i].getY() - s.getY()) && (frog[i].getY() - s.getY()) <= 25)
						&& ((frog[i].getX() - s.getX()) <= 75 && (frog[i].getX() - s
								.getX()) >= -30)) {
					frog[i].setLives();
					if(options.numberOfPlayers() == 2)
						view.setLives(frog[0].getLives(), frog[1].getLives());
					else if(options.numberOfPlayers() == 1)
						view.setLives(frog[0].getLives(), frog[0].getLives());
					frog[i].setX(180);
					frog[i].setY(500);
					
					
					return true;
				}
			}
		}}
		return false;
	}
	
	public boolean isOver()
	{
		if(options.numberOfPlayers() == 1) {
			if(frog[0].getLives() == 0)
			{
				view.end();
				//this.cancel();	
				return true;
			}
		}
		if(options.numberOfPlayers() == 2)
		{
			if(frog[1].getLives() == 0 && frog[0].getLives() == 0)
			{
				view.end();
				//this.cancel();	
				return true;
			}
			if(frog[0].getLives() == 0)
			{
				objects.removeObject(frog[0]);
				game.setLives(false, true);
				return false;
			}
			if(frog[1].getLives() == 0)
			{
				game.setLives(true, false);
				objects.removeObject(frog[1]);				
				return false;
			}
			
		}
		return false;
	}
	
	public boolean haveBonus()
	{
		for(int i = 0; i < options.numberOfPlayers(); i++)
		{
		if (bon == true && (-10 <= (frog[i].getY() - bonus.getY()) && (frog[i].getY() - bonus.getY()) <= 10)
				&& ((frog[i].getX() - bonus.getX()) <= 30 && (frog[i].getX() - bonus
						.getX()) >= -10)) {
			bon = false;
			
			frog[i].setScore(options.pointsForBonus());
			if(options.numberOfPlayers() == 2)
				view.setWins(frog[0].getScore(), frog[1].getScore());
			else if(options.numberOfPlayers() == 1)
				view.setWins(frog[0].getScore(), frog[0].getScore());
			
			bonus.haveBonus(bon);
			
			return true;
		}}
		return false;
	}
	
}
