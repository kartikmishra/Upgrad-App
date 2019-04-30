package com.example.upgradapp.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.upgradapp.adapter.NavTagsAdapter;
import com.example.upgradapp.model.NavTagsItems;
import com.example.upgradapp.adapter.NavTagsRelatedAdapter;
import com.example.upgradapp.model.NavTagsResponse;
import com.example.upgradapp.adapter.QuestionListAdapter;
import com.example.upgradapp.model.QuestionsItem;
import com.example.upgradapp.R;
import com.example.upgradapp.model.TagsResponse;
import com.example.upgradapp.dataSource.QuestionViewModel;
import com.example.upgradapp.retrofit.APIClient;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavTagsAdapter.ListItemClickListener,
        QuestionListAdapter.ListItemClickListener, QuestionListAdapter.ListItemLongClickListener  {

    private static final String TAG = "MainActivity";
     private List<Integer> list = new ArrayList<>();
     private List<String> tagsList = new ArrayList<>();
     private List<NavTagsItems> navTagsItems = new ArrayList<>();
     private NavTagsAdapter adapter;
     private RecyclerView navTagsRecyclerView;
    private RecyclerView navTagsRelatedRecyclerView;
    private NavTagsRelatedAdapter navTagsRelatedAdapter;
    public static String tagName;
    private QuestionListAdapter questionListAdapter;
    private RecyclerView question_recycler_view;
    private QuestionViewModel viewModel;
    private ProgressBar mProgressBar;
    private TextView warning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getBundleExtra("tagsPos");

        list = bundle.getIntegerArrayList("tagsPosition");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        getTAGS();
        
        navTagsRecyclerView = findViewById(R.id.nav_tags_rv);
        navTagsRelatedRecyclerView = findViewById(R.id.nav_tags_related_rv);
        mProgressBar = findViewById(R.id.progressBar);
        warning = findViewById(R.id.warningTv);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager manager1 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        navTagsRecyclerView.setLayoutManager(manager);
        navTagsRecyclerView.setHasFixedSize(true);
        navTagsRelatedRecyclerView.setLayoutManager(manager1);
        navTagsRelatedRecyclerView.setHasFixedSize(true);
//        adapter = new NavTagsAdapter(this,tagsList);
//        recyclerView.setAdapter(adapter);


        question_recycler_view = findViewById(R.id.questions_recycler_view);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        question_recycler_view.addItemDecoration(itemDecoration);
        LinearLayoutManager manager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        question_recycler_view.setLayoutManager(manager2);
        question_recycler_view.setHasFixedSize(true);


        viewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        questionListAdapter = new QuestionListAdapter(this, this,this);
        viewModel.getQuestionPagedList().observe(this, new Observer<PagedList<QuestionsItem>>() {
            @Override
            public void onChanged(@Nullable PagedList<QuestionsItem> questionsItems) {


                questionListAdapter.submitList(questionsItems);

                questionListAdapter.notifyDataSetChanged();

            }
        });


        question_recycler_view.setAdapter(questionListAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void getTAGS(){

        APIClient.getmInstance().getApi().getTags()
                .enqueue(new Callback<TagsResponse>() {
                    @Override
                    public void onResponse(Call<TagsResponse> call, Response<TagsResponse> response) {

                        if(response.isSuccessful()){


                            tagsList.add(response.body().getItems().get(list.get(0)).getName());
                            tagsList.add(response.body().getItems().get(list.get(1)).getName());
                            tagsList.add(response.body().getItems().get(list.get(2)).getName());
                            tagsList.add(response.body().getItems().get(list.get(3)).getName());


                           adapter = new NavTagsAdapter(MainActivity.this,
                                   tagsList,MainActivity.this);
                           navTagsRecyclerView.setAdapter(adapter);


                        }
                    }

                    @Override
                    public void onFailure(Call<TagsResponse> call, Throwable t) {

                    }
                });
    }


    public void getNavTAGS(String name){

        APIClient.getmInstance().getApi().getNavTags(name, "stackoverflow")
                .enqueue(new Callback<NavTagsResponse>() {
                    @Override
                    public void onResponse(Call<NavTagsResponse> call, Response<NavTagsResponse> response) {
                        warning.setVisibility(View.GONE);
                        if(response.body() !=null){
                            navTagsItems = response.body().getItems();

                            navTagsRelatedAdapter = new NavTagsRelatedAdapter(MainActivity.this,navTagsItems);
                            navTagsRelatedRecyclerView.setAdapter(navTagsRelatedAdapter);


                        }
                    }

                    @Override
                    public void onFailure(Call<NavTagsResponse> call, Throwable t) {

                    }
                });
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {

        getNavTAGS(tagsList.get(clickedItemIndex));

        tagName = tagsList.get(clickedItemIndex);

        questionListAdapter.getCurrentList().getDataSource().invalidate();

        questionListAdapter.notifyDataSetChanged();

    }


    @Override
    public void onQuestionsListItemClick(int clickedItemIndex) {

        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("siteURL",QuestionListAdapter.list.get(clickedItemIndex).getLink());
        startActivity(intent);


    }

    @Override
    public void onQuestionsLongListItemClick(int clickedItemIndex) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = QuestionListAdapter.list.get(clickedItemIndex).getLink();
        String shareSub = "Link to the question : ";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share using"));

    }
}
