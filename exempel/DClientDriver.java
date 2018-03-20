import java.io.*;

public class DClientDriver
{
	public static void main(String[] args)
	{
		DClient myClient = new DClient("80.216.105.36", 1234);
		String response = "";

		try
		{
			response = myClient.sendMessage("Hey Buddy!");
		} 
		catch (IOException ex)
		{
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			System.exit(1);
		}

		System.out.println(response);
	}
}