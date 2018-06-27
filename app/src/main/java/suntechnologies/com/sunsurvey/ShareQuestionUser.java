package suntechnologies.com.sunsurvey;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ShareQuestionUser extends AppCompatActivity {


 Button shareQuestion;
 TextView serveyId;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_uestion);

       serveyId =findViewById(R.id.serveyId);
        shareQuestion =findViewById(R.id.share);
        //getting the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setting the title
        toolbar.setTitle("Sun Survey");

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);
        Bundle bundle = getIntent().getExtras();
        final String serveyIdForQuestion = bundle.getString("serveyId");
        serveyId.setText(serveyIdForQuestion);
        shareQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,"Hi you take survey for sun technologies  following serivey Id: "+serveyIdForQuestion);
                startActivity(Intent.createChooser(sharingIntent,serveyIdForQuestion));
            }
        });

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
           /* case R.id.menuAbout:
                Toast.makeText(this, "You clicked about", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuSettings:
                Toast.makeText(this, "You clicked settings", Toast.LENGTH_SHORT).show();
                break;*/

            case R.id.menuLogout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ShareQuestionUser.this, AdminLogin.class);
                startActivity(intent);
                finish();
                break;

        }
        return true;
    }
}
