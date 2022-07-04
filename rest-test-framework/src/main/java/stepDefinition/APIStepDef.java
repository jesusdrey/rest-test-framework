package stepDefinition;

import actionUtils.APIActions;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.PhotoResponse;
import com.google.gson.Gson;
import io.cucumber.gherkin.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class APIStepDef
{
    private APIActions apiActions;
    private HttpResponse response;
    private HttpEntity getEntity;
    private String url;
    private String apiResponse;
    JsonObject jsonResp;
    private PhotoResponse photos;

    public APIStepDef() {
        this.apiActions = new APIActions();
        this.url = "";
    }

    @Given("^I have URL \"([^\"]*)\" and query params \"([^\"]*)\"$")
    public void iHaveURLAndQueryParams(String url, String queryParams){
        this.url = url + "?" + queryParams;
    }

    @Given("^I send a \"([^\"]*)\" call$")
    public void iMakeAnApiCall(String method) throws IOException {
        if(method.equals("GET")){
            response =  apiActions.makeAPIGetCall(url);
            getEntity = this.response.getEntity();
            apiResponse = EntityUtils.toString(getEntity);
            photos = new Gson().fromJson(apiResponse, PhotoResponse.class);
        }
    }

    @Then("^I expect response code to be: \"([^\"]*)\"$")
    public void iExpectResponseCode(String codeExpected) throws IOException {
        String code = String.valueOf(response.getStatusLine().getStatusCode());
        Assert.assertTrue(code.contains(codeExpected));
    }

    @Then("^I retrieve the \"([^\"]*)\" first values$")
    public void iRetrieveFirstValues(String maxNum){
        int num = Integer.parseInt(maxNum);
        List <String> list = new ArrayList<>();

        for(int i=0;i<num;i++){
            list.add( photos.getPhotos().get(i).getImg_src());
            System.out.println(list);
        }
    }

}
