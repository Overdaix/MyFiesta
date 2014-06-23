package com.cincosolutions.myfiesta;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.Wsdl2Code.WebServices.WebService1.Drink;
import com.Wsdl2Code.WebServices.WebService1.Ingredient;
import com.Wsdl2Code.WebServices.WebService1.VectorDrink;
import com.Wsdl2Code.WebServices.WebService1.IWsdl2CodeEvents;
import com.Wsdl2Code.WebServices.WebService1.VectorIngredient;
import com.Wsdl2Code.WebServices.WebService1.WebService1;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class DrinkInfo extends Activity implements IWsdl2CodeEvents {
	private WebService1 webService;
	private ArrayList<Drink> drinkItems = new ArrayList<Drink>();
	private ArrayList<Ingredient> ingredientItems = new ArrayList<Ingredient>();
	private com.Wsdl2Code.WebServices.WebService1.Drink drink;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drink_info);
		String url = "http://myfiesta.jeroendboer.nl/webservice1.asmx";
		webService = new WebService1(this, url);
		
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
		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SetText(R.id.tvName, drink.naam);
		SetText(R.id.tvDesc, drink.description);

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

