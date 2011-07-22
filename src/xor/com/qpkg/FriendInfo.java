package xor.com.qpkg;

import android.graphics.drawable.Drawable;


public class FriendInfo 
{
	
	private String name;
	private Drawable image;
		// Constructor for the SearchInfo class
	public FriendInfo(String n,Drawable img) 
    {
		super();
		this.name = n;
		this.image = img;
    }
	
	
	
	// Getter and setter methods for all the fields.
	// Though you would not be using the setters for this example,
	// it might be useful later.
	
		
	public String getName() 
	{
		return name;
	}

	public void setName(String n) 
	{
		this.name = n;
	} 

	public Drawable getImage()
	{
		return image;
	}
	
	public void setImage(Drawable image)
	{
		this.image = image;
	}
}


