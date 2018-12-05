package Frogger;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;



public class Game extends JFrame {
	
	
	Timer timer, timer2;
	Database db = new Database();
	Options options = new Options();
	Objects objects = new Objects();
	View view = new View(objects,db);
    public static final int WIDTH = 400;
    public static final int HEIGHT = 645;  
        
    Frog frog = new Frog(ID.Frog, view,1);
    Frog frog2 = new Frog(ID.Frog, view,2);
    
    int s = 0;
    boolean isPaused = false;
	Handler handler = new Handler(view, objects, frog,frog2, options, this);    
    String keyPressed, keyPressed2;
    boolean livefrog1 = true,livefrog2 = true;
    
    public void setLives(boolean livefrog1, boolean livefrog2)
    {
    	this.livefrog1 =livefrog1;this.livefrog2 =livefrog2;
    }
    private void run()
	{    	
    	
		 this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		 this.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		 this.setMinimumSize(new Dimension(WIDTH,HEIGHT));
	     this.add(view);
		 //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     this.setDefaultCloseOperation(0);
		 this.setResizable(false);
		 this.setLocationRelativeTo(null);
		 this.setVisible(true);
		 this.setTitle("Frogger");	 
		
		 
		 objects.addObject(frog);
		 if(options.numberOfPlayers() == 2)
			 objects.addObject(frog2);
	timer = new Timer(true);
	timer.schedule(handler, 0, 40);	 
	
    addKeyListener(new KeyListener() {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE && s == 0) {						
				options.setNumberOfPlayers(1);
				view.setPlayers(1);
				
				setLives(true,true);
				objects.removeObject(frog2);
				view.setI(2);
				view.repaint();
				s++;						
			}	
			if (e.getKeyCode() == KeyEvent.VK_T && s == 0) {						
				options.setNumberOfPlayers(2);
				setLives(true,true);
				view.setPlayers(2);
				view.setI(2);
				view.repaint();						
				s++;						
			}				
			
			if(livefrog1 == true) {
			if (e.getKeyCode() == KeyEvent.VK_UP && s > 0) {
				keyPressed = "u";
				frog.setKeyPressed(keyPressed);
				frog.update();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN && s > 0) {
				keyPressed = "d";
				frog.setKeyPressed(keyPressed);
				frog.update();
				
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT && s > 0) {
				keyPressed = "l";
				frog.setKeyPressed(keyPressed);
				frog.update();
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT && s > 0) {
				keyPressed = "r";
				frog.setKeyPressed(keyPressed);
				frog.update();
			}
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE && s > 0) {
				System.exit(0);
			}
			}
			
			if(livefrog2 == true) {
			if (e.getKeyCode() == KeyEvent.VK_W && s > 0 && options.numberOfPlayers() == 2) {
				keyPressed2 = "u";
				frog2.setKeyPressed(keyPressed2);
				frog2.update();
			}
			if (e.getKeyCode() == KeyEvent.VK_S && s > 0 && options.numberOfPlayers() == 2) {
				keyPressed2 = "d";
				frog2.setKeyPressed(keyPressed2);
				frog2.update();
				
			}
			if (e.getKeyCode() == KeyEvent.VK_A && s > 0 && options.numberOfPlayers() == 2) {
				keyPressed2 = "l";
				frog2.setKeyPressed(keyPressed2);
				frog2.update();
			}
			if (e.getKeyCode() == KeyEvent.VK_D && s > 0 && options.numberOfPlayers() == 2) {
				keyPressed2 = "r";
				frog2.setKeyPressed(keyPressed2);
				frog2.update();				
			}
			}
			if (e.getKeyCode() == KeyEvent.VK_R && s > 0 && handler.isOver()==true) {						
				if(options.numberOfPlayers() == 1)
					db.setData(frog.getScore());
				if(options.numberOfPlayers() == 2) {
					db.setData(frog.getScore());
					db.setData(frog2.getScore());
				}
				view.fillTopScore();
				view.setI(0);
				frog.setNumberOfLives(options.numberOfLives());
				frog2.setNumberOfLives(options.numberOfLives());
				frog.startScore(0);frog2.startScore(0);
				frog.setX(200);frog2.setX(50);frog.setY(500);frog2.setY(500);
				objects.addObject(frog);
				objects.addObject(frog2);
				view.setWins(0, 0);
				view.setLives(options.numberOfLives(), options.numberOfLives());
				view.repaint();
				s=0; 
			}	
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE && s > 0 && handler.isOver()==true) {						
				if(options.numberOfPlayers() == 1)
					db.setData(frog.getScore());
				if(options.numberOfPlayers() == 2) {
					db.setData(frog.getScore());
					db.setData(frog2.getScore());
				}
				System.exit(0);
			}	
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			return;
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			return;
		}
	});

    
    
	}
	
	public static void main(String[] args) {
		new Game().run();

	}

}
