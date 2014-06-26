package com.cincosolutions.myfiesta;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BOgame extends Activity{
	
	Button bBlue, bOrange;
	ImageView ivCard;
	
	boolean first = false;
	
	ArrayList<String> cards = new ArrayList<String>();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bogame_game);
		
		bBlue = (Button) findViewById(R.id.bBlue);
		bOrange = (Button) findViewById(R.id.bOrange);
		ivCard = (ImageView) findViewById(R.id.ivCard);
	}
	
	public void orange(View v){
		int min = 1;
		int max = 52;

		Random r = new Random();
		final int card = r.nextInt(max - min + 1) + min;
		new CountDownTimer(1450, 1000) {
			 public void onTick(long millisUntilFinished) {
			 }
			
			 public void onFinish() {
				 changeimage(card);
				 checkOrange(card);
				 first = true;
			 }
			}
			.start();
		if(!cards.contains("card" + card)){
			MediaPlayer mpCard = MediaPlayer.create(this, R.raw.card);
			if(first == true){
				ivCard.startAnimation(AnimationUtils.loadAnimation(BOgame.this, android.R.anim.slide_out_right));
				ivCard.setVisibility(View.INVISIBLE);
				bBlue.setVisibility(View.INVISIBLE);
				bOrange.setVisibility(View.INVISIBLE);
			}
			SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
			boolean boAudio = app_preferences.getBoolean("booAudio", true);
			if(!boAudio){
				mpCard.start();
			}
			cards.add("card" + card);
		}else{
			orange(v);
		}
	}
	
	public void blue(View v){
		int min = 1;
		int max = 52;

		Random r = new Random();
		final int card = r.nextInt(max - min + 1) + min;
		new CountDownTimer(1450, 1000) {
			 public void onTick(long millisUntilFinished) {
			 }
			
			 public void onFinish() {
				 changeimage(card);
				 checkBlue(card);
				 first = true;
			 }
			}
			.start();
		if(!cards.contains("card" + card)){
			//Play sound
			MediaPlayer mpCard = MediaPlayer.create(this, R.raw.card);
			//If its not the first card (first = true)
			if(first == true){
				ivCard.startAnimation(AnimationUtils.loadAnimation(BOgame.this, android.R.anim.slide_out_right));
				ivCard.setVisibility(View.INVISIBLE);
				bBlue.setVisibility(View.INVISIBLE);
				bOrange.setVisibility(View.INVISIBLE);
			}
			//If sound is not off (Options)
			SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
			boolean boAudio = app_preferences.getBoolean("booAudio", true);
			if(!boAudio){
				mpCard.start();
			}
			cards.add("card" + card);
		}else{
			blue(v);
		}
	}
	
	public void checkBlue(int card){
		if(card == 1 || card == 4 || card == 5 || card == 8 || card == 9
				|| card == 12 || card == 13 || card == 16 || card == 17
				|| card == 20 || card == 21 || card == 24 || card == 25
				|| card == 28 || card == 29 || card == 32 || card == 33
				|| card == 36 || card == 37 || card == 40 || card == 41
				|| card == 44 || card == 45 || card == 46 || card == 49
				|| card == 52){
			right();
		}else{
			wrong();
		}
	}
	
	public void checkOrange(int card){
		if(card == 2 || card == 3 || card == 6 || card == 7 || card == 10
				|| card == 11 || card == 14 || card == 15 || card == 18
				|| card == 19 || card == 22 || card == 23 || card == 26
				|| card == 27 || card == 30 || card == 31 || card == 34
				|| card == 35 || card == 38 || card == 39 || card == 42
				|| card == 43 || card == 47 || card == 48 || card == 50
				|| card == 51){
			right();
		}else{
			wrong();
		}
	}
	
	public void wrong(){
		Toast.makeText(getApplicationContext(), "Drink!",
				   Toast.LENGTH_SHORT).show();
	}
	
	public void right(){
		Toast.makeText(getApplicationContext(), "Next!",
				   Toast.LENGTH_SHORT).show();
	}
	
	public void changeimage(int card){
		ivCard.setVisibility(View.VISIBLE);
		bBlue.setVisibility(View.VISIBLE);
		bOrange.setVisibility(View.VISIBLE);
		ivCard.setImageResource(getResources().getIdentifier("drawable/card"+card, null, getPackageName()));
	}

}
