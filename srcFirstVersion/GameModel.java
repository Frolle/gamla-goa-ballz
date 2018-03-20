import java.util.Observable;

/**
 * 
 * @author Grupp 4
 * @version 1.0
 * @since 2012-02-01
 */
public class GameModel extends Observable{

	private Player[] players;
	private GameEvent gameEvent;
	private int activePlayers;

	/**
	 * Constructor of this class
	 * 
	 * @param playerNames Array of strings containing the names of the players. Sent from MainPong.
	 */
	public GameModel(String[] playerNames)
	{
		
		players = createPlayers(playerNames);
		activePlayers = players.length;
	}
	
	/**
	 * Create the chosen number of players with their respective names.
	 * 
	 * @param playerNames
	 * @return Array of players
	 */
	private Player[] createPlayers(String[] playerNames)
	{
		int index = playerNames.length;
		Player[] players = new Player[index];
		//TODO if 1 player add AI
		for(int i = 0; i < index; i++)
		{
			if ((playerNames[i] == null)||(playerNames[i].equals("")))
			{
				players[i] = new Player("Player "+(i+1));
			}
			else
			{
				players[i] = new Player(playerNames[i]);
			}

		}
		
		return players;
	}
	
	/**
	 * Controller calls this method that uses the parameter to determine 
	 * which paddle that will be moved in which direction. Sets the nextPaddleToMove 
	 * and paddleDx, paddleDy vars. Calls notifyOberservers()
	 * 
	 * @param paddleToMove Which paddle to move, sent from control.
	 * @param paddleDx Change in paddle in x-position.
	 * @param paddleDy Change in paddle in y-position
	 */
	public void setPaddleAction(int paddleToMove, int paddleDx, int paddleDy)
	{
		if(paddleToMove < players.length)
		{
			gameEvent = new GameEvent();
			gameEvent.setDx(paddleDx);
			gameEvent.setDy(paddleDy);
			gameEvent.setPaddleIndex(paddleToMove);
			gameEvent.setEventType(GameEvent.GAME_EVENT_PADDLE);
			setChanged();
			notifyObservers(gameEvent);
		}
	}
	

	

	
	/**
	 * Using x and y to decide the direction of the ball.
	 * 
	 * Moves the ball in an appropriate direction, also calls notifyObservers
	 * 
	 * @param dx
	 * @param dy
	 */
	public void setBallSpeed(int dx, int dy)
	{
		gameEvent = new GameEvent();
		gameEvent.setDx(dx);
		gameEvent.setDy(dy);
		gameEvent.setEventType(GameEvent.GAME_EVENT_BALL);
		setChanged();
		notifyObservers(gameEvent);
	
	}

	/**
	 * 
	 * @param playerIndex
	 * @return
	 */
	public String getPlayerName(int playerIndex)
	{
		
		return players[playerIndex].getName();
	}
	

	
	/**
	 * Increments or decrements the players life
	 * 
	 * @param playerIndex The player who's life will be affected.
	 * @param decrementLife Boolean value for decrementing or incrementing. True for decrement.
	 */
	public void setPlayerLife(int playerIndex, boolean decrementLife)
	{
		if(decrementLife)
		{
			//TODO Fix for more than 2 players
			players[playerIndex].decrementLife();
			if(players[playerIndex].getLife() < 1)
			{
				activePlayers--;
				gameEvent = new GameEvent();
				gameEvent.setEventType(GameEvent.GAME_EVENT_PLAYERDEAD);
				gameEvent.setPlayerIndex(playerIndex);
				setChanged();
				notifyObservers(gameEvent);
				if (activePlayers<2)
				{
					activePlayers = players.length;
					gameEvent = new GameEvent();
					gameEvent.setWinner(getWinner());
					gameEvent.setPlayers(players);
					gameEvent.setEventType(GameEvent.GAME_EVENT_GAMEOVER);
					setChanged();
					notifyObservers(gameEvent);
				}
			}
		}
		else
		{
			players[playerIndex].incrementLife();
		}
	}
	
	private String getWinner() {
		Player winner = players[0];
		for(int i = 1; i < players.length; i++)
		{
			if (players[i].getScore()>winner.getScore())
			winner = players[i];
		}
		return winner.getName();
	}

	/**
	 * Changes the players score.
	 * 
	 * @param playerIndex The player, whose score will be affected.
	 * @param playerScore Number of points awarded.
	 */
	public void setPlayerScore(int playerIndex, int playerScore)
	{
		players[playerIndex].changeScore(playerScore);
		gameEvent = new GameEvent();
		gameEvent.setPlayerIndex(playerIndex);
		gameEvent.setPoints(players[playerIndex].getScore());
		gameEvent.setEventType(GameEvent.GAME_EVENT_POINTS);
		setChanged();
		notifyObservers(gameEvent);
	}
	public void resetGame()
	{
		for(int i = 0; i < players.length; i++)
		{
			players[i].resetPlayer();
			gameEvent = new GameEvent();
			gameEvent.setPlayerIndex(i);
			gameEvent.setPoints(players[i].getScore());
			gameEvent.setEventType(GameEvent.GAME_EVENT_POINTS);
			setChanged();
			notifyObservers(gameEvent);
		}
	}

	public Player[] getPlayers() {
		return players;
	}
	
}