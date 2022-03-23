package com.example.newsapp.Retrofit;

import com.example.newsapp.Model.Category.CategoryResponse;


import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

//    @GET("/top-headlines")
//    Call<TopHeadlinesResponse> getTopHeadlines(@Query("Api_Key") String ApiKey);

    @GET("top-headlines")
    Call<CategoryResponse> getTopHeadlinesWithCountry(@Query("country") String countryName, @Query("apiKey") String ApiKey);

    @GET("top-headlines")
    Call<CategoryResponse> getCategoryResponse(@Query("country") String countryName,@Query("category") String category, @Query("apiKey") String ApiKey);

    @GET("everything")
    Call<CategoryResponse> getEverything(@Query("q") String query, @Query("apiKey") String ApiKey);

}
