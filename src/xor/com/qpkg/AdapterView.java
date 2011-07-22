package xor.com.qpkg;


import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class AdapterView extends RelativeLayout 
{

	FriendInfo entry;
	QuickActionSampleAppActivity activity;
	Context context;
	
	
	public AdapterView(Context context, FriendInfo entry) 
	{
		super(context);
		this.context= context;
		this.entry = entry;
		
		this.setTag(entry);
		
		View view = inflate(context, R.layout.list, null);
		
		TextView _name = (TextView)view.findViewById(R.id.title);
		if(entry.getName() != null)
		{	
			_name.setText(Html.fromHtml(entry.getName()));
		}
				
		 ImageView _thumbnail = (ImageView)view.findViewById(R.id.thumbnail);
		 if(entry.getImage() != null)
		 {	 
			 _thumbnail.setBackgroundDrawable(entry.getImage());
		 }
		 else
		 {
			 _thumbnail.setBackgroundDrawable(null);
		 }
		 		 
		 addView(view);
				
	}
	
}

