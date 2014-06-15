package com.cincosolutions.myfiesta;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

import com.Wsdl2Code.WebServices.WebService1.Drink;
import com.Wsdl2Code.WebServices.WebService1.Ingredient;
import com.Wsdl2Code.WebServices.WebService1.VectorDrink;
import com.Wsdl2Code.WebServices.WebService1.IWsdl2CodeEvents;
import com.Wsdl2Code.WebServices.WebService1.VectorIngredient;
import com.Wsdl2Code.WebServices.WebService1.WebService1;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

public class DrinkInfo extends Activity implements IWsdl2CodeEvents {
	private WebService1 webService;
	private ArrayList<Drink> drinkItems = new ArrayList<Drink>();
	private ArrayList<Ingredient> ingredientItems = new ArrayList<Ingredient>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drink_info);
		String url = "http://myfiesta.jeroendboer.nl/webservice1.asmx";
		webService = new WebService1(this, url);
		LoadDrink();
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
}