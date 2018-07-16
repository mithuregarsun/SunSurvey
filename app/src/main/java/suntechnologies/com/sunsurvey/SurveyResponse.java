package suntechnologies.com.sunsurvey;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import suntechnologies.com.sunsurvey.Adapter.AllQuestionSurveyAdapter;
import suntechnologies.com.sunsurvey.Adapter.TotalSurveyAdapter;
import suntechnologies.com.sunsurvey.Models.CreateSurveyQuestion;
import suntechnologies.com.sunsurvey.Models.DividerItemDecorations;
import suntechnologies.com.sunsurvey.Models.SurveyName;

public class SurveyResponse extends Activity {

    AllQuestionSurveyAdapter allQuestionSurveyAdapter;
    RecyclerView recyclerView;
    TotalSurvey totalSurvey;
    ArrayList<CreateSurveyQuestion> surveyQuestionArrayList = new ArrayList<>();
    private DatabaseReference createSurveyData,responseSurveyData;
    Dialog dialog;
    String serveyId;
Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.response_activity);
        recyclerView = findViewById(R.id.recycler_view);
        Bundle bundle = getIntent().getExtras();
        serveyId = (String) bundle.get("surveyID");
        dialog = new SpotsDialog(this,"Loading....");
        dialog.setCancelable(false);
        dialog.show();

        createSurveyData = FirebaseDatabase.getInstance().getReference("SurveyQuestion/"+serveyId);
        createSurveyData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                CreateSurveyQuestion surveyQuestionObj;
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    String recordId = snap.getKey();
                    Log.d("tesr",snap.getValue().toString());
                    surveyQuestionObj = snap.getValue(CreateSurveyQuestion.class);
                    surveyQuestionArrayList.add(new CreateSurveyQuestion(surveyQuestionObj.answer,surveyQuestionObj.question,surveyQuestionObj.option,surveyQuestionObj.surveyId,surveyQuestionObj.count));

                }
                activity = SurveyResponse.this;
                allQuestionSurveyAdapter = new AllQuestionSurveyAdapter(activity, surveyQuestionArrayList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.addItemDecoration(new DividerItemDecorations(SurveyResponse.this, DividerItemDecoration.VERTICAL,36));
                recyclerView.setAdapter(allQuestionSurveyAdapter);
                dialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
