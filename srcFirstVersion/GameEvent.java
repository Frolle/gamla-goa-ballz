/** GameEvent is a helpclass for observer/observable- functions to manage
	@ author Anders E	refactored by  Batman und Robin
	@ param none
*/
public class GameEvent 
{
	// Game event actions
	public static final int GAME_EVENT_BALL 					= 	0;
	public static final int GAME_EVENT_PADDLE 					= 	1;
	public static final int GAME_EVENT_GAME_CHANGER 			= 	2;
	public static final int GAME_EVENT_POINTS 					=   3;
	public static final int GAME_EVENT_GAMEOVER					=   4;
	public static final int GAME_EVENT_PLAYERDEAD 				=   5;
	
	//fields
		private int eventType;
		private int	dx;
		private int	dy;
		private double size;
		private int paddleIndex;
		private int points;
		private int playerIndex;
		private boolean gameOver;
		private Player[] players;
		private String winner;


		
		public GameEvent()
		{
			gameOver= false;					// from the beginning, game not over...
		}
				
	
	//methods
	
		int	getDx()
		{
			return dx;
		}
		int	getDy()
		{
			return dy;
		}
		
		public int	getPaddleIndex()
		{	
			return paddleIndex;
		}
		
		int	getPlayerIndex()
		{	
			return playerIndex;
		}
		
		int	getPoints()
		{	
			return points;
		}
		
		boolean getGameover()
		{
			return gameOver;
		}
		
		double getSize()
		{
			return size;
		}
		
		public int getEventType()
		{
			return eventType;
		}
		
		public void setEventType(int eventType)
		{
			this.eventType=eventType;
		}
		
		void setPoints(int points)
		{
			this.points = points;
		}
		
		void setPlayerIndex(int playerIndex)
		{
			this.playerIndex = playerIndex;
		}
		
		public void	setPaddleIndex(int paddleIndex)
		{
			this.paddleIndex=paddleIndex;
		}
		
		void	setDx(int dx)
		{
			this.dx = dx;;
		}
		
		void	setDy(int dy)
		{
			this.dy= dy;
		}
		
		void	setGameOver()
		{
			gameOver = true;
		}
		
		void	setSize(double size)
		{
			this.size = size;
		}
		
		public void setPlayers(Player[] players) {
			this.players = players;
		}

		public Player[] getPlayers() {
			return players;
		}
		
		public String getWinner() {
			// TODO Auto-generated method stub
			return winner;
		}
		
		public void setWinner(String winner) {
			this.winner = winner;
		}
		
}
