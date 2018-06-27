package suntechnologies.com.sunsurvey.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.inputmethod.InputMethodManager;

public class Helper {

 public static  void showDialog(Context context, String title,String message){
     AlertDialog alertDialog = new AlertDialog.Builder(
             context).create();

     // Setting Dialog Title
     alertDialog.setTitle(title);

     // Setting Dialog Message
     alertDialog.setMessage(message);



     // Setting OK Button
     alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int which) {

             dialog.dismiss();
         }
     });

     // Showing Alert Message
     alertDialog.show();
 }
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}
