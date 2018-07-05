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
import suntechnologies.com.sunsurvey.Adapter.TotalSurveyAdapter;
import suntechnologies.com.sunsurvey.Models.DividerItemDecorations;
import suntechnologies.com.sunsurvey.Models.SurveyName;


public class TotalSurvey extends Activity {

    TotalSurveyAdapter totalSurveyAdapter;
    RecyclerView recyclerView;
    TotalSurvey totalSurvey;
    ArrayList<SurveyName> surveyNameArrayList = new ArrayList<>();
    private DatabaseReference createSurveyData,responseSurveyData;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_survey);
        recyclerView = findViewById(R.id.recycler_view);
        dialog = new SpotsDialog(this,"Loading....");
        dialog.setCancelable(false);
        dialog.show();
        createSurveyData = FirebaseDatabase.getInstance().getReference("CreateSurveyName/");
        createSurveyData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    String recordId = snap.getKey();
                    String username = (String) dataSnapshot.child(recordId).child("survey_name").getValue();

                    surveyNameArrayList.add(new SurveyName(recordId, username));

                }

                totalSurveyAdapter = new TotalSurveyAdapter(totalSurvey, surveyNameArrayList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.addItemDecoration(new DividerItemDecorations(TotalSurvey.this, DividerItemDecoration.VERTICAL,36));

                recyclerView.setAdapter(totalSurveyAdapter);
                dialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
