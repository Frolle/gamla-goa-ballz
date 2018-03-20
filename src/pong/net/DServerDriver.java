package pong.net;
/**
 * This class contains the main method for starting the PongServer. The
 * class needs to be started separately from the game on the server
 * that wants to be used.
 * @author Group 4
 * @version 1.0
 * @since 2012-02-20
 *
 */
public class DServerDriver
{
	/**
	 * Runs when the application starts and creates a PongServer on port 1234
	 * @param args
	 */
	public static void main(String[] args)
	{
		PongServer serverSocket = new PongServer(1234);
		System.out.println("Server is running");
		serverSocket.start();
	}
}