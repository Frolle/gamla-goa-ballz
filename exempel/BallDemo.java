import java.awt.*;
import java.awt.geom.*;

/**
 * Class BallDemo - provides two short demonstrations showing how to use the 
 * Canvas class. 
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class BallDemo   
{
    private Canvas myCanvas;

    private int xLenth = 500;
    private int yLenth = 500;
    private int wallThickness = 40;
    private int upBorder = wallThickness;
    private int downBorder = yLenth - wallThickness;
    private int rightBorder = xLenth - wallThickness; 
    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", xLenth, yLenth);
        myCanvas.setVisible(true);
    }
 
    /**
     * Demonstrate some of the drawing operations that are
     * available on a Canvas object.
     */
    public void drawDemo()
    {
        /*myCanvas.setFont(new Font("helvetica", Font.BOLD, 14));
        myCanvas.setForegroundColor(Color.red);

        myCanvas.drawString("We can draw text, ...", 20, 30);
        myCanvas.wait(1000);

        myCanvas.setForegroundColor(Color.black);
        myCanvas.drawString("...draw lines...", 60, 60);
        myCanvas.wait(500);
        myCanvas.setForegroundColor(Color.gray);
        myCanvas.drawLine(200, 20, 300, 50);
        myCanvas.wait(500);
        myCanvas.setForegroundColor(Color.blue);
        myCanvas.drawLine(220, 100, 370, 40);
        myCanvas.wait(500);
        myCanvas.setForegroundColor(Color.green);
        myCanvas.drawLine(290, 10, 320, 120);
        myCanvas.wait(1000);

        myCanvas.setForegroundColor(Color.gray);
        myCanvas.drawString("...and shapes!", 110, 90);
		*/
        myCanvas.setForegroundColor(Color.red);

        // the shape to draw and move
        int xPos = 0;
        Rectangle upRect = new Rectangle(0, 0, xLenth, wallThickness);
        Rectangle riRect = new Rectangle(rightBorder, 0, wallThickness, yLenth);
        Rectangle doRect = new Rectangle(0, downBorder, xLenth, wallThickness);
/*
        // move the rectangle across the screen
        for(int i = 0; i < 200; i ++) {
            myCanvas.fill(rect);
            myCanvas.wait(10);
            myCanvas.erase(rect);
            xPos++;
            rect.setLocation(xPos, 150);
        }
        */
        // at the end of the move, draw once more so that it remains visible
        myCanvas.fill(upRect);
        myCanvas.fill(riRect);
        myCanvas.fill(doRect);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, 16, Color.blue, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, 20, Color.red, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 && ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
        ball.erase();
        ball2.erase();
    }
}
