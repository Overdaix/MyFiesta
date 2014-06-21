package com.cincosolutions.myfiesta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class KingsGameActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Full Screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_kings_game);
	}
	
	public void start(View v) {
		Intent king = new Intent(
				"com.cincosolutions.myfiesta.KINGSGAME");
		startActivity(king);
	}
	
	public void backbtnGame(View v) {
		Intent back = new Intent(
				"com.cincosolutions.myfiesta.GAMESACTIVITY");
		startActivity(back);
	}

}
