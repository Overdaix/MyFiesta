package com.cincosolutions.myfiesta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CalculateInstruction extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculate_instruction);
	}
	
	public void start(View v) {
		Intent start = new Intent(
				"com.cincosolutions.myfiesta.CALCULATEGAME");
		startActivity(start);
	}
}
