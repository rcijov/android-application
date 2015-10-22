package com.simona.halep.Database.Entities;

public class Result {

	  private long id;
	  private String date;
	  private String tournament;
	  private String round;
	  private String result;
	  private String opponent;
	  private String rank;
	  private String score;

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
	  
	  public String getResult() {
	    return result;
	  }

	  public void setResult(String result) {
	    this.result = result;
	  }
	  
	  public String getOpponent() {
	    return opponent;
	  }

	  public void setOpponent(String opponent) {
	    this.opponent = opponent;
	  }
	  
	  public String getRank() {
	    return rank;
	  }

	  public void setRank(String rank) {
	    this.rank = rank;
	  }
	  
	  public String getScore() {
	    return score;
	  }

	  public void setScore(String score) {
	    this.score = score;
	  }
}

