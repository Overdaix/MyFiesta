package com.cincosolutions.myfiesta;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ArroganceGame extends Activity{
	
	int number = 0;
	ImageView ivDice;
	TextView tvText;
	Button bThrow, bNext;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arrogance_game);
		checkNumber();
		ivDice = (ImageView) findViewById(R.id.ivDice);
		tvText = (TextView) findViewById(R.id.tvText);
		bThrow = (Button) findViewById(R.id.bThrow);
		bNext = (Button) findViewById(R.id.bNext);
	}
	
	public void checkNumber() {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Choose a number");

		final EditText et_input = new EditText(this);
		et_input.setInputType(InputType.TYPE_CLASS_NUMBER);
		alert.setView(et_input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
			bNext.setVisibility(View.INVISIBLE);
			bThrow.setVisibility(View.VISIBLE);
			number = Integer.parseInt(et_input.getText().toString());
			if(number >= 7 || number <= 0){
				Toast.makeText(getApplicationContext(), "This number doesn't excist on a dice..",
						   Toast.LENGTH_SHORT).show();
				checkNumber();
			}
			
		  }
		});
		alert.show();
	}
	
	public void checkDice(View v){
		bThrow.setVisibility(View.INVISIBLE);
		bNext.setVisibility(View.VISIBLE);
		tvText.setVisibility(View.VISIBLE);
		int min = 1;
		int max = 6;
		Random r = new Random();
		final int dice = r.nextInt(max - min + 1) + min;
		
		//Get preferences
		SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
		boolean boAudio = app_preferences.getBoolean("booAudio", true);
		if(!boAudio){
			MediaPlayer mpDice = MediaPlayer.create(this, R.raw.dice);
			mpDice.start();
		}
		changeImage(dice);
		if(number == dice){
			tvText.startAnimation(AnimationUtils.loadAnimation(ArroganceGame.this, android.R.anim.slide_in_left));
			tvText.setText("Drink the shot(s)!");
		}else{
			tvText.startAnimation(AnimationUtils.loadAnimation(ArroganceGame.this, android.R.anim.slide_in_left));
			tvText.setText("Make a new shot!");
		}
	}
	
	public void changeImage(int dice){
		ivDice.setImageResource(getResources().getIdentifier("drawable/dice"+dice, null, getPackageName()));
	}
	
	public void nextPlayer(View v){
		checkNumber();
		tvText.setVisibility(View.INVISIBLE);
	}

}
