package com.example.upgradapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.upgradapp.R;
import com.example.upgradapp.adapter.TagsAdapter;
import com.example.upgradapp.model.TagsItem;
import com.example.upgradapp.model.TagsResponse;
import com.example.upgradapp.retrofit.APIClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInterestActivity extends AppCompatActivity {

    private RecyclerView tags_recycler_view;
    private List<TagsItem> tagsItems = new ArrayList<>();
    private TagsAdapter adapter;
    public static Button nextButton;
    public static Button clearButton;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interest);
        Toolbar toolbar =  findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Select tags of your choice");
        getTAGS();

        tags_recycler_view = findViewById(R.id.tags_recycler_view);
        nextButton = findViewById(R.id.nextButton);
        clearButton = findViewById(R.id.clearButton);
        mProgressBar = findViewById(R.id.progressBar);

        adapter = new TagsAdapter(this, tagsItems);

        LinearLayoutManager manager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        tags_recycler_view.setLayoutManager(manager);
        tags_recycler_view.setHasFixedSize(true);
        tags_recycler_view.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        tags_recycler_view.addItemDecoration(itemDecoration);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Integer> list = new ArrayList<>();
                list.add(adapter.getSelectionIds().keyAt(0));
                list.add(adapter.getSelectionIds().keyAt(1));
                list.add(adapter.getSelectionIds().keyAt(2));
                list.add(adapter.getSelectionIds().keyAt(3));
                Bundle bundle = new Bundle();
                bundle.putIntegerArrayList("tagsPosition", (ArrayList<Integer>) list);
                Intent intent = new Intent(UserInterestActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("tagsPos",bundle);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void getTAGS(){

        APIClient.getmInstance().getApi().getTags()
                .enqueue(new Callback<TagsResponse>() {
                    @Override
                    public void onResponse(Call<TagsResponse> call, Response<TagsResponse> response) {
                        if(response.isSuccessful()){

                            mProgressBar.setVisibility(View.GONE);
                            tagsItems = response.body().getItems();

                            adapter.addAllItems(tagsItems);

                        }
                    }

                    @Override
                    public void onFailure(Call<TagsResponse> call, Throwable t) {


                    }
                });


    }
}
