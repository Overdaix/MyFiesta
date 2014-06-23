package com.cincosolutions.myfiesta;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class KingsGame extends Activity{
	
	TextView tvRule, tvRuleText;
	Button bNextCard;
	ImageView ivCard;
	
	int kings = 0;
	
	boolean first = false;
	
	ArrayList<String> cards = new ArrayList<String>();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kings_game);
		
		tvRule = (TextView) findViewById(R.id.tvRule);
		bNextCard = (Button) findViewById(R.id.bNextCard);
		ivCard = (ImageView) findViewById(R.id.ivCard);
	}
	
	public void nextcard(View v){
		int min = 1;
		int max = 52;

		Random r = new Random();
		final int card = r.nextInt(max - min + 1) + min;
		new CountDownTimer(1450, 1000) {
			 public void onTick(long millisUntilFinished) {
			 }
			
			 public void onFinish() {
				 changeimage(card);
				 changerule(card);
				 first = true;
			 }
			}
			.start();
		bNextCard.setText("Next card!");
		if(!cards.contains("card" + card)){
			MediaPlayer mpCard = MediaPlayer.create(this, R.raw.card);
			if(first == true){
				ivCard.startAnimation(AnimationUtils.loadAnimation(KingsGame.this, android.R.anim.slide_out_right));
				ivCard.setVisibility(View.INVISIBLE);
				bNextCard.setVisibility(View.INVISIBLE);
			}
			mpCard.start();
			cards.add("card" + card);
			if(card == 45 || card == 46 || card == 47 || card == 48){
				kings++;
				if(kings >= 4){
					end();
				}
			}
		}else{
			nextcard(v);
		}
	}
	
	public void changeimage(int card){
		ivCard.setVisibility(View.VISIBLE);
		bNextCard.setVisibility(View.VISIBLE);
		ivCard.setImageResource(getResources().getIdentifier("drawable/card"+card, null, getPackageName()));
	}
	
	public void end(){
		MediaPlayer mpHorn = MediaPlayer.create(this, R.raw.horn);
		mpHorn.start();
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Game over!");
		alertDialog.setMessage("You've got the 4th king! Drink the kings shot!");
		alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			kings = 0;
			Intent start = new Intent(
					"com.cincosolutions.myfiesta.KINGSGAMEACTIVITY");
			startActivity(start);
		}
		});
		alertDialog.show();
	}
	
	public void changerule(int card){
		if(card == 1 || card == 2 || card == 3 || card == 4){
			tvRule.setText("Give a shot to someone else!");
		}else if(card == 5 || card == 6 || card == 7 || card == 8){
			tvRule.setText("Drink a shot!");
		}else if(card == 9 || card == 10 || card == 11 || card == 12){
			tvRule.setText("Say a brands name, and every other player needs to say another brand name but in the same category. The player without a answer or is taking to long to come up with a brand needs to take a shot. example: Shoes: Nike, Converse, Puma, Addidas.");
		}else if(card == 13 || card == 14 || card == 15 || card == 16){
			tvRule.setText("You can go to the bathroom. During that this player is going to the bathroom no one else can go to the bathroom, except that player. If someone else still goes to the bathroom he or she needs to take 2 shots as punishment.");
		}else if(card == 17 || card == 18 || card == 19 || card == 20){
			tvRule.setText("If you need to take a shot you can pass it to someone else. (You can keep the car till you need to drink)");
		}else if(card == 21 || card == 22 || card == 23 || card == 24){
			tvRule.setText("As group everyone needs to count. Every one takes a turn and needs to count up: 1,2,3 etc. the numbers that are in a multiplication table of 7 can't be used!(1X7=,3X7=) apse the numbers containing a 7 can't be used(7, 37,107) the player who doesn't follow these rules/make a mistake needs to take a shot.");
		}else if(card == 25 || card == 26 || card == 27 || card == 28){
			tvRule.setText("Make a new rule. Every player that doesn’t follow this rule will get punish by taking 1 shot.");
		}else if(card == 29 || card == 30 || card == 31 || card == 32){
			tvRule.setText("You can get rid of 1 rule.");
		}else if(card == 33 || card == 34 || card == 35 || card == 36){
			tvRule.setText("Change the direction of drawing cards. The rotation changes to opposite direction.");
		}else if(card == 37 || card == 38 || card == 39 || card == 40){
			tvRule.setText("Eveyone points there forefinger into the air and counts down from 5 to 0.when you finishes counting down everyone needs to points at someone. The player with most other player pointing at them needs to take a shot.");
		}else if(card == 41 || card == 42 || card == 43 || card == 44){
			tvRule.setText("The player that has this card can randomly throw there hands in the air, and everyone needs to do this the player that does this last needs to take a shot.");
		}else if(card == 45 || card == 46 || card == 47 || card == 48){
			tvRule.setText("If You draw the last King you have WON! Just go on..");
		}else if(card == 49 || card == 50 || card == 51 || card == 52){
			tvRule.setText("Your the quizmaster. That means that this player can ask questions to whoever he or she wants. The other player answering these question needs to take a shot. You stay the quizmaster until someone else draw a 10.");
		}
	}

}
