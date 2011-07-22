package xor.com.qpkg;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class QuickActionSampleAppActivity extends Activity
{
    	
	ListView resultPane;
	List<FriendInfo> list;
	CustomAdapter adapter;
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        resultPane = (ListView)findViewById(R.id.list);
        String friendList[] = {"Contact no.1","Contact no.2","Contact no.3","Contact no.4","Contact no.5","Contact no.6","Contact no.7","Contact no.8","Contact no.9","Contact no.10"};
        Drawable image = null;
        
        list = new ArrayList<FriendInfo>();
        for(int i=0;i<friendList.length;i++)
        {
        	list.add(new FriendInfo(friendList[i],image));
        }
                
        adapter = new CustomAdapter(this,list);
        resultPane.setAdapter(adapter);
               
        
    	final QuickActionIcons edit = new QuickActionIcons();; 
    	    	
        edit.setTitle("Edit");
        edit.setIcon(getResources().getDrawable(R.drawable.edit));
        edit.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v) 
        	{
        		Toast.makeText(QuickActionSampleAppActivity.this,"Edit Contact",Toast.LENGTH_SHORT).show();
        	}
    		
		
        });
        
               
        final QuickActionIcons call = new QuickActionIcons();;
        
        call.setTitle("Call");
        call.setIcon(getResources().getDrawable(R.drawable.call));
        call.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v) 
        	{
        		Toast.makeText(QuickActionSampleAppActivity.this,"Call Contact",Toast.LENGTH_SHORT).show();
        	}
    		
		
        });
        
        final QuickActionIcons send_data = new QuickActionIcons();;
        
        send_data.setTitle("Data Transfer");
        send_data.setIcon(getResources().getDrawable(R.drawable.bluetooth));
        send_data.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v) 
        	{
        		Toast.makeText(QuickActionSampleAppActivity.this,"Start Transfer of Data",Toast.LENGTH_SHORT).show();
        	}
    		
		
        });
        
    
    
    resultPane.setOnItemClickListener(new OnItemClickListener() 
    {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
			QuickActionBar qab = new QuickActionBar(view);
			
			qab.addItem(edit);
			qab.addItem(call);
			qab.addItem(send_data);
			qab.setAnimationStyle(QuickActionBar.GROW_FROM_LEFT);
			
			qab.show();
		}
    });	
 }  
	
}