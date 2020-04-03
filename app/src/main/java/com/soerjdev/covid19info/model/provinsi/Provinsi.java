package com.soerjdev.covid19info.model.provinsi;

import com.google.gson.annotations.SerializedName;

public class Provinsi{

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
			"Provinsi{" + 
			"attributes = '" + attributes + '\'' + 
			"}";
		}
}