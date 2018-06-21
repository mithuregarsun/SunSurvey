package suntechnologies.com.sunsurvey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class TakeSurveyUser extends Activity {

    String TAG = "test";
    TextView questionText;
    RadioButton radioButtonA, radioButtonB, radioButtonC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        questionText = (TextView) findViewById(R.id.textQuestion);
        radioButtonA = (RadioButton) findViewById(R.id.optionA);
        radioButtonB = (RadioButton) findViewById(R.id.optionB);
        radioButtonC = (RadioButton) findViewById(R.id.optionC);
       Button next =  findViewById(R.id.optionC);
         RadioGroup radioGroup = (RadioGroup) findViewById(R.id.optionsGroup);
         RadioButton answer = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());

       //  questionText.setText(question.getQuestion());
       //  radioButtonA.setText(question.getOptionA());
       // radioButtonB.setText(question.getOptionB());
       // radioButtonC.setText(question.getOptionC());
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("SurveyQuestion/17368");

// Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              //  Post post = dataSnapshot.getValue(Post.class);
                System.out.println(dataSnapshot);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TakeSurveyUser.this, FeedBackUser.class);
                startActivity(intent);
            }
        });

    }
}
