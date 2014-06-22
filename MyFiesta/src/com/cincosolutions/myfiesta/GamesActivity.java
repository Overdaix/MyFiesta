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

public class GamesActivity extends Activity implements SimpleGestureListener {
	private SimpleGestureFilter detector;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Full Screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_games);

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

			Intent openDrinksMenu = new Intent(
					"com.cincosolutions.myfiesta.DRINKSMENU");
			startActivity(openDrinksMenu);

			break;
		case SimpleGestureFilter.SWIPE_LEFT:
			str = "Swipe Left";
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

	public void SettingsAct(View v) {
		Intent openSettingsActivity = new Intent(
				"com.cincosolutions.myfiesta.SETTINGSACTIVITY");
		startActivity(openSettingsActivity);
	}

	public void DrinksAct(View v) {
		Intent openDrinksMenu = new Intent(
				"com.cincosolutions.myfiesta.DRINKSMENU");
		startActivity(openDrinksMenu);
	}

	public void AnimalGameAct(View v) {
		Intent openAnimalGame = new Intent(
				"com.cincosolutions.myfiesta.ANIMALGAMEACTIVITY");
		startActivity(openAnimalGame);
	}

	public void AssholeGameAct(View v) {
		Intent openAssholeGame = new Intent(
				"com.cincosolutions.myfiesta.ASSHOLEGAMEACTIVITY");
		startActivity(openAssholeGame);
	}

	public void AvalancheGameAct(View v) {
		Intent openAvalancheGame = new Intent(
				"com.cincosolutions.myfiesta.AVALANCHEGAMEACTIVITY");
		startActivity(openAvalancheGame);
	}

	public void BasketballGameAct(View v) {
		Intent openBasketballGame = new Intent(
				"com.cincosolutions.myfiesta.BASEKETBALLGAMEACTIVITY");
		startActivity(openBasketballGame);
	}

	public void BeerpongGameAct(View v) {
		Intent openBeerpongGame = new Intent(
				"com.cincosolutions.myfiesta.BEERPONGGAMEACTIVITY");
		startActivity(openBeerpongGame);
	}

	public void BoxingGameAct(View v) {
		Intent openBoxingGame = new Intent(
				"com.cincosolutions.myfiesta.BOXINGGAMEACTIVITY");
		startActivity(openBoxingGame);
	}

	public void BusdriverGameAct(View v) {
		Intent openBusdriverGame = new Intent(
				"com.cincosolutions.myfiesta.BUSDRIVERGAMEACTIVITY");
		startActivity(openBusdriverGame);
	}

	public void FlipsipstripGameAct(View v) {
		Intent openFlipsipstripGame = new Intent(
				"com.cincosolutions.myfiesta.FLIPSIPSTRIPGAMEACTIVITY");
		startActivity(openFlipsipstripGame);
	}

	public void FubarGameAct(View v) {
		Intent openFubarGame = new Intent(
				"com.cincosolutions.myfiesta.FUBARGAMEACTIVITY");
		startActivity(openFubarGame);
	}

	public void FuzyduckGameAct(View v) {
		Intent openFuzyduckGame = new Intent(
				"com.cincosolutions.myfiesta.FUZYDUCKGAMEACTIVITY");
		startActivity(openFuzyduckGame);
	}

	public void KingsGameAct(View v) {
		Intent openKingsGame = new Intent(
				"com.cincosolutions.myfiesta.KINGSGAMEACTIVITY");
		startActivity(openKingsGame);
	}

	public void PowerhourGameAct(View v) {
		Intent openPowerhourGame = new Intent(
				"com.cincosolutions.myfiesta.POWERHOURACTIVITY");
		startActivity(openPowerhourGame);
	}

	public void QuartersGameAct(View v) {
		Intent openQuartersGame = new Intent(
				"com.cincosolutions.myfiesta.QUARTERSGAMEACTIVITY");
		startActivity(openQuartersGame);
	}
	
	public void kings(View v) {
		Intent kings = new Intent(
				"com.cincosolutions.myfiesta.KINGSGAMEACTIVITY");
		startActivity(kings);
	}

	public void SpinnersGameAct(View v) {
		Intent openSpinnersGame = new Intent(
				"com.cincosolutions.myfiesta.SPINNERSGAMEACTIVITY");
		startActivity(openSpinnersGame);
	}

	public void ThreemanGameAct(View v) {
		Intent openThreemanGame = new Intent(
				"com.cincosolutions.myfiesta.THREEMANGAMEACTIVITY");
		startActivity(openThreemanGame);
	}

	public void ThumperGameAct(View v) {
		Intent openThumperGame = new Intent(
				"com.cincosolutions.myfiesta.THUMPERGAMEACTIVITY");
		startActivity(openThumperGame);
	}
	
	public void CalculatorGameAct(View v) {
		Intent openCalculatorGame = new Intent(
				"com.cincosolutions.myfiesta.CALCULATORGAMEACTIVITY");
		startActivity(openCalculatorGame);
	}
	
	public void ArroganceGameAct(View v) {
		Intent openArroganceGame = new Intent(
				"com.cincosolutions.myfiesta.ARROGANCEGAMEACTIVITY");
		startActivity(openArroganceGame);
	}

	/*
	 * 
	 * public void countgame(View v) { Intent countgame = new Intent(
	 * "com.cincosolutions.myfiesta.COUNTDOWNGAME"); startActivity(countgame); }
	 * 
	 * public void powerhour(View v) { Intent powerhour = new Intent(
	 * "com.cincosolutions.myfiesta.POWERHOURACTIVITY");
	 * startActivity(powerhour); }
	 * 
	 * public void calculate(View v) { Intent calculate = new Intent(
	 * "com.cincosolutions.myfiesta.CALCULATEINSTRUCTION");
	 * startActivity(calculate); }
	 */

}
