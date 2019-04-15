package com.example.upgradapp.dataSource;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.upgradapp.model.QuestionsItem;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class QuestionViewModel extends ViewModel {


    LiveData<PagedList<QuestionsItem>> questionPagedList;
    LiveData<QuestionDataSource> pageKeyedDataSourceLiveData;


    public QuestionViewModel() {


        Executor executor = Executors.newFixedThreadPool(5);

        QuestionDataSourceFactory questionDataSource = new QuestionDataSourceFactory();

        pageKeyedDataSourceLiveData = questionDataSource.getMutableLiveData();

        PagedList.Config config =
                (new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setPageSize(30))
                        .build();

        questionPagedList =  (new LivePagedListBuilder(questionDataSource,config))
                .setFetchExecutor(executor)
                .build();

    }

    public LiveData<PagedList<QuestionsItem>> getQuestionPagedList() {
        return questionPagedList;
    }


}
