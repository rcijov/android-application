package com.simona.halep.Pages;

import java.util.List;

import com.simona.halep.Base;
import com.simona.halep.R;
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

	   private StatsDataSource datasource;
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
	       
	       datasource = new StatsDataSource(getActivity());
	       datasource.open();

	       values = datasource.getAllStats();
	       textView = (TextView) rootView.findViewById(R.id.text);
	
	       if(values.size() == 0)
	       {
	    	   com.simona.halep.Database.Entities.Stats stat = new com.simona.halep.Database.Entities.Stats();
		       stat.setStat("Aces");
		       stat.setNrStat("133");
		       datasource.createStat(stat.getStat(),stat.getNrStat());
		       values.add(stat);
	       }
	       
	       String txt = "Simona Halep Main Ranks";
	       for(int i = 0; i < values.size(); i++)
	       {
	    	   txt = txt + "\n Number: " + values.get(i).getStat();
	    	   txt = txt + " -- Stat: " + values.get(i).getNrStat();
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
