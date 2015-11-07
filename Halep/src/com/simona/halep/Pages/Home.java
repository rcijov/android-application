package com.simona.halep.Pages;

import com.simona.halep.Base;
import com.simona.halep.R;
import com.simona.halep.Database.InitDatabase;
import com.simona.halep.R.layout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

public class Home extends Fragment {  

	   private InitDatabase database;
	
	   public static Home newInstance() {
		   Home fragment = new Home();  
	     return fragment;  
	   }  

	   public Home() {  
	   }  

	   @Override  
	   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
	       View rootView = inflater.inflate(R.layout.fragment_home, container, false);  
	       
	       database = InitDatabase.getInstance(getActivity());
	       database.getNews();
	       database.getRanks();
	       database.getResults();
	       database.getStats();
	       
	       return rootView;  
	   }  

	   @Override  
	   public void onAttach(Activity activity) {  
	       super.onAttach(activity);  
	       ((Base) activity).onSectionAttached(1);  
	   }  
}  
