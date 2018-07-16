package suntechnologies.com.sunsurvey.Models;

public class SurveyName {
public String name;
public String surveyId;
public String response;

  public SurveyName(String surveyId, String name,String response){
       this.surveyId = surveyId;
       this.name = name;
       this.response = response;
  }
}
