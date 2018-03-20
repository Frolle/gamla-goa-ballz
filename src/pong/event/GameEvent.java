package pong.event;
/** 
 * GameEvent is a help class to determine what types of events that has occurred.
 * An observable creates an instance of this class, depending on what event that 
 * happened, and sends it as the object with the notifyObserver() method.
 * Is a superclass to GameEventBall, GameEventPaddle and GameEventPlayer.
 * @author Group 4
 * @version 1.8
 * @since 2012-02-24
*/
public class GameEvent 
{

	
/**
 * Fields
 */

		private int	dx;
		private int	dy;
		private double size;
		private int playerIndex;
		
		public GameEvent()
		{
			
		}
				
	
		/**
		 * Returns the difference in X coordinate.
		 * @return The difference in X coordinate.
		 */
		public int	getDx()
		{
			return dx;
		}
		/**
		 * Returns the difference in Y coordinate.
		 * @return The difference in Y coordinate.
		 */
		public int	getDy()
		{
			return dy;
		}
		/**
		 * Returns the player index as an int.
		 * @return The player index.
		 */
		public int	getPlayerIndex()
		{	
			return playerIndex;
		}
		/**
		 * Returns the size variable as a double.
		 * @return The size variable as a double.
		 */
		public double getSize()
		{
			return size;
		}
		/**
		 * Sets the player index as the parameter.
		 * @param playerIndex as an int.
		 */
		public void setPlayerIndex(int playerIndex)
		{
			this.playerIndex = playerIndex;
		}
		/**
		 * Sets the difference of the X coordinate.
		 * @param dx coordinate (int).
		 */
		public void setDx(int dx)
		{
			this.dx = dx;
		}
		/**
		 * Sets the difference of the Y coordinate.
		 * @param dy coordinate (int).
		 */
		public void setDy(int dy)
		{
			this.dy= dy;
		}
		/**
		 * Sets the size as the parameter
		 * @param size (double).
		 */
		public void setSize(double size)
		{
			this.size = size;
		}
				

}
