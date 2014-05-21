package com.cincosolutions.myfiesta;

import android.R.anim;
import android.app.Activity;
import android.app.Activity;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class DrinksMenu extends Activity {

	Button btnAddIngredient, btnDelete, btnPrefs;
	ListView lv;
	LinearLayout llIngredient, llPrefContainer;
	String drinks[] = { "Bacardi", "Malibu", "Safari" };
	AutoCompleteTextView etSearch;
	boolean booPrefClicked = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.drinksmenu);
		final View viewToLoad = LayoutInflater.from(getApplicationContext())
				.inflate(R.layout.drinksprefs, null);
	
		llPrefContainer = (LinearLayout) findViewById(R.id.llPrefContainer);		
		llPrefContainer.addView(viewToLoad);
		btnPrefs = (Button) findViewById(R.id.btnPrefs);
		viewToLoad.setVisibility(View.GONE);
		// etSearch.getText().toString();

		// if(etSearch.getText().toString().contentEquals(drinks))
		// Create the adapter and set it to the AutoCompleteTextView

		btnPrefs.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(booPrefClicked == false)
				{
				
					viewToLoad.setVisibility(View.VISIBLE);
					etSearch = (AutoCompleteTextView) findViewById(R.id.etIngredient);
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							DrinksMenu.this, android.R.layout.simple_list_item_1,
							drinks);
					etSearch.setAdapter(adapter);
					btnAddIngredient = (Button) findViewById(R.id.btnAddIngredient);
					btnAddIngredient.setOnClickListener(new View.OnClickListener() {
	
						public void onClick(View v) {
							TextView tv = new TextView(DrinksMenu.this);
							tv.setText(etSearch.getText().toString());
							llIngredient = (LinearLayout) findViewById(R.id.lvIngredient);
							llIngredient.addView(tv);
						}
					});
					booPrefClicked = true;
				}
				else{
					//ViewGroup parent = (ViewGroup) findViewById(R.id.llPrefContainer);
					//parent.removeView(viewToLoad);
					viewToLoad.setVisibility(View.GONE);
					booPrefClicked = false;
				}
			}
			
		});

		lv = (ListView) findViewById(R.id.drinksList);
		lv.setAdapter(new ArrayAdapter<String>(DrinksMenu.this,
				android.R.layout.simple_list_item_1, drinks));

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

			}

		});
		getApplicationContext().setTheme(R.style.AppTheme);
	}

}
