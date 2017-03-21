package com.example.mymap;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends Activity
{
	MapView mapView;
	 public void onCreate(Bundle savedInstanceState)
	 {
        super.onCreate(savedInstanceState);
        Log.d("oing","first");
        mapView=new MapView(this);
 		initView();
 		setContentView(mapView);
	 }
	protected void onResume()
	{
		// Create MapView
		initView();
		//Log.d("oing","first");

		//Log.d("oing","Second");
		// Never ever forget this :)
		super.onResume();
	}
	public void initView()
	{
		Log.d("oing","zero");
		mapView.Refresh();
		
	}

}
