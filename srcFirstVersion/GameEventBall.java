/**
 * Subclass to GameEvent to determine what type of game event that happened.
 * Contains class specific methods that alter the ball's behaviour.
 * @author Batman und Robin
 *
 */
public class GameEventBall extends GameEvent {
	private double ballSpeedFactor = 1;
	private double ballSizeFactor = 1;
	private int ballDirectionFactor = 1;

	public GameEventBall() 
	{
	}
	
	public void setBallSpeedFactor(double ballSpeedFactor)
	{
		this.ballSpeedFactor = ballSpeedFactor;
	}
	
	public double getBallSpeedFactor()
	{
		return ballSpeedFactor;
	}
	
	@Override
		public void setSize(double ballSizeFactor)
	{
		super.setSize(ballSizeFactor);
	}
	
	public void setBallDirection(int ballDirectionFactor)
	{
		this.ballDirectionFactor = ballDirectionFactor;
	}
	
	public int getBallDirection()
	{
		return ballDirectionFactor;
	}
}
