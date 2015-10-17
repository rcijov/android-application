package com.simona.halep.Pages;

import java.util.List;
import java.util.Random;

import com.simona.halep.Base;
import com.simona.halep.R;
import com.simona.halep.R.layout;
import com.simona.halep.Database.CommentsDataSource;
import com.simona.halep.Database.Comment;

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

public class News extends Fragment implements android.view.View.OnClickListener {  
	
	   private CommentsDataSource datasource;
	   private TextView textView;
	   private List<Comment> values;

	   public static News newInstance() {
		   News fragment = new News();  
	     return fragment;  
	   }  

	   public News() {  
	   }  

	   @Override  
	   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
	       View rootView = inflater.inflate(R.layout.news, container, false);  
	       
	       datasource = new CommentsDataSource(getActivity());
	       datasource.open();

	       values = datasource.getAllComments();
	       textView = (TextView) rootView.findViewById(R.id.text);
	       textView.setText(values.toString());
	       
	       ((Button)rootView.findViewById(R.id.add)).setOnClickListener(this);
	       ((Button)rootView.findViewById(R.id.delete)).setOnClickListener(this);
	       
	       return rootView;  
	   }
	   
//	   // Will be called via the onClick attribute
//	   // of the buttons in main.xml
	   public void onClick(View view) {
	     Comment comment = null;
	     switch (view.getId()) {
	     case R.id.add:
	       String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
	       int nextInt = new Random().nextInt(3);
	       // save the new comment to the database
	       comment = datasource.createComment(comments[nextInt]);
	       values.add(comment);
	       break;
	     case R.id.delete:
	       if (values.size() != 0) {
	         comment = (Comment) values.get(0);
	         datasource.deleteComment(comment);
	         values.remove(0);
	       }
	       break;
	     }
	     textView.setText(values.toString());
	   }

	   @Override  
	   public void onAttach(Activity activity) {  
	       super.onAttach(activity);  
	       ((Base) activity).onSectionAttached(5);  
	   }  
}  
