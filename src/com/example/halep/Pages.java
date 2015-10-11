package com.example.halep;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

public class Pages extends Home {

	private static Pages instance = null;
	
	public static ArrayList<Page> arrPages = new ArrayList<Page>();
	
	public static ArrayList<String> arrTitles = new ArrayList<String>();
	
	/* HOME, NEWS, RANKS, RESULTS, PHOTOS, VIDEOS */
	private static final String[] lookupPages = new String[] {
			"HOME",
			"NEWS",
			"RANKS",
			"RESULTS",
			"PHOTOS",
			"VIDEOS"
	};
	
	public Pages()
	{
		if(arrPages.size() == 0)
		{
			for(String str : lookupPages)
			{
				addPage(str);
			}	        
		}
	}
	
	public static Pages getInstance() {
	      if(instance == null) {
	         instance = new Pages();
	      }
	      return instance;
	}

	/**
	 * Add Page
	 * @param title
	 */
	private static void addPage(String title)
	{
		Page page = new Page();
		page.title = title;
		arrTitles.add(title);
		page.type = TypePage.valueOf(title);
		arrPages.add(page);
	}
	
	
}

