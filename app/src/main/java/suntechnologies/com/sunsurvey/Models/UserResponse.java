package suntechnologies.com.sunsurvey.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class UserResponse implements Serializable {

    public String question;
    public String optionA;
    public String optionB;
    public String optionC;
    public String optionD;
    public int userResponseA;
    public int userResponseB;
    public int userResponseC;
    public int userResponseD;
    public int totalResponse;
    public ArrayList<String>optionArrayList;
    public ArrayList<Integer>countArrayList;


    public UserResponse(ArrayList<String>optionArrayList,ArrayList<Integer>countArrayList,String question,String optionA,String optionB,String optionC,String optionD,int userResponseA,int userResponseB,int userResponseC,int userResponseD,int totalResponse){
       this.optionArrayList = optionArrayList;
       this.countArrayList = countArrayList;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.userResponseA = userResponseA;
        this.userResponseB = userResponseB;
        this.userResponseC = userResponseC;
        this.userResponseD = userResponseD;
        this.totalResponse = totalResponse;


    }

}
