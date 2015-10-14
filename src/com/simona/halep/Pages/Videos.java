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

public class Videos extends Fragment {  

	   public static Videos newInstance() {
		   Videos fragment = new Videos();  
	     return fragment;  
	   }  

	   public Videos() {  
	   }  

	   @Override  
	   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
	       View rootView = inflater.inflate(R.layout.videos, container, false);  
	       return rootView;  
	   }  

	   @Override  
	   public void onAttach(Activity activity) {  
	       super.onAttach(activity);  
	       ((Base) activity).onSectionAttached(7);  
	   }  
}  
