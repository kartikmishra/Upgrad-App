package com.example.upgradapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.upgradapp.R;
import com.example.upgradapp.model.TagsItem;
import com.example.upgradapp.ui.UserInterestActivity;

import java.util.List;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagsAdapterViewHolder> {

    private Context context;
    private List<TagsItem> tagsItems, selected;
    private int lastSelectedPosition = -1;
    private  int selected_position = RecyclerView.NO_POSITION;
    private SparseBooleanArray mSelected_items;

    public TagsAdapter(Context context, List<TagsItem> tagsItems) {
        this.context = context;
        this.tagsItems = tagsItems;
        mSelected_items = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public TagsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.tags_recycler_view_item,viewGroup,false);
        return new TagsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TagsAdapterViewHolder tagsAdapterViewHolder, final int i) {

        final TagsItem mTagsItem = tagsItems.get(i);

        if(mTagsItem!=null) {
            tagsAdapterViewHolder.tags_texview.setText(mTagsItem.getName());
            tagsAdapterViewHolder.tags_texview.setBackgroundColor(
                    mSelected_items.get(i) ? 0x9934B5E4 : Color.TRANSPARENT);


            tagsAdapterViewHolder.tags_texview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getSelectedCount() < 4) {
                        toggleSelection(tagsAdapterViewHolder.getAdapterPosition());
                    } else if (getSelectedCount() == 4) {
                        UserInterestActivity.nextButton.setVisibility(View.VISIBLE);
                        UserInterestActivity.clearButton.setVisibility(View.VISIBLE);

                        UserInterestActivity.clearButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mSelected_items.clear();
                                UserInterestActivity.clearButton.setVisibility(View.INVISIBLE);
                                UserInterestActivity.nextButton.setVisibility(View.INVISIBLE);

                            }
                        });
                    }
                }
            });
        }
    }

    public void toggleSelection(int position){
        selectView(position, !mSelected_items.get(position));
    }

    private void selectView(int position, boolean b) {
        if(b){
            mSelected_items.put(position,b);
        }
        else {
            mSelected_items.delete(position);
        }
        notifyDataSetChanged();
    }

    public void removeSelection(){
        mSelected_items = new SparseBooleanArray();
        notifyDataSetChanged();
    }
    public int getSelectedCount(){
        return mSelected_items.size();
    }

    public SparseBooleanArray getSelectionIds(){
        return mSelected_items;
    }


    public  void addAllItems(List<TagsItem> items) {
        tagsItems.addAll(items);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return tagsItems.size();
    }

    public class TagsAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tags_texview;
        public TagsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tags_texview = itemView.findViewById(R.id.tags_textview);

        }
    }

    public List<TagsItem> getSelected() {
        return selected;
    }
}
