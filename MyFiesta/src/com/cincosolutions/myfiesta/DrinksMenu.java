package com.cincosolutions.myfiesta;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.cincosolutions.myfiesta.SimpleGestureFilter.SimpleGestureListener;

import android.util.Log;
import android.view.MotionEvent;
import android.R.anim;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.Wsdl2Code.WebServices.WebService1.Drink;
import com.Wsdl2Code.WebServices.WebService1.Ingredient;
import com.Wsdl2Code.WebServices.WebService1.Mix;
import com.Wsdl2Code.WebServices.WebService1.VectorDrink;
import com.Wsdl2Code.WebServices.WebService1.IWsdl2CodeEvents;
import com.Wsdl2Code.WebServices.WebService1.VectorIngredient;
import com.Wsdl2Code.WebServices.WebService1.VectorMix;
import com.Wsdl2Code.WebServices.WebService1.VectorString;
import com.Wsdl2Code.WebServices.WebService1.WebService1;

import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;

public class DrinksMenu extends Activity implements SimpleGestureListener,
		IWsdl2CodeEvents {

	SimpleGestureFilter detector;
	Button btnAddIngredient, btnWis, btnSearch;
	ImageView btnPrefs, favoImage;
	ListView lv;
	LinearLayout llIngredient, llPrefContainer, llPrefContainerContents;
	String drinks[] = { "Bacardi", "Malibu", "Safari", "SneeuwWitje",
			"Black Russian", "Drankje2", "Drankje3" };
	VectorString ingredienten = new VectorString();
	AutoCompleteTextView etSearch;
	boolean booPrefClicked = false;
	int tvCounter = 0;
	private WebService1 webService;
	private ArrayList<Drink> drinkItems = new ArrayList<Drink>();
	RadioButton rad1, rad2;
	private ArrayList<Ingredient> ingredientItems = new ArrayList<Ingredient>();
	private ArrayList<Mix> mixItems = new ArrayList<Mix>();
	ArrayList<String> ingredientnamen = new ArrayList<String>();
	ArrayList<String> mixnamen = new ArrayList<String>();
	private String strFavo;
	String[] arrIDs;
	String name = "";
	TextView tving1, tving2;
	RadioGroup group;
	ProgressDialog pd;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.drinksmenu);
		llPrefContainer = (LinearLayout) findViewById(R.id.llPrefContainer);

		final ViewGroup parent = (ViewGroup) llPrefContainer.getParent();
		final View view = getLayoutInflater().inflate(R.layout.drinksprefs,
				parent, false);
		llPrefContainer.addView(view);
		view.setVisibility(View.GONE);

		llPrefContainerContents = (LinearLayout) findViewById(R.id.llPrefContents);
		String url = "http://myfiesta.jeroendboer.nl/webservice1.asmx";
		webService = new WebService1(this, url);
		rad1 = (RadioButton) findViewById(R.id.radDrinks1);
		rad2 = (RadioButton) findViewById(R.id.radDrinks2);

		group = (RadioGroup) findViewById(R.id.radioGroup);
		tving1 = (TextView) findViewById(R.id.tving1);
		tving2 = (TextView) findViewById(R.id.tvIng2);
		group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				RadioButton selectRadio = (RadioButton) findViewById(group
						.getCheckedRadioButtonId());
				if (rad1.isChecked()) {
					View root = llPrefContainerContents.getRootView();

					root.setBackgroundColor(getResources().getColor(
							android.R.color.darker_gray));
					tving1.setTextColor(Color.parseColor("#7f7f7f"));
					tving2.setTextColor(Color.parseColor("#7f7f7f"));
					etSearch.setEnabled(false);
					btnAddIngredient.setEnabled(false);
					btnSearch.setEnabled(false);
					btnWis.setEnabled(false);
				} else if (rad2.isChecked()) {
					View root = llPrefContainerContents.getRootView();

					root.setBackgroundResource(R.drawable.bg);
					tving1.setTextColor(Color.WHITE);
					tving2.setTextColor(Color.WHITE);
					etSearch.setEnabled(true);
					btnAddIngredient.setEnabled(true);
					btnSearch.setEnabled(true);
					btnWis.setEnabled(true);
				}
			}
		});

		LoadDrink();
		LoadIngredient();
		btnPrefs = (ImageView) findViewById(R.id.btnPrefs);
		btnPrefs.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (booPrefClicked == false) {

					btnPrefs.setImageResource(R.drawable.filter1);
					view.setVisibility(View.VISIBLE);
					etSearch = (AutoCompleteTextView) findViewById(R.id.etIngredient);
					String[] mStringArray = new String[ingredientnamen.size()];
					mStringArray = ingredientnamen.toArray(mStringArray);
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							DrinksMenu.this,
							android.R.layout.simple_list_item_1, mStringArray);
					etSearch.setAdapter(adapter);

					btnAddIngredient = (Button) findViewById(R.id.btnAddIngredient);
					btnAddIngredient
							.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									if (etSearch.getText().toString().length() < 1) {
										Context context = getApplicationContext();
										CharSequence text =  "Enter an ingrediënt";
										int duration = Toast.LENGTH_SHORT;

										Toast toast = Toast.makeText(
												context, text, duration);
										toast.show();
									}
									else{
										
										String ingredientinput = etSearch
												.getText().toString();

										ingredientinput = ingredientinput
												.substring(0, 1).toUpperCase()
												+ ingredientinput.substring(1);

										if (ingredientnamen
												.contains(ingredientinput)) {
											llIngredient = (LinearLayout) findViewById(R.id.lvIngredient);

											final TextView tv = new TextView(
													DrinksMenu.this);
											ImageView remove = new ImageView(
													DrinksMenu.this);

											remove.setLayoutParams(new ViewGroup.LayoutParams(
													ViewGroup.LayoutParams.WRAP_CONTENT,
													ViewGroup.LayoutParams.WRAP_CONTENT));

											tvCounter++;
											tv.setText(ingredientinput);
											tv.setLayoutParams(new LayoutParams(
													LayoutParams.WRAP_CONTENT,
													LayoutParams.WRAP_CONTENT));
											remove.setId(tvCounter);

											tv.setTextColor(Color.WHITE);

											tv.setId(tvCounter);
											tv.setClickable(true);
											tv.setOnClickListener(new View.OnClickListener() {

												@Override
												public void onClick(View v) {
													// TODO Auto-generated
													// method
													// stub

													llIngredient.removeView(tv);
													ingredienten.remove(tv.getText().toString());
												}
											});
											llIngredient.addView(tv);
											etSearch.setText("");
											ingredienten.add(ingredientinput);
											Context context = getApplicationContext();
											CharSequence text = ingredientinput
													+ " added to ingrediënts list";
											int duration = Toast.LENGTH_SHORT;

											Toast toast = Toast.makeText(
													context, text, duration);
											toast.show();
											

										} else {
											Context context = getApplicationContext();
											CharSequence text = ingredientinput
													+ " doesn't exist";
											int duration = Toast.LENGTH_SHORT;

											Toast toast = Toast.makeText(
													context, text, duration);
											toast.show();
										}
									}
								
								}
							});
					booPrefClicked = true;
				} else {

					view.setVisibility(View.GONE);
					btnPrefs.setImageResource(R.drawable.filter);
					booPrefClicked = false;
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);

				}
				
			}

		});
		
		btnSearch = (Button) findViewById(R.id.btnSearch);
		btnSearch.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {

					webService.FindMixesAsync(ingredienten);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pd = new ProgressDialog(DrinksMenu.this);
				pd.setTitle("Loading Drinks...");
				pd.setMessage("Please wait.");
				pd.setCancelable(false);
				pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				pd.setIndeterminate(true);
				pd.show();
				view.setVisibility(View.GONE);
				btnPrefs.setImageResource(R.drawable.filter);
				booPrefClicked = false;
		
			}
		});
		btnWis = (Button) findViewById(R.id.btnWis);
		btnWis.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				/*
				 * // TODO Auto-generated method stub ingredienten.clear(); for
				 * (int i = 0; i < tvCounter; i++) { String id =
				 * Integer.toString(tvCounter); int resID =
				 * getResources().getIdentifier(id, "id",
				 * "com.cincosolutions.myfiesta"); TextView tv = (TextView)
				 * findViewById(resID); llIngredient.removeView(tv); }
				 */

			}
		});

		detector = new SimpleGestureFilter(this, this);

		SharedPreferences app_preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		final SharedPreferences.Editor editor = app_preferences.edit();

		// Manual adding Drink1 (baco) to favorites for testing.
		/*
		 * editor.putString("Drink1", "Drink1"); editor.commit(); // Very
		 * important
		 */

		/*
		 * //Manual deleting Drink1 from favorites. editor.remove("Drink1");
		 * editor.commit();
		 */

	}

	@Override
	public void Wsdl2CodeStartedRequest() {
		Log.e("Wsdl2Code", "Wsdl2CodeStartedRequest");
		/*pd = new ProgressDialog(this);
		pd.setMessage("Loading drinks... ");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        pd.setProgress(0);
        pd.setMax(50);
        pd.show();*/

	}

	public void Wsdl2CodeFinished(String methodName, Object Data) {
		Log.e("Wsdl2Code", "Wsdl2CodeFinished");
		Log.i("Wsdl2Code", methodName);
		if (methodName == "FindMixes") {
			FindMixes(Data);
		} else if (methodName == "GetDrinks") {
			lv = (ListView) findViewById(R.id.drinksList);
			ArrayList<Drink> drinkslijst = new ArrayList<Drink>();

			for (Drink drink : (VectorDrink) Data) {
				drinkItems.add(drink);
			}
			final SharedPreferences app_preferences = PreferenceManager
					.getDefaultSharedPreferences(this);
			final SharedPreferences.Editor editor = app_preferences.edit();

			for (Drink drink : drinkItems) {
				int intDrinkId = drink.id;
				// If current drink is in array list
				if (app_preferences.contains("Drink" + intDrinkId)) {
					drink.favorite = 1;
				}
				drinkslijst.add(drink);
			}
			com.Wsdl2Code.WebServices.WebService1.Drink drink1 = null;

			final DrinkAdapter adapter = new DrinkAdapter(this,
					R.layout.drinkslistrow, drinkslijst);

			lv.setAdapter(adapter);
			Button btnFavo = (Button) findViewById(R.id.btnFavo);

			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView arg0, View view,
						int position, long id) {

					try {
						Drink drink = adapter.getItem(position);

						Bundle bundle = new Bundle();
						bundle.putSerializable("DRINK", drink);
						Intent intent = new Intent(DrinksMenu.this,
								com.cincosolutions.myfiesta.DrinkInfo.class);
						intent.putExtras(bundle);

						startActivity(intent);
					} catch (Exception ex) {
						Log.e("MyFiesta", ex.getMessage());
					}

				}
			});

		} else if (methodName == "GetIngredients") {
			ArrayList<Ingredient> ingredientslist = new ArrayList<Ingredient>();

			for (Ingredient ingredient : (VectorIngredient) Data) {
				ingredientItems.add(ingredient);

			}

			for (Ingredient ingredient : ingredientItems) {
				ingredientslist.add(ingredient);
				ingredientnamen.add(ingredient.name);

			}

		}
	
	
      

	}

	public void FindMixes(Object Data) {
		ArrayList<Mix> mixlist = new ArrayList<Mix>();
		for (Mix mix : (VectorMix) Data) {

			mixItems.add(mix);

		}

		for (Mix mix : mixItems) {
			mixlist.add(mix);
			mixnamen.add(mix.naam);
			Log.e("Naam:", mix.naam);
		}
		final MixAdapter adapter = new MixAdapter(this, R.layout.drinkslistrow,
				mixlist);
		Context context = getApplicationContext();
		CharSequence text = mixlist.size() + " mixes found";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(
				context, text, duration);
		
		toast.show();
		lv.setAdapter(adapter);
	
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView arg0, View view, int position,
					long id) {

				try {
					Mix mix = adapter.getItem(position);

					Bundle bundle = new Bundle();
					bundle.putSerializable("MIX", mix);
					Intent intent = new Intent(DrinksMenu.this,
							com.cincosolutions.myfiesta.MixInfo.class);
					intent.putExtras(bundle);

					startActivity(intent);
				} catch (Exception ex) {
					Log.e("MyFiesta", ex.getMessage());
				}
			}

		});
		adapter.notifyDataSetChanged();
	}

	private void LoadDrink() {
		int id = 0;
		String naam = "";
		String description = "";
		String image = "";
		int favorite = 0;

		try {
			// webService.GetLastReportTimeAsync();
			webService.GetDrinksAsync(id, naam, image, description, favorite);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pd = new ProgressDialog(this);
		pd.setTitle("Loading Drinks...");
		pd.setMessage("Please wait.");
		pd.setCancelable(false);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setIndeterminate(true);
		pd.show();


			

		// listview.addFooterView(footerlinear);
	}

	private void LoadIngredient() {

		try {
			// webService.GetLastReportTimeAsync();
			webService.GetIngredientsAsync(name);
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
		//String str = "";

		switch (direction) {

		case SimpleGestureFilter.SWIPE_RIGHT:
			Intent openSettingsActivity = new Intent(
					"com.cincosolutions.myfiesta.SETTINGSACTIVITY");
			startActivity(openSettingsActivity);
			finish();
			break;
		case SimpleGestureFilter.SWIPE_LEFT:
			Intent openGamesActivity = new Intent(
					"com.cincosolutions.myfiesta.GAMESACTIVITY");
			startActivity(openGamesActivity);
			finish();
			break;
		case SimpleGestureFilter.SWIPE_DOWN:
			//str = "Swipe Down";
			break;
		case SimpleGestureFilter.SWIPE_UP:
			//str = "Swipe Up";
			break;

		}
		//Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDoubleTap() {
		//Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void Wsdl2CodeFinishedWithException(Exception ex) {
		// TODO Auto-generated method stub
		Log.e("asdasd", ex.toString());

	}

	@Override
	public void Wsdl2CodeEndedRequest() {
		// TODO Auto-generated method stub
		setProgressBarIndeterminateVisibility(false);
        pd.dismiss();

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

	public void GamesAct(View v) {
		Intent openGamesActivity = new Intent(
				"com.cincosolutions.myfiesta.GAMESACTIVITY");
		startActivity(openGamesActivity);
		finish();
	}

	public void SettingsAct(View v) {
		Intent openSettingsActivity = new Intent(
				"com.cincosolutions.myfiesta.SETTINGSACTIVITY");
		startActivity(openSettingsActivity);
		finish();
	}
}