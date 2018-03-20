package pong.event;
/** GameEvent is a helpclass for observer/observable- functions to manage
	@ author Anders E	refactored by  Batman und Robin
	@ param none
*/
public class GameEvent 
{

	
	//fields

		private int	dx;
		private int	dy;
		private double size;
		private int playerIndex;
		
		public GameEvent()
		{
								// from the beginning, game not over...
		}
				
	
	//methods
	
		public int	getDx()
		{
			return dx;
		}
		public int	getDy()
		{
			return dy;
		}

		public int	getPlayerIndex()
		{	
			return playerIndex;
		}
		
		public double getSize()
		{
			return size;
		}
						
		public void setPlayerIndex(int playerIndex)
		{
			this.playerIndex = playerIndex;
		}

		public void setDx(int dx)
		{
			this.dx = dx;
		}
		
		public void setDy(int dy)
		{
			this.dy= dy;
		}
			
		public void setSize(double size)
		{
			this.size = size;
		}
				

}
