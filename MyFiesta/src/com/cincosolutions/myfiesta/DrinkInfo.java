package com.cincosolutions.myfiesta;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.Wsdl2Code.WebServices.WebService1.Drink;
import com.Wsdl2Code.WebServices.WebService1.Ingredient;
import com.Wsdl2Code.WebServices.WebService1.VectorDrink;
import com.Wsdl2Code.WebServices.WebService1.IWsdl2CodeEvents;
import com.Wsdl2Code.WebServices.WebService1.VectorIngredient;
import com.Wsdl2Code.WebServices.WebService1.WebService1;

import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DrinkInfo extends Activity implements IWsdl2CodeEvents {
	private WebService1 webService;
	private ArrayList<Drink> drinkItems = new ArrayList<Drink>();
	private ArrayList<Ingredient> ingredientItems = new ArrayList<Ingredient>();
	private com.Wsdl2Code.WebServices.WebService1.Drink drink;
	private com.Wsdl2Code.WebServices.WebService1.Ingredient ingredient;
	private ArrayList<String> ingredientnamen = new ArrayList();
	private String mStringArray[];
	private ImageView ivFavo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drink_info);
		String url = "http://myfiesta.jeroendboer.nl/webservice1.asmx";
		webService = new WebService1(this, url);

		ivFavo = (ImageView) findViewById(R.id.ivFavo);

		drink = new com.Wsdl2Code.WebServices.WebService1.Drink();
		Intent iin = getIntent();
		Bundle b = iin.getExtras();
		if (b != null) {
			try {
				
				drink = (com.Wsdl2Code.WebServices.WebService1.Drink) b
						.getSerializable("DRINK");
			} catch (Exception ex) {
				String error = ex.getMessage();

			}

		}
		

		try {
			// VectorAssignment v = webservice.FindOpdrachtenAndroid(melding.id,
			// persoonGUID);
			webService.GetDrinksAsync(drink.id, drink.naam, drink.image, drink.description, drink.favorite);
			webService.GetMixIngredientsAsync(drink.naam);
		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		SetText(R.id.tvIngredient3, "");
		SetText(R.id.tvName, drink.naam);
		SetText(R.id.tvDesc, drink.description);

		
		final SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
		final SharedPreferences.Editor editor = app_preferences.edit();
		if (app_preferences.contains("Drink" + drink.id)) {
			ivFavo.setImageResource(R.drawable.favo1);
		} else {
			ivFavo.setImageResource(R.drawable.favo2);
		}
		ivFavo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (app_preferences.contains("Drink" + drink.id)) {
					ivFavo.setImageResource(R.drawable.favo2);
					editor.remove("Drink"+drink.id);
					editor.commit();
				} else {
					ivFavo.setImageResource(R.drawable.favo1);
					editor.putString("Drink"+drink.id, "Drink"+drink.id); 
					editor.commit();
				}
			}
		});

	}

	
	
	
	@Override
	public void Wsdl2CodeStartedRequest() {
		// TODO Auto-generated method stub
		Log.e("Wsdl2Code", "Wsdl2CodeStartedRequest");

	}

	@Override
	public void Wsdl2CodeFinished(String methodName, Object Data) {
		// TODO Auto-generated method stub
		Log.e("Wsdl2Code", "Wsdl2CodeFinished");
		Log.i("Wsdl2Code", methodName);
		if (methodName == "GetDrinks") {
			ArrayList<Drink> drinkslijst = new ArrayList<Drink>();
			for (Drink drink : (VectorDrink) Data) {
				drinkItems.add(drink);
			}
			for (Drink drink : drinkItems) {
				drinkslijst.add(drink);
			}
		}
		if (methodName == "GetMixIngredients") {
			ArrayList<Ingredient> ingredientslist = new ArrayList<Ingredient>();

			for (Ingredient ingredient : (VectorIngredient) Data) {
				ingredientItems.add(ingredient);

			}

			for (Ingredient ingredient : ingredientItems) {
				ingredientslist.add(ingredient);
				ingredientnamen.add(ingredient.name);

			}
			 mStringArray = new String[ingredientnamen.size()];

				mStringArray = ingredientnamen.toArray(mStringArray);
				if(mStringArray.length >= 1){
					SetText(R.id.tvIngredient1, mStringArray[0].toString());
				}
				if(mStringArray.length >= 2){
					SetText(R.id.tvIngredient2, mStringArray[1].toString());
				}
				if(mStringArray.length >= 3){
					SetText(R.id.tvIngredient3, mStringArray[2].toString());
				}
				

		}
	}

	@Override
	public void Wsdl2CodeFinishedWithException(Exception ex) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void Wsdl2CodeEndedRequest() {
		// TODO Auto-generated method stub
		
	}	

	private void SetText(int fieldId, int text) {
		SetText(fieldId, "" + text);
	}

	private void SetText(int fieldId, String text) {
		try {
			if (text == null)
				text = "";

			TextView textField = (TextView) findViewById(fieldId);
			textField.setText(text.trim());
		} catch (Exception ex) {
		}
	}

		// listview.addFooterView(footerlinear);
	public void GamesAct(View v) {
		Intent openGamesActivity = new Intent(
				"com.cincosolutions.myfiesta.GAMESACTIVITY");
		startActivity(openGamesActivity);
	}

	public void DrinksAct(View v) {
		Intent openDrinksMenu = new Intent(
				"com.cincosolutions.myfiesta.DRINKSMENU");
		startActivity(openDrinksMenu);
	}

	public void SettingsAct(View v) {
		Intent openSettingsActivity = new Intent(
				"com.cincosolutions.myfiesta.SETTINGSACTIVITY");
		startActivity(openSettingsActivity);
	}

	public void backbtnGame(View v) {
		Intent back = new Intent("com.cincosolutions.myfiesta.DRINKSMENU");
		startActivity(back);
	}

}

