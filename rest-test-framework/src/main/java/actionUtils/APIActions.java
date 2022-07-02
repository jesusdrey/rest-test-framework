package actionUtils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class APIActions {

    public HttpResponse makeAPIGetCall(String url) throws IOException {
        HttpResponse response = null;
        HttpClientBuilder b = HttpClientBuilder.create();
        HttpClient httpClient = b.build();
        HttpGet request = new HttpGet(url);
        response = httpClient.execute(request);

    return response;
    }

}
