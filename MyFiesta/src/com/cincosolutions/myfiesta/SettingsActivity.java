package com.cincosolutions.myfiesta;

import com.Wsdl2Code.WebServices.WebService1.WebService1;
import com.cincosolutions.myfiesta.SimpleGestureFilter.SimpleGestureListener;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class SettingsActivity extends Activity implements SimpleGestureListener {
	private SimpleGestureFilter detector;
	CheckBox cbAudio, cbNotif;
	Button btReset;
	
	
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
		
		//Get preferences
		SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);

		

		boolean boAudio = app_preferences.getBoolean("booAudio", true);
		boolean boNotifi = app_preferences.getBoolean("booNotif", true);

		//Checkboxes and buttons
		final CheckBox cbAudio = (CheckBox) findViewById(R.id.checkAudio);
		final CheckBox cbNotif = (CheckBox) findViewById(R.id.checkNotif);
		Button btReset = (Button) findViewById(R.id.btReset);
		
		if(!boAudio){
			cbAudio.setChecked(true);
		}
		if(!boNotifi){
			cbNotif.setChecked(true);
		}
		
		final SharedPreferences.Editor editor = app_preferences.edit();
		
		cbAudio.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(!cbAudio.isChecked()){
					editor.putBoolean("booAudio", true);
					editor.commit();
					cbAudio.setChecked(false);
				} else {
					editor.putBoolean("booAudio", false);
					editor.commit();
					cbAudio.setChecked(true);
				}
			}
		});	
		
		cbNotif.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(!cbNotif.isChecked()){
					editor.putBoolean("booNotif", true);
					editor.commit();
					cbNotif.setChecked(false);
				} else {
					editor.putBoolean("booNotif", false);
					editor.commit();
					cbNotif.setChecked(true);
				}
			}
		});	
		
		btReset.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				cbAudio.setChecked(false);
				cbNotif.setChecked(false);
				editor.putBoolean("booAudio", false);
				editor.putBoolean("booNotif", false);
				editor.commit();
			}
		});	
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
					"com.cincosolutions.myfiesta.DRINKSMENU");
			startActivity(openEvents);
			
			break;
		case SimpleGestureFilter.SWIPE_DOWN:
			str = "Swipe Down";
			break;
		case SimpleGestureFilter.SWIPE_UP:
			str = "Swipe Up";
			break;

		}
		//Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDoubleTap() {
		//Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
	}

	public void GamesAct(View v) {
		Intent openGamesActivity = new Intent(
				"com.cincosolutions.myfiesta.GAMESACTIVITY");
		startActivity(openGamesActivity);
	}

	public void DrinksAct(View v) {
		Intent openDrinksMenu = new Intent(
				"com.cincosolutions.myfiesta.DRINKSMENU");
		startActivity(openDrinksMenu);
	}

}
