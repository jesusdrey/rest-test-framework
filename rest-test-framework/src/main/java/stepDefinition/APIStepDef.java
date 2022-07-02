package stepDefinition;

import actionUtils.APIActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.http.HttpResponse;
import org.junit.Assert;

import java.io.IOException;

public class APIStepDef
{
    private APIActions apiActions;
    private HttpResponse response;

    public APIStepDef() {
        this.apiActions = new APIActions();
    }

    @Given("^I send a GET call to: \"([^\"]*)\"$")
    public void iMakeAnApiCall(String url) throws IOException {
        response =  apiActions.makeAPIGetCall(url);
    }

    @Then("^I expect response code to be: \"([^\"]*)\"$")
    public void iExpectResponseCode(String codeExpected) throws IOException {
        String code = String.valueOf(response.getStatusLine().getStatusCode());
        Assert.assertTrue(code.contains(codeExpected));
    }
}
