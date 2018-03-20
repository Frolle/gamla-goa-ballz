package pong.event;
import pong.gui.Player;


public class GameEventGameover {
	
	private boolean gameOverCheck = false;
	private Player[] players;
	private String winner;
	
	public GameEventGameover() {
		// TODO Auto-generated constructor stub
	}

	public void setGameOver()
	{
		gameOverCheck = true;
	}
	
	public boolean getGameOver()
	{
		return gameOverCheck;
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
