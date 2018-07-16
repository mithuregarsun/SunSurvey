package suntechnologies.com.sunsurvey.viewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import suntechnologies.com.sunsurvey.R;

public class TotalSurveyViewHolder extends  RecyclerView.ViewHolder  {
    public TextView surveyTitle, count;
    public ImageView thumbnail;
   public CardView card_view;
    public TotalSurveyViewHolder(View view) {
        super(view);
        surveyTitle = (TextView) view.findViewById(R.id.title);
        count = (TextView) view.findViewById(R.id.count);
        thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        card_view = (CardView) view.findViewById(R.id.card_view);

    }
}
