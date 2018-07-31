package suntechnologies.com.sunsurvey.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class CreateSurveyQuestion implements Serializable {
    public String answer;
    public String question;
    public Options option;
    public String surveyId;
    public String count;

     CreateSurveyQuestion() {

    }

    public CreateSurveyQuestion( String question,String answer, Options option,String surveyId,String count) {
        this.option = option;
        this.question = question;
        this.answer = answer;
        this.surveyId = surveyId;
        this.count = count;
    }

    public Options getOption() {
        return option;
    }

    public void setOption(Options option) {
        this.option = option;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }



    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}

