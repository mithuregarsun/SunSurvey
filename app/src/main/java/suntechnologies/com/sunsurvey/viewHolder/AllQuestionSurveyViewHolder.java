package suntechnologies.com.sunsurvey.viewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import suntechnologies.com.sunsurvey.R;

public class AllQuestionSurveyViewHolder extends  RecyclerView.ViewHolder  {
    public TextView surveyQuestion, option1,option2,option3,option4;
    public ImageView thumbnail;
    public CardView card_view;

    public AllQuestionSurveyViewHolder(View view) {
        super(view);
        surveyQuestion = (TextView) view.findViewById(R.id.survey_question);
        option1 = (TextView) view.findViewById(R.id.op1);
        option2 = (TextView) view.findViewById(R.id.op2);
        option3 = (TextView) view.findViewById(R.id.op3);
        option4 = (TextView) view.findViewById(R.id.op4);
        card_view = (CardView) view.findViewById(R.id.card_view);


    }
}
