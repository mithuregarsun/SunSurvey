package suntechnologies.com.sunsurvey;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import suntechnologies.com.sunsurvey.Models.CreateSurveyQuestion;
import suntechnologies.com.sunsurvey.utility.Helper;

public class MainActivity extends Activity {
    EditText sunSurveyId;
    TextView adminLogin;
    Button takeSurvey;
    private FirebaseAuth auth;
    Dialog dialog;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public static final String MY_PREFS_VALUE = "SESSION_TOKEN";
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        sunSurveyId = findViewById(R.id.sunSurveyId);
        adminLogin = findViewById(R.id.adminLogin);
        takeSurvey = findViewById(R.id.take_survey);
        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        dialog = new SpotsDialog(this, "Searching Survey....");
        dialog.setCancelable(false);
        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdminLogin.class);
                startActivity(intent);
                finish();

            }
        });

        takeSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (sunSurveyId.getText().toString().trim().length() > 0)
                    auth.signInAnonymously().addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            dialog.show();

                            final FirebaseUser user = auth.getCurrentUser();

                            if (task.isSuccessful()) {

                                final FirebaseDatabase database = FirebaseDatabase.getInstance();

                                final DatabaseReference ref = database.getReference("CreateSurveyName/" + sunSurveyId.getText().toString().trim());

                                ref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        Log.d("sussss", String.valueOf(dataSnapshot.getValue()));


                                        if (dataSnapshot.getValue() != null) {
                                            dialog.dismiss();
                                            editor.putString(MY_PREFS_VALUE, user.getUid());
                                            editor.apply();
                                            String name = (String) dataSnapshot.child("survey_name").getValue();
                                         //   String userId = (String) dataSnapshot.child("userUsed").getValue();
                                            dataRecived(name, sunSurveyId.getText().toString().trim());
                                            Map<String,String> strings = new HashMap<>();
                                            strings.put("survey_name",name);
                                           // userMap.put("myUser", tempObject);

                                         //   String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());


                                            //ref .setValue(userMap);

                                            ref.child("userUsed").child(user.getUid()).setValue(strings);
                                            //
                                            // boolean test =   responseSession(name, user.getUid(),sunSurveyId.getText().toString().trim());
                                            //  Log.d("tere",String.valueOf(test));
                                    /*    if(test)
                                            Helper.showDialog(MainActivity.this,"Error","This survey is not available! ");
                                        else
                                            dataRecived( name,sunSurveyId.getText().toString().trim());*/

                                        } else {
                                            dialog.dismiss();
                                            Helper.showDialog(MainActivity.this, "Error", "This survey is not available! ");
                                        }


                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        System.out.println("The read failed: " + databaseError.getCode());
                                        Helper.showDialog(MainActivity.this, "Error", "This survey is not available! ");
                                        dialog.dismiss();
                                    }
                                });


                            } else {
                                dialog.dismiss();
                                Toast.makeText(MainActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                else {
                    dialog.dismiss();
                    Helper.showDialog(MainActivity.this, "Error", "Enter valid sun survey id...");
                }


            }
        });
    }

    public boolean responseSession(final String name, final String uid, final String serveyId) {
        final ArrayList<String> uidArryList = new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();


        DatabaseReference ref;
        ref = database.getReference("SurveyAnswer/" + serveyId);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean flag = false;

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                    Log.d("tere", childDataSnapshot.getKey());
                    uidArryList.add(childDataSnapshot.getKey());


                }
                for (int i = 0; i < uidArryList.size(); i++) {
                    if (uidArryList.get(i).equalsIgnoreCase(uid)) {
                        Log.d("tere", "match");
                        flag = true;


                    } else {
                        flag = false;


                    }
                }
                if (!flag) {
                    Log.d("tere", String.valueOf(flag) + "uid " + uid);
                } else {
                    Log.d("tere", String.valueOf(flag) + "uid " + uid);
                    dataRecived(name, serveyId);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        return true;
    }

    public void dataRecived(final String name, final String serveyId) {
        final ArrayList<CreateSurveyQuestion> questionArryList = new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference ref;
        ref = database.getReference("SurveyQuestion/" + serveyId);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                CreateSurveyQuestion surveyQuestionObj;

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                    surveyQuestionObj = childDataSnapshot.getValue(CreateSurveyQuestion.class);

                    questionArryList.add(new CreateSurveyQuestion(surveyQuestionObj.question, surveyQuestionObj.answer, surveyQuestionObj.option,surveyQuestionObj.surveyId,surveyQuestionObj.count));


                }


                if (questionArryList != null) {
                    Intent intent = new Intent(MainActivity.this, TakeSurveyUser.class);
                    intent.putExtra("QuestionList", questionArryList);
                    intent.putExtra("createSurvey", name);
                    intent.putExtra("surveyID", serveyId);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MainActivity.this.startActivity(intent);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
