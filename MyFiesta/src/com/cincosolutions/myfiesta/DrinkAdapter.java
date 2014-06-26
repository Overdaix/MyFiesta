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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.Wsdl2Code.WebServices.WebService1.Drink;



public class DrinkAdapter extends BaseAdapter {

	private int[] colors = new int[] { 0x30fffae7, 0x30f3d75c };
	private final Context context;
	private final ArrayList<Drink> drinks;
	private final int rowResourceId;

	public DrinkAdapter(Context context, int textViewResourceId,
			ArrayList<Drink> objects) {
		// super(context, textViewResourceId);
		this.context = context;
		this.drinks = objects;
		this.rowResourceId = textViewResourceId;
	}

	@Override
	public int getCount() {
		return drinks.size();
	}

	@Override
	public long getItemId(int position) {
		Drink m = getItem(position);
		return m.id;
	}

	@Override
	public Drink getItem(int position) {
		return drinks.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(rowResourceId, parent, false);

		try {
			Drink drink = getItem(position);
	
		
			try {
				if (drink.naam == null) {

				} else {
					rowView.setId(drink.id);


					}
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {

				TextView textView1 = (TextView) rowView
						.findViewById(R.id.firstLine);
				if (drink.naam != null) {
					textView1
							.setText(drink.naam + " ");
				}
				TextView textView2 = (TextView) rowView.findViewById(R.id.secondline);
				if (drink.description != null){
					textView2.setText(drink.description);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			   try {
				    ImageView btnFavo = (ImageView) rowView.findViewById(R.id.btnFavo);
				    if (drink.favorite == 1) {
				     btnFavo.setImageResource(R.drawable.favo1);
				    } else {
				     btnFavo.setImageResource(R.drawable.favo2);
				    }
				   				    

				   } catch (Exception e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				   }
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rowView;
		 
	}


}
