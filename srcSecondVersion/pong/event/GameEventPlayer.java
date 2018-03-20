package pong.event;
/**
 * Subclass to GameEvent to determine what type of game event that happened.
 * Contains class specific methods that alter the player's stats.
 * @author Batman und Robin
 *
 */
public class GameEventPlayer extends GameEvent {

	private int playerDextraLife;
	private boolean playerDead = false;
	private boolean scoreEvent = false;
	private int points;

	public GameEventPlayer() 
	{
	}
	
	public void setPlayerLife(int playerDextraLife)
	{
		this.playerDextraLife = playerDextraLife;
	}
	
	public int getPlayerLife()
	{
		return playerDextraLife;
	}
	
	public void setPlayerDead()
	{
		this.playerDead = true;
	}
	
	public boolean getPlayerDead()
	{
		return playerDead;
	}
	
	public void setScoreEvent()
	{
		scoreEvent = true;
	}
	
	public boolean getScoreEvent()
	{
		return scoreEvent;
	}
		
	@Override
	public void setPlayerIndex(int activePlayer)
	{
		super.setPlayerIndex(activePlayer);
	}
		
	public int	getPoints()
	{	
		return points;
	}
	
	public void setPoints(int points)
	{
		this.points = points;
	}
	

}
