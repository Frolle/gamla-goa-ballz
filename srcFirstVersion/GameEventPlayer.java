/**
 * Subclass to GameEvent to determine what type of game event that happened.
 * Contains class specific methods that alter the player's stats.
 * @author Batman und Robin
 *
 */
public class GameEventPlayer extends GameEvent {

	private int playerBonusPoints = 0;
	private int playerDextraLife = 0;
	private boolean playerDead = false;
	
	public GameEventPlayer() 
	{
	}
	@Override
	public void setPoints(int playerBonusPoints)
	{
		super.setPoints(playerBonusPoints);
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
	
	@Override
	public void setPlayerIndex(int lastPlayerHit)
	{
		super.setPlayerIndex(lastPlayerHit);
	}
	

}
