package pong.controller;
import pong.view.graphics.*;


/**  
 * A class that contains various algorithms to simulate
 * an AI controlling the paddle. Uses the Controller
 * to move the AI's paddle.
 * @author Grupp 4
 * @version 2.0
 * @since 2012-02-24
 */


public class AI{
	
	private Controller controller;

	
	
	public AI(Controller controller){
		this.controller = controller;
	}
	/**
	 * Calculates the next move of the AI´s paddle depending on which paddle is sent.
	 * @param paddle
	 * @param ball
	 * @param paddleIndex
	 */
	public void calculateNextAiMove(Paddle paddle, Ball ball, int paddleIndex)
	{
		int paddleUP = paddleIndex*2+1;
		int paddleDOWN = paddleIndex*2+2;
		
		if (paddleIndex==0||paddleIndex==1)
		{
			if (ball.getCenterY() > paddle.getCenterY() + paddle.height/3)
			{
				controller.calculatePaddleAction(paddleDOWN);	
			}
			else if (ball.getCenterY() < paddle.getCenterY() - paddle.height/3)
			{
				controller.calculatePaddleAction(paddleUP);
			}
		}
		else if (paddleIndex==2||paddleIndex==3)
		{
			if (ball.getCenterX() > paddle.getCenterX()+paddle.width/3)
			{
				controller.calculatePaddleAction(paddleDOWN);	
			}
			else if (ball.getCenterX() < paddle.getCenterX()-paddle.width/3)
			{
				controller.calculatePaddleAction(paddleUP);
			}
		}
	}
	
	
}
