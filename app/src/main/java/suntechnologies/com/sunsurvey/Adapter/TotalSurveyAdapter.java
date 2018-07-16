package suntechnologies.com.sunsurvey.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import suntechnologies.com.sunsurvey.MainActivity;
import suntechnologies.com.sunsurvey.Models.SurveyName;
import suntechnologies.com.sunsurvey.R;
import suntechnologies.com.sunsurvey.SurveyResponse;
import suntechnologies.com.sunsurvey.TakeSurveyUser;
import suntechnologies.com.sunsurvey.interfaces.RecyclerViewClickListener;
import suntechnologies.com.sunsurvey.viewHolder.TotalSurveyViewHolder;

public class TotalSurveyAdapter extends RecyclerView.Adapter<TotalSurveyViewHolder> {
    ArrayList<SurveyName>iistLtem;
    Context context;
    Activity activity;
    private static RecyclerViewClickListener itemListener;
    public TotalSurveyAdapter(Activity activity,ArrayList<SurveyName> iistLtem){
        this.iistLtem = iistLtem;
        this.activity = activity;

    }
    @Override
    public TotalSurveyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewGroup itemView = (ViewGroup) layoutInflater.inflate(R.layout.survey_item,parent,false);

        return new TotalSurveyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(TotalSurveyViewHolder holder, final int position) {
             SurveyName surveyName = iistLtem.get(position);
             holder.surveyTitle.setText(surveyName.name);
             holder.count.setText(surveyName.response);

       holder.card_view.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               SurveyName surveyName = iistLtem.get(position);
               Intent intent = new Intent(activity, SurveyResponse.class);
               intent.putExtra("surveyID", surveyName.surveyId);
               activity.startActivity(intent);
           }
       });

    }

    @Override
    public int getItemCount() {
        return iistLtem.size();
    }
}
