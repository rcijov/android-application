package com.example.halep;

import java.util.ArrayList;

public class Pages {

	private static Pages instance = null;
	
	public static ArrayList<Page> arrPages = new ArrayList<Page>();
	
	public static ArrayList<String> arrTitles = new ArrayList<String>();
	
	public Pages()
	{
		if(arrPages.size() == 0)
		{
			addPage("Home");
	        addPage("Ranks");
	        addPage("Results");
	        addPage("News");
	        addPage("Photos");
	        addPage("Videos");
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
		arrPages.add(page);
		addBody(page);
	}
	
	/**
	 * Add body of the page
	 * @param page
	 */
	private static void addBody(Page page)
	{
		page.body = "hello";
	}
}

