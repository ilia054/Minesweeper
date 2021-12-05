package mines;

import javafx.scene.control.Button;

public class SubButton extends Button {//in order to save our button location we will create a sub Button class
	private int x,y;
	public SubButton (int x,int y)// the location of the button
	{	super(".");
		this.x=x;
		this.y=y;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	

}
