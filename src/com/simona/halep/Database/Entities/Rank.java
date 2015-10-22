package com.simona.halep.Database.Entities;

public class Rank {

	  private long id;
	  private String date;
	  private String tournament;
	  private String round;
	  private String points;

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
	  
	  public String getTournament() {
	    return tournament;
	  }

	  public void setTournament(String tournament) {
	    this.tournament = tournament;
	  }
	  
	  public String getRound() {
	    return round;
	  }

	  public void setRound(String round) {
	    this.round = round;
	  }
	  
	  public String getPoints() {
	    return points;
	  }

	  public void setPoints(String points) {
	    this.points = points;
	  }
}
