package suntechnologies.com.sunsurvey.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import suntechnologies.com.sunsurvey.Models.CreateSurveyQuestion;
import suntechnologies.com.sunsurvey.R;
import suntechnologies.com.sunsurvey.viewHolder.AllQuestionSurveyViewHolder;

public class AllQuestionSurveyAdapter extends RecyclerView.Adapter<AllQuestionSurveyViewHolder> {

    ArrayList<CreateSurveyQuestion> surveyQuestionArrayList ;
    Activity activity;

    public AllQuestionSurveyAdapter(Activity activity,ArrayList<CreateSurveyQuestion> surveyQuestionArrayList){
        this.activity =activity;
        this.surveyQuestionArrayList = surveyQuestionArrayList;

    }
    @Override
    public AllQuestionSurveyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewGroup itemView = (ViewGroup) layoutInflater.inflate(R.layout.all_surey_item,parent,false);

        return new AllQuestionSurveyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(AllQuestionSurveyViewHolder holder,final int position) {
        CreateSurveyQuestion createSurveyQuestion = surveyQuestionArrayList.get(position);
        holder.surveyQuestion.setText(createSurveyQuestion.answer);
        holder.option1.setText(createSurveyQuestion.option.optionA);
        holder.option2.setText(createSurveyQuestion.option.optionB);
        holder.option3.setText(createSurveyQuestion.option.optionC);
        holder.option4.setText(createSurveyQuestion.option.optionD);
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

    @Override
    public int getItemCount() {
        return surveyQuestionArrayList.size();
    }
}
