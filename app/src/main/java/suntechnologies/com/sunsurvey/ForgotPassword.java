package suntechnologies.com.sunsurvey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import suntechnologies.com.sunsurvey.utility.Helper;

import static android.content.ContentValues.TAG;

public class ForgotPassword  extends Activity {

    String TAG = "test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        findViewById(R.id.relativeLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Helper.hideSoftKeyboard(ForgotPassword.this);
                return true;
            }
        });

        final EditText email_id = findViewById(R.id.email_id);
        Button button = findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email_id.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Helper.hideSoftKeyboard(ForgotPassword.this);
                                    Log.d(TAG, "Email sent.");
                                    Toast.makeText(ForgotPassword.this,"Reset password link is sent your mail id: "+email_id.getText().toString().trim(),Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(ForgotPassword.this, AdminLogin.class);
                                    startActivity(intent);
                                    finish();

                                }
                            }
                        });
            }
        });


    }
}
