package suntechnologies.com.sunsurvey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

public class CreateSurvey extends Activity {

    String TAG = "test";
    Button createSurvey,sumbit;
    EditText opt1,opt2,opt3,opt4,question;
    ArrayList<String> option = new ArrayList<>();
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_survey);
        mDatabase = FirebaseDatabase.getInstance().getReference("SurveyQuestion");
        createSurvey = findViewById(R.id.createSurvey);
        sumbit = findViewById(R.id.submit);
        question = findViewById(R.id.question);
        opt1 = findViewById(R.id.opt1);
        opt2 = findViewById(R.id.opt2);
        opt3 = findViewById(R.id.opt3);
        opt4 = findViewById(R.id.opt4);

        createSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createSurvey.setVisibility(View.GONE);

            }
        });

        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int serveyId = (gen());

                 option.add(opt1.getText().toString().trim());
                 option.add(opt2.getText().toString().trim());
                 option.add(opt3.getText().toString().trim());
                 option.add(opt4.getText().toString().trim());


                writeNewUser(String.valueOf(serveyId),question.getText().toString().trim(), option) ;



            }
        });



    }
    public int gen() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
    private void writeNewUser(String serveyId, String question, ArrayList<String>options) {

        CreateSurveyQuestion createSurveyQuestion = new CreateSurveyQuestion(options);
        mDatabase.child(serveyId).child(question).setValue(createSurveyQuestion);
        Intent intent = new Intent(CreateSurvey.this, ShareQuestionUser.class);
        Bundle bundle = new Bundle();


        bundle.putString("serveyId", serveyId);

        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
