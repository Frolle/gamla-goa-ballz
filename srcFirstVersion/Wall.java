
import java.awt.*;
import javax.swing.*;

public class Wall extends Rectangle implements Colorable
{

	private boolean active;
	private Color color;
	
	
	public Wall(int x, int y, int w, int h)
	{
		super(x, y, w, h);
		color = Color.DARK_GRAY;
		active = true;									// väggen sätts som aktiv default.
	}
	
	public void activate()
	{
		active = true;
	}
	
	public void deactivate()
	{
		active = false;
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	public void moveX(int dx)
	{
		x += dx;
	}
	
	/**
	 * 
	 * @param dy
	 */
	public void moveY(int dy)
	{
		y += dy;
	}
	
	/**
	 * Sets the color of the paddle
	 * @param Color
	 */
	public void setColor(Color color)
	{
		if(active)
		{
			this.color = color;
		}
	}
	
	/**
	 * Returns the color of the paddle
	 * @return Color
	 */
	public Color getColor()
	{
		return color;
	}
}