package com.example.upgradapp.adapter;

import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.upgradapp.R;
import com.example.upgradapp.model.QuestionsItem;

import java.util.ArrayList;
import java.util.List;

public class QuestionListAdapter extends PagedListAdapter<QuestionsItem, QuestionListAdapter.QuestionListAdapterViewHolder> {


    private Context context;
    private ListItemClickListener itemClickListener;
    public static QuestionsItem questionsItem;
    public static List<QuestionsItem> list = new ArrayList<>();
    private ListItemLongClickListener listItemLongClickListener;


    public QuestionListAdapter(Context context , ListItemClickListener listItemClickListener,
                               ListItemLongClickListener listItemLongClickListener){
        super(DIFF_CALLBACK);
        this.context = context;
        this.itemClickListener = listItemClickListener;
        this.listItemLongClickListener = listItemLongClickListener;

    }

    public interface ListItemClickListener{
        void onQuestionsListItemClick(int clickedItemIndex);
    }

    public interface ListItemLongClickListener{
        void onQuestionsLongListItemClick(int clickedItemIndex);
    }


    @NonNull
    @Override
    public QuestionListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(R.layout.question_recycler_view_item,viewGroup,false);
        return new QuestionListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionListAdapterViewHolder questionListAdapterViewHolder, int i) {

        questionsItem = getItem(i);
        if(questionsItem!=null){

            list.add(questionsItem);
            questionListAdapterViewHolder.questions_textview.setText(questionsItem.getTitle());
        }

    }

    public class QuestionListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        TextView questions_textview;

        public QuestionListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            questions_textview = itemView.findViewById(R.id.questionTextView);
            questions_textview.setOnClickListener(this);

            questions_textview.setOnLongClickListener(this);
        }


        @Override
        public void onClick(View v) {
            itemClickListener.onQuestionsListItemClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {

            listItemLongClickListener.onQuestionsLongListItemClick(getAdapterPosition());
            return true;
        }
    }

    private static DiffUtil.ItemCallback<QuestionsItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<QuestionsItem>() {
        @Override
        public boolean areItemsTheSame(QuestionsItem oldItem, QuestionsItem newItem) {
            return oldItem.getQuestionId() == newItem.getQuestionId();
        }

        @Override
        public boolean areContentsTheSame(QuestionsItem oldItem, QuestionsItem newItem) {
            return oldItem.equals(newItem);
        }
    };


    @Override
    public void onCurrentListChanged(@Nullable PagedList<QuestionsItem> currentList) {
        list = new ArrayList<>();
        super.onCurrentListChanged(currentList);
    }
}
