package com.simona.halep.Pages;

import java.util.List;

import com.simona.halep.Base;
import com.simona.halep.R;
import com.simona.halep.Database.NewsDataSource;
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

public class Photos extends Fragment {  
	
	   public static Photos newInstance() {
		   Photos fragment = new Photos();  
	     return fragment;  
	   }  

	   public Photos() {  
	   }  

	   @Override  
	   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
	       View rootView = inflater.inflate(R.layout.photos, container, false);  
	       return rootView;  
	   }  

	   @Override  
	   public void onAttach(Activity activity) {  
	       super.onAttach(activity);  
	       ((Base) activity).onSectionAttached(6);  
	   }  
}  
