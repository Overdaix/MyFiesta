package com.cincosolutions.myfiesta;

import com.cincosolutions.myfiesta.SimpleGestureFilter.SimpleGestureListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class SettingsActivity extends Activity implements SimpleGestureListener {
	private SimpleGestureFilter detector;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Full Screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_settings);

		// Detect touched area
		detector = new SimpleGestureFilter(this, this);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent me) {
		// Call onTouchEvent of SimpleGestureFilter class
		this.detector.onTouchEvent(me);
		return super.dispatchTouchEvent(me);
	}

	@Override
	public void onSwipe(int direction) {
		String str = "";

		switch (direction) {

		case SimpleGestureFilter.SWIPE_RIGHT:
			str = "Swipe Right";
			break;
		case SimpleGestureFilter.SWIPE_LEFT:
			str = "Swipe Left";

			Intent openEvents = new Intent(
					"com.cincosolutions.myfiesta.EVENTS");
			startActivity(openEvents);

			break;
		case SimpleGestureFilter.SWIPE_DOWN:
			str = "Swipe Down";
			break;
		case SimpleGestureFilter.SWIPE_UP:
			str = "Swipe Up";
			break;

		}
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDoubleTap() {
		Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
	}

	public void EventsAct(View v) {
		Intent openEvents = new Intent(
				"com.cincosolutions.myfiesta.EVENTS");
		startActivity(openEvents);
	}

	public void DrinksAct(View v) {
		Intent openDrinksMenu = new Intent(
				"com.cincosolutions.myfiesta.DRINKSMENU");
		startActivity(openDrinksMenu);
	}

}
