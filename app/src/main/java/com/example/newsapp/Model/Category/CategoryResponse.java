package com.example.newsapp.Model.Category;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CategoryResponse implements Serializable {

	@SerializedName("totalResults")
	private int totalResults;

	@SerializedName("articles")
	private List<ArticlesItem> articles;

	@SerializedName("status")
	private String status;

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}

	public void setArticles(List<ArticlesItem> articles){
		this.articles = articles;
	}

	public List<ArticlesItem> getArticles(){
		return articles;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
	public String toString() {
		return "CategoryResponse{" +
				"totalResults=" + totalResults +
				", articles=" + articles +
				", status='" + status + '\'' +
				'}';
	}
}