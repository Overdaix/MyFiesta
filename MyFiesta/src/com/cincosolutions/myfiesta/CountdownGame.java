package com.cincosolutions.myfiesta;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class CountdownGame extends Activity {
	
TextView mTextField, mTextField2, mTextField3;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.countdown_game);
		
		mTextField = (TextView) findViewById(R.id.mTextField);
		mTextField2 = (TextView) findViewById(R.id.mTextField2);
		mTextField3 = (TextView) findViewById(R.id.mTextField3);
		final MediaPlayer mpTik = MediaPlayer.create(this, R.raw.tik);
		final MediaPlayer mpHorn = MediaPlayer.create(this, R.raw.horn);
		
		new CountDownTimer(3600000, 1000) {
			
			 public void onTick(long millisUntilFinished) {
				 mpTik.start();
			     //mTextField.setText("Seconds remaining: " + millisUntilFinished / 1000);
			     if(millisUntilFinished / 1000 == 3590){
			    	 mpHorn.start();
			    	 mTextField2.setVisibility(View.VISIBLE);
			    	 mTextField2.setText("Shot!");
			    	 mTextField2.startAnimation(AnimationUtils.loadAnimation(CountdownGame.this, android.R.anim.slide_in_left));
			    	 mTextField3.setText("1");
			     }
			     if(millisUntilFinished / 1000 == 3585){
			    	 mTextField2.startAnimation(AnimationUtils.loadAnimation(CountdownGame.this, android.R.anim.fade_out));
					 mTextField2.setVisibility(View.INVISIBLE);
			     }
			     if(millisUntilFinished / 1000 == 3575){
			    	 mpHorn.start();
			    	 mTextField2.setVisibility(View.VISIBLE);
			    	 mTextField2.setText("Drink!");
			    	 mTextField2.startAnimation(AnimationUtils.loadAnimation(CountdownGame.this, android.R.anim.slide_in_left));
			    	 mTextField3.setText("2");
			     }
			     if(millisUntilFinished / 1000 == 3570){
			    	 mTextField2.startAnimation(AnimationUtils.loadAnimation(CountdownGame.this, android.R.anim.fade_out));
			    	 mTextField2.setVisibility(View.INVISIBLE);
			     }
			     if(millisUntilFinished / 1000 == 3565){
			    	 mpHorn.start();
			    	 mTextField2.setVisibility(View.VISIBLE);
			    	 mTextField2.setText("Cheers!");
			    	 mTextField2.startAnimation(AnimationUtils.loadAnimation(CountdownGame.this, android.R.anim.slide_in_left));
			    	 mTextField3.setText("3");
			     }
			     if(millisUntilFinished / 1000 == 3560){
			    	 mTextField2.startAnimation(AnimationUtils.loadAnimation(CountdownGame.this, android.R.anim.fade_out));
			    	 mTextField2.setVisibility(View.INVISIBLE);
			     }
			 }
			
			 public void onFinish() {
			     mTextField.setText("Finished!");
			 }
			}
			.start();

	}

}
