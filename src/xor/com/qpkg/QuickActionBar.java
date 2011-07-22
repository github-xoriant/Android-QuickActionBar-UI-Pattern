package xor.com.qpkg;

import java.util.ArrayList;


import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuickActionBar extends NewPopUpWindow
{
	private final View root;
	private final ImageView arrowUp;
	private final ImageView arrowDown;
	private final Animation loadAnimation;
	private final LayoutInflater inflater;
	private final Context context;
	
	public static final int GROW_FROM_LEFT = 1;
	public static final int GROW_FROM_CENTER = 2;
		
	private int animationStyle;
	private boolean startAnimation;
	private ViewGroup itemArray;
	private ArrayList<QuickActionIcons> itemList;
	
	public QuickActionBar(View anchor) 
	{
        super(anchor);
        
        itemList	= new ArrayList<QuickActionIcons>();
		context		= anchor.getContext();
		inflater 	= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		root		= (ViewGroup) inflater.inflate(R.layout.popupmenu, null);
		
		arrowDown 	= (ImageView) root.findViewById(R.id.arrow_down);
		arrowUp 	= (ImageView) root.findViewById(R.id.arrow_up);
		
		setContentView(root);
		
		loadAnimation 	= AnimationUtils.loadAnimation(anchor.getContext(), R.anim.translation);
		
		loadAnimation.setInterpolator(new Interpolator() 
		{
			public float getInterpolation(float t) 
			{
	              // Pushes past the target area, then snaps back into place.
	                // Equation for graphing: 1.2-((x*1.6)-1.1)^2
				final float inner = (t * 1.55f) - 1.1f;
				
	            return 1.2f - inner * inner;
	        }
		});
	        
		itemArray 	= (ViewGroup) root.findViewById(R.id.itemList);
		animationStyle	= GROW_FROM_CENTER;
		startAnimation = true;
	}

	public void setAnimationStyle(int style) 
	{
		this.animationStyle = style;
	}
	
	public void addItem(QuickActionIcons action) 
	{
		itemList.add(action); 
	}
	
	public void show () 
	{
		preShow();

		int[] location 		= new int[2];
		
		anchor.getLocationOnScreen(location);

		Rect anchorRect 	= new Rect(location[0], location[1], location[0] + anchor.getWidth(), location[1] 
		                	+ anchor.getHeight());

		root.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		root.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		int rootWidth 		= root.getMeasuredWidth();
		int rootHeight 		= root.getMeasuredHeight();

		int screenWidth 	= windowManager.getDefaultDisplay().getWidth();
		int screenHeight 	= windowManager.getDefaultDisplay().getHeight();
		
		int xPos 			= (screenWidth - rootWidth) / 2;
		int yPos	 		= anchorRect.top - rootHeight;

		boolean popupOnTop		= true;
		
		// display popup at the bottom
		
		if (screenHeight - anchorRect.bottom > rootHeight) 
		{
			yPos 	= anchorRect.bottom;
			popupOnTop	= false;
		}

		displayDirectionChoice(((popupOnTop) ? R.id.arrow_down : R.id.arrow_up), anchorRect.centerX());
		
		setAnimationStyle(popupOnTop);
		
		setUpList();
		
		window.showAtLocation(this.anchor, Gravity.NO_GRAVITY, xPos, yPos);
		
		if (startAnimation) itemArray.startAnimation(loadAnimation);
	}

	
	private void setAnimationStyle(boolean popupOnTop) 
	{
		switch (animationStyle) 
		{
			case GROW_FROM_LEFT:		window.setAnimationStyle((popupOnTop) ? R.style.Animations_PopUpMenu : R.style.Animations_PopDownMenu);
										break;
					
			case GROW_FROM_CENTER:			window.setAnimationStyle((popupOnTop) ? R.style.Animations_GrowFromTop : R.style.Animations_GrowFromBottom);
										break;
					
		}
	}
	
	/* Display Choice of direction of pop up using appropriate arrows
	 * 
	 */
	
	private void displayDirectionChoice(int directionChoice, int requestedX) 
	{
        final View showArrow = (directionChoice == R.id.arrow_up) ? arrowUp : arrowDown;
        final View hideArrow = (directionChoice == R.id.arrow_up) ? arrowDown : arrowUp;

        final int arrowWidth = arrowUp.getMeasuredWidth();

        showArrow.setVisibility(View.VISIBLE);
        
        ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams)showArrow.getLayoutParams();
        
        param.leftMargin = requestedX - arrowWidth / 2;
      
        hideArrow.setVisibility(View.INVISIBLE);
    }
	
	private void setUpList() 
	{
		String title;
		Drawable icon;
		OnClickListener listener;
		int pos = 1;
		
		for (int i = 0; i < itemList.size(); i++) 
		{
			title 		= itemList.get(i).getTitle();
			icon 		= itemList.get(i).getIcon();
			listener	= itemList.get(i).getListener();
	
			LinearLayout actionHolder	= (LinearLayout) inflater.inflate(R.layout.quickactions, null);
			ImageView actionImage 			= (ImageView) actionHolder.findViewById(R.id.image);
			TextView actiontxt 			= (TextView) actionHolder.findViewById(R.id.caption);
			
			if (icon != null) 
			{
				actionImage.setImageDrawable(icon);
			} 
			else 
			{
				actionImage.setVisibility(View.GONE);
			}
			
			if (title != null) 
			{
				actiontxt.setText(title);
			} 
			else 
			{
				actiontxt.setVisibility(View.GONE);
			}
			
			if (listener != null) 
			{
				actionHolder.setOnClickListener(listener);
			}
			
			
			actionHolder.setFocusable(true);
			actionHolder.setClickable(true);
			 
			itemArray.addView(actionHolder, pos);
			
			pos++;
		}
	}
	
}
