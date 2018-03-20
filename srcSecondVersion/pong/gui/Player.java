package pong.gui;
/**
 * @author Grupp 4
 * @version 1.0
 * @since 2012-02-01
 */
public class Player {

	private int score;
	private String playerName;
	private int life;
	private final int MAXLIFE = 3;
	
	/**
	 * Constructor of this class
	 * 
	 * @param playerName Creating a Player with the designated name
	 */
	public Player(String playerName)
	{
		this.playerName = playerName;
		this.score = 0;
		this.life = MAXLIFE;
	}
	
	/**
	 * Accessor for score
	 * 
	 * @return The player's score
	 */
	public int getScore()
	{
		return score;
	}
	
	/**
	 * Accessor for the player's name
	 * 
	 * @return Name of player
	 */
	public String getName()
	{
		return playerName;
	}
	
	/**
	 * Changes the players score
	 * 
	 * @param points The number of points to add to the already existing score
	 */
	public void changeScore(int points)
	{
		score += points;
	}
	
	/**
	 * Increments the players's score
	 */
	public void incrementLife()
	{
		life++;
	}
	
	/**
	 * Decrements the player's score
	 */
	public void decrementLife()
	{
		life--;
	}
	
	public int getLife()
	{
		return life;
	}
	public void resetPlayer()
	{
		score = 0;
		life = MAXLIFE;
	}
}
