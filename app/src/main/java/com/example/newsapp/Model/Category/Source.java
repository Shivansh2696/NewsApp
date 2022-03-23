package com.example.newsapp.Model.Category;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Source implements Serializable {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	public void setName(String name){
		this.name = name;
	}


	public String getName(){
		return name;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}