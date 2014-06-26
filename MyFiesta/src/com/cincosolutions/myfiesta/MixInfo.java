package com.cincosolutions.myfiesta;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.Wsdl2Code.WebServices.WebService1.Drink;
import com.Wsdl2Code.WebServices.WebService1.Ingredient;
import com.Wsdl2Code.WebServices.WebService1.VectorDrink;
import com.Wsdl2Code.WebServices.WebService1.IWsdl2CodeEvents;
import com.Wsdl2Code.WebServices.WebService1.VectorIngredient;
import com.Wsdl2Code.WebServices.WebService1.VectorMix;
import com.Wsdl2Code.WebServices.WebService1.WebService1;
import com.Wsdl2Code.WebServices.WebService1.Mix;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MixInfo extends Activity implements IWsdl2CodeEvents {
	private WebService1 webService;
	private ArrayList<Drink> drinkItems = new ArrayList<Drink>();
	private ArrayList<Mix> mixItems = new ArrayList<Mix>();
	private ArrayList<Ingredient> ingredientItems = new ArrayList<Ingredient>();
	private com.Wsdl2Code.WebServices.WebService1.Drink drink;
	private com.Wsdl2Code.WebServices.WebService1.Mix mix;
	private com.Wsdl2Code.WebServices.WebService1.Ingredient ingredient;
	 ArrayList<String> ingredientnamen = new ArrayList<String>();
	 ArrayList<String> ingredientamount = new ArrayList<String>();
	private String mStringArray[];
	private String amountArray[];
	ImageView drinkImg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.drink_info);
		String url = "http://myfiesta.jeroendboer.nl/webservice1.asmx";
		webService = new WebService1(this, url);
		
		mix = new com.Wsdl2Code.WebServices.WebService1.Mix();
		Intent iin = getIntent();
		Bundle b = iin.getExtras();
		if (b != null) {
			try {
				
				mix = (com.Wsdl2Code.WebServices.WebService1.Mix) b
						.getSerializable("MIX");
			} catch (Exception ex) {
				String error = ex.getMessage();

			}

		}
		

		try {
			// VectorAssignment v = webservice.FindOpdrachtenAndroid(melding.id,
			// persoonGUID);
			webService.GetMixesAsync(mix.id, mix.naam);
			webService.GetMixIngredientsAsync(mix.naam);
		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		SetText(R.id.tvIngredient3, "");
		SetText(R.id.tvName, mix.naam);
		SetText(R.id.tvDesc, mix.description);
		try {
			drinkImg = (ImageView)findViewById(R.id.drinkImg);
		    Class res = R.drawable.class;
		    String imageName = mix.image;		    
		    imageName = imageName.replace(".png", "");
		    Field field = res.getField(imageName);
		    int drawableId = field.getInt(null);
		    drinkImg.setImageResource(drawableId);
		}
		catch (Exception e) {
		    Log.e("MyTag", "Failure to get drawable id.", e);
		}
		
		
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
		
		if (methodName == "GetMixes") {
			ArrayList<Mix> mixList = new ArrayList<Mix>();	
			for (Mix mix : (VectorMix) Data) {
				mixItems.add(mix);
			}
			for (Mix mix : mixItems) {
				String amount = mix.amount;
			/*	amount.replace("%", "");
				amount = amount.substring(0) + ""*/
				ingredientamount.add(mix.amount);
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
			 amountArray = new String[ingredientamount.size()];

				amountArray = ingredientamount.toArray(amountArray);
			 mStringArray = new String[ingredientnamen.size()];

				mStringArray = ingredientnamen.toArray(mStringArray);
				if(mStringArray.length >= 1){
					SetText(R.id.tvIngredient1, amountArray[0].toString() + " " + mStringArray[0].toString());
				}
				if(mStringArray.length >= 2){
					SetText(R.id.tvIngredient2, amountArray[1].toString() + " " + mStringArray[1].toString());
				}
				if(mStringArray.length >= 3){
					SetText(R.id.tvIngredient3, amountArray[2].toString() + " " + mStringArray[2].toString());
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
	public static int getResId(String variableName, Class<?> c) {

	    try {
	        Field idField = c.getDeclaredField(variableName);
	        return idField.getInt(idField);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return -1;
	    } 
	}

}

