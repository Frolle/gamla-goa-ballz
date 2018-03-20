package pong.model;
import java.util.Observable;

import pong.controller.AI;
import pong.event.GameEventBall;
import pong.event.GameEventGameover;
import pong.event.GameEventPaddle;
import pong.event.GameEventPlayer;

/**
 * 
 * @author Group 4
 * @version 3.0
 * @since 2012-02-22
 */
public class GameModel extends Observable{

	private Player[] players;
	private GameEventPaddle gameEventPaddle;
	private GameEventBall gameEventBall;
	private GameEventPlayer gameEventPlayer;
	private GameEventGameover gameEventGameover;
	private int activePlayers;
	private boolean gameOn;

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
		String[] aiNames = new String[4];
		aiNames[0] = "ARTIFICIAL UNO";
		aiNames[1] = "ARTIFICIAL BILL";
		aiNames[2] = "ARTIFICIAL LEA";
		aiNames[3] = "ARTIFICIAL HÅKAN";
		Player[] players = new Player[index];
		{
			for(int i = 0; i < index; i++)
			{
				if ((playerNames[i] == null)||(playerNames[i].equals("")))
				{
					players[i] = new Player(aiNames[i], true);
					activePlayers--;
				}
				else
				{
					players[i] = new Player(playerNames[i], false);
				}
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
		if(paddleToMove < players.length )
		{
			gameEventPaddle = new GameEventPaddle();
			gameEventPaddle.setDx(paddleDx);
			gameEventPaddle.setDy(paddleDy);
			// ÔøΩndrat
			gameEventPaddle.setPlayerIndex(paddleToMove);
			setChanged();
			notifyObservers(gameEventPaddle);
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
		gameEventBall = new GameEventBall();
		gameEventBall.setDx(dx);
		gameEventBall.setDy(dy);
		setChanged();
		notifyObservers(gameEventBall);
	
	}

	/**
	 * Returns the name of a specific player using index;
	 * @param playerIndex
	 * @return String name of Player
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
			players[playerIndex].decrementLife();
			if((players[playerIndex].getLife() < 1) && (!players[playerIndex].isAI()))
			{
				activePlayers--;
				gameEventPlayer = new GameEventPlayer();
				gameEventPlayer.setPlayerDead();
				gameEventPlayer.setPlayerIndex(playerIndex);
				setChanged();
				notifyObservers(gameEventPlayer);
				if (activePlayers<2)
				{
					setGameOn(false);
					activePlayers = players.length;
					gameEventGameover = new GameEventGameover();
					gameEventGameover.setWinner(getWinner());
					gameEventGameover.setPlayers(players);
					gameEventGameover.setGameOver();
					setChanged();
					notifyObservers(gameEventGameover);
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
		if(!(players[playerIndex].isAI())){
			players[playerIndex].changeScore(playerScore);
			gameEventPlayer = new GameEventPlayer();
			gameEventPlayer.setPlayerIndex(playerIndex);
			gameEventPlayer.setPoints(players[playerIndex].getScore());
			gameEventPlayer.setScoreEvent();
			setChanged();
			notifyObservers(gameEventPlayer);
		}
	}
	
	/**
	 * Resets the game and sends a gameEventPlayer to the observers.
	 */
	public void resetGame()
	{
		for(int i = 0; i < players.length; i++)
		{
			players[i].resetPlayer();
			gameEventPlayer = new GameEventPlayer();
			gameEventPlayer.setPlayerIndex(i);
			gameEventPlayer.setPoints(players[i].getScore());
			gameEventPlayer.setScoreEvent();
			activePlayers = players.length;
			setGameOn(true);
			setChanged();
			notifyObservers(gameEventPlayer);
		}
	}
	/**
	 * Returns the Player array
	 * @return Array of Players
	 */
	public Player[] getPlayers() {
		return players;
	}
	/**
	 * Returns true if game is on
	 * @return gameOn
	 */
	public boolean isGameOn() {
		return gameOn;
	}
	/**
	 * Sets gameOn to true or false
	 * @param gameOn
	 */
	public void setGameOn(boolean gameOn) {
		this.gameOn = gameOn;
	}
	
}