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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import suntechnologies.com.sunsurvey.Adapter.AllQuestionSurveyAdapter;
import suntechnologies.com.sunsurvey.Adapter.TotalSurveyAdapter;
import suntechnologies.com.sunsurvey.Adapter.UserResponseSurveyAdapter;
import suntechnologies.com.sunsurvey.Models.CreateSurveyQuestion;
import suntechnologies.com.sunsurvey.Models.DividerItemDecorations;
import suntechnologies.com.sunsurvey.Models.Options;
import suntechnologies.com.sunsurvey.Models.SurveyName;
import suntechnologies.com.sunsurvey.Models.UserResponse;

public class SurveyResponse extends Activity {

    UserResponseSurveyAdapter userResponseSurveyAdapter;
    RecyclerView recyclerView;


    private DatabaseReference createSurveyData;
    Dialog dialog;
    String serveyId;
    Activity activity;
    ArrayList<UserResponse> userResponseArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.response_activity);
        recyclerView = findViewById(R.id.recycler_view);
        Bundle bundle = getIntent().getExtras();
        serveyId = (String) bundle.get("surveyID");
        dialog = new SpotsDialog(this, "Loading....");
        dialog.setCancelable(false);
        dialog.show();

        createSurveyData = FirebaseDatabase.getInstance().getReference("SurveyQuestionForUser/" + serveyId);

        createSurveyData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {

                    String question = snap.child("question").getValue(String.class);

                    String optionC, optionA, optionD, optionB;
                    Object userIdA, userIdB, userIdC, userIdD;

                    Map<DataSnapshot, Object> map = (Map<DataSnapshot, Object>) snap.child("option").getValue();
                   ArrayList<String>options = new ArrayList<>();
                   ArrayList<Integer>arrayList = new ArrayList<>();
                    optionA = String.valueOf(map.get("optionA"));
                    optionB = (String) map.get("optionB");
                    optionC = (String) map.get("optionC");
                    optionD = (String) map.get("optionD");
                    userIdA = (Object) map.get("userIdA");
                    userIdB = (Object) map.get("userIdB");
                    userIdC = (Object) map.get("userIdC");
                    userIdD = (Object) map.get("userIdD");
                    options.add(optionA);
                    options.add(optionB);
                    options.add(optionC);
                    options.add(optionD);

                    int userCountA = 0, userCountB = 0, userCountC = 0, userCountD = 0;

                    if (userIdA != null && String.valueOf(userIdA).length() > 0) {
                        String[] parts = String.valueOf(userIdA).split(",");

                        userCountA = parts.length;
                    }
                    if (userIdB != null && String.valueOf(userIdB).length() > 0) {

                        String[] parts = String.valueOf(userIdB).split(",");
                        userCountB = parts.length;

                    }
                    if (userIdC != null && String.valueOf(userIdC).length() > 0) {

                        String[] parts = String.valueOf(userIdC).split(",");
                        userCountC = parts.length;

                    }
                    if (userIdD != null && String.valueOf(userIdD).length() > 0) {

                        String[] parts = String.valueOf(userIdD).split(",");
                        userCountD = parts.length;

                    }

                    arrayList.add(userCountA);
                    arrayList.add(userCountB);
                    arrayList.add(userCountC);
                    arrayList.add(userCountD);
                    userResponseArrayList.add(new UserResponse(options,arrayList,question, optionA, optionB, optionC, optionD, userCountA, userCountB, userCountC, userCountD, userCountA + userCountB + userCountC + userCountD));


                }

                activity = SurveyResponse.this;
                userResponseSurveyAdapter = new UserResponseSurveyAdapter(activity, userResponseArrayList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.addItemDecoration(new DividerItemDecorations(SurveyResponse.this, DividerItemDecoration.VERTICAL, 36));
                recyclerView.setAdapter(userResponseSurveyAdapter);
                dialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
