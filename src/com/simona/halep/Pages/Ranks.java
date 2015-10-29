package com.simona.halep.Pages;

import java.util.List;

import com.simona.halep.Base;
import com.simona.halep.R;
import com.simona.halep.Database.InitDatabase;
import com.simona.halep.Database.RanksDataSource;
import com.simona.halep.Database.StatsDataSource;
import com.simona.halep.Database.Entities.Rank;
import com.simona.halep.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.Fragment;

public class Ranks extends Fragment {  

	   private InitDatabase database;
	   private TextView textView;
	   private List<Rank> values;
	
	   public static Ranks newInstance() {
		   Ranks fragment = new Ranks();  
	     return fragment;  
	   }  

	   public Ranks() {  
	   }  

	   @Override  
	   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
	       View rootView = inflater.inflate(R.layout.ranks, container, false);  
	       
	       database = InitDatabase.getInstance(getActivity());
	       values = database.getRanks();
	       textView = (TextView) rootView.findViewById(R.id.text);

	       
	       String txt = "Date 	--   Tournament 	-- 	Round 	-- 	Points ";
	       for(int i = 0; i < values.size(); i++)
	       {
	    	   txt = txt + "\n" + values.get(i).getDate();
	    	   txt = txt + " --    " + values.get(i).getTournament();
	    	   txt = txt + " --    " + values.get(i).getRound();
	    	   txt = txt + " --    " + values.get(i).getPoints();
	       }
	       textView.setText(txt);
	       
	       return rootView;  
	   }  

	   @Override  
	   public void onAttach(Activity activity) {  
	       super.onAttach(activity);  
	       ((Base) activity).onSectionAttached(2);  
	   }  
}  
