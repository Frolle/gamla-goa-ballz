import java.util.LinkedList;
import java.io.*;



public class HighScoreHandler 
{

	private LinkedList<String[]> highScoreList = new LinkedList<String[]>();
	private final int HIGHSCORELENGTH = 10;

	public HighScoreHandler() 
	{
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
				if (players[i].getScore()>=new Integer(highScoreList.get(j)[1]))
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
		
	    /*for (int x = 0; x < highScoreList.size(); x++)
		{
			DEBUG
	    	System.out.println(highScoreList.get(x)[0] + " : " + highScoreList.get(x)[1]);
		}*/
	 
		writeHighScore();
	}
	
	
	public void resetHighScore()
	{
		highScoreList.clear();
		String[] temp = {"Fesk", "0"};
		for (int i = 0; i < HIGHSCORELENGTH; i++)
		{
			highScoreList.addFirst(temp);
		}
		writeHighScore();
	}

	
	private void writeHighScore()
	{
		try 
		{			
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("HighScore.Ballz")); 
			writer.writeObject(highScoreList);
		}
		catch(IOException e)
		{
			System.out.println("Something went wrong : "+e.getMessage());
		}
		
	}

	private void readHighScore()
	{
		try 
		{
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream("HighScore.Ballz"));
			highScoreList =(LinkedList<String[]>) reader.readObject();
		}
		catch(FileNotFoundException e) 
		{
			resetHighScore();
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("" + e.getMessage());
		}
		catch(IOException e) 
		{
			System.out.println("" + e.getMessage());
		}
			
	}
}
