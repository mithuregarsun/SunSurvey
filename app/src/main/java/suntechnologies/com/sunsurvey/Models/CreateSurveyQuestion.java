package suntechnologies.com.sunsurvey.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class CreateSurveyQuestion implements Serializable {
    public String answer;
    public String question;
    public ArrayList<String> option;

    public CreateSurveyQuestion() {

    }

    public CreateSurveyQuestion( String question,String answer, ArrayList<String> option) {
        this.option = option;
        this.question = question;
        this.answer = answer;
    }

    public ArrayList<String> getOption() {
        return option;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setOption(ArrayList<String> option) {
        this.option = option;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
