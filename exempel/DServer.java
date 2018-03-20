import java.io.*;
import java.net.*;

public class DServer extends Thread
{
	// Some property fields
	private int port = -1;
	private boolean isRunning = false;
	private ServerSocket server = null;
	private Socket client = null;
	private PrintWriter outStream = null;
	private BufferedReader inStream = null;

	// Constructors
	public DServer()
	{
	}

	public DServer(int port)
	{
		this.port = port;
	}

	// Get and Set port
	public int getPort()
	{
		return port;
	}

	protected void setPort(int port)
	{
		this.port = port;
	}

	// Get and Set isRunning
	public boolean isRunning()
	{
		return isRunning;
	}

	protected void setIsRunning(boolean isRunning)
	{
		this.isRunning = isRunning;
	}

	public String processMessage(String message)
	{
		return message + " Oh Hai!";
	}

	// Run the thread
	final public void run()
	{
		isRunning = true;

		// Try to create a server socket on the port
		try
		{
			server = new ServerSocket(port);
		} catch (IOException ex)
		{
			System.err.println(ex.getMessage());
			return;
		}

		while (isRunning)
		{
			// Wait for client to talk to us
			try
			{
				client = server.accept();
				System.out.println("Accepted connection");
			} catch (IOException ex)
			{
				System.err.println(ex.getMessage());
				return;
			}

			try
			{
				outStream = new PrintWriter(client.getOutputStream(), true);
				inStream = new BufferedReader(new InputStreamReader(client.getInputStream()));
				outStream.println(processMessage(inStream.readLine()));

			} catch (IOException ex)
			{
				System.err.println(ex.getMessage());
				return;
			}
		}

	}

}