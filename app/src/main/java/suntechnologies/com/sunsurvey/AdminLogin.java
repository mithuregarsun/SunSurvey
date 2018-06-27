package suntechnologies.com.sunsurvey;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

import dmax.dialog.SpotsDialog;
import suntechnologies.com.sunsurvey.utility.Helper;

public class AdminLogin extends Activity {
    EditText emailTextId,passwordTextId;
    TextView ForgotTextView;
    Button signBtn;
    String email,password;
    private FirebaseAuth auth;
    RelativeLayout relativeLayout;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);

        auth = FirebaseAuth.getInstance();




        emailTextId = findViewById(R.id.email_id);
        passwordTextId = findViewById(R.id.password);
        ForgotTextView = findViewById(R.id.forgot_textView);
        signBtn = findViewById(R.id.signBtn);
        dialog = new SpotsDialog(AdminLogin.this,"Signing In...");
        emailTextId.setText("mithuregar@gmail.com");
        passwordTextId.setText("123456");
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                email = emailTextId.getText().toString().trim();
                password = passwordTextId.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                  if(email.length()>0 && password.length()>0 && email.matches(emailPattern)){
                      auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(AdminLogin.this, new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {

                              FirebaseUser user = auth.getCurrentUser();
                              if (task.isSuccessful()) {
                                  Helper.hideSoftKeyboard(AdminLogin.this);
                                  dialog.dismiss();
                                  Intent intent = new Intent(AdminLogin.this, CreateSurvey.class);
                                  startActivity(intent);
                                  finish();


                              }
                              else{
                                  dialog.dismiss();
                                  Toast.makeText( AdminLogin.this, task.getException().toString(),Toast.LENGTH_SHORT).show();
                              }



                          }

                      });

                  }else if(password==null ||password.equalsIgnoreCase("")){
                      Helper.showDialog(AdminLogin.this,"Alert","Password is not empty!");
                      dialog.dismiss();
                  }




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
       findViewById(R.id.relativeLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Helper.hideSoftKeyboard(AdminLogin.this);
                return true;
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
