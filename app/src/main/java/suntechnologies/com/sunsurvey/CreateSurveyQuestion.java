package suntechnologies.com.sunsurvey;

import java.util.ArrayList;

public class CreateSurveyQuestion {
    public String question;
    public ArrayList<String> option;

    public CreateSurveyQuestion() {

    }

    public CreateSurveyQuestion( ArrayList<String> option) {

        this.option = option;
    }

}

