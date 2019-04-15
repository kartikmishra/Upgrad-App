package com.example.upgradapp.dataSource;


import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.example.upgradapp.ui.MainActivity;
import com.example.upgradapp.model.QuestionResponse;
import com.example.upgradapp.model.QuestionsItem;
import com.example.upgradapp.retrofit.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionDataSource extends PageKeyedDataSource<Long, QuestionsItem>  {


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params,
                            @NonNull final LoadInitialCallback<Long, QuestionsItem> callback) {

        APIClient.getmInstance().getApi().getQuestions(1,"creation",
                MainActivity.tagName,"stackoverflow")
                .enqueue(new Callback<QuestionResponse>() {
                    @Override
                    public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {

                        if(response.body()!=null){
                            callback.onResult(response.body().getItems(),null,Long.parseLong("2"));
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionResponse> call, Throwable t) {

                    }
                });

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, QuestionsItem> callback) {

        APIClient.getmInstance().getApi().getQuestions(1,"creation",
                MainActivity.tagName,"stackoverflow")
                .enqueue(new Callback<QuestionResponse>() {
                    @Override
                    public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                        long key = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {
                            callback.onResult(response.body().getItems(), key);
                        }

                    }

                    @Override
                    public void onFailure(Call<QuestionResponse> call, Throwable t) {

                    }
                });


    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, QuestionsItem> callback) {


        APIClient.getmInstance().getApi().getQuestions(1,"creation",
                MainActivity.tagName,"stackoverflow")
                .enqueue(new Callback<QuestionResponse>() {
                    @Override
                    public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                        long nextKey = 0;
                        if (response.body() != null) {
                            nextKey = (params.key.equals(response.body().getQuotaMax())) ? null : params.key + 1;
                        }
                        if (response.body() != null) {
                            callback.onResult(response.body().getItems(), nextKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionResponse> call, Throwable t) {

                    }
                });

    }

}
