package com.cincosolutions.myfiesta;

import com.cincosolutions.myfiesta.SimpleGestureFilter.SimpleGestureListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class SpinnersGameActivity extends Activity implements
		SimpleGestureListener {
	private SimpleGestureFilter detector;
	private static final int ACTIVITY_EDIT = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Full Screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_spinners_game);

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
		// String str = "";

		switch (direction) {

		case SimpleGestureFilter.SWIPE_RIGHT:
			// str = "Swipe Right";

			Intent openGamesActivity = new Intent(
					"com.cincosolutions.myfiesta.GAMESACTIVITY");
			startActivityForResult(openGamesActivity, ACTIVITY_EDIT);
			overridePendingTransition(R.anim.right_in, R.anim.right_out);

			break;
		case SimpleGestureFilter.SWIPE_LEFT:
			// str = "Swipe Left";

			break;
		case SimpleGestureFilter.SWIPE_DOWN:
			// str = "Swipe Down";
			break;
		case SimpleGestureFilter.SWIPE_UP:
			// str = "Swipe Up";
			break;

		}
		// Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDoubleTap() {
		// Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
	}

	public void GamesAct(View v) {
		Intent openGamesActivity = new Intent(
				"com.cincosolutions.myfiesta.GAMESACTIVITY");
		startActivityForResult(openGamesActivity, ACTIVITY_EDIT);
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}

	public void DrinksAct(View v) {
		Intent openDrinksMenu = new Intent(
				"com.cincosolutions.myfiesta.DRINKSMENU");
		startActivityForResult(openDrinksMenu, ACTIVITY_EDIT);
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}

	public void SettingsAct(View v) {
		Intent openSettingsActivity = new Intent(
				"com.cincosolutions.myfiesta.SETTINGSACTIVITY");
		startActivityForResult(openSettingsActivity, ACTIVITY_EDIT);
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}

	public void backbtnGame(View v) {
		Intent back = new Intent("com.cincosolutions.myfiesta.GAMESACTIVITY");
		startActivityForResult(back, ACTIVITY_EDIT);
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}

}
