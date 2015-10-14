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

public class News extends Fragment {  

	   public static News newInstance() {
		   News fragment = new News();  
	     return fragment;  
	   }  

	   public News() {  
	   }  

	   @Override  
	   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
	       View rootView = inflater.inflate(R.layout.news, container, false);  
	       return rootView;  
	   }  

	   @Override  
	   public void onAttach(Activity activity) {  
	       super.onAttach(activity);  
	       ((Base) activity).onSectionAttached(5);  
	   }  
}  
