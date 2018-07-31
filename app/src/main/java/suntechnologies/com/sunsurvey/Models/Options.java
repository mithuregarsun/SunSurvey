package suntechnologies.com.sunsurvey.Models;

import java.io.Serializable;


public class Options implements Serializable {
    public String optionA;
    public String userIdA;
    public String optionB;
    public String userIdB;
    public String optionC;
    public String userIdC;
    public String optionD;
    public String userIdD;

    Options(){

    }
    public  Options(String optionA,String userIdA,String optionB,String userIdB,String optionC,String userIdC,String optionD,String userIdD){
        this.optionA = optionA;
        this.userIdA = userIdA;
        this.optionB = optionB;
        this.userIdB = userIdB;
        this.optionC = optionC;
        this.userIdC = userIdC;
        this.optionD = optionD;
        this.userIdD = userIdD;
    }
}
