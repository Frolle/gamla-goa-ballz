import java.io.*;
import java.net.*;

public class DClient
{
	// Some properties
	private String server;
	private int port = -1;
	private Socket client = null;
	private PrintWriter outStream = null;
	private BufferedReader inStream = null;
	
	// Constructor with port
	public DClient(int port)
	{
		server = "127.0.0.1";
		port = port;
	}
	
	// Constructor with server and port
	public DClient(String server, int port)
	{
		this.server = server;
		this.port = port;
	}
	
	// Sends the message
	public String sendMessage(String message) throws IOException
	{
		// Setup a client
		InetAddress address = InetAddress.getByName(server);
		//client = new Socket(_server, _port);
		client = new Socket(address, port);
		
		// Set the outstream
		outStream = new PrintWriter(client.getOutputStream(), true);
		
		// Set the instrem
		inStream = new BufferedReader(
						new InputStreamReader(client.getInputStream())
					);
		
		// Send the message
		outStream.println(message);
		
		// Read the response
		String response = inStream.readLine();
		
		// Close up
		outStream.close();
		inStream.close();
		client.close();
		
		// Return the response to the caller
		return response;
	}
}