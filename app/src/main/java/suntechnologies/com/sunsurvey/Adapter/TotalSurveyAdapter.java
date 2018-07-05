package suntechnologies.com.sunsurvey.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import suntechnologies.com.sunsurvey.Models.SurveyName;
import suntechnologies.com.sunsurvey.R;
import suntechnologies.com.sunsurvey.viewHolder.TotalSurveyViewHolder;

public class TotalSurveyAdapter extends RecyclerView.Adapter<TotalSurveyViewHolder> {
    ArrayList<SurveyName>iistLtem;
    Context context;

    public TotalSurveyAdapter(Context context,ArrayList<SurveyName> iistLtem){
        this.iistLtem = iistLtem;
        this.context = context;

    }
    @Override
    public TotalSurveyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewGroup itemView = (ViewGroup) layoutInflater.inflate(R.layout.survey_item,parent,false);

        return new TotalSurveyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(TotalSurveyViewHolder holder, int position) {
             SurveyName surveyName = iistLtem.get(position);
         holder.surveyTitle.setText(surveyName.name);
        holder.count.setText(surveyName.surveyId);

    }

    @Override
    public int getItemCount() {
        return iistLtem.size();
    }
}
