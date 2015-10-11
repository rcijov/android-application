package com.example.halep;

import java.util.ArrayList;

public class Pages {

	private static Pages instance = null;
	
	public static ArrayList<Page> arrPages = new ArrayList<Page>();
	
	public static ArrayList<String> arrTitles = new ArrayList<String>();
	
	/* HOME, NEWS, RANKS, RESULTS, PHOTOS, VIDEOS */
	public Pages()
	{
		if(arrPages.size() == 0)
		{
			addPage("HOME");
	        addPage("NEWS");
	        addPage("RANKS");
	        addPage("RESULTS");
	        addPage("PHOTOS");
	        addPage("VIDEOS");
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
		addBody(page);
	}
	
	
	
	/**
	 * Add body of the page
	 * HOME, NEWS, RANKS, RESULTS, PHOTOS, VIDEOS
	 * @param page
	 */
	private static void addBody(Page page)
	{
		switch (page.type) 
		{
	        case HOME:
	        	page.body = "HOME";
	            break;
	        case NEWS:
	        	page.body = "NEWS";
	            break;
	        case RANKS:
	        	page.body = "RANKS";
	            break;
	        case RESULTS:
	        	page.body = "RESULTS";
	            break;
	        case PHOTOS:
	        	page.body = "PHOTOS";
	            break;
	        case VIDEOS:
	        	page.body = "VIDEOS";
	            break;
		}
				
	}
}

