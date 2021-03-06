import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

/**
 * Frame with starting menu for Pong.
 * @author Gustaf, Patrik, Simon
 * @version 3.6
 */
public class GameMenu extends JPanel
{
	//Reference to mainPong.
	private MainPong mainPong;
	//For storing the buttons of GameMenu.
	private HashMap<String, JButton> buttonSet;
	//For storing the names of the players/name of the player.	
	private String[] playerNames;
	
	/**
	 * Constructor creates buttons and designs GameMenu.
	 * @param mainPong Reference to mainPong.
	 */
	public GameMenu(MainPong mainPong)
	{
		this.mainPong = mainPong;		
		createButtons();
		designGameMenu();		
	}	
		
	/**
	 * Design GameMenu and add buttons.
	 */
	private void designGameMenu()
	{									
		//Start menu
		
		//Create the center field to put in middle of GameMenu.
		JPanel centerField = new JPanel();
		centerField.setLayout(new GridLayout(5, 1, 3, 3));
		centerField.add(buttonSet.get("onePlayerButton"));
		centerField.add(buttonSet.get("twoPlayerButton"));
		centerField.add(buttonSet.get("threePlayerButton"));
		centerField.add(buttonSet.get("fourPlayerButton"));
		centerField.add(buttonSet.get("exitButton"));
		
		//Create Border layout in GameMenu... 
		this.setLayout(new BorderLayout());
		//...add centerField in CENTER area...
		this.add(centerField, BorderLayout.CENTER);
		//...add Welcome message in NORHT area.
		JTextArea welcomeMessage = new JTextArea("\n\tWelcome to Ballz!" +
					"\n\tBienvenido a Cojones!\n\tVšlkommen till Kulor!\n");
		welcomeMessage.setEditable(false);
		this.add(welcomeMessage, BorderLayout.NORTH);
		//.. and fix the design.
		this.add(Box.createVerticalStrut(80), BorderLayout.SOUTH);
		this.add(Box.createHorizontalStrut(80),BorderLayout.EAST);
		this.add(Box.createHorizontalStrut(80), BorderLayout.WEST);
		//Set maximum size for the menu		
		this.setPreferredSize(new Dimension(300, 300));
		this.setBackground(Color.white);
		
	}
	
	/**
	 * Create and add buttons in HashMap buttonSet.
	 * Adds actionListeners to buttons.
	 * Buttons: One Player, Two Players, Three Players, Four Players, OK, Exit.
	 */
	private void createButtons()
	{
		//Create a HashMap where buttons are stored.
		buttonSet = new HashMap<String, JButton>();
		
		//Create button and save it in a HashMap. Then add actionListener to button.
		buttonSet.put("onePlayerButton", new JButton("One Player"));
		buttonSet.get("onePlayerButton").addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						clickAPlayerButton(1);
					}
				});
		
		//Create button and save it in a HashMap. Then add actionListener to button.
		buttonSet.put("twoPlayerButton", new JButton("Two Players"));
		buttonSet.get("twoPlayerButton").addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						clickAPlayerButton(2);
					}
				});
		
		//Create button and save it in a HashMap. Then add actionListener to button.
		buttonSet.put("threePlayerButton", new JButton("Three Players"));
		buttonSet.get("threePlayerButton").addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						clickAPlayerButton(3);
					}
				});
		
		//Create button and save it in a HashMap. Then add actionListener to button.
		buttonSet.put("fourPlayerButton", new JButton("Four Players"));
		buttonSet.get("fourPlayerButton").addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						clickAPlayerButton(4);
					}
				});
				
		//Create button and save it in a HashMap. Then add actionListener to button.
		buttonSet.put("exitButton", new JButton("Exit"));
		buttonSet.get("exitButton").addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						clickExitButton();
					}
				});
	}
			
	/**
	 * Action event.
	 * Creates an array of Strings of the right size for playerNames.
	 * Asks for name of player/players.
	 * Then implements initGame method.
	 * @param amount Amount of players
	 */
	private void clickAPlayerButton(int amount)
	{
		//Creates an array of the right size to store player names in.
		playerNames = new String[amount];
		//Collect name/names
		for(int i = 0; i < amount; i++)
		{
			if (amount == 1)
			{
				//One player.
				playerNames[i] = JOptionPane.showInputDialog("Please enter your name");
			}else{
				//Multiple players.
				playerNames[i] =
					JOptionPane.showInputDialog("Please enter name of player " + (i+1));
			}						
		}
		initGame();
	}
	
	/**
	 * Action event.
	 * Will make mainPong end the game.
	 */	
	private void clickExitButton()
	{
		mainPong.exitGame();
	}
	
	/**
	 * Will makes mainPong start a game and sends along the list of players.
	 */
	private void initGame()
	{
		mainPong.initGame(playerNames);
	}	
}
