package suntechnologies.com.sunsurvey;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText sunSurveyId;
    TextView adminLogin;
Button takeSurvey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                Toast.makeText(getApplicationContext(),"Are you ready to take survey ",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
