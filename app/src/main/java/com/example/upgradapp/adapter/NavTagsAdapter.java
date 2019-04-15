package com.example.upgradapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.upgradapp.R;

import java.util.List;

public class NavTagsAdapter  extends RecyclerView.Adapter<NavTagsAdapter.NavTagsAdapterViewHolder> {


    private Context context;
    private List<String> selectedTagsList;
    private ListItemClickListener listItemClickListener;


    public NavTagsAdapter(Context context, List<String> selectedTagsList, ListItemClickListener listItemClickListener) {
        this.context = context;
        this.selectedTagsList = selectedTagsList;
        this.listItemClickListener = listItemClickListener;
    }

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }

    @NonNull
    @Override
    public NavTagsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.nav_tags_recycler_view_item,viewGroup,false);

        return new NavTagsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NavTagsAdapterViewHolder navTagsAdapterViewHolder, int i) {

        if(selectedTagsList!=null){
            navTagsAdapterViewHolder.tags.setText(selectedTagsList.get(i));
            navTagsAdapterViewHolder.tags.setTextColor(Color.rgb(255,69,0));
        }

    }


    public  void addAllItems(List<String> items) {
        selectedTagsList.addAll(items);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return selectedTagsList.size();
    }

    public class NavTagsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tags;

        public NavTagsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tags = itemView.findViewById(R.id.nav_tags_tv);
            tags.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int i = getAdapterPosition();
            listItemClickListener.onListItemClick(i);
        }
    }
}
