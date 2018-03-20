package pong.event;
import java.awt.Color;
import java.awt.Rectangle;
import pong.gui.graphics.*;


public class GameChangerIcon extends Rectangle implements Colorable{
	private Color color;
		
	private static int width = 40;
	private static int height = 40;
	private boolean isActive = false;
	
	public GameChangerIcon(int x, int y, Color color)
	{
		// Initiate the rectangle
		super((x-width/2),(y-height/2),width,height);
		this.setColor(color);
	}

	@Override
	public void setColor(Color color) {
		this.color=color;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}
	public boolean getActive()
	{
		return isActive;
	}
	public void setActive(boolean setState)
	{
		this.isActive=setState;
	}
}
