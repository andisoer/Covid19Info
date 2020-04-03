package com.soerjdev.covid19info.model.worldata;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("OBJECTID")
	private int OBJECTID;

	@SerializedName("Long_")
	private double Long_;

	@SerializedName("Recovered")
	private int Recovered;

	@SerializedName("Country_Region")
	private String Country_Region;

	@SerializedName("Active")
	private int Active;

	@SerializedName("Last_Update")
	private long Last_Update;

	@SerializedName("Deaths")
	private int Deaths;

	@SerializedName("Confirmed")
	private int Confirmed;

	@SerializedName("Lat")
	private double Lat;

	public void setOBJECTID(int oBJECTID){
		this.OBJECTID = oBJECTID;
	}

	public int getOBJECTID(){
		return OBJECTID;
	}

	public void setLong_(int Long_){
		this.Long_ = Long_;
	}

	public double getLong_(){
		return Long_;
	}

	public void setRecovered(int Recovered){
		this.Recovered = Recovered;
	}

	public int getRecovered(){
		return Recovered;
	}

	public void setCountry_Region(String Country_Region){
		this.Country_Region = Country_Region;
	}

	public String getCountry_Region(){
		return Country_Region;
	}

	public void setActive(int Active){
		this.Active = Active;
	}

	public int getActive(){
		return Active;
	}

	public void setLast_Update(long Last_Update){
		this.Last_Update = Last_Update;
	}

	public long getLast_Update(){
		return Last_Update;
	}

	public void setDeaths(int Deaths){
		this.Deaths = Deaths;
	}

	public int getDeaths(){
		return Deaths;
	}

	public void setConfirmed(int Confirmed){
		this.Confirmed = Confirmed;
	}

	public int getConfirmed(){
		return Confirmed;
	}

	public void setLat(int Lat){
		this.Lat = Lat;
	}

	public double getLat(){
		return Lat;
	}

	@Override
 	public String toString(){
		return 
			"Attributes{" + 
			"OBJECTID = '" + OBJECTID + '\'' +
			",Long_ = '" + Long_ + '\'' +
			",Recovered = '" + Recovered + '\'' +
			",Country_Region = '" + Country_Region + '\'' +
			",Active = '" + Active + '\'' +
			",Last_Update = '" + Last_Update + '\'' +
			",Deaths = '" + Deaths + '\'' +
			",Confirmed = '" + Confirmed + '\'' +
			",Lat = '" + Lat + '\'' +
			"}";
		}
}