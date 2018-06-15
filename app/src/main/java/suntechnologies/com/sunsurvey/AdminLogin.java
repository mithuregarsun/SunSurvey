package suntechnologies.com.sunsurvey;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminLogin extends Activity {
    EditText emailTextId,passwordTextId;
    TextView ForgotTextView;
    Button signBtn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.admin_login);
        emailTextId = findViewById(R.id.email_id);
        passwordTextId = findViewById(R.id.password);
        ForgotTextView = findViewById(R.id.forgot_textView);
        signBtn = findViewById(R.id.signBtn);
        email = emailTextId.getText().toString().trim();
        password = passwordTextId.getText().toString().trim();





        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!email.matches(emailPattern))
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();


                }
                else if(password.length()< 8 &&!isValidPassword(password)){
                    System.out.println("Not Valid password");

                }else{

                    Toast.makeText(getApplicationContext(),"valid emai and password",Toast.LENGTH_SHORT).show();

                }

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
