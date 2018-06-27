package suntechnologies.com.sunsurvey;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TakeSurveyUser extends Activity implements View.OnClickListener  {

    String TAG = "test";
    TextView questionText,surveyName;
    RadioButton radioButtonA, radioButtonB, radioButtonC,radioButtonD;
    Button contine, next;

    LinearLayout takeSurveyquestion,surveyNameLayout;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public static final String MY_PREFS_VALUE = "SESSION_TOKEN";
    String serveyId;
    ArrayList<CreateSurveyQuestion> questionArryList=new ArrayList<>();;

    CreateSurveyQuestion question;
    int count=0;
    RadioGroup radioGroup;
    RadioButton answer;
    FirebaseDatabase database;
    DatabaseReference ref;
    String sessionToken;
    int questionID = 0;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
         database = FirebaseDatabase.getInstance();

        Bundle bundle = getIntent().getExtras();

        takeSurveyquestion = findViewById(R.id.take_surveyquestion);
        surveyNameLayout = findViewById(R.id.surveyNameLayout);
        contine = findViewById(R.id.contine);
        surveyName = findViewById(R.id.surveyName);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        sessionToken = prefs.getString(MY_PREFS_VALUE, null);

        surveyName.setText((String)bundle.get("createSurvey"));
        serveyId = (String) bundle.get("surveyID");
        questionArryList = (ArrayList<CreateSurveyQuestion>)bundle.get("QuestionList");
        mDatabase = FirebaseDatabase.getInstance().getReference("SurveyAnswer").child(String.valueOf(serveyId));

        contine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surveyNameLayout.setVisibility(View.GONE);
                takeSurveyquestion.setVisibility(View.VISIBLE);
            }
        });



        setupLayout();

    }
    private void setupLayout() {
        setupLayoutElements();
        setupView();
    }
   private void setupLayoutElements(){
        questionText = (TextView) findViewById(R.id.textQuestion);
        radioButtonA = (RadioButton) findViewById(R.id.optionA);
        radioButtonB = (RadioButton) findViewById(R.id.optionB);
        radioButtonC = (RadioButton) findViewById(R.id.optionC);
        radioButtonD = (RadioButton) findViewById(R.id.optiond);
       Button  next =  findViewById(R.id.next);
       next.setOnClickListener((View.OnClickListener) this);
    }
    private void setupView() {
        questionText.setText("Question:"+questionID+" "+ questionArryList.get(questionID).question);
        radioButtonA.setText(questionArryList.get(questionID).getOption().get(0));
        radioButtonB.setText(questionArryList.get(questionID).getOption().get(1));
        radioButtonC.setText(questionArryList.get(questionID).getOption().get(2));
        radioButtonD.setText(questionArryList.get(questionID).getOption().get(3));

    }
    @Override
    public void onClick(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.optionsGroup);
        RadioButton answer = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());


        writeNewUser(String.valueOf(serveyId) ,questionArryList.get(questionID).question, questionID,answer.getText().toString()) ;
        questionID++;
        if (surveyNotFinished()) {

            question = questionArryList.get(questionID);
            setupView();
        } else {
            Intent intent = new Intent(this, FeedBackUser.class);
            startActivity(intent);
            finish();
        }
    }
    private boolean surveyNotFinished() {
        return questionID < questionArryList.size();
    }
    private void writeNewUser(String serveyId,String question,int questionID,String answer ) {

            ServeyTakeAnswer serveyTakeAnswer = new ServeyTakeAnswer(question,answer);

        mDatabase.child(String.valueOf(questionID)).setValue(serveyTakeAnswer);



    }
}
