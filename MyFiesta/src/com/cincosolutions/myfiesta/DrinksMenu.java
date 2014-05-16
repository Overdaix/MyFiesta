package com.cincosolutions.myfiesta;

import android.R.anim;
import android.app.Activity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class DrinksMenu extends Activity {

	Button btnAddIngredient, btnWis;
	ListView drinksList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.drinksmenu);

		drinksList = (ListView) findViewById(android.R.id.list);
		drinksList.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1));

	}
}
