package com.simona.halep.Pages;

import java.util.List;

import com.simona.halep.Base;
import com.simona.halep.R;
import com.simona.halep.Database.InitDatabase;
import com.simona.halep.Database.StatsDataSource;
import com.simona.halep.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.Fragment;

public class Stats extends Fragment {  

	   private InitDatabase database;
	   private TextView textView;
	   private List<com.simona.halep.Database.Entities.Stats> values;
	
	   public static Stats newInstance() {
		   Stats fragment = new Stats();  
	     return fragment;  
	   }  

	   public Stats() {  
	   }  

	   @Override  
	   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
	       View rootView = inflater.inflate(R.layout.stats, container, false);  

	       database = InitDatabase.getInstance(getActivity());
	       values = database.getStats();
	       textView = (TextView) rootView.findViewById(R.id.text);
	       
	       String txt = "Simona Halep Main Ranks";
	       for(int i = 0; i < values.size(); i++)
	       {
	    	   txt = txt + "\n Stat: " + values.get(i).getStat();
	    	   txt = txt + " -- Stat Ytd: " + values.get(i).getNrStatYtd();
	    	   txt = txt + " -- Stat Car: " + values.get(i).getNrStatCar();
	       }
	       textView.setText(txt);
	       
	       return rootView;  
	   }  

	   @Override  
	   public void onAttach(Activity activity) {  
	       super.onAttach(activity);  
	       ((Base) activity).onSectionAttached(3);  
	   }  
}  
