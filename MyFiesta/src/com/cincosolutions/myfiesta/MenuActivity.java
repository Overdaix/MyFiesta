package com.cincosolutions.myfiesta;

import com.cincosolutions.myfiesta.SimpleGestureFilter.SimpleGestureListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MenuActivity extends Activity implements SimpleGestureListener{

	 SimpleGestureFilter detector;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Full Screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	
		setContentView(R.layout.activity_menu);
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
			 	Intent openEventsActivity = new Intent("com.cincosolutions.myfiesta.EVENTSMENU");
			 	startActivity(openEventsActivity);
			 	
			 break;
			 case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
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