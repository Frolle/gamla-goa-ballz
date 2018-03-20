package pong.gui;
import java.util.LinkedList;
import java.io.*;

import pong.net.PongClient;



public class HighScoreHandler 
{

	private LinkedList<String[]> highScoreList;
	private final int HIGHSCORELENGTH = 10;
	private final int PORT = 1234;
	private final String IP = "danielkvist.dnsd.me"; // "80.216.115.80";
	PongClient client;
	
	
	public HighScoreHandler() 
	{
		client = new PongClient(IP, PORT);
		highScoreList = new LinkedList<String[]>();
		readHighScore();
	}
	
	public LinkedList<String[]> getHighScoreList()
	{
		return highScoreList;
	}
	
	public void updateScore(Player[] players)
	{
		for (int i = 0; i < players.length; i++)
		{
			for (int j = 0; j < highScoreList.size(); j++)
			{
				if (!(players[i] instanceof AI) && players[i].getScore() >= new Integer(highScoreList.get(j)[1]))
				{
					String[] tempPlayer = new String[2];
					tempPlayer[0] = players[i].getName();
					
					Integer tempScore = new Integer(players[i].getScore());
					tempPlayer[1] = tempScore.toString(); 
					
					highScoreList.add(j, tempPlayer);
					highScoreList.removeLast();
					j = highScoreList.size();
				}
			}
		}
	 
		writeHighScore();
	}
	
	
	public void resetHighScore()
	{
		highScoreList = new LinkedList<String[]>();
		String[] temp = {"Fesk", "0"};
		
		for (int i = 0; i < HIGHSCORELENGTH; i++)
		{
			highScoreList.addFirst(temp);
		}
		
		writeHighScore();
	}

	
	private void writeHighScore()
	{
		client.writeHighScore(highScoreList);
	}

	private void readHighScore()
	{
		
		// Ask the client to get the list from the server
		highScoreList = client.readHighScore();
		
		// If the list doesn't exist, reset it and create it
		if(null == highScoreList)
		{
			resetHighScore();
		}			
	}
}
