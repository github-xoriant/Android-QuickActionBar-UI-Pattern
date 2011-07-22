package xor.com.qpkg;

import java.util.List;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CustomAdapter extends BaseAdapter 
{
	private Context context;
	private List<FriendInfo> list;
		
	public CustomAdapter(Context context, List<FriendInfo> list)
	{
		this.context = context;
		this.list = list;
				
	}
	public int getCount() 
	{
		return list.size();
	}

	public Object getItem(int position) 
	{
		return list.get(position);
	}

	public long getItemId(int position) 
	{
		return position;
	}

	public View getView(int position, View view, ViewGroup viewGroup) 
	{
		FriendInfo entry = list.get(position);
		return new AdapterView(context,entry);	
		
	}
	
	
}

