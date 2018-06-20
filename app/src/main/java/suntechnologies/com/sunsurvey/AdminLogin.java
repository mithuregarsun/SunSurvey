package suntechnologies.com.sunsurvey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminLogin extends Activity {
    EditText emailTextId,passwordTextId;
    TextView ForgotTextView;
    Button signBtn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String email,password;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);

        auth = FirebaseAuth.getInstance();
        emailTextId = findViewById(R.id.email_id);
        passwordTextId = findViewById(R.id.password);
        ForgotTextView = findViewById(R.id.forgot_textView);
        signBtn = findViewById(R.id.signBtn);


        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailTextId.getText().toString().trim();
                password = passwordTextId.getText().toString().trim();


                  if(email.length()>0 && password.length()>0)
                  auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(AdminLogin.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {

                           FirebaseUser user = auth.getCurrentUser();
                           if (task.isSuccessful()) {
                               Log.d("sussss", String.valueOf(user.getUid()));
                               FirebaseDatabase database = FirebaseDatabase.getInstance();
                               DatabaseReference myRef = database.getReference("message");

                               myRef.setValue("Hello, World!");


                               Intent intent = new Intent(AdminLogin.this, CreateSurvey.class);
                               startActivity(intent);
                               finish();


                           }
                           else{
                               Toast.makeText( AdminLogin.this, task.getException().toString(),Toast.LENGTH_SHORT).show();
                           }



                       }

                   });



            }
        });
        ForgotTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLogin.this, ForgotPassword.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
    }
    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}
