package suntechnologies.com.sunsurvey;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends Activity {
    EditText sunSurveyId;
    TextView adminLogin;
    Button takeSurvey;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        sunSurveyId = findViewById(R.id.sunSurveyId);
        adminLogin = findViewById(R.id.adminLogin);
        takeSurvey = findViewById(R.id.take_survey);


       adminLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, AdminLogin.class);

               startActivity(intent);

           }
       });

        takeSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Toast.makeText(getApplicationContext(),"Are you ready to take survey ",Toast.LENGTH_SHORT).show();

                auth.signInAnonymously().addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        FirebaseUser user = auth.getCurrentUser();
                        if (task.isSuccessful()) {
                            Log.d("sussss", String.valueOf(user.getUid()));
                        }
                        else{
                            Toast.makeText( MainActivity.this, task.getException().toString(),Toast.LENGTH_SHORT).show();
                        }



                    }
                });


            }
        });
    }
}
