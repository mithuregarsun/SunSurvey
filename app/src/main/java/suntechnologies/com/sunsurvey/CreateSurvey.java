package suntechnologies.com.sunsurvey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

import suntechnologies.com.sunsurvey.utility.Helper;
public class CreateSurvey extends AppCompatActivity implements OnItemSelectedListener {

    String TAG = "test",answer;
    Button createSurvey,sumbit,next,enterQuestion;
    EditText opt1,opt2,opt3,opt4,question,survey_name;
    TextView questionCount;
    Spinner spinner;
    LinearLayout createSurveyLayout,createSurveyNameLayout;
    ArrayList<String> option = new ArrayList<>();
    int serveyId;
    HashMap<String ,CreateSurveyQuestion>stringCreateSurveyQuestionHashMap;

    private DatabaseReference mDatabase;

    String quest ;
    String optionFirst;
    String optionSecond;
    String optionThird;
    String optionFourth;


    int i =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_survey);

        createSurvey = findViewById(R.id.createSurvey);
        createSurveyLayout = findViewById(R.id.createSurveyLayout);
        createSurveyNameLayout = findViewById(R.id.createSurveyName);
        next = findViewById(R.id.next);
        questionCount = findViewById(R.id.questionCount);
        sumbit = findViewById(R.id.submit);
        enterQuestion = findViewById(R.id.enterQuestion);
        survey_name = findViewById(R.id.survey_name);
        question = findViewById(R.id.question);
        opt1 = findViewById(R.id.opt1);
        opt2 = findViewById(R.id.opt2);
        opt3 = findViewById(R.id.opt3);
        opt4 = findViewById(R.id.opt4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setting the title
        toolbar.setTitle("Sun Survey");


        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);
         spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        List<String> optionList = new ArrayList<>();
        optionList.add("Select your answer");
        optionList.add(opt2.getText().toString());
        optionList.add(opt3.getText().toString());
        optionList.add(opt4.getText().toString());


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, optionList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        enterQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(survey_name.getText().toString() != null){
                    mDatabase = FirebaseDatabase.getInstance().getReference("CreateSurveyName").child(String.valueOf(serveyId));
                    mDatabase.child("survey_name").setValue(survey_name.getText().toString());
                    createSurveyNameLayout.setVisibility(View.GONE);

                    createSurveyLayout.setVisibility(View.VISIBLE);
                }else{
                    Helper.showDialog(CreateSurvey.this,"Alert","Please create survey name!");
                }


            }
        });

        createSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createSurveyNameLayout.setVisibility(View.VISIBLE);
                createSurvey.setVisibility(View.GONE);

                serveyId = (gen());

            }
        });

        //questionCount.setText(String.valueOf(i)+" Out off "+10);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                 quest = question.getText().toString();
                 optionFirst = opt1.getText().toString();
                 optionSecond = opt2.getText().toString();
                 optionThird = opt3.getText().toString();
                 optionFourth = opt4.getText().toString();


                 if(quest.length()>0 && optionFirst.length()>0 && optionSecond.length()>0 && optionThird.length()>0 && optionFourth.length()>0)
                 {



                     int current_value = i++;

                     Log.d("CurrentValue",String.valueOf(current_value));
                     questionCount.setText(String.valueOf(++current_value)+" Out off "+20);

                     sumbit.setText("Submit");
                     option.add(opt1.getText().toString());
                     option.add(opt2.getText().toString());
                     option.add(opt3.getText().toString());
                     option.add(opt4.getText().toString());
                     answer = "test";
                     stringCreateSurveyQuestionHashMap = new HashMap<>();
                     stringCreateSurveyQuestionHashMap.put(String.valueOf(i),new CreateSurveyQuestion(quest,answer,option));

                     writeNewUser(String.valueOf(serveyId) ,stringCreateSurveyQuestionHashMap) ;
                     if(i==20){

                         Intent intent = new Intent(CreateSurvey.this, ShareQuestionUser.class);
                         Bundle bundle = new Bundle();
                         bundle.putString("serveyId", String.valueOf(serveyId));
                         intent.putExtras(bundle);
                         startActivity(intent);
                     }


                 }else{
                     Helper.showDialog(CreateSurvey.this,"Alert","Please complete question/options !");
                 }


            }
        });
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quest = question.getText().toString();
                optionFirst = opt1.getText().toString();
                optionSecond = opt2.getText().toString();
                optionThird = opt3.getText().toString();
                optionFourth = opt4.getText().toString();


                if(quest.length()>0 && optionFirst.length()>0 && optionSecond.length()>0 && optionThird.length()>0 && optionFourth.length()>0)
                {



                    int current_value = i++;

                    Log.d("CurrentValue",String.valueOf(current_value));
                    questionCount.setText(String.valueOf(++current_value)+" Out off "+20);

                    sumbit.setText("Submit");
                    option.add(opt1.getText().toString());
                    option.add(opt2.getText().toString());
                    option.add(opt3.getText().toString());
                    option.add(opt4.getText().toString());
                    answer = "test";
                    stringCreateSurveyQuestionHashMap = new HashMap<>();
                    stringCreateSurveyQuestionHashMap.put(String.valueOf(i),new CreateSurveyQuestion(quest,answer,option));

                    writeNewUser(String.valueOf(serveyId) ,stringCreateSurveyQuestionHashMap) ;



                        Intent intent = new Intent(CreateSurvey.this, ShareQuestionUser.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("serveyId", String.valueOf(serveyId));
                        intent.putExtras(bundle);
                        startActivity(intent);




                }else{
                    Helper.showDialog(CreateSurvey.this,"Alert","Please complete question/options !");
                }




            }
        });

        findViewById(R.id.mainLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    Helper.hideSoftKeyboard(CreateSurvey.this);
                    return false;
                }
                else{
                    return true;
                }

            }



        });
        findViewById(R.id.createSurveyName).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    Helper.hideSoftKeyboard(CreateSurvey.this);
                    return false;
                }
                else{
                    return true;
                }

            }



        });
        findViewById(R.id.createSurveyLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    Helper.hideSoftKeyboard(CreateSurvey.this);
                    return false;
                }
                else{
                    return true;
                }

            }



        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String answer = parent.getItemAtPosition(position).toString();

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    public int gen() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
    private void writeNewUser(String serveyId, HashMap<String,CreateSurveyQuestion>stringCreateSurveyQuestionHashMap) {

         answer = "test";
        mDatabase = FirebaseDatabase.getInstance().getReference("SurveyQuestion").child(serveyId);

         opt1.setText("");opt2.setText("");opt3.setText("");opt4.setText("");question.setText("");

        for (Map.Entry<String, CreateSurveyQuestion> entry : stringCreateSurveyQuestionHashMap.entrySet()) {
          //  mDatabase.setValue(entry.getValue());
            mDatabase.child(String.valueOf(entry.getKey())).setValue(entry.getValue());

        }
        option.clear();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.menuLogout:

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(CreateSurvey.this, AdminLogin.class);
                startActivity(intent);
                finish();
                break;

        }
        return true;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
