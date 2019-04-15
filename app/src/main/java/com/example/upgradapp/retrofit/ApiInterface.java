package com.example.upgradapp.retrofit;

import com.example.upgradapp.model.NavTagsResponse;
import com.example.upgradapp.model.QuestionResponse;
import com.example.upgradapp.model.TagsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("2.2/tags?page=1&order=desc&sort=popular&site=stackoverflow")
    Call<TagsResponse> getTags();

    @GET("2.2/tags")
    Call<NavTagsResponse> getNavTags(@Query("inname") String name , @Query("site") String site);

    @GET("2.2/questions")
    Call<QuestionResponse> getQuestions(@Query("page") long page, @Query("sort") String sort
        , @Query("tagged") String tagged , @Query("site") String site);
}
