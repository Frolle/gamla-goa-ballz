package pong;
import pong.event.*;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Shape;

import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import pong.event.GameChangerIcon;
import pong.event.GameEventBall;
import pong.event.GameEventGameover;
import pong.event.GameEventPaddle;
import pong.event.GameEventPlayer;
import pong.gui.graphics.Ball;
import pong.gui.graphics.Colorable;
import pong.gui.graphics.Paddle;
import pong.gui.menu.ButtonPanel;
import pong.gui.panel.HighScorePanel;
import pong.gui.panel.ScorePanel;


/**
 * This is a JPanel which implements Observer that contains 
 * the UI elements of the game. It also has a reference to 
 * the model for updating purposes. It has to implement 
 * the update method from the interface to be able to 
 * receive the notifyObservers event from the model.
 *
 */
public class GameView extends JPanel implements Observer 
{
	private int gamePlanSide;
	private int wallThickness;
	private int numberOfPlayers;
	private int paddleThickness;
	private int paddleLength;
	private int westMargin;
	private int eastMargin;
	private int northMargin;
	private int southMargin;
	private static double WALL_PERCENTAGE 	= 	0.015;
	public Color[] COLOR_ARRAY 				= 	new Color[] { new Color(30, 144, 255), 
																Color.PINK, 
																Color.YELLOW, 
																Color.GREEN };
	private GameChangerIcon gameChangerIcon;
	private GameModel gameModel;
	private ArrayList<Shape> shapeArray;
	private Ball ball;
	/*private GameChanger gameChanger;
	private GameChangerIcon gameChangerIcon;
	*/
	private Paddle[] paddles = new Paddle[4];
	private JTextField[] nameTextFields;
	private JTextField[] scoreTextFields;
	private JLabel winnerTextLabel;
	private HighScorePanel highScorePanel;

	private Controller controller;

	private ScorePanel scorePanel;
	private MainPong mainPong;
	
	/**
	 * Constructor that creates the controller and a game 
	 * view of the board, adds itself as an Observer to 
	 * the gameModel  and calls createGUI.
	 * @param numberOfPlayers 
	 * @param gameModel
	 */
	public GameView(int numberOfPlayers, GameModel gameModel, MainPong mainPong) 
	{
		this.gameModel = gameModel;
		this.numberOfPlayers = numberOfPlayers;
		this.controller = new Controller(this, gameModel);
		this.mainPong = mainPong;
		gameModel.addObserver(this);
		//gameChanger.addObserver(this);
		
	}

	public double getWallThickness() 
	{
		return (double) wallThickness;
	}
	
	/**
	 * Starts the game
	 */
	public void startGame()
	{
		/*int speed = (int) (gamePlanSide * 0.006);
		controller.setSpeed(speed, speed);*/
		controller.start(gamePlanSide);
	}
	
	/**
	 * Creates the specific components through use of method 
	 * calls such as createWalls()
	 * @param gamePlanSideOuter
	 */
	public void createGUI(int gamePlanSideOuter, int gamePlanMargin)
	{		
		// Set some basic measurement vars and initiate arrays
		setMargins();
		this.gamePlanSide = gamePlanSideOuter - northMargin - southMargin;
		this.wallThickness = (int)(gamePlanSideOuter * WALL_PERCENTAGE);
		this.paddleThickness = wallThickness;
		this.paddleLength = (int)(gamePlanSide * 0.2);
		this.nameTextFields = new JTextField[numberOfPlayers];
		this.scoreTextFields = new JTextField[numberOfPlayers];

		this.setBackground(Color.black);
		
		// Create a list that will hold all our graphical elements that need
		// to be drawn
		shapeArray = new ArrayList<Shape>(8);
		
		// Create the GUI.
		createLayout();
		createPaddles();
		createBall();
		
		// Add the key listener
		createListeners();
	}
	
	/**
	 * Helper function to createGUI() that sets the basic layout with a 
	 * floating panel on the left side of the main panel. The floating
	 * panel is the score panel.
	 */
	private void createLayout()
	{
		// Create a flow layout to be able to float a nested panel to the left
		FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
		fl.setHgap(0);
		fl.setVgap(0);
		
		// Set the flow layout to this panel
		this.setLayout(fl);
		
		// Create a wrapping container for the left panel
		JPanel leftContainer = new JPanel();
		leftContainer.setLayout(new BorderLayout(0, 0));
		leftContainer.setBackground(Color.BLACK);
		leftContainer.setBorder(new EmptyBorder(0, wallThickness, 0, wallThickness));
		
		// Create the winner text field
		winnerTextLabel = new JLabel();
		winnerTextLabel.setText("");
		winnerTextLabel.setBackground(Color.BLACK);
		winnerTextLabel.setForeground(Color.WHITE);
		
		// Create the button panel that's at the bottom of the left container
		ButtonPanel buttonPanel = new ButtonPanel(this);
			
		// Create a new panel that we will use as our score panel, it will be the
		// panel floating to the left
		scorePanel = new ScorePanel(mainPong.GAMEPLAN_X_MARGIN, 
									wallThickness, 
									numberOfPlayers);
		
		// Loop through players and create the fields in the score panel
		for(int i = 0; i < numberOfPlayers; i++)
		{
			// Create the textfields at index i, with the playerName, 
			// playerScore and in color at index i
			scorePanel.createTextField(i, 
										gameModel.getPlayerName(i), 
										0, 
										COLOR_ARRAY[i]);
		}
		
		// Add the components to the left container
		leftContainer.add(scorePanel, BorderLayout.NORTH);		
		leftContainer.add(winnerTextLabel, BorderLayout.CENTER);
		leftContainer.add(buttonPanel, BorderLayout.SOUTH);
		
		// Set size of the left container and add it to the view
		leftContainer.setPreferredSize(new Dimension(mainPong.GAMEPLAN_X_MARGIN, 
													gamePlanSide)); 
		this.add(leftContainer);
		

		
	}
	/**
	 * sets the margins depending on the platform
	 */
	private void setMargins()
	{
		Insets i = mainPong.getInsets();
		westMargin = i.left;
		eastMargin = i.right;
		northMargin = i.top;
		southMargin = i.bottom;
		
	}
	/**
	 * Creates the ball
	 */
	private void createBall()
	{
		// Create a new ball in the center of the gamePlan and add it to the shape array
		ball = new Ball((gamePlanSide / 2) + mainPong.GAMEPLAN_X_MARGIN , 
						(gamePlanSide) / 2, 
						wallThickness);
		
		shapeArray.add(ball);
	}
	
	/**
	 * Updates the score fields, is called from the update() method
	 * @param i
	 */
	private void updateScoreFields(int playerIndex, int points)
	{
		//TODO  Set player score into text field use Observer/observable strategy
		scorePanel.updateScoreFields(playerIndex, points);
	}

	/**
	 * Adds a key listener by registering a custom KeyDispatcher with the
	 * KeyboardFocusManager. The KeyDispatcher is an inner class to the GameView
	 */
	private void createListeners()
	{		
		// Get the systems keyboard manager
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		
		// Create a new key dispatcher that is defined as a custom inner class
		KeyDispatcher dispatcher = new KeyDispatcher();
		
		// Add our custom key event dispatcher to the systems keyboard focus manager
		manager.addKeyEventDispatcher(dispatcher);		
	}

	/**
	 * Creates and positions the paddles
	 */
	private void createPaddles()
	{
		for(int i = 0; i < 4; i++)
		{
			switch(i){
			case 0: 
				// Player one paddle (left)
				paddles[i] = new Paddle(mainPong.GAMEPLAN_X_MARGIN, 
										gamePlanSide / 2, 
										paddleThickness, 
										paddleLength, 
										true, 
										COLOR_ARRAY[i]);
				break;
			case 1: 
				// Player 2 paddle (right)
				paddles[i] = new Paddle(mainPong.GAMEPLAN_X_MARGIN + gamePlanSide - paddleThickness,
										gamePlanSide / 2 - (int)(paddles[0].getHeight()/2), 
										paddleThickness,
										paddleLength,
										true, 
										COLOR_ARRAY[i]);
				//TODO if one player is selected create AI
				if(i >= numberOfPlayers)
				{
					deActivatePaddle(i);
				}
				break;
			case 2: 
				// Player 3 paddle (bottom)
				
				paddles[i] = new Paddle(mainPong.GAMEPLAN_X_MARGIN + (gamePlanSide / 2), 
											gamePlanSide - paddleThickness, 
											paddleLength, 
											paddleThickness, 
											true, 
											COLOR_ARRAY[i]);
				if(i >= numberOfPlayers)
				{
					deActivatePaddle(i);
				}				
				break;
			case 3: 
				//Player 4 paddle (top)
				paddles[i] = new Paddle(mainPong.GAMEPLAN_X_MARGIN + (gamePlanSide / 2), 
											0, 
											paddleLength, 
											paddleThickness, 
											true, 
											COLOR_ARRAY[i]);
				if(i >= numberOfPlayers)
				{
					deActivatePaddle(i);
				}
				break;
			}

			shapeArray.add(paddles[i]);
		}


	}
	
	/**
	 * Returns the ball
	 * @return
	 */
	public Ball getBall()
	{
		return ball;
	}
	
	/**
	 * Returns the paddles array
	 */
	public Paddle[] getPaddles()
	{
		return paddles;
	}
	
	/**
	 * Updates the ball position when the view is updated
	 */
	private void updateBallPosition(GameEventBall gameEventBall)
	{
		int dx = gameEventBall.getDx();
		int dy = gameEventBall.getDy();
		ball.move(dx, dy);
		repaint();
	}
	
	/**
	 * Updates the paddle position when the view is updated
	 */
	private void updatePaddlePosition(GameEventPaddle gameEventPaddle)
	{
		
		int dx = gameEventPaddle.getDx();
		int dy = gameEventPaddle.getDy();
		int playerIndex = gameEventPaddle.getPlayerIndex();
		paddles[playerIndex].move(dx, dy);
		repaint();
	}
	

	/**
	 * Paints the shapes in the shape array list
	 */
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		for (Shape s : shapeArray)
		{
			Colorable c = (Colorable) s;
			g2.setColor(c.getColor());
			g2.fill(s);
		}
	}

	
	/**
	 * Called by the system when the model notifies it's observers
	 */
	@Override
	public void update(Observable observable, Object object) 
	{
		
		if(observable instanceof GameChangerHandler)
		{
			if(object instanceof GameChangerIcon)
			{
				gameChangerIcon = (GameChangerIcon)object;
				
				if(gameChangerIcon.getActive())
				{
					shapeArray.add(gameChangerIcon);
					validate();
					repaint();
				}
				else if(gameChangerIcon.getActive()!=true)
				{
					shapeArray.remove(gameChangerIcon);
				}
			}
			if(object instanceof GameEventBall)
			{
				GameEventBall gameEventBall = (GameEventBall)object;
				if(ball.getHeight()<gamePlanSide/3)
				ball.setSize(gameEventBall.getSize());
			}
		}
		
		if(observable instanceof GameModel)
		{
			if(object instanceof GameEventBall)
			{
				GameEventBall gameEventBall = (GameEventBall) object;
				updateBallPosition(gameEventBall);
			}
			else if(object instanceof GameEventPaddle)
				{
					GameEventPaddle gameEventPaddle = (GameEventPaddle) object;
					updatePaddlePosition(gameEventPaddle);
				}

			else if(object instanceof GameEventPlayer)	
			{
				GameEventPlayer gameEventPlayer = (GameEventPlayer) object;

				if (gameEventPlayer.getScoreEvent()==true)
				{
					updateScoreFields(gameEventPlayer.getPlayerIndex(), gameEventPlayer.getPoints());
				}
				else if (gameEventPlayer.getPlayerDead()==true)
				{
					deActivatePaddle(gameEventPlayer.getPlayerIndex());
				}
			}
			
			else if(object instanceof GameEventGameover)
			{
				GameEventGameover gameEventGameover = (GameEventGameover) object;
				
				if(gameEventGameover.getGameOver()==true)	
				{
					controller.stop();
					ball.setColor(Color.black);
					
					winnerTextLabel.setText("Winner is : " + gameEventGameover.getWinner() + "!");
					// Create highscore panel
					highScorePanel = new HighScorePanel(gameEventGameover.getPlayers());
					highScorePanel.setPreferredSize(new Dimension(gamePlanSide, gamePlanSide));
					highScorePanel.setBorder(new EmptyBorder(gamePlanSide / 4, 0, 0, 0));
					
					// Add the high score
					highScorePanel.setVisible(true);
					this.add(highScorePanel);
					
					validate();
				}

			}
		}
		
	}
	
	public void endGameToMenu()
	{
		//TODO Ends the game, called from the ButtonPanel
		// This method needs to call mainPong which then 
		// will show the player menu again.
		controller.stop();
		mainPong.makeNewGame();
	}
	
	public void rematch()
	{
		//TODO Resets player score
		gameModel.resetGame();
		
		//resetBall();  // kanske ej beh�vs
		//ball.setColor(Color.WHITE);
		if (highScorePanel != null)
		{
			this.remove(highScorePanel);
			highScorePanel = null;
		}
		winnerTextLabel.setText("");
		shapeArray.clear();
		createBall();
		createPaddles();
		revalidate();
		repaint();
		startGame();
	}

	public int getNumberOfPlayers()
	{
		// TODO Auto-generated method stub
		return numberOfPlayers;
	}
	
	public int getGamePlanSide()
	{
		return gamePlanSide;
	}
	
	public void resetBall()
	{
		ball.resetBall(mainPong.GAMEPLAN_X_MARGIN+(gamePlanSide) / 2, (gamePlanSide) / 2);
	}
	
	public void resetUNO(){
		paddles[1].resetUNO(mainPong.GAMEPLAN_X_MARGIN + gamePlanSide - paddleThickness,
				gamePlanSide / 2 - (int)(paddles[0].getHeight()/2));
	}
	
	/**
	 * Deactivates a paddle into a wall
	 * @param index tells wich paddle to deactivate
	 */
	private void deActivatePaddle(int index)
	{
		
			switch(index){
			case 0: 
				// Player one paddle (left)
				paddles[index].deactivate(mainPong.GAMEPLAN_X_MARGIN, 
										0, 
										wallThickness, 
										gamePlanSide);
				break;
			case 1: 
				// Player 2 paddle (right)
				paddles[index].deactivate(mainPong.GAMEPLAN_X_MARGIN + gamePlanSide - wallThickness, 
										0, 
										wallThickness, 
										gamePlanSide);
				break;
			case 2: 
				// Player 3 paddle (bottom)
				paddles[index].deactivate(mainPong.GAMEPLAN_X_MARGIN, 
										gamePlanSide - wallThickness, 
										gamePlanSide, 
										wallThickness);
				break;
			case 3: 
				//Player 4 paddle (top)
				paddles[index].deactivate(mainPong.GAMEPLAN_X_MARGIN, 
											0, 
											gamePlanSide, 
											wallThickness);
				break;
		}
	}
	
	/**
	 * Inner class to handle the key presses
	 *
	 */
	public class KeyDispatcher implements KeyEventDispatcher
	{
		@Override
		public boolean dispatchKeyEvent(KeyEvent e)
		{
			if (e.getID() == KeyEvent.KEY_RELEASED) 
			{
				switch(e.getKeyCode())
				{
					case KeyEvent.VK_Q:
						controller.calculatePaddleAction(controller.PLAYER_ONE_PADDLE_ACTION_UP);
						break;
					case KeyEvent.VK_A:
						controller.calculatePaddleAction(controller.PLAYER_ONE_PADDLE_ACTION_DOWN);
						break;
					case KeyEvent.VK_UP:
						controller.calculatePaddleAction(controller.PLAYER_TWO_PADDLE_ACTION_UP);
						break;
					case KeyEvent.VK_DOWN:
						controller.calculatePaddleAction(controller.PLAYER_TWO_PADDLE_ACTION_DOWN);
						break;
					case KeyEvent.VK_K:
						controller.calculatePaddleAction(controller.PLAYER_THREE_PADDLE_ACTION_UP);
						break;
					case KeyEvent.VK_L:
						controller.calculatePaddleAction(controller.PLAYER_THREE_PADDLE_ACTION_DOWN);
						break;
					case KeyEvent.VK_F:
						controller.calculatePaddleAction(controller.PLAYER_FOUR_PADDLE_ACTION_UP);
						break;
					case KeyEvent.VK_G:
						controller.calculatePaddleAction(controller.PLAYER_FOUR_PADDLE_ACTION_DOWN);
						break;
				}
				
	        } 
	        return false;
		}

	}
	public int[] getCenter()
	{
		int[] hejs = new int[2];
		hejs[0] = mainPong.GAMEPLAN_X_MARGIN+(gamePlanSide) / 2;
		hejs[1] = (gamePlanSide) / 2;
		return hejs;
	}

	public GameChangerIcon getGameChangerIcon() {
		return gameChangerIcon;
	}
	
}
