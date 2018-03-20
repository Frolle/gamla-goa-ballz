package pong.event;
import java.util.*;
import java.awt.Color;

public class GameChangerHandler extends Observable {
	private Random random = new Random();
	private int gameChangerIndex;
	private Color[] colors = new Color[] {Color.YELLOW, Color.BLUE, Color.CYAN, Color.GREEN,
										  Color.MAGENTA, Color.ORANGE, Color.RED, Color.PINK};
	private GameChangerIcon gameChangerIcon;
	private GameEventBall gameEventBall;
	private GameEventPaddle gameEventPaddle;
	private GameEventPlayer gameEventPlayer;
	
	private final int BALL_SIZE_CHANGER = 0;
	private final int BALL_DIRECTION_CHANGER = 1;
	private final int BALL_SPEED_CHANGER = 2;
	private final int PADDLE_SIZE_DECREASE = 3;
	private final int PADDLE_SIZE_INCREASE = 4;
	private final int BONUS_POINTS = 5;
	private final int PLAYER_INCREASE_LIFE = 6;
	private final int PLAYER_LOSE_LIFE = 7;

	public GameChangerHandler()
	{
			gameChangerIndex = 0;
	}
	
	public void createGameChanger(int x, int y)
	{	
		gameChangerIcon = new GameChangerIcon(x, y, colors[gameChangerIndex]);
		gameChangerIcon.setActive(true);
		setChanged();
		notifyObservers(gameChangerIcon);
	}
	
	public void deactivateGameChanger()
	{
		gameChangerIcon.setActive(false);
		setChanged();
		notifyObservers(gameChangerIcon);
	}
	
	public void awardGameChanger(int lastPlayerHit)
	{
		switch(gameChangerIndex)
		{
			case BALL_SIZE_CHANGER:
				int ballSizeFactor = 2;
				gameEventBall = new GameEventBall();
				gameEventBall.setSize(ballSizeFactor);
				setChanged();
				notifyObservers(gameEventBall);
				break;
				
		}
	}
}