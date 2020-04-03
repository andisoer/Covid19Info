package com.soerjdev.covid19info.model.worldata;

import com.google.gson.annotations.SerializedName;

public class WorldData{

	@SerializedName("attributes")
	private Attributes attributes;

	public void setAttributes(Attributes attributes){
		this.attributes = attributes;
	}

	public Attributes getAttributes(){
		return attributes;
	}

	@Override
 	public String toString(){
		return 
			"WorldData{" + 
			"attributes = '" + attributes + '\'' + 
			"}";
		}
}