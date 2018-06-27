package suntechnologies.com.sunsurvey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class FeedBackUser extends Activity {

    String TAG = "test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

       Button button = findViewById(R.id.thank_you_done);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
               Intent intent = new Intent(FeedBackUser.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
}
