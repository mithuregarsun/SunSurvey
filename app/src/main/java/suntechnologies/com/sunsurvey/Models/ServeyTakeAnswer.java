package suntechnologies.com.sunsurvey.Models;

import java.util.ArrayList;

public class ServeyTakeAnswer {
    public String answer;
    public String question;
    public ArrayList<String> option;

    public ServeyTakeAnswer() {

    }

    public ServeyTakeAnswer( String question,String answer) {
        this.question = question;
        this.answer = answer;
    }

}
