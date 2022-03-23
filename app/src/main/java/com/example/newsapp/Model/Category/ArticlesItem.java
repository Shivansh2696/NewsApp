package com.example.newsapp.Model.Category;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.example.newsapp.BR;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Observable;

public class ArticlesItem extends BaseObservable implements Serializable {

	@SerializedName("publishedAt")
	private String publishedAt;

	@SerializedName("author")
	private String author;

	@SerializedName("urlToImage")
	private String urlToImage;

	@SerializedName("description")
	private String description;

	@SerializedName("source")
	private Source source;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	@SerializedName("content")
	private String content;

	@Bindable
	public String getPublishedAt() {
		return publishedAt;
	}

	@Bindable
	public String getAuthor() {
		return author;
	}

	@Bindable
	public String getUrlToImage() {
		return urlToImage;
	}

	@Bindable
	public String getDescription() {
		return description;
	}

	@Bindable
	public Source getSource() {
		return source;
	}

	@Bindable
	public String getTitle() {
		return title;
	}

	@Bindable
	public String getUrl() {
		return url;
	}

	@Bindable
	public String getContent() {
		return content;
	}

	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
		notifyPropertyChanged(BR.publishedAt);
	}

	public void setAuthor(String author) {
		this.author = author;
		notifyPropertyChanged(BR.author);
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
		notifyPropertyChanged(BR.urlToImage);
	}

	public void setDescription(String description) {
		this.description = description;
		notifyPropertyChanged(BR.description);
	}

	public void setSource(Source source) {
		this.source = source;
		notifyPropertyChanged(BR.source);
	}

	public void setTitle(String title) {
		this.title = title;
		notifyPropertyChanged(BR.title);
	}

	public void setUrl(String url) {
		this.url = url;
		notifyPropertyChanged(BR.url);
	}

	public void setContent(String content) {
		this.content = content;
		notifyPropertyChanged(BR.content);
	}
}