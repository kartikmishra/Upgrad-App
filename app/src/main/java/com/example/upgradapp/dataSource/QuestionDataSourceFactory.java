package com.example.upgradapp.dataSource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.upgradapp.model.QuestionsItem;

public class QuestionDataSourceFactory extends QuestionDataSource.Factory<Long, QuestionsItem> {


    private MutableLiveData<QuestionDataSource> mutableLiveData = new MutableLiveData<>();



    @Override
    public DataSource<Long, QuestionsItem> create() {
        QuestionDataSource questionDataSource = new QuestionDataSource();
        mutableLiveData.postValue(questionDataSource);
        return questionDataSource;
    }

    public MutableLiveData<QuestionDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

}
