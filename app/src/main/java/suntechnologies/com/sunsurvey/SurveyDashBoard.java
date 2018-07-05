package suntechnologies.com.sunsurvey;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dmax.dialog.SpotsDialog;
import suntechnologies.com.sunsurvey.Models.CreateSurveyQuestion;


public class SurveyDashBoard extends Activity {

   TextView totalSunSurveyResponse,totalSunSurvey;
   Button createSurvey,viewAllSurvey;
   int count =0;
   int count1 =0;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_dashboard);
        totalSunSurveyResponse = findViewById(R.id.totalSunSurveyResponse);
        totalSunSurvey = findViewById(R.id.totalSunSurvey);
        createSurvey = findViewById(R.id.createSurvey);
        viewAllSurvey = findViewById(R.id.viewAllSurvey);
        dialog = new SpotsDialog(this,"Loading....");
        dialog.setCancelable(false);
        dialog.show();

        createSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SurveyDashBoard.this, CreateSurvey.class);
                startActivity(intent);
            }
        });


        viewAllSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SurveyDashBoard.this, TotalSurvey.class);
                startActivity(intent);


            }
        });

         FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference ref,ref2;
        ref = database.getReference("SurveyQuestion/");
        ref2 = database.getReference("SurveyAnswer/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CreateSurveyQuestion surveyQuestionObj;
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    Log.d("safjsfw"+snap.getKey(),snap.getChildrenCount() + "");
                    count++;
                }
                totalSunSurvey.setText(String.valueOf(count));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CreateSurveyQuestion surveyQuestionObj;
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    Log.d("safjsfw"+snap.getKey(),snap.getChildrenCount() + "");
                    count1++;
                }
                totalSunSurveyResponse.setText(String.valueOf(count1));
                dialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }




}
