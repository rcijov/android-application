package com.simona.halep.Pages;

import java.util.List;
import java.util.Random;

import com.simona.halep.Base;
import com.simona.halep.R;
import com.simona.halep.Database.StatsDataSource;
import com.simona.halep.Database.Entities.Stats;
import com.simona.halep.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.Fragment;

public class Videos extends Fragment implements android.view.View.OnClickListener {  
	
	   private StatsDataSource datasource;
	   private TextView textView;
	   private List<Stats> values;

	   public static Videos newInstance() {
		   Videos fragment = new Videos();  
	     return fragment;  
	   }  

	   public Videos() {  
	   }  

	   @Override  
	   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
	       View rootView = inflater.inflate(R.layout.videos, container, false);  
	       
	       datasource = new StatsDataSource(getActivity());
	       datasource.open();

	       values = datasource.getAllStats();
	       textView = (TextView) rootView.findViewById(R.id.text);
	       
	       String txt = "";
	       for(int i = 0; i < values.size(); i++)
	       {
	    	   txt = txt + "\n Number: " + values.get(i).getStat();
	    	   txt = txt + " -- Stat: " + values.get(i).getNrStat();
	       }
	       textView.setText(txt);
	       
	       ((Button)rootView.findViewById(R.id.add)).setOnClickListener(this);
	       ((Button)rootView.findViewById(R.id.delete)).setOnClickListener(this);
	       
	       return rootView;  
	   }  
	   
//	   // Will be called via the onClick attribute
//	   // of the buttons in main.xml
	   public void onClick(View view) {
	     Stats stat = null;
	     switch (view.getId()) {
	     case R.id.add:
	       String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
	       int nextInt = new Random().nextInt(3);
	       // save the new comment to the database
	       stat = datasource.createStat(comments[nextInt],"123");
	       values.add(stat);
	       break;
	     case R.id.delete:
	       if (values.size() != 0) {
	         stat = (Stats) values.get(0);
	         datasource.deleteStat(stat);
	         values.remove(0);
	       }
	       break;
	     }
	     if(values.size() != 0)
	     {
	    	 String txt = "";
	    	 for(int i = 0; i < values.size(); i++)
	    	 {
	    		 txt = txt + "\n Number: " + values.get(i).getStat();
	    		 txt = txt + " -- Stat: " + values.get(i).getNrStat();
	    	 }
	    	 textView.setText(txt);
	     }
	     else
	     {
	    	 textView.setText("Empty");
	     }
	   }

	   @Override  
	   public void onAttach(Activity activity) {  
	       super.onAttach(activity);  
	       ((Base) activity).onSectionAttached(7);  
	   }  
}  
