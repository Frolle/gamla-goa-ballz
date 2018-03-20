package pong.net;
import java.io.*;

public class DServerDriver
{
	public static void main(String[] args)
	{
		PongServer serverSocket = new PongServer(1234);
		System.out.println("Server is running");
		serverSocket.start();
	}
}