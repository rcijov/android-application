package com.simona.halep.Database.Entities;

public class News {

	  private long id;
	  private String date;
	  private String title;
	  private String body;

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }
	  
	  public String getDate() {
	    return date;
	  }

	  public void setDate(String date) {
	    this.date = date;
	  }
	  
	  public String getTitle() {
	    return title;
	  }

	  public void setTitle(String title) {
	    this.title = title;
	  }
	  
	  public String getBody() {
	    return body;
	  }

	  public void setBody(String body) {
	    this.body = body;
	  }
	  
}
