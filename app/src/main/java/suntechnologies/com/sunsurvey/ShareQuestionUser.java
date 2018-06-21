package suntechnologies.com.sunsurvey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShareQuestionUser extends Activity {


 Button serveyId,shareQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_uestion);

        serveyId =findViewById(R.id.serveyId);
        shareQuestion =findViewById(R.id.share);
        Bundle bundle = getIntent().getExtras();


        final String stuff = bundle.getString("serveyId");
        serveyId.setText(stuff);
        shareQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,"Hi you take survey for sun technologies  following serivey Id: "+stuff);
                startActivity(Intent.createChooser(sharingIntent,stuff));
            }
        });

    }
}
