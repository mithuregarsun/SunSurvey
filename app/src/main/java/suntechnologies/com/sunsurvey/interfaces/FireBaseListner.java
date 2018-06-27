package suntechnologies.com.sunsurvey.interfaces;

import com.google.firebase.database.DataSnapshot;


public interface FireBaseListner {

    void OnError(String message);

    void onResponse(DataSnapshot response) ;
}
