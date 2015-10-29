package com.simona.halep.Database.Entities;

public class Stats {

	  private long id;
	  private String stat;
	  private String nrStatYtd;
	  private String nrStatCar;

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }
	  
	  public String getStat() {
	    return stat;
	  }

	  public void setStat(String stat) {
	    this.stat = stat;
	  }
	  
	  public String getNrStatYtd() {
	    return nrStatYtd;
	  }

	  public void setNrStatYtd(String nrStatYtd) {
	    this.nrStatYtd = nrStatYtd;
	  }
	  
	  public String getNrStatCar() {
	    return nrStatCar;
	  }

	  public void setNrStatCar(String nrStatCar) {
	    this.nrStatCar = nrStatCar;
	  }
	  
}
