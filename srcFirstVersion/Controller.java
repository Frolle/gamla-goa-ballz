import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

/**
 * A class that functions as a game engine, performing all the
 * necessary algorithms for movement within the game.
 * @author Simon, Gustaf, Patrik
 * @version 2.0
 * @version 2012-02-13
 *
 */
public class Controller implements ActionListener
{
	private GameView gameView;
	private Timer gameTimer;
	private GameModel gameModel;
	
	private int ballSpeedX;
	private int ballSpeedY;
	private int gamePlanSide;
	private final int PERIOD							=	12;
	private int changerPeriod							=	0;
	
	public final int COLLISION_INVERT_X 				=	-1;
	public final int COLLISION_INVERT_Y 				= 	-1;
	
	public final int COLLISION_LOSE 					=	0;
	public final int COLLISION_NONE 					= 	1;
	
	
	public final int PLAYER_ONE_PADDLE_ACTION_UP 		= 	1;
	public final int PLAYER_ONE_PADDLE_ACTION_DOWN 		= 	2;
	public final int PLAYER_TWO_PADDLE_ACTION_UP		= 	3;
	public final int PLAYER_TWO_PADDLE_ACTION_DOWN 		= 	4;
	public final int PLAYER_THREE_PADDLE_ACTION_UP 		= 	5;
	public final int PLAYER_THREE_PADDLE_ACTION_DOWN 	= 	6;
	public final int PLAYER_FOUR_PADDLE_ACTION_UP 		= 	7;
	public final int PLAYER_FOUR_PADDLE_ACTION_DOWN		=	8;
	
	public final int PLAYER_ONE							=	0;
	public final int PLAYER_TWO							=	1;
	public final int PLAYER_THREE						=	2;
	public final int PLAYER_FOUR						=	3;

	
	/**
	 * Constructor that initiates and instances all objects.
	 * @param gameView reference to the class GameView.
	 */
	public Controller(GameView gameView, GameModel gameModel)
	{
		this.gameView = gameView;
		this.gameModel = gameModel;
		gameTimer = new Timer(PERIOD, this);
		gameTimer.setInitialDelay(2000);
	}
	
	/**
	 * Action event.
	 * Timer uses this to update the game.
	 * @param e The action event that callt the method
	 */
	public void actionPerformed(ActionEvent e)
	{
		gameTimer();
		gameChangerTimer();
	}
	
	/**
	 * Uses collisionDetect to check if a collision
	 * has occurred and sets the ball speed to initial values.
	 */
	public void gameTimer()
	{
		collisionDetect();			
		gameModel.setBallSpeed(ballSpeedX, ballSpeedY);		
	}
	/**
	 * The timer for creating a new GameChanger object and
	 * subsequently removing it.
	 */
	
	public void gameChangerTimer()
	{
	/*	TODO
		changerPeriod++;
		if(changerPeriod == 5000)
		{
			GameChanger gameChanger = new GameChanger();
			gameChanger.activateGameChanger();
			changerPeriod = 0;
		}
		if(COLLISION_CHANGER)
		{
			gameChanger.createGameChanger(lastPlayerHit);
			gameChanger.deactivateGameChanger();
		}
		if(changerPeriod == 2000)
			gameChanger.deactivateGameChanger();
			*/
	}
	
	/**
	 * Start the timer
	 */
	public void start(int gamePlanSide)
	{
		/*int speed = (int) (gamePlanSide * 0.006);*/
		if(gamePlanSide != 0)
		{
			this.gamePlanSide = gamePlanSide;
		}
		Random random = new Random();
		double[] speedList = {0.005, -0.006, 0.007, -0.008, 0.009,
							  -0.005, 0.006, -0.007, 0.008, -0.009};
		ballSpeedX = (int) (this.gamePlanSide * speedList[random.nextInt(speedList.length)]);
		ballSpeedY = (int) (this.gamePlanSide * speedList[random.nextInt(speedList.length)]);
		
		gameTimer.start();		
	}
	
	/**
	 * Stop the timer
	 */
	public void stop()
	{
		
		gameTimer.stop();
		
	}
	

	/**
	 * Sets the movement of the paddles through gameModel.
	 * @param paddleMovement
	 */
	public void calculatePaddleAction(int paddleMovement)
	{
		//TODO Finish the paddle restrictions
		Paddle[] paddles = gameView.getPaddles();
		switch (paddleMovement)
		{
			case PLAYER_ONE_PADDLE_ACTION_UP:
				if(paddles[PLAYER_ONE].getY() > gameView.getWallThickness())
					gameModel.setPaddleAction(PLAYER_ONE, 0, -(int)paddles[PLAYER_ONE].getHeight() / 3);
				break;
				
			case PLAYER_ONE_PADDLE_ACTION_DOWN:
				if((paddles[PLAYER_ONE].getY() + paddles[PLAYER_ONE].getHeight()) <= paddles[PLAYER_THREE].getY())
					gameModel.setPaddleAction(PLAYER_ONE, 0, (int)paddles[PLAYER_ONE].getHeight() /3);
				break;
				
			case PLAYER_TWO_PADDLE_ACTION_UP:
				if(paddles[PLAYER_TWO].getY() > gameView.getWallThickness())
					gameModel.setPaddleAction(PLAYER_TWO, 0, -(int)paddles[PLAYER_TWO].getHeight() / 3);
				break;
				
			case PLAYER_TWO_PADDLE_ACTION_DOWN:
				if((paddles[PLAYER_TWO].getY() + paddles[PLAYER_TWO].getHeight()) <= paddles[PLAYER_THREE].getY())
					gameModel.setPaddleAction(PLAYER_TWO, 0, (int)paddles[PLAYER_TWO].getHeight() / 3);
				break;
				
			case PLAYER_THREE_PADDLE_ACTION_UP:
				if(paddles[PLAYER_THREE].getX() - ((int)paddles[PLAYER_THREE].getWidth() / 3) < paddles[PLAYER_ONE].getX())
					gameModel.setPaddleAction(PLAYER_THREE, -(int)(paddles[PLAYER_THREE].getX() - paddles[PLAYER_ONE].getX()), 0);
				
				else if(paddles[PLAYER_THREE].getX() > paddles[PLAYER_ONE].getX())
					gameModel.setPaddleAction(PLAYER_THREE, -(int)paddles[PLAYER_THREE].getWidth() / 3, 0);
				
				break;
				
			case PLAYER_THREE_PADDLE_ACTION_DOWN:
				if((paddles[PLAYER_THREE].getX() + paddles[PLAYER_THREE].getWidth() + (paddles[PLAYER_THREE].getWidth() / 3)) > paddles[PLAYER_TWO].getX())
					gameModel.setPaddleAction(PLAYER_THREE, (int)(paddles[PLAYER_TWO].getX() - paddles[PLAYER_THREE].getWidth() - paddles[PLAYER_THREE].getX()), 0);
				
				else if((paddles[PLAYER_THREE].getX() + paddles[PLAYER_THREE].getWidth()) < paddles[PLAYER_TWO].getX())
					gameModel.setPaddleAction(PLAYER_THREE, (int)paddles[PLAYER_THREE].getWidth() / 3, 0);
				
				break;
				
			case PLAYER_FOUR_PADDLE_ACTION_UP:
				if(paddles[PLAYER_FOUR].getX() - ((int)paddles[PLAYER_FOUR].getWidth() / 3) < paddles[PLAYER_ONE].getX())
					gameModel.setPaddleAction(PLAYER_FOUR, -(int)(paddles[PLAYER_FOUR].getX() - paddles[PLAYER_ONE].getX()), 0);
				
				else if(paddles[PLAYER_FOUR].getX() > paddles[PLAYER_ONE].getX())
					gameModel.setPaddleAction(PLAYER_FOUR, -(int)paddles[PLAYER_FOUR].getWidth() / 3, 0);
				
				break;
				
			case PLAYER_FOUR_PADDLE_ACTION_DOWN:
				if((paddles[PLAYER_FOUR].getX() + paddles[PLAYER_FOUR].getWidth() + (paddles[PLAYER_FOUR].getWidth() / 3)) > paddles[PLAYER_TWO].getX())
					gameModel.setPaddleAction(PLAYER_FOUR, (int)(paddles[PLAYER_TWO].getX() - paddles[PLAYER_FOUR].getWidth() - paddles[PLAYER_FOUR].getX()), 0);
				
				else if((paddles[PLAYER_FOUR].getX() + paddles[PLAYER_FOUR].getWidth()) < paddles[PLAYER_TWO].getX())
					gameModel.setPaddleAction(PLAYER_FOUR, (int)paddles[PLAYER_FOUR].getWidth() / 3, 0);
				
				break;
		}
	}
	
	/**
	 * Checks if the ball hits a paddle and inverts
	 * the speed to make it look like it bounced.
	 * Also deals points to a player who made the ball bounce.
	 * 
	 */
	public int collisionDetect()
	{
		int collision;
		if(ballSpeedX > 0)
		{
			collision = check(1);
		}
		else
		{
			collision = check(0);
		}
		if (ballSpeedY > 0)
		{
			collision = check(2);
		}
		else
		{
			collision = check(3);
		}		
		
		return collision;
	}
	
	private int check(int i)
	{
		Ball ball = gameView.getBall();
		Paddle[] paddles = gameView.getPaddles();
		double ballYPos = ball.getY();
		double ballXPos = ball.getX();
		double ballDiameter = ball.getHeight();
		double paddleY=paddles[i].getY();
		double paddleX=paddles[i].getX();
		double paddleXWidth=paddles[i].getWidth();
		double paddleYHeight=paddles[i].getHeight();
		switch (i){
			case 0:
				if (ballXPos<=(paddleX+paddleXWidth))
				{
					if (ballYPos>=paddleY&&ballYPos<=(paddleY+paddleYHeight))
					{
						ballSpeedX = ballSpeedX*COLLISION_INVERT_X;
						if (paddles[i].isActive())
							gameModel.setPlayerScore(i, 1);
						//poŠng
					}
					else //if (ballYPos<paddleY&&ballYPos>(paddleY+paddleYHeight))
					{
						deathHandler(i);
					}
						
				}	
				break;
			case 1:
				if (ballXPos>=(paddleX - ballDiameter))
				{
					if (ballYPos>=paddleY&&ballYPos<=(paddleY+paddleYHeight))
					{
						ballSpeedX = ballSpeedX*COLLISION_INVERT_X;
						if (paddles[i].isActive())
							gameModel.setPlayerScore(i, 1);
						
						//poŠng
					}
					else
					{
						deathHandler(i);
					}
				}
				break;
			case 2 :
				if (ballYPos>=(paddleY-ballDiameter))
				{
					if (ballXPos>=paddleX&&ballXPos<=(paddleX+paddleXWidth))
					{
						ballSpeedY = ballSpeedY*COLLISION_INVERT_Y;
						//poŠng
						if (paddles[i].isActive())
							gameModel.setPlayerScore(i, 1);
					}
					else //if (ballXPos<paddleX&&ballXPos>(paddleX+paddleXWidth))
					{
						deathHandler(i);
					}

					
				}
				break;
			case 3:
				if (ballYPos<=(paddleY+paddleYHeight))
				{
					if (ballXPos>=paddleX&&ballXPos<=(paddleX+paddleXWidth))
					{
						ballSpeedY = ballSpeedY*COLLISION_INVERT_Y;
						//poŠng
						if (paddles[i].isActive())
							gameModel.setPlayerScore(i, 1);
					}
					else
					{
						deathHandler(i);
					}
				}
				break;
		}
		
		return 0;
	}
	private void deathHandler(int i)
	{
		gameModel.setPlayerLife(i, true);
		gameModel.setPlayerScore(i, -2);
		gameTimer.stop();
		gameView.resetBall();
		start(0);
	}
	
}