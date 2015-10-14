package com.simona.halep.Pages;

import com.simona.halep.Base;
import com.simona.halep.R;
import com.simona.halep.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

public class Results extends Fragment {  

	   public static Results newInstance() {
		   Results fragment = new Results();  
	     return fragment;  
	   }  

	   public Results() {  
	   }  

	   @Override  
	   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
	       View rootView = inflater.inflate(R.layout.results, container, false);  
	       return rootView;  
	   }  

	   @Override  
	   public void onAttach(Activity activity) {  
	       super.onAttach(activity);  
	       ((Base) activity).onSectionAttached(4);  
	   }  
}  
