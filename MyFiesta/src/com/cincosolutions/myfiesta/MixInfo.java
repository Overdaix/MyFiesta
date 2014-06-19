package com.cincosolutions.myfiesta;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.Wsdl2Code.WebServices.WebService1.Mix;
import com.Wsdl2Code.WebServices.WebService1.Ingredient;
import com.Wsdl2Code.WebServices.WebService1.VectorMix;
import com.Wsdl2Code.WebServices.WebService1.IWsdl2CodeEvents;
import com.Wsdl2Code.WebServices.WebService1.VectorIngredient;
import com.Wsdl2Code.WebServices.WebService1.WebService1;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class MixInfo extends Activity implements IWsdl2CodeEvents {
	private WebService1 webService;
	private ArrayList<Mix> mixItems = new ArrayList<Mix>();
	private ArrayList<Ingredient> ingredientItems = new ArrayList<Ingredient>();
	private com.Wsdl2Code.WebServices.WebService1.Mix mix;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drink_info);
		String url = "http://myfiesta.jeroendboer.nl/webservice1.asmx";
		webService = new WebService1(this, url);
		
		mix = new com.Wsdl2Code.WebServices.WebService1.Mix();
		Intent iin = getIntent();
		Bundle b = iin.getExtras();
		if (b != null) {
			try {
				mix = (com.Wsdl2Code.WebServices.WebService1.Mix) b
						.getSerializable("DRINK");
			} catch (Exception ex) {
				String error = ex.getMessage();

			}

		}
		

		try {
			// VectorAssignment v = webservice.FindOpdrachtenAndroid(melding.id,
			// persoonGUID);
			webService.GetMixesAsync(mix.id, mix.naam);
		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SetText(R.id.tvName, mix.naam);
		SetText(R.id.tvDesc, mix.description);

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
		if (methodName == "GetMixs") {
			ArrayList<Mix> mixeslijst = new ArrayList<Mix>();
			for (Mix mix : (VectorMix) Data) {
				mixItems.add(mix);
			}
			for (Mix mix : mixItems) {
				mixeslijst.add(mix);
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
	
}