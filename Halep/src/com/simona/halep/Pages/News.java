package com.simona.halep.Pages;

import java.util.List;
import java.util.Random;

import com.simona.halep.Base;
import com.simona.halep.R;
import com.simona.halep.R.layout;
import com.simona.halep.Database.InitDatabase;
import com.simona.halep.Database.NewsDataSource;
import com.simona.halep.Database.StatsDataSource;
import com.simona.halep.Database.Entities.Stats;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.Fragment;

public class News extends Fragment {  
	
	   private InitDatabase database;
	   private TextView textView;
	   private List<com.simona.halep.Database.Entities.News> values;

	   public static News newInstance() {
		   News fragment = new News();  
	     return fragment;  
	   }  

	   public News() {  
	   }  

	   @Override  
	   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
	       View rootView = inflater.inflate(R.layout.news, container, false);  
	  
	       database = InitDatabase.getInstance(getActivity());
	       values = database.getNews();
	       textView = (TextView) rootView.findViewById(R.id.text);
	       
	       String txt = "";
	       for(int i = 0; i < values.size(); i++)
	       {
	    	   txt = txt + "\nDate: " + values.get(i).getDate();
	    	   txt = txt + " -- Title: " + values.get(i).getTitle();
	    	   txt = txt + " -- Body: " + values.get(i).getBody() + "\n";
	       }
	       textView.setText(txt);
	       
	       return rootView;  
	   }
	   

	   @Override  
	   public void onAttach(Activity activity) {  
	       super.onAttach(activity);  
	       ((Base) activity).onSectionAttached(5);  
	   }  
}  
