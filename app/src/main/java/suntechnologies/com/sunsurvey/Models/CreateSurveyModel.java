package suntechnologies.com.sunsurvey.Models;

public class CreateSurveyModel {

    public  String survey_name;
    public  String create_survey_date;

    public CreateSurveyModel(String survey_name, String create_survey_date){
        this.create_survey_date = create_survey_date;
        this.survey_name = survey_name;

    }
}
