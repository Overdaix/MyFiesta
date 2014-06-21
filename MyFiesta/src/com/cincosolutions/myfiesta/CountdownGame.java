package com.cincosolutions.myfiesta;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class CountdownGame extends Activity {
	
TextView mTextField, mTextField2, mTextField3, mTextField5;
	
int drinkCounter = 0;
long sec = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.countdown_game);
		
		mTextField = (TextView) findViewById(R.id.mTextField);
		mTextField2 = (TextView) findViewById(R.id.mTextField2);
		mTextField3 = (TextView) findViewById(R.id.mTextField3);
		mTextField5 = (TextView) findViewById(R.id.mTextField5);
		final MediaPlayer mpTik = MediaPlayer.create(this, R.raw.tik);
		final MediaPlayer mpHorn = MediaPlayer.create(this, R.raw.horn);
		
		new CountDownTimer(3600000, 1000) {
			
			 public void onTick(long millisUntilFinished) {
				 mpTik.start();
				 sec = millisUntilFinished / 1000;
			     if(sec == 3500 || sec == 3400 || sec == 3300 || sec == 3200 || sec == 3100
			    		 || sec == 3000 || sec == 2900 || sec == 2800 || sec == 2700 || sec == 2600
			    		 || sec == 2500 || sec == 2400 || sec == 2300 || sec == 2200 || sec == 2100
			    		 || sec == 2000 || sec == 1900 || sec == 1800 || sec == 1700 || sec == 1600
			    		 || sec == 1500 || sec == 1400 || sec == 1300 || sec == 1200 || sec == 1100
			    		 || sec == 1000 || sec == 900 || sec == 800 || sec == 700 || sec == 600
			    		 || sec == 500 || sec == 400 || sec == 300 || sec == 200 || sec == 100
			    		 || sec == 2){
			    	 mpHorn.start();
			    	 mTextField5.setVisibility(View.INVISIBLE);
			    	 mTextField2.setVisibility(View.VISIBLE);
			    	 int min = 1;
			 		 int max = 5;

			 		 Random r = new Random();
			 		 final int word = r.nextInt(max - min + 1) + min;
			 		 if(word == 1){
			 			 mTextField2.setText("Shot!");
			 		 }else if(word == 2){
			 			mTextField2.setText("Cheers!");
			 		 }else if(word == 3){
			 			mTextField2.setText("Drink!");
			 		 }else if(word == 4){
			 			mTextField2.setText("Enjoy!");
			 		 }else if(word == 5){
			 			mTextField2.setText("Congratz!");
			 		 }
			    	 mTextField2.startAnimation(AnimationUtils.loadAnimation(CountdownGame.this, android.R.anim.slide_in_left));
			    	 drinkCounter++;
			    	 mTextField3.setText("" + drinkCounter);
			     }
			     if(sec == 3495 || sec == 3395 || sec == 3295 || sec == 3195 || sec == 3095
			    		 || sec == 2995 || sec == 2895 || sec == 2795 || sec == 2695 || sec == 2595
			    		 || sec == 2495 || sec == 2395 || sec == 2295 || sec == 2195 || sec == 2095
			    		 || sec == 1995 || sec == 1895 || sec == 1795 || sec == 1695 || sec == 1595
			    		 || sec == 1495 || sec == 1395 || sec == 1295 || sec == 1195 || sec == 1095
			    		 || sec == 995 || sec == 895 || sec == 795 || sec == 695 || sec == 595
			    		 || sec == 495 || sec == 395 || sec == 295 || sec == 195 || sec == 95
			    		 || sec == 1){
			    	 mTextField2.startAnimation(AnimationUtils.loadAnimation(CountdownGame.this, android.R.anim.fade_out));
					 mTextField2.setVisibility(View.INVISIBLE);
					 mTextField5.setText("Refill the shots..");
					 mTextField5.setVisibility(View.VISIBLE);
			     }
			 }
			
			 public void onFinish() {
			     mTextField.setText("Finished!");
			     end();
			 }
			}
			.start();

	}
	
	public void end(){
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Finished!");
		alertDialog.setMessage("Congratulations, you have survived the Power Hour game!");
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			Intent start = new Intent(
					"com.cincosolutions.myfiesta.POWERHOURACTIVITY");
			startActivity(start);
		}
		});
		alertDialog.show();
	}

}
