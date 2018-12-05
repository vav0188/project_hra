package Frogger;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class View extends JPanel {
	
	private int ll = 0;
	private String[] top;
	private Objects objects;
	private Frog frog;
	private Database db;
	private Bonus bonus;
	private int i;
	private Image img, img1, img2, img3, img4, imgo;
	private int wins = 0, lives = 0, players = 1, wins2 = 0, lives2 = 0, winner = 1;
	Color b = new Color(246,229,160);	
	Color r = new Color(255,128,128);
	
	public View(Objects o, Database db)
	{
		try {
			img = ImageIO.read(new File("pozadi.png"));
		} catch (IOException exc) {
			System.out.println("Can't load image.");
		}
		try {
			img1 = ImageIO.read(new File("frogicon.png"));
		} catch (IOException exc) {
			System.out.println("Can't load image.");
		}
		try {
			img2 = ImageIO.read(new File("frogicon2.png"));
		} catch (IOException exc) {
			System.out.println("Can't load image.");
		}
		try {
			img3 = ImageIO.read(new File("frogfinal.png"));
		} catch (IOException exc) {
			System.out.println("Can't load image.");
		}
		try {
			img4 = ImageIO.read(new File("frogfinal2.png"));
		} catch (IOException exc) {
			System.out.println("Can't load image.");
		}
		try {
			imgo = ImageIO.read(new File("over.png"));
		} catch (IOException exc) {
			System.out.println("Can't load image.");
		}
		
		objects = o;	
		i = 0;
		this.db = db;
		fillTopScore();
		
	}
	
	public void fillTopScore()
	{
		top = db.getData().split("x");
	}
	
	public void setI(int i)
	{
		if(i == 0 || i == 2)
			this.i = i;
	}
	public void end()
	{
		i = 3;
	}
	
	public void setWins(int w, int w2)
	{
		wins = w; wins2 = w2;
	}
	public void setLives(int l, int l2)
	{
		lives = l; lives2 = l2;
	}	
	public void giveBonus(Bonus bonus)
	{
		this.bonus = bonus;
	}
	public void setPlayers(int players)
	{
		this.players = players;
	}
	public void setWinner(int winner)
	{
		this.winner = winner;
	}

	@Override
	public void paint(Graphics g) {
		
		if(i == 0)
		{
			
			try {
				img = ImageIO.read(new File("pozadi.png"));
			} catch (IOException exc) {
				System.out.println("Can't load image.");
			}
			g.drawImage(img,0,0, this);	
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.setColor(Color.white);
			for(int i = 0; i < top.length; i++)
			{
				g.drawString(""+top[i], 168, 409+(i*18)+3);
			}		
		}	
		if(i == 2)
		{

			try {
				img = ImageIO.read(new File("hra.png"));
			} catch (IOException exc) {
				System.out.println("Can't load image.");
			}			
			g.drawImage(img,0,0, this);	
			g.setColor(Color.gray);
			g.drawRect(0, 560, 400, 645);
			g.fillRect(0, 560, 400, 645);
			
			g.drawImage(img2,10,560, this);	
			g.setColor(Color.white);
			g.setFont(new Font("Calibri", Font.BOLD, 19));			
			g.drawString("SCORE: " + wins, 200, 580);
			g.setColor(Color.white);
			g.setFont(new Font("Calibri", Font.BOLD, 19));			
			g.drawString("LIVES: " + lives, 50, 580);
			
			if(players == 2) {
				g.drawImage(img1,10,584, this);	
				g.setColor(Color.white);
				g.setFont(new Font("Calibri", Font.BOLD, 19));			
				g.drawString("SCORE: " + wins2, 200, 605);
				g.setColor(Color.white);
				g.setFont(new Font("Calibri", Font.BOLD, 19));			
				g.drawString("LIVES: " + lives2, 50, 605);
			}
			if(bonus.createBonus())		
				g.drawImage(bonus.loadImage(), bonus.getX(), bonus.getY(), this);
			for(Object o : objects.getObjects())
				g.drawImage(o.loadImage(), o.getX(),o.getY(), this);
		}
		if(i == 3)
		{
			
			if(players == 1) {
				g.drawImage(imgo,0,0, this);
				g.drawImage(img3,100,100, this);
				g.setColor(Color.white);
				g.setFont(new Font("Calibri", Font.BOLD, 40));			
				g.drawString("FINAL SCORE: " + wins, 80, 420);
				if(db.getFifth() < wins)
				{
					g.setColor(Color.white);
					g.setFont(new Font("Calibri", Font.BOLD, 20));			
					g.drawString("AND YOU ARE IN TOP 5!", 40, 500);
				}
			}
			if(players == 2) {
				g.drawImage(imgo,0,0, this);
				if(winner == 1)
					g.drawImage(img3,100,100, this);
				else if(winner == 2)
					g.drawImage(img4,100,100, this);
				g.setColor(Color.white);
				g.setFont(new Font("Calibri", Font.BOLD, 40));			
				
				g.setFont(new Font("Calibri", Font.BOLD, 30));	
				if(wins > wins2) {
					g.drawString("FINAL SCORE OF WINNER: " + wins, 20, 420);
					g.drawString("FINAL SCORE OF LOSER: " + wins2, 35, 470);
					if(db.getFifth() < wins)
					{
						g.setColor(Color.white);
						g.setFont(new Font("Calibri", Font.BOLD, 20));			
						g.drawString("AND WINNER IS IN TOP 5!", 40, 500);
						if(db.getFifth() < wins2)
							g.drawString("LOSER IS ALSO IN TOP 5!", 40, 520);
					}
				}
				if(wins < wins2) {
					g.drawString("FINAL SCORE OF WINNER: " + wins2, 20, 420);
					g.drawString("FINAL SCORE OF LOSER: " + wins, 35, 470);
					if(db.getFifth() < wins2)
					{
						g.setColor(Color.white);
						g.setFont(new Font("Calibri", Font.BOLD, 20));			
						g.drawString("AND WINNER IS IN TOP 5!", 40, 500);
						if(db.getFifth() < wins)
							g.drawString("LOSER IS ALSO IN TOP 5!", 40, 520);
						
					}
				}

			}
		}
}
}
