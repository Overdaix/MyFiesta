package com.cincosolutions.myfiesta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.cincosolutions.myfiesta.SimpleGestureFilter.SimpleGestureListener;

import android.util.Log;
import android.view.MotionEvent;
import android.R.anim;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.Wsdl2Code.WebServices.WebService1.Drink;
import com.Wsdl2Code.WebServices.WebService1.Ingredient;
import com.Wsdl2Code.WebServices.WebService1.VectorDrink;
import com.Wsdl2Code.WebServices.WebService1.IWsdl2CodeEvents;
import com.Wsdl2Code.WebServices.WebService1.VectorIngredient;
import com.Wsdl2Code.WebServices.WebService1.WebService1;

import android.os.AsyncTask;

public class DrinksMenu extends Activity implements SimpleGestureListener,
		IWsdl2CodeEvents {

	SimpleGestureFilter detector;
	Button btnAddIngredient, btnDelete;
	ImageView btnPrefs;
	ListView lv;
	LinearLayout llIngredient, llPrefContainer;
	String drinks[] = { "Bacardi", "Malibu", "Safari", "SneeuwWitje",
			"Black Russian", "Drankje2", "Drankje3" };
	List<String> ingredienten = new ArrayList<String>();
	AutoCompleteTextView etSearch;
	boolean booPrefClicked = false;
	int tvCounter = 0;
	private WebService1 webService;
	private ArrayList<Drink> drinkItems = new ArrayList<Drink>();
	private ArrayList<Ingredient> ingredientItems = new ArrayList<Ingredient>();

	public void callWebService() {
		WebService1 webService = new WebService1(this);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.drinksmenu);

		llPrefContainer = (LinearLayout) findViewById(R.id.llPrefContainer);
		final ViewGroup parent = (ViewGroup) llPrefContainer.getParent();
		final View view = getLayoutInflater().inflate(R.layout.drinksprefs,
				parent, false);
		llPrefContainer.addView(view);

		view.setVisibility(View.GONE);

		String url = "http://myfiesta.jeroendboer.nl/webservice1.asmx";
		webService = new WebService1(this, url);

		LoadDrink();

		btnPrefs = (ImageView) findViewById(R.id.btnPrefs);
		btnPrefs.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (booPrefClicked == false) {

					btnPrefs.setImageResource(R.drawable.settings2);
					view.setVisibility(View.VISIBLE);
					etSearch = (AutoCompleteTextView) findViewById(R.id.etIngredient);

					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							DrinksMenu.this,
							android.R.layout.simple_list_item_1, drinks);
					etSearch.setAdapter(adapter);

					btnAddIngredient = (Button) findViewById(R.id.btnAddIngredient);
					btnAddIngredient
							.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {

									llIngredient = (LinearLayout) findViewById(R.id.lvIngredient);

									final ViewGroup parent = (ViewGroup) llIngredient
											.getParent();
									final View view = getLayoutInflater()
											.inflate(
													R.layout.ingredientrowview,
													parent, false);

									llIngredient.addView(view);

									final LinearLayout ingredientRow = (LinearLayout) findViewById(R.id.firstLine);
									final TextView tv = new TextView(DrinksMenu.this);
									ImageView remove = new ImageView(
											DrinksMenu.this);

									remove.setLayoutParams(new ViewGroup.LayoutParams(
											ViewGroup.LayoutParams.WRAP_CONTENT,
											ViewGroup.LayoutParams.WRAP_CONTENT));

									tvCounter++;
									tv.setText(etSearch.getText().toString());
									tv.setLayoutParams(new LayoutParams(
											LayoutParams.WRAP_CONTENT,
											LayoutParams.WRAP_CONTENT));
									remove.setId(tvCounter);

							
									ingredientRow
											.setOrientation(LinearLayout.VERTICAL);
									
									tv.setId(tvCounter);
									tv.setClickable(true);
									tv.setOnClickListener(new View.OnClickListener(){
										
										@Override
										public void onClick(View v) {
											// TODO Auto-generated method stub
										
											ingredientRow.removeView(tv);
										}
									});
									ingredientRow.addView(tv);
									
									ingredienten.add(etSearch.getText().toString());
		
								}
							});
					booPrefClicked = true;
				} else {

					view.setVisibility(View.GONE);
					btnPrefs.setImageResource(R.drawable.settings1);
					booPrefClicked = false;
				}
			}

		});
		
		detector = new SimpleGestureFilter(this, this);

	}


	@Override
	public void Wsdl2CodeStartedRequest() {
		Log.e("Wsdl2Code", "Wsdl2CodeStartedRequest");

	}

	public void Wsdl2CodeFinished(String methodName, Object Data) {
		Log.e("Wsdl2Code", "Wsdl2CodeFinished");
		Log.i("Wsdl2Code", methodName);

		if (methodName == "GetDrinks") {
			lv = (ListView) findViewById(R.id.drinksList);
			ArrayList<Drink> drinkslijst = new ArrayList<Drink>();

			for (Drink drink : (VectorDrink) Data) {
				drinkItems.add(drink);
			}

			for (Drink drink : drinkItems) {
				drinkslijst.add(drink);
			}
			com.Wsdl2Code.WebServices.WebService1.Drink drink1 = null;

			final DrinkAdapter adapter = new DrinkAdapter(this,
					R.layout.drinkslistrow, drinkslijst);

			lv.setAdapter(adapter);
			lv.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent("com.cincosolutions.myfiesta.DRINKINFO");
					startActivity(intent);
					
				}
			});

		}
		if(methodName == "GetIngredients"){
			ArrayList<Ingredient> ingredientslist = new ArrayList<Ingredient>();
			for (Ingredient ingredient : (VectorIngredient) Data) {
				ingredientItems.add(ingredient);
			}
		
			for(Ingredient ingredient : ingredientItems){
				ingredientslist.add(ingredient);
			}
			

		}

	}

	private void LoadDrink() {

		try {
			// webService.GetLastReportTimeAsync();
			webService.GetDrinksAsync();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// listview.addFooterView(footerlinear);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent me) {
		// Call onTouchEvent of SimpleGestureFilter class
		this.detector.onTouchEvent(me);
		return super.dispatchTouchEvent(me);
	}

	@Override
	public void onSwipe(int direction) {
		String str = "";

		switch (direction) {

		case SimpleGestureFilter.SWIPE_RIGHT:
			str = "Swipe Right";

			Intent openEvents = new Intent("com.cincosolutions.myfiesta.EVENTS");
			startActivity(openEvents);

			break;
		case SimpleGestureFilter.SWIPE_LEFT:
			str = "Swipe Left";
			break;
		case SimpleGestureFilter.SWIPE_DOWN:
			str = "Swipe Down";
			break;
		case SimpleGestureFilter.SWIPE_UP:
			str = "Swipe Up";
			break;

		}
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDoubleTap() {
		Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void Wsdl2CodeFinishedWithException(Exception ex) {
		// TODO Auto-generated method stub
		Log.e("asdasd", ex.toString());

	}

	@Override
	public void Wsdl2CodeEndedRequest() {
		// TODO Auto-generated method stub

	}

	private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

	}

	public void EventsAct(View v) {
		Intent openEvents = new Intent("com.cincosolutions.myfiesta.EVENTS");
		startActivity(openEvents);
	}

	public void SettingsAct(View v) {
		Intent openSettingsActivity = new Intent(
				"com.cincosolutions.myfiesta.SETTINGSACTIVITY");
		startActivity(openSettingsActivity);
	}
}