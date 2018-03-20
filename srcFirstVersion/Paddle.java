import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.*;

public class Paddle extends Rectangle implements Colorable 
{
	private Color color;
	private Color INACTIVE_COLOR = Color.DARK_GRAY;
	private boolean active;
	
	public Paddle(int x, int y, int width, int height, boolean active, Color color)
	{
		// Initiate the rectangle
		super(x,y,width,height);
		
		this.setColor(color);
		
		this.active = active;
		
	}
	
	public Paddle(int x, int y, int width, int height){
		// Initiate the rectangle
		super(x,y,width,height)
		;
		//set false as default
		this.active = false;
		
		// Set to inactive color as default
		this.setColor(INACTIVE_COLOR);
	}
	
	/**
	 * Moves the paddle the the new position
	 * @param int
	 * @param int
	 */
	public void move(int dx, int dy)
	{
		if(active)
		{
			super.x += dx;
			super.y += dy;
		}
	}
	
	/**
	 * Deactivates the paddle and turns it into a wall
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void deactivate(int x, int y, int width, int height)
	{
		// Set size to gamePlan the gameplan size
		super.x = x;
		super.y = y;
		
		// Position deactived paddle
		super.width = width;
		super.height = height;
		
		// Set to inactive color
		this.setColor(INACTIVE_COLOR);
		
		//Set active as false
		this.active = false;
	}

	/**
	 * Sets the color of the paddle
	 * @param Color
	 */
	@Override
	public void setColor(Color color)
	{
		this.color = color;		
	}

	/**
	 * Returns the color of the paddle
	 * @return Color
	 */
	@Override
	public Color getColor()
	{
		return color;
	}
	public boolean isActive()
	{
		return active;
	}
}
 