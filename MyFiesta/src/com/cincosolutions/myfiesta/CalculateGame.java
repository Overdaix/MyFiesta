package com.cincosolutions.myfiesta;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculateGame extends Activity {
	
	TextView tvNumber1, tvNumber2, tvSign1, tvEnd;
	EditText etAnswer;
	Button bNext;
	
	int Score = 0;
	int Count = 0;
	
	boolean Pauze = false;
	
	SharedPreferences app_preferences;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculate_game);
		
		final MediaPlayer mpTik = MediaPlayer.create(this, R.raw.tik);
		final MediaPlayer mpHorn = MediaPlayer.create(this, R.raw.horn);
		
		final SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		tvNumber1 = (TextView) findViewById(R.id.tvNumber1);
		tvNumber2 = (TextView) findViewById(R.id.tvNumber2);
		tvSign1 = (TextView) findViewById(R.id.tvSign1);
		tvEnd = (TextView) findViewById(R.id.tvEnd);
		etAnswer = (EditText) findViewById(R.id.etAnswer);
		bNext = (Button) findViewById(R.id.bNext);
		
		LoadNumbers();
		new CountDownTimer(20000, 1000) {
			
			 public void onTick(long millisUntilFinished) {
				 if(Pauze == false){
					boolean boAudio = app_preferences.getBoolean("booAudio", true);
					if(!boAudio){
						mpTik.start();
					}
				 }
			 }
			 public void onFinish() {
				 if(Pauze == false){
					 boolean boAudio = app_preferences.getBoolean("booAudio", true);
					 if(!boAudio){
						mpHorn.start();
					 }
				     end();
				 }
			 }
		}
		.start();
	}
	
	private void LoadNumbers() {
		
		int min = 1;
		int max = 10;

		Random r1 = new Random();
		int i1 = r1.nextInt(max - min + 1) + min;
		Random r2 = new Random();
		int i2 = r2.nextInt(max - min + 1) + min;
		
		tvNumber1.setText("" + i1);
		tvNumber2.setText("" + i2);
		
	}
	
	public void next(View v){
		
			String Number1 = tvNumber1.getText().toString();
			String Number2 = tvNumber2.getText().toString();
			int Answer1 = Integer.parseInt(etAnswer.getText().toString());
			int Num1 = Integer.parseInt(Number1);
			int Num2 = Integer.parseInt(Number2);
			
			int Answer2 = Num1+Num2;
			
			if(Answer1 == Answer2){
				Count++;
				Toast.makeText(getApplicationContext(), "Right!",
						   Toast.LENGTH_SHORT).show();
				Score++;
				if(Count < 10){
					LoadNumbers();
				}
				etAnswer.setText("");
			}else{
				Count++;
				Toast.makeText(getApplicationContext(), "Wrong!",
						   Toast.LENGTH_SHORT).show();
				if(Count < 10){
					LoadNumbers();
				}
				etAnswer.setText("");
			}
			
			
			if(Count >= 10){
				end();
			}
		
	}
	
	public void end(){
		etAnswer.setVisibility(View.GONE);
		bNext.setVisibility(View.GONE);
		Pauze = true;
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Game over!");
		alertDialog.setMessage("Your score is:" + Score + "/" + Count + ". New game?");
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			Count = 0;
			Score = 0;
			Intent start = new Intent(
					"com.cincosolutions.myfiesta.CALCULATEINSTRUCTION");
			startActivity(start);
		}
		});
		alertDialog.show();
		
	}

}
