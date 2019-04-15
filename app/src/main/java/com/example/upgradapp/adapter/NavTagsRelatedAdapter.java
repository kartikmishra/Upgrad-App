package com.example.upgradapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.upgradapp.R;
import com.example.upgradapp.model.NavTagsItems;

import java.util.List;

public class NavTagsRelatedAdapter extends RecyclerView.Adapter<NavTagsRelatedAdapter.NavTagsRelatedAdapterViewHolder> {

    private Context context;
    private List<NavTagsItems> list;

    public NavTagsRelatedAdapter(Context context, List<NavTagsItems> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NavTagsRelatedAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.nav_tags_recycler_view_item,viewGroup,false);

        return new NavTagsRelatedAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NavTagsRelatedAdapterViewHolder navTagsRelatedAdapterViewHolder, int i) {

        if(list!=null){

            navTagsRelatedAdapterViewHolder.tags.setText("#" + list.get(i).getName());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  NavTagsRelatedAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tags;
        public NavTagsRelatedAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            tags = itemView.findViewById(R.id.nav_tags_tv);
        }
    }
}
