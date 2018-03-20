package pong.gui.graphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;



public class Ball extends Ellipse2D.Double implements Colorable
{

	private Color color;

	public Ball(double centerX, double centerY, double radius)
	{
		super(centerX - radius, centerY - radius, radius, radius);	
		color = Color.WHITE;
	}
	
	public void move(double dx, double dy)
	{
		this.x += dx;
		this.y += dy;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public Color getColor()
	{
		return color;
	}
	public void resetBall(int x, int y)
	{
		this.x = x;
		this.y = y;
		
	}
	public double getDiameter()
	{
		return this.height;
	}
	public void setSize(double sizeFactor)
	{
		this.height *= sizeFactor;
		this.width *= sizeFactor;
	}
}
