package stepDefinition;

import actionUtils.APIActions;
import actionUtils.UtilActions;
import models.PhotoResponse;
import com.google.gson.Gson;
import io.cucumber.gherkin.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class APIStepDef
{
    private APIActions apiActions;
    private UtilActions utilActions;
    private HttpResponse response;
    private HttpEntity getEntity;
    private String url;
    private String apiResponse;
    JsonObject jsonResp;
    private PhotoResponse photos;
    private List <String> list = new ArrayList<>();
    private List <String> listMars = new ArrayList<>();
    private List <String> listEarth = new ArrayList<>();

    public APIStepDef() {
        this.apiActions = new APIActions();
        this.utilActions = new UtilActions();
        this.url = "";
    }

    @Given("^I have URL \"([^\"]*)\" and query params \"([^\"]*)\"$")
    public void iHaveURLAndQueryParams(String url, String queryParams){
        this.url = utilActions.replaceParametrizedValuesInString(url) + "?" + utilActions.replaceParametrizedValuesInString(queryParams);
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

    @Then("^I retrieve the \"([^\"]*)\" first values for \"([^\"]*)\"$")
    public void iRetrieveFirstValues(String maxNum,String planet){
        int num = Integer.parseInt(maxNum);

        if(planet.equals("Mars")){
            for(int i=0;i<num;i++){
                listMars.add( photos.getPhotos().get(i).getImg_src());
            }
        }
        else if(planet.equals("Earth")){
            for(int i=0;i<num;i++){
                listEarth.add( photos.getPhotos().get(i).getImg_src());
            }
        }

    }

    @Then("^I compare \"([^\"]*)\" to \"([^\"]*)\"$")
    public void compareTwoList(String one, String two) throws IOException, ClassNotFoundException {
        System.out.println("Comparison from "+ one +" against "+two);
        if(one.equals("Mars"))
            Assert.assertEquals(listMars,listEarth);
        else if (one.equals("Earth"))
            Assert.assertEquals(listEarth,listMars);
    }

    @Then ("^I validate if camera is greater than others$")
    public void validateCamera(){
        List<Object> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        int greater = 0;
        String greaterName = "";
        boolean found = false;


        list = utilActions.getJsonPath(apiResponse,"photos.camera.name");
        list.stream().collect(Collectors.groupingBy(s -> s)).forEach((k, v) -> map.put((String) k,v.size()));

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue() > greater) {
                greater = entry.getValue();
                greaterName = entry.getKey();
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if(greater!=entry.getValue()) {
                if((entry.getValue()*10) >greater ){
                    found = true;
                }
            }
        }

        if(!found){
            System.out.println(greaterName + " is greater than 10 times other cameras");
        }
        else{
            System.out.println("each Curiosity camera took on 1000 Mars sol is not greater than 10 times the amount taken by other cameras");
        }
    }

}
