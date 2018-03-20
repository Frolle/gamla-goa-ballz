/**
 * Subclass to GameEvent to determine what type of game event that happened.
 * Contains class specific methods that alter the paddle's behaviour.
 * @author Batman und Robin
 *
 */
public class GameEventPaddle extends GameEvent {
	
	public GameEventPaddle() {
		
	}

	@Override
	public void setSize(double paddleSizeFactor)
	{
		super.setSize(paddleSizeFactor);
	}
	
	public void setPlayerIndex(int lastPlayerHit)
	{
		super.setPlayerIndex(lastPlayerHit);
	}
	
}
