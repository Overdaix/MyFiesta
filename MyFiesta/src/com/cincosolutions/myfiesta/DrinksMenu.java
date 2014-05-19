package com.cincosolutions.myfiesta;

import android.R.anim;
import android.app.Activity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class DrinksMenu extends Activity {

	Button btnAddIngredient, btnDelete;
	ListView lv;
	String drinks[] = {"Bacardi", "Malibu", "Safari"};
	EditText etSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.drinksmenu);

		etSearch = (EditText) findViewById(R.id.etIngredient);	
		etSearch.getText().toString();
		//if(etSearch.getText().toString().contentEquals(drinks))
		
		lv = (ListView) findViewById(R.id.drinksList);
		lv.setAdapter(new ArrayAdapter<String>(DrinksMenu.this, android.R.layout.simple_list_item_1, drinks));
	
		lv.setOnItemClickListener(new OnItemClickListener(){

		
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
}
