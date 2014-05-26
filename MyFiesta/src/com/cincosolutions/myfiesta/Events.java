package com.cincosolutions.myfiesta;

import com.cincosolutions.myfiesta.SimpleGestureFilter.SimpleGestureListener;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

public class Events extends Activity  implements SimpleGestureListener{

	 SimpleGestureFilter detector;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eventsmenu);
		
		detector = new SimpleGestureFilter(this,this);
	}
	
	 @Override
	 public boolean dispatchTouchEvent(MotionEvent me){
        // Call onTouchEvent of SimpleGestureFilter class
         this.detector.onTouchEvent(me);
         return super.dispatchTouchEvent(me);
	 }

	@Override
	public void onSwipe(int direction) {
		String str = "";
	     switch (direction) {
			 case SimpleGestureFilter.SWIPE_RIGHT : str = "Swipe Right";
			 
			 break;
			 case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
			 	// Do nothing cause drinks are the most right.
			 	Intent openDrinksActivity = new Intent("com.cincosolutions.myfiesta.DRINKSMENU");
			 	startActivity(openDrinksActivity);
			  break;
			 case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
			  // Do nothing.
			  break;
			 case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
			  // Do nothing.
			 break;
		 }
	     //Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDoubleTap() {
		// TODO Auto-generated method stub
		
	}
}
