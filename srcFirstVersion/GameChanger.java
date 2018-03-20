import java.util.*;

public class GameChanger extends Observable{

	
	private Random random;
	private int gameChangerIndex;
	private String[] colors ={"Purple", "Magenta", "Yellow", 
			"Green", "Pink", "Blue", "Red", "Orange"};
	private GameEventBall gameEventBall;
	private GameEventPaddle gameEventPaddle;
	private GameEventPlayer gameEventPlayer;
	private final String closeGameChanger = ("Terminate");
	
	private final int BALL_SIZE_CHANGER = 0;
	private final int BALL_DIRECTION_CHANGER = 1;
	private final int PADDLE_SIZE_INCREASE = 2;
	private final int BONUS_POINTS = 3;
	private final int PLAYER_INCREASE_LIFE = 4;
	private final int BALL_SPEED_CHANGER = 5;
	private final int PLAYER_LOSE_LIFE = 6;
	private final int PADDLE_SIZE_DECREASE = 7;
	
	public GameChanger() {
		// TODO Auto-generated constructor stub
		random = new Random();
		gameChangerIndex = random.nextInt(8);
		activateGameChanger();
				
	}
	
	public void createGameChanger(int lastPlayerHit)
	{
					
		switch(gameChangerIndex){
		
			case BALL_SIZE_CHANGER:
				double alteredBallSize = 2;
				setChanged();
				gameEventBall = new GameEventBall();
				gameEventBall.setSize(alteredBallSize); 
				notifyObservers(gameEventBall);
				break;
					
			case BALL_DIRECTION_CHANGER:
				int invertDirection = -1;
				setChanged();
				gameEventBall = new GameEventBall();
				gameEventBall.setBallDirection(invertDirection);
				break;
					
			case PADDLE_SIZE_INCREASE:
				int increase = 2;
				setChanged();
				gameEventPaddle = new GameEventPaddle();
				gameEventPaddle.setSize(increase);
				gameEventPaddle.setPlayerIndex(lastPlayerHit);
				notifyObservers(gameEventPaddle);
				break;
					
			case BONUS_POINTS: 
				int bonusPoints = 5;
				setChanged();
				gameEventPlayer = new GameEventPlayer();
				gameEventPlayer.setPoints(bonusPoints);
				gameEventPlayer.setPlayerIndex(lastPlayerHit);
				notifyObservers(gameEventPlayer);
				break;
					
			case PLAYER_INCREASE_LIFE: 
				int extraLife = 3;
				setChanged();
				gameEventPlayer = new GameEventPlayer();
				gameEventPlayer.setPlayerLife(extraLife);
				gameEventPlayer.setPlayerIndex(lastPlayerHit);
				notifyObservers(gameEventPlayer);
				break;
					
			case BALL_SPEED_CHANGER: 
				double alteredBallSpeed = 2;
				setChanged();
				gameEventBall = new GameEventBall();
				gameEventBall.setBallSpeedFactor(alteredBallSpeed); 
				notifyObservers(gameEventBall);
				break;
			
			case PLAYER_LOSE_LIFE:
				int dextraLife = -3;
				setChanged();
				gameEventPlayer = new GameEventPlayer();
				gameEventPlayer.setPlayerLife(dextraLife);
				gameEventPlayer.setPlayerIndex(lastPlayerHit);
				notifyObservers(gameEventPlayer);
				break;
					
			case PADDLE_SIZE_DECREASE: 
				
				int decrease = (1)/2;
				setChanged();
				gameEventPaddle = new GameEventPaddle();
				gameEventPaddle.setSize(decrease);
				gameEventPaddle.setPlayerIndex(lastPlayerHit);
				notifyObservers(gameEventPaddle);
				break;
					
			default: break;
		}
	
	}

	
	public void activateGameChanger()
	{
		setChanged();
		notifyObservers(colors[gameChangerIndex]);
	}
	public void deactivateGameChanger()
	{
		setChanged();
		notifyObservers(closeGameChanger);
	}
}
