package com.example.mymap;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.TextView;
import android.content.res.*;

public class MapView extends View
{
	protected Context context;
	protected Paint bitmapPaint = new Paint();
	
	protected Bitmap bm01;
	protected Bitmap bm02;
	protected Bitmap bm03;
	protected Bitmap bm26;
	
	protected TextView oldTextView;
	protected TextView currentTextView;
	protected TextView originTextView;
	private VelocityTracker mVelocityTracker;//生命变量 
	
	protected PointD current;
	protected PointD old;
	protected PointD origin;
	protected PointD offset;
	//protected Bitmap bm01;
	//protected Bitmap bm26;
	
	public MapView(Context context)
	{
		super(context);
		this.context = context;
		current=new PointD();
		old=new PointD();
		offset=new PointD();
		origin=new PointD(20,20);
		oldTextView=(TextView)findViewById(R.id.old);
		currentTextView=(TextView)findViewById(R.id.current);
		originTextView=(TextView)findViewById(R.id.origin);
		
		ReadFileIn();
	}

	void ReadFileIn()
	{
		Resources res=getResources();
		//Log.d("oing","Second");
		int id01 =res.getIdentifier("map01", "drawable","com.example.mymap" ); //name:图片的名，defType：资源类型（drawable，string。。。），defPackage:工程的包名
		Drawable BM01=getResources().getDrawable(id01);
		Log.d("oing","Second");
		BitmapDrawable bd01 = (BitmapDrawable)BM01;
		Log.d("oing","Third");
		bm01 = bd01.getBitmap();
		Log.d("oing","Forth");
		
		int id02 =res.getIdentifier("map02","drawable","com.example.mymap" );//name:图片的名，defType：资源类型（drawable，string。。。），defPackage:工程的包名
		Drawable BM02=getResources().getDrawable(id02);
		BitmapDrawable bd02 = (BitmapDrawable)BM02;
		bm02 = bd02.getBitmap();
		
		int id03 =res.getIdentifier("map03", "drawable","com.example.mymap" ); //name:图片的名，defType：资源类型（drawable，string。。。），defPackage:工程的包名
		Drawable BM03=getResources().getDrawable(id03);
		BitmapDrawable bd03 = (BitmapDrawable)BM03;
		bm03 = bd03.getBitmap();
		
		int id26 =res.getIdentifier("map26","drawable","com.example.mymap" );//name:图片的名，defType：资源类型（drawable，string。。。），defPackage:工程的包名
		Drawable BM26=getResources().getDrawable(id26);
		BitmapDrawable bd26 = (BitmapDrawable)BM26;
		bm26 = bd26.getBitmap();
		
	}
	
	protected void onDraw(Canvas canvas)
	{
		int width= bm01.getWidth();
		int height=bm01.getHeight();
		//PointD offset=new PointD();

		canvas.drawBitmap(bm01, (float)(origin.x+offset.x), (float)(origin.y+offset.y), bitmapPaint);
		canvas.drawBitmap(bm02, (float)(origin.x+width+offset.x),(float)(origin.y+offset.y), bitmapPaint);
		canvas.drawBitmap(bm03, (float)(origin.x+offset.x), (float)(origin.y+height+offset.y), bitmapPaint);
		canvas.drawBitmap(bm26, (float)(origin.x+width+offset.x), (float)(origin.y+height+offset.y), bitmapPaint);
	}
	
	public void Refresh()
	{
		invalidate();
	}

	public boolean onTouchEvent(MotionEvent event)
	{
		int action = event.getAction();
		if (action == MotionEvent.ACTION_DOWN)
		{
			// Keep touch position for later use (dragging)
			old.x = (int) event.getX();
			old.y = (int) event.getY();
			return true;
		}
		else if (action == MotionEvent.ACTION_MOVE)
		{
			  
			  current = new PointD(event.getX(), event.getY());
			    //在onTouchEvent(MotionEvent ev)中 
			    if (mVelocityTracker == null) { 
			            mVelocityTracker = VelocityTracker.obtain();//获得VelocityTracker类实例 
			    } 
			    mVelocityTracker.addMovement(event);//将事件加入到VelocityTracker类实例中 
			    //判断当ev事件是MotionEvent.ACTION_UP时：计算速率 
			    VelocityTracker velocityTracker = mVelocityTracker;
			    velocityTracker.computeCurrentVelocity(50,5); //设置units的值为1000，意思为一秒时间内运动了多少个像素 
			    Log.d("oing","x:"+velocityTracker.getXVelocity());
			    Log.d("oing","y:"+velocityTracker.getYVelocity());
			    //double differX=current.x-old.x;
			    //double differY=current.y-old.y;
			    //if(differX>5)
			    //{
			    	offset.x=current.x-old.x+velocityTracker.getXVelocity();
			    //}
			    //else
			    //{
			    	//offset.x=old.x+velocityTracker.getXVelocity();
			    //}
			    //if(differY>5)
			    //{
			    	offset.y=current.y-old.y+velocityTracker.getYVelocity();
			    //}
			    //else
			    //{
			    	//offset.y=old.y+velocityTracker.getYVelocity();
			    //}
				
			invalidate(); // Causes the view to redraw itself

			return true;
		}
		else if(action==MotionEvent.ACTION_UP)
		{
			origin.x=origin.x+offset.x;
			origin.y=origin.y+offset.y;
		}

		return super.onTouchEvent(event);
	}
}
