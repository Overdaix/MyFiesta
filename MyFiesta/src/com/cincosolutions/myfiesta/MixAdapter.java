package com.cincosolutions.myfiesta;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.graphics.Typeface;
import android.renderscript.RenderScript.Priority;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Wsdl2Code.WebServices.WebService1.Mix;
import com.Wsdl2Code.WebServices.WebService1.WebService1;



public class MixAdapter extends BaseAdapter {

	private com.Wsdl2Code.WebServices.WebService1.Mix mix;
	private int[] colors = new int[] { 0x30fffae7, 0x30f3d75c };
	private final Context context;
	private WebService1 webService;
	private final ArrayList<Mix> mixes;
	private final int rowResourceId;
	private com.Wsdl2Code.WebServices.WebService1.Ingredient ingredient;
	 ArrayList<String> ingredientnamen = new ArrayList<String>();
		private String mStringArray[];

	public MixAdapter(Context context, int textViewResourceId,
			ArrayList<Mix> objects) {
		// super(context, textViewResourceId);
		this.context = context;
		this.mixes = objects;
		this.rowResourceId = textViewResourceId;
	}

	@Override
	public int getCount() {
		return mixes.size();
	}

	@Override
	public long getItemId(int position) {
		Mix m = getItem(position);
		return m.id;
	}

	@Override
	public Mix getItem(int position) {
		return mixes.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		try {
			// VectorAssignment v = webservice.FindOpdrachtenAndroid(melding.id,
			// persoonGUID);
			webService.GetMixIngredientsAsync(mix.naam);
			
		
		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(rowResourceId, parent, false);

		try {
			Mix mix = getItem(position);
	
		
			try {
				if (mix.naam == null) {

				} else {
					rowView.setId(mix.id);


					}
				
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {

				TextView textView1 = (TextView) rowView
						.findViewById(R.id.firstLine);
				if (mix.naam != null) {
					textView1
							.setText(mix.naam + " ");
				}
				if(ingredient.name != null){
				TextView textView2 = (TextView) rowView
						.findViewById(R.id.secondline);
					textView2.setText(ingredient.name + "");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rowView;

	}
	

}
