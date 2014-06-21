package com.cincosolutions.myfiesta;

import com.cincosolutions.myfiesta.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class PowerHourActivity extends Activity  {
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Full Screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_power_hour);

	}


	public void start(View v) {
		Intent count = new Intent("com.cincosolutions.myfiesta.COUNTDOWNGAME");
		startActivity(count);
	}
	
	public void backbtnGame(View v) {
		Intent back = new Intent("com.cincosolutions.myfiesta.GAMESACTIVITY");
		startActivity(back);
	}
	

}